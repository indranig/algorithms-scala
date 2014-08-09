package org.awong.sorting

import scala.collection.SeqLike
import scala.collection.generic.CanBuildFrom
import scala.math.Ordering

/**
 * @see http://jsuereth.com/scala/2011/06/16/generic-quicksort.html
 */
object Quick {
	def sort[T, Coll](a: Coll)(implicit ev0 : Coll <:< SeqLike[T, Coll], 
								cbf : CanBuildFrom[Coll, T, Coll], 
								n : Ordering[T]) : Coll = {
		import n._
		if (a.length < 2) {
			a
		} else {
			// We pick the first value for the pivot.
			val pivot = a.head
			val (lower : Coll, tmp : Coll) = a.partition(_ < pivot)
			val (upper : Coll, same : Coll) = tmp.partition(_ > pivot)
			val b = cbf()
			b.sizeHint(a.length)
			b ++= sort[T,Coll](lower)
			b ++= same
			b ++= sort[T,Coll](upper)
			b.result
		}
	}
}