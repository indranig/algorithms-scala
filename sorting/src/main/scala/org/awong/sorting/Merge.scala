package org.awong.sorting

object Merge {
	def sort[A](xs:Seq[A])(implicit ordering: Ordering[A]): Seq[A] = {
		import ordering._
		import scala.collection.mutable.Buffer
		
		def merge(left:Buffer[A], right:Buffer[A]): Buffer[A] = {
			if (left.isEmpty) {
				right
			} else if (right.isEmpty) {
				left
			} else {
				val x = left.head
				val xs1 = left.tail
				val y = right.head
				val ys1 = right.tail
				if (x <= y) { 
					x +: merge(xs1, right)
				} else {
					y +: merge(left, ys1)
				}
			}
		}
		def sortImpl(input: Buffer[A]): Buffer[A] = {
			if (input.isEmpty || input.size <= 1) {
				input
			} else {
				val n = input.length / 2
				val (left, right) = input.splitAt(n)
				merge(sortImpl(left), sortImpl(right))
			}
		}
		val result = sortImpl(xs.toBuffer)
		result.toSeq
	}

}