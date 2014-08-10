package org.awong.sorting


object Selection {
	def sort(xs: Array[Int]): Array[Int] = {
		def sortImpl(array: Array[Int]): Unit = {
			for (i <- 0 until array.length) {
				var min = i
				for (j <- i until array.length) {
					if (array(j) < array(min))
						min = j
				}
				swap(array, i, min)
			}
		}
		var a = xs
		sort(a)
		a
	}
}