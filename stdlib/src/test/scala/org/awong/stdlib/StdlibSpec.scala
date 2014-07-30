package org.awong.stdlib

import org.awong.AbstractFlatSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class StdlibSpec extends AbstractFlatSpec {
	"This" should "be able to read a txt file in its local src/main/resources directory" in {
		val maybeStream = StdIO.resourceAsString(List("numbers.txt"))
		maybeStream match {
			case Some(source) =>
				(source.mkString) should equal("1 2 3 4 5")
			case None =>
				fail
		}
	}
	
	"This" should "be able to read a stdlib/src/main/resources/application.conf" in {
		val conf = StdIO.config
		conf.getString("application.defaultEncoding") should equal ("UTF-8")
	}
	
	"This" should "be able to read a txt file in its local src/main/resources directory as an observable stream of lines" in {
		val maybeObservable = StdIO.resourceAsObservable(List("numbers.txt"))
		maybeObservable match {
			case Some(observable) => {
				var buffer = scala.collection.mutable.Buffer[String]()
				observable.subscribe(
					it => {
						logger.debug("next = {}", it.toString)
						buffer += it
					},
					th => {
						logger.error(th.toString)
					},
					() => {
						logger.info("done")
					}
				)
				buffer.size should be (1)
				buffer.toList should be ("1 2 3 4 5"::Nil)
			}
			case None =>
				fail
		}
	}
	
}