package org.awong.sorting

object Merge {
	def sort[T <: Ordered[T]](xs:Seq[T]): Seq[T] = {
		def merge(xs:Seq[T], ys:Seq[T]): Seq[T] = {
			(xs,ys) match {
				case (_,_) if xs.isEmpty => ys
				case (_,_) if ys.isEmpty => xs
				case (_,_) => {
					val x = xs.head
					val xs1 = xs.tail
					val y = ys.head
					val ys1 = ys.tail
					if (x < y) { 
						x +: x +: merge(xs1, ys)  
					} else {
						y +: y +: merge(xs, ys1)
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
}