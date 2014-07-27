package org.awong

import java.io.{File => JFile}
import scala.io.Source
import java.io.{File => JFile}

object StdIO extends Logging {
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
	
	private def directoriesOfOtherModules(parentDir: JFile, currentModule: JFile): IndexedSeq[JFile] = {
		import collection.immutable.ListSet
		val uris = ListSet("stdlib", "fundamentals", "sorting", "searching", "graphs", "strings", "context", "beyond")
		val files = uris map { new JFile(parentDir, _) }
		val otherModules = files - currentModule
		otherModules.toIndexedSeq
	}
	
	private def getResourceFile(moduleDir: JFile, resourcePath: List[String]): JFile = {
		subFile(moduleDir, ("src" ::"main" :: "resources" :: resourcePath): _*)
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
		val resourceFile = getResourceFile(moduleDir, resourcePath)
		logger.debug("Accessing resourceFile {}", resourceFile.toString)
		if (resourceFile.exists) {
			Some(resourceFile)
		} else {
			val otherModules = directoriesOfOtherModules(parentDir, moduleDir)
			val otherResources = for (m <- otherModules) yield getResourceFile(m, resourcePath)
			otherResources.find(_.exists) match {
				case Some(r) => Some(r)
				case None => None
			}
		}
	}

	val defaultEncoding = "UTF-8"
	
	def resourceAsStreamFromSrc(resourcePath: List[String]): Option[Source] = {
		resourceAsFileFromSrc(resourcePath) match {
			case Some(file) =>
				Some(Source.fromFile(file, "UTF-8"))
			case None =>
				None
		}
	}
	
	def resourceAsStringStreamFromSrc(resourcePath: List[String]): Option[Stream[String]] = {
		for (src <- resourceAsStreamFromSrc(resourcePath)) yield src.getLines.toStream
	}
	
	def resourceAsString(resourcePath: List[String]): Option[String] = {
		resourceAsStreamFromSrc(resourcePath) match {
			case Some(source) =>
				Some(source.mkString)
			case None =>
				None
		}
	}
}