package org.awong.beyond

import org.awong.AbstractWordSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class BeyondSpec extends AbstractWordSpec {
	"This" should {
		"load 1423 pairs" in {
			BeyondData.rs1423.nPairs should be (1423)
		}
	}
}