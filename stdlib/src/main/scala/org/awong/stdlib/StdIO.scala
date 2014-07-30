package org.awong.stdlib

import java.io.{File => JFile}
import scala.io.Source
import rx.lang.scala.Observable
import com.typesafe.config.ConfigFactory

object StdIO extends org.awong.Logging {
	lazy val config = ConfigFactory.load
	

	
	/**
	 * Get a child of a file. For example,
	 * 
	 *   subFile(homeDir, "b", "c")
	 * 
	 * corresponds to ~/b/c
	 */
	private def subFile(file: JFile, children: String*): JFile = {
		children.foldLeft(file)((file, child) => new JFile(file, child))
	}
	
	private def directoriesOfOtherModules(parentDir: JFile, currentModule: JFile): List[JFile] = {
		import collection.immutable.ListSet
		val uris = ListSet("stdlib", "fundamentals", "sorting", "searching", "graphs", "strings", "context", "beyond")
		val files = uris map { new JFile(parentDir, _) }
		val otherModules = files - currentModule
		otherModules.toList
	}
	
	private def getResourceFiles(moduleDir: JFile, resourcePath: List[String]): List[JFile] = {
		def getResourceFileImpl(moduleDir: JFile, dirs: List[String], resourcePath: List[String]): JFile = {
			subFile(moduleDir, (dirs ::: resourcePath): _*)
		}
		val resources = List("src"::"main"::"resources"::Nil, "src"::"test"::"resources"::Nil)
		
		val resourceFiles = for (r <- resources) yield getResourceFileImpl(moduleDir, r, resourcePath)
		resourceFiles
	}
	
	
	/**
	 * Get a resource from the `src/main/resources` directory. Eclipse does not copy
	 * resources to the output directory, then the class loader cannot find them.
	 */
	private def resourceAsFileFromSrc(resourcePath: List[String]): Option[JFile] = {
		val uri = getClass.getResource(".").toURI
		logger.debug("Accessing uri {}.", uri)
		val classesDir = new JFile(uri)
		val moduleDir = classesDir.getParentFile.getParentFile.getParentFile.getParentFile.getParentFile
		logger.debug("Accessing moduleDir {}", moduleDir.toString)
		val parentDir = moduleDir.getParentFile
		val resourceFiles = getResourceFiles(moduleDir, resourcePath)
		resourceFiles.find( _.exists ) match {
			case Some(f) => Some(f)
			case None =>
				val otherModules = directoriesOfOtherModules(parentDir, moduleDir)
				val listOfOtherResouceFiles = for (m <- otherModules) yield getResourceFiles(m, resourcePath)
				val allFiles = for (resourceFiles <- listOfOtherResouceFiles; file <- resourceFiles if file.exists) yield file
				allFiles.headOption
		}
	}

	def resourceAsStreamFromSrc(resourcePath: List[String]): Option[Source] = {
		resourceAsFileFromSrc(resourcePath) match {
			case Some(file) =>
				Some(Source.fromFile(file, Defaults.defaultEncoding))
			case None =>
				None
		}
	}
	
	def readInts(resourcePath: List[String]): Stream[Int] = {
		readStrings(resourcePath) map { _.toInt }
	}
	
	def readDoubles(resourcePath: List[String]): Stream[Double] = {
		readStrings(resourcePath) map { _.toDouble }
	}
	
	def readStrings(resourcePath: List[String]): Stream[String] = {
		val maybeStream = for (src <- resourceAsStreamFromSrc(resourcePath)) yield src.getLines.toStream
		maybeStream match {
			case Some(stream) => stream
			case None => Stream[String]()
		}
	}
	
	def resourceAsString(resourcePath: List[String]): Option[String] = {
		resourceAsStreamFromSrc(resourcePath) match {
			case Some(source) =>
				Some(source.mkString)
			case None =>
				None
		}
	}
	
	def resourceAsObservable(resourcePath: List[String]): Option[Observable[String]] = {
		import org.apache.commons.io.FileUtils
		import collection.JavaConverters._
		resourceAsFileFromSrc(resourcePath) match {
			case Some(file) =>
				val jlineIterator = FileUtils.lineIterator(file, Defaults.defaultEncoding)
				try {
					val iterator: Iterator[String] = jlineIterator.asScala
					Some(toObservable(iterator))
				} finally {
					jlineIterator.close
				}
			case None =>
				None
		}
	}
	
	def toObservable[T](iterator: Iterator[T]): Observable[T] = {
		Observable(iterator.toStream: _*)
	}
	def toObservable[T](iterable: Iterable[T]): Observable[T] = {
		Observable(iterable.toStream: _*)
	}
}