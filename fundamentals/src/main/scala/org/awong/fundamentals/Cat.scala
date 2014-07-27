package org.awong.fundamentals

/*************************************************************************
 *  Compilation:  javac Cat.java
 *  Execution:    Cat.concat(input0.txt input1.txt) // output.txt
 *  Dependencies: In.java Out.java
 *
 *  Reads in text files specified as the first command-line 
 *  parameters, concatenates them, and writes the result to
 *  filename specified as the last command line parameter.
 *
 *  % more in1.txt
 *  This is
 *
 *  % more in2.txt 
 *  a tiny
 *  test.
 * 
 *  % java Cat in1.txt in2.txt out.txt
 *
 *  % more out.txt
 *  This is
 *  a tiny
 *  test.
 *
 *************************************************************************/

import java.io.File

object Cat {
	def concat(files: File*): File = {
		concat(files.toBuffer)
	}
	def concat(files: Iterable[File]): File = ???
}