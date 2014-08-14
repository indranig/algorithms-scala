package org.awong.beyond

import org.awong.AbstractFlatSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class BeyondSpec extends AbstractFlatSpec {
	"This" should "pass a trivial test" in {
		1 should equal (1 + 0)
	}
	"This" should "fail a trivial test" in {
		fail
	}
	
	"This" should "load 1423 pairs" in {
		BeyondData.rs1423.nPairs should be (1423)
	}
}