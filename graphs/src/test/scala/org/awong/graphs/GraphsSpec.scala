package org.awong.graphs

import org.awong.AbstractWordSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class GraphsSpec extends AbstractWordSpec {
	"This" should {
		"count mediumG.txt" in {
			GraphsData.mediumG.size should be (1274)
		}
	}
}