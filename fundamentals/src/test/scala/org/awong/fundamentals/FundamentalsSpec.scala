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
	
	"BinarySearch" should {
		"find something" in {
			val key = 499
			val maybeResult = BinarySearch.search(key, 1 to 1000)
			maybeResult should be (Some(key - 1))
		}
		"find nothing" in {
			val key = 10001
			val maybeResult = BinarySearch.search(key, 1 to 1000)
			maybeResult should be (None)
		}
	}
}