package org.awong.sorting

object Selection {
	def sort[T <: Ordered[T]](xs: Array[T]): Array[T] = {
		def swap(a: Array[T], i: Int, j: Int): Array[T] = {
			val swap = a(i)
			a(i) = a(j)
			a(j) = swap
			a
		}

		var a = xs
		// advance the position through the entire array
		// (could do j < a.length because single element is also min element)
		for (j <- 1 until a.length) {
			// find the min element in the unsorted a[j .. n-1]
			// assume the min is the first element
			var iMin = j
			// test against elements after j to find the smallest
			var i = (j + 1)
			while (i < a.length) {
				// if this element is less, then it is the new minimum
				if (a(i) < a(iMin)) {
					// found new minimum; remember its index
					iMin = i
				}
				i = i + 1
			}
			if (iMin != j) {
				a = swap(a, j, iMin)
			}
		}
		a
	}

}