package org.awong.stdlib

import org.awong.AbstractFlatSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.awong.StdIO

@RunWith(classOf[JUnitRunner])
class StdlibSpec extends AbstractFlatSpec {
	"StdIO" should "be able to read a txt file in its local src/main/resources directory" in {
		val maybeStream = StdIO.resourceAsString(List("numbers.txt"))
		maybeStream match {
			case Some(source) =>
				(source.mkString) should equal("1 2 3 4 5")
			case None =>
				fail
		}
	}
}