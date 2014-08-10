package org.awong.sorting

import scala.collection.mutable.ArrayBuffer

object Insertion {
	def sort[T](xs: Array[T])(implicit ordering: Ordering[T]): Array[T] = {
		import ordering._
		var a = xs
		for (i <- 1 until a.length) {
			// A[ i ] is added in the sorted sequence A[0, .. i-1]
			// save A[i] to make a hole at index iHole
			val item = a(i)
			var iHole = i
			// keep moving the hole to next smaller index until A[iHole - 1] is <= item
			while (iHole > 0 && a(iHole - 1) > item) {
				// move hole to next smaller index
				a(iHole) = a(iHole - 1)
				iHole = iHole - 1
			}
			// put item in the hole
			a(iHole) = item
		}
		a
	}
}