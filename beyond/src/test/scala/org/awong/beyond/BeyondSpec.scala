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
	
	"Lamport clock" should {
		"Increment" in {
			var clock = LamportClock()
			clock.value should be (1)
			clock = clock.increment
			clock.value should be (2)
		}
		"Merge" in {
			var thisClock = LamportClock(10)
			val thatClock = LamportClock(9)
			thisClock = thisClock.merge(thatClock)
			thisClock.value should be (11)
			thisClock = thisClock.merge(LamportClock(20))
			thisClock.value should be (21)
		}
	}
	def incrementNode[N](clock: VectorClock[N], node: N, times: Int): VectorClock[N] = {
		var newClock = clock
		(1 to times).foreach { _ =>
			newClock = newClock.increment(node)
		}
		newClock
	}
	"Vector clock" should {
		"Increment" in {
			val nodes = 1::2::Nil
			var clock = VectorClock(nodes)
			clock.value(1) should be (Some(1))
			clock = clock.increment(1)
			clock.value(1) should be (Some(2))
		}
		"Merge" in {
			val nodes = 1::2::Nil
			var thisClock = VectorClock(1::2::Nil)
			
			thisClock = thisClock.increment(1)
			thisClock = incrementNode(thisClock, 2, 3)
			thisClock.value(1) should be (Some(2))
			thisClock.value(2) should be (Some(4))
		
			var thatClock = VectorClock(2::3::Nil)
			thatClock = incrementNode(thatClock, 2, 2)
			thatClock = incrementNode(thatClock, 3, 2)
			thatClock.value(2) should be (Some(3))
			thatClock.value(3) should be (Some(3))

			thisClock = thisClock.merge(thatClock)
			thisClock.value(1) should be (Some(2))
			thisClock.value(2) should be (Some(4))
			thisClock.value(3) should be (Some(3))
			
			thisClock = thisClock.merge(VectorClock(4::Nil))
			thisClock.value(1) should be (Some(2))
			thisClock.value(2) should be (Some(4))
			thisClock.value(3) should be (Some(3))
			thisClock.value(4) should be (Some(1))
		}
	}
}