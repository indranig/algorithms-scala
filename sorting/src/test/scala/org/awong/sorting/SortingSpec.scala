package org.awong.sorting

import org.awong.AbstractWordSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class SortingSpec extends AbstractWordSpec {
	var range: Seq[Int] = _
	var smallArray: Array[Int]  = _
	
	before {
		range = 1 to 100
		var xs = util.Random.shuffle(range.toList)
		smallArray = xs.toArray
	}
	"Heap sort" when {
		"sorting a small array" should {
			"should sort correctly" in {
				val result = Heap.sort(smallArray)
				result should === (range.toArray)
			}
		}
	}
	
	"Insertion sort" when {
		"sorting a small array" should {
			"should sort correctly" in {
				val result = Insertion.sort(smallArray)
				result should === (range.toArray)
			}
		}
	}
	"Selection sort" when {
		"sorting a small array" should {
			"should sort correctly" in {
				val result = Selection.sort(smallArray)
				result should === (range.toArray)
			}
		}
	}
	"Shell sort" when {
		"sorting a small array" should {
			"should sort correctly" in {
				val result = Shell.sort(smallArray)
				result should === (range.toArray)
			}
		}
	}
	"Merge sort" when {
		"sorting a small array" should {
			"should sort correctly" in {
				val result = Merge.sort(smallArray.toList)
				result should === (range.toArray)
			}
		}
	}
	"Quick sort" when {
		"sorting a small array" should {
			"should sort correctly" in {
				val result = Quick.sort(smallArray.toSeq)
				result should === (range.toArray)
			  
			}
		}
	}
}