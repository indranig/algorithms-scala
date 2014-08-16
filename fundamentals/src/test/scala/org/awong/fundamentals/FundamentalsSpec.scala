package org.awong.fundamentals

import org.awong.AbstractWordSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class FundamentalsSpec extends AbstractWordSpec {
	"This" should {
		"be able to read a txt file in its stdlib src/main/resources directory" in {
			FundamentalsData.get1Kints should have size 1000
		}
	}
	
	
}