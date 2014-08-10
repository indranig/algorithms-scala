package org.awong.sorting

object Merge {
	def sort[T](xs:Seq[T])(implicit ordering: Ordering[T]): Seq[T] = {
		import ordering._
		def merge(xs:Seq[T], ys:Seq[T]): Seq[T] = {
			(xs,ys) match {
				case (_,_) if xs.isEmpty => ys
				case (_,_) if ys.isEmpty => xs
				case (_,_) => {
					val x = xs.head
					val xs1 = xs.tail
					val y = ys.head
					val ys1 = ys.tail
					if (x <= y) { 
						x +: merge(xs1, ys)
					} else {
						y +: merge(xs, ys1)
					}
				}
			}
		}
		if (xs.isEmpty) {
			xs
		} else {
			val n = xs.length / 2
			val (ys,zs) = xs.splitAt(n)
			merge(sort(ys), sort(zs))
		}
	}
	
	
	def mergeSort(input: List[Int]) = {
		def merge(left: List[Int], right: List[Int]): Stream[Int] = (left, right) match {
			case (x :: xs, y :: ys) if x <= y =>
				x #:: merge(xs, right)
			case (x :: xs, y :: ys) =>
				y #:: merge(left, ys)
			case _ =>
				if (left.isEmpty)
					right.toStream
				else
					left.toStream
		}
		def sort(input: List[Int], length: Int): List[Int] = input match {
			case Nil | List(_) => input
			case _ =>
				val middle = length / 2
				val (left, right) = input splitAt middle
				merge(sort(left, middle), sort(right, middle + length % 2)).toList
		}
		sort(input, input.length)
	}	
}