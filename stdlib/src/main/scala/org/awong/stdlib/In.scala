package org.awong.stdlib

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;
import java.util.Scanner;

import scala.util.{Try, Success, Failure}

object In extends org.awong.Logging {

	def inputStream2scanner(is: InputStream): Try[java.util.Scanner] = {
		try {
			val scanner =  new Scanner(new BufferedInputStream(is), Defaults.defaultEncoding)
			scanner.useLocale(Defaults.defaultLocale)
			Success(scanner)
		} catch {
			case th: Throwable =>
				logger.error("Cannot open input stream")
				Failure(th)
		}
	}
	
	def socket2scanner(socket: Socket): Try[java.util.Scanner] = {
		try {
			val is = socket.getInputStream()
			inputStream2scanner(is)
		} catch {
			case th: Throwable =>
				logger.error("Cannot open socket")
				Failure(th)
		}
	}
	def url2scanner(url: URL): Try[java.util.Scanner] = {
		try {
			val site = url.openConnection()
			val is = site.getInputStream()
			inputStream2scanner(is)
		} catch {
			case th: Throwable =>
				logger.error("Cannot open url")
				Failure(th)
		}
	}
	
	def file2scanner(file: File): Try[java.util.Scanner] = {
		try {
			val scanner = new Scanner(file, Defaults.defaultEncoding)
			scanner.useLocale(Defaults.defaultLocale)
			Success(scanner)
		} catch {
			case th: Throwable =>
				logger.error("Cannot open file")
				Failure(th)
		}
	}
	
	def apply(file: File): Option[In] = {
		file2scanner(file) match {
			case Success(scanner) => Some(new In(scanner))
			case Failure(_) => None
		}
	}
	
	
	/**
	 * Read strings from a file
	 */
	def readStrings(file: java.io.File): Array[String] = {
		val maybeIn = In(file)
		maybeIn match {
			case Some(in) =>
				in.readAll() match {
					case Some(str) =>
						val fields = str.trim().split("\\s+")
						fields
					case None =>
						Array[String]()
				}
			case None =>
				Array[String]()
		}
	}

	/**
	 * Read ints from file
	 */
	def readInts(file: java.io.File): Array[Int] = {
		readStrings(file) map { f =>
			Integer.parseInt(f)
		}
	}

	/**
	 * Read doubles from file
	 */
	def readDoubles(file: java.io.File): Array[Double] = {
		readStrings(file) map { f =>
			java.lang.Double.parseDouble(f)
		}
	}
	
	private def string2boolean(s: String): Boolean = {
		s.toLowerCase() match {
			case "true" => true
			case "false" => false
			case "1" => true
			case "0" => false
			case _  =>
				throw new java.util.InputMismatchException()
		}
	}

}

class In(val scanner: java.util.Scanner) {
	require(scanner != null)
	/**
	 * Does the input stream exist?
	 */
	def exists(): Boolean =  {
		return scanner != null
	}

	/**
	 * Is the input stream empty?
	 */
	def isEmpty(): Boolean = {
		return !scanner.hasNext()
	}

	/**
	 * Does the input stream have a next line?
	 */
	def hasNextLine(): Boolean = {
		scanner.hasNextLine();
	}

	/**
	 * Read and return the next line.
	 */
	def readLine(): Option[String] = {
		try {
			Some(scanner.nextLine())
		} catch {
			case th:Throwable =>
				None
		}
	}

	/**
	 * Read and return the next character.
	 */
	def readChar(): Char = {
		// (?s) for DOTALL mode so . matches any character, including a line termination character
		// 1 says look only one character ahead
		// consider precompiling the pattern
		val s = scanner.findWithinHorizon("(?s).", 1)
		s.charAt(0)
	}

	// return rest of input as string
	/**
	 * Read and return the remainder of the input as a string.
	 */
	def readAll(): Option[String] = {
		if (!scanner.hasNextLine()) {
			None
		}
		
		// reference: http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
		Some(scanner.useDelimiter("\\A").next())
	}



	/**
	 * Return the next string from the input stream.
	 */
	def readString(): String = {
		scanner.next()
	}

	/**
	 * Return the next int from the input stream.
	 */
	def readInt(): Int = {
		return scanner.nextInt()
	}

	/**
	 * Return the next double from the input stream.
	 */
	def readDouble(): Double = {
		scanner.nextDouble()
	}

	/**
	 * Return the next float from the input stream.
	 */
	def readFloat(): Float = {
		scanner.nextFloat()
	}

	/**
	 * Return the next long from the input stream.
	 */
	def readLong(): Long = {
		scanner.nextLong()
	}

	/**
	 * Return the next byte from the input stream.
	 */
	def readByte(): Byte = {
		scanner.nextByte()
	}


	/**
	 * Return the next boolean from the input stream, allowing "true" or "1"
	 * for true and "false" or "0" for false.
	 */
	def readBoolean(): Boolean = {
		val s = readString();
		In.string2boolean(s)
	}
	

	/**
	 * Close the input stream.
	 */
	def close(): Unit = {
			scanner.close();
	}

}
