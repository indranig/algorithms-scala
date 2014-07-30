package org.awong.fundamentals

import org.awong.stdlib.StdIO
import org.awong.AbstractFlatSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class FundamentalsSpec extends AbstractFlatSpec {
	"This" should "be able to read a txt file in its stdlib src/main/resources directory" in {
		val maybeStream = StdIO.resourceAsString(List("numbers.txt"))
		maybeStream match {
			case Some(source) =>
				(source.mkString) should equal("1 2 3 4 5")
			case None =>
				fail
		}
	}
	
	
	"This" should "be able to read a txt file in its local src/main/resources directory" in {
		val maybeStream = StdIO.resourceAsString(List("tobe.txt"))
		maybeStream match {
			case Some(source) =>
				(source.mkString) should equal("to be or not to - be - - that - - - is")
			case None =>
				fail
		}
	}
}