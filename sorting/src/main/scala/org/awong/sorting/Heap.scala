package org.awong.sorting

object Heap {
	def sort[T](xs: Array[T])(implicit ordering: Ordering[T]): Array[T] = {
		import ordering._
		def siftdown(xs: Array[T], i: Int, size: Int): Array[T] = {
			var a = xs
			var l = 2*i+1
			var r = 2*i+2
			var largest = i
			if (l <= size-1 && a(l) > a(i)) {
				largest = l
			}
			if (r <= size-1 && a(r) > a(largest)) {
				largest = r
			}
			if (largest != i) {
				a = swap(a,i,largest)
				siftdown(a,largest,size)
			}
			a
		}
		def heapify(xs: Array[T], size: Int): Array[T] = {
			var a = xs
			var p = (size/2) - 1
			while (p >= 0) {
				a = siftdown(a,p,size)
				p = p - 1
			}
			a
		}
		var a = xs
		var size = a.length
		heapify(a,size)
		var end = size - 1
		while (end > 0) {
			a = swap(a, 0, end)
			siftdown(a, 0, end)
			end = end - 1
		}
		a
	}

}

