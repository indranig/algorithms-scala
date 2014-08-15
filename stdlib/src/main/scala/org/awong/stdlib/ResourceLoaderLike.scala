package org.awong.stdlib

import java.io.{File => JFile}
import rx.lang.scala.Observable
import scala.util.{Try, Success, Failure}

/**
 * A trait to load resources. By convention, mix this into a class
 * which is either in:
 * <ul>
 * <li>src/main/scala/org.awong.{modulename}</li>
 * <li>src/test/scala/org.awong.{modulename}</li>
 * </ul>
 */
trait ResourceLoaderLike { this: org.awong.Logging =>

	
	/**
	 * Get a child of a file. For example,
	 * 
	 *   subFile(homeDir, "b"::"c"::Nil)
	 * 
	 * corresponds to ~/b/c
	 */
	private def subFile(file: JFile, children: Seq[String]): JFile = {
		children.foldLeft(file)((file, child) => new JFile(file, child))
	}
	private def moduleDir(): JFile = {
		val uri = getClass.getResource(".").toURI
		logger.debug("Accessing uri {}", uri)
		val classesDir = new JFile(uri)
		// UGH we have to call parent file 6 times.
		val moduleDir = classesDir.getParentFile.getParentFile.getParentFile.getParentFile.getParentFile.getParentFile
		logger.debug("Accessing moduleDir {}", moduleDir.toString)
		moduleDir
	}
	
	def resourceAsFile(filename: String): Option[JFile] = {
		val module = moduleDir()
		var jfile = subFile(module, "src"::"test"::"resources"::filename::Nil)
		if (jfile.exists() && jfile.canRead()) {
			Some(jfile)
		} else {
			jfile = subFile(module, "src"::"main"::"resources"::filename::Nil)
			if (jfile.exists() && jfile.canRead()) {
				Some(jfile)
			} else {
				None
			}
		}
	}
	
	def resourceAsSource(filename: String): Option[io.Source] = {
		for (file <- resourceAsFile(filename)) yield io.Source.fromFile(file, Defaults.defaultEncoding)
	}
	
	private def readFile[A](file: JFile)(handler: Iterator[String] => A): Try[A] = {
		import org.apache.commons.io.FileUtils
		import collection.JavaConverters._

		val jlineIterator = FileUtils.lineIterator(file, Defaults.defaultEncoding)
		try {
			val iterator: Iterator[String] = jlineIterator.asScala
			val a = handler(iterator)
			Success(a)
		} catch {
			case th: Throwable => Failure(th)
		} finally {
			jlineIterator.close
		}
	}
	
	private def readSource[A](source: io.Source)(handler: io.Source => A): Try[A] = {
		try {
			val a = handler(source)
			Success(a)
		} catch {
			case th: Throwable => Failure(th)
		} finally {
			source.close()
		}
	}
	
	def resourceAsString(filename: String) : Option[String] = {
		val tryString = resourceAsSource(filename) map { source =>
			readSource(source)(_.mkString)
		} getOrElse {
			Failure(new IllegalArgumentException)
		}
		tryString.toOption
	}
	
	def resourceAsStringStream(filename: String) : Stream[String] = {
		resourceAsFile(filename) match {
			case Some(file) =>
				val tryStream = readFile(file) { iter =>
					iter.toStream
				}
				tryStream.getOrElse(Seq[String]().toStream)
			case None =>
				Seq[String]().toStream
		}
	}
	
	private def toObservable[T](iterator: Iterator[T]): Observable[T] = {
		Observable(iterator.toStream: _*)
	}
	private def toObservable[T](iterable: Iterable[T]): Observable[T] = {
		Observable(iterable.toStream: _*)
	}
	
	def resourceAsObservable(filename: String) : Try[Observable[String]] = {
		val aMaybe = for {source <- resourceAsSource(filename)} yield { readSource(source) { _.getLines } }
		val tryIterator = aMaybe.getOrElse(Failure(new IllegalArgumentException))
		tryIterator map { toObservable(_) }
	}
	
	def resourceAsIntStream(filename: String): Stream[Int] = {
		resourceAsStringStream(filename) map( _.toInt )
	}
	def resourceAsDoubleStream(filename: String): Stream[Double] = {
		resourceAsStringStream(filename) map( _.toDouble )
	}
}