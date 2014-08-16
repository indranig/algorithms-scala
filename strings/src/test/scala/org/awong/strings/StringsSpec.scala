package org.awong.strings

import org.awong.AbstractWordSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class StringsSpec extends AbstractWordSpec {
	"This" should {
		"pass a trivial test" in {
			StringsData.medTale match {
				case None => fail
				case Some(str) =>
					str.split("\\n").size should be (103)
			}
		}
	}
}