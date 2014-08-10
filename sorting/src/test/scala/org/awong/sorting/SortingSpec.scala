package org.awong.sorting

import org.awong.AbstractWordSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class SortingSpec extends AbstractWordSpec {
	var range: Seq[Int] = _
	var smallArray: Array[Int]  = _
	
	before {
		range = 1 to 10
		var xs = util.Random.shuffle(range.toList)
		smallArray = xs.toArray
	}
	"Heap sort" when {
		"sorting a small array" should {
			"should sort correctly" in {
				val result = Heap.sort(smallArray)
				result should === ((1 to 10).toArray)
			}
		}
	}
	
	"Insertion sort" when {
		"sorting a small array" should {
			"should sort correctly" in {
				val result = Insertion.sort(smallArray)
				result should === ((1 to 10).toArray)
			}
		}
	}
	"Selection sort" when {
		"sorting a small array" should {
			"should sort correctly" in {
				val result = Selection.sort(smallArray)
				result should === ((1 to 10).toArray)
			}
		}
	}
	"Shell sort" when {
		"sorting a small array" should {
			"should sort correctly" in {
				val result = Shell.sort(smallArray)
				result should === ((1 to 10).toArray)
			}
		}
	}
	"Merge sort" when {
		"sorting a small array" should {
			"should sort correctly" in {
				val result = Merge.mergeSort(smallArray.toList)
				result should === ((1 to 10).toArray)
			}
		}
	}
	"Quick sort" when {
		"sorting a small array" should {
			"should sort correctly" in {
				val result = Quick.sort(smallArray.toSeq)
				result should === ((1 to 10).toArray)
			  
			}
		}
	}
}