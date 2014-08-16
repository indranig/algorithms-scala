package org.awong.strings

import org.awong.AbstractWordSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class StringsSpec extends AbstractWordSpec {
	"This" should {
		"pass a trivial test" in {
			(1+0) should be (1)
		}
	}
}