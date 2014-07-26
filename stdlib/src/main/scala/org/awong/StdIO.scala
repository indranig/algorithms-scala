package org.awong

import java.io.{File => JFile}
import scala.io.Source
import java.io.{File => JFile}

object StdIO {
	/**
	 * Get a child of a file. For example,
	 * 
	 *   subFile(homeDir, "b", "c")
	 * 
	 * corresponds to ~/b/c
	 */
	private def subFile(file: JFile, children: String*) = {
		children.foldLeft(file)((file, child) => new JFile(file, child))
	}
	
	/**
	 * Get a resource from the `src/main/resources` directory. Eclipse does not copy
	 * resources to the output directory, then the class loader cannot find them.
	 */
	private def resourceAsFileFromSrc(resourcePath: List[String]): Option[JFile] = {
		val classesDir = new JFile(getClass.getResource(".").toURI)
		val projectDir = classesDir.getParentFile.getParentFile.getParentFile.getParentFile
		val resourceFile = subFile(projectDir, ("src" :: "src" ::"main" :: "main" ::"resources" :: "resources" :: resourcePath): _*)
		if (resourceFile.exists) {
			Some(resourceFile)
		} else {
			None
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