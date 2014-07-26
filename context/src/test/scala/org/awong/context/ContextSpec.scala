package org.awong.context

import org.awong.AbstractFlatSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ContextSpec extends AbstractFlatSpec {
	"This" should "pass a trivial test" in {
		1 should equal (1 + 0)
	}
	"This" should "fail a trivial test" in {
		fail
	}
}