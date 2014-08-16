package org.awong.context

import org.awong.AbstractWordSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ContextSpec extends AbstractWordSpec {
	"This" should {
		"read brownian.txt" in {
			ContextData.brownian.size should be (1001)
		}
		"read diffusion.txt" in {
			ContextData.diffusion.size should be (201)
		}
		"read mobydick.txt" in {
			ContextData.mobydick.size should be (22158)
		}
	}
}