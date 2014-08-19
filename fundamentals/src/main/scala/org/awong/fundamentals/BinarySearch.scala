package org.awong.fundamentals

object BinarySearch {
	def search[A <% Ordered[A]](target: A, sortedList: Seq[A]): Option[Int] = {
		def bsf(list: Seq[A], target: A, start: Int, end: Int): Option[Int] = {
			if (start > end) {
				None
			} else {
				val mid = start + (end - start + 1)/2
				list match {
					case (arr: Seq[A]) if (arr(mid) == target) =>
						Some(mid)
					case (arr: Seq[A]) if (arr(mid) > target) => 
						bsf(list, target, start, mid - 1)
					case (arr: Seq[A]) if (arr(mid) < target) =>
						bsf(list, target, mid + 1, end)
				}
			}
		}
		bsf(sortedList, target, 0, sortedList.length-1)
	}
	
	implicit class SearchableArray[T <: AnyRef](a: Array[T]) {
		def binarySearch(key: T): Option[Int] = {
			val result = java.util.Arrays.binarySearch(a.asInstanceOf[Array[AnyRef]], key)
			if (result < 0) {
				None
			} else {
				Some(result)
			}
		}
	}
	implicit class SearchableSeq[T](a: Seq[T])(implicit ordering: Ordering[T]) {
		import scala.collection.JavaConversions._
		import java.util.{Collections, List => JList}
		val list: JList[T] = a.toList
		def binarySearch(key: T): Option[Int] = {
			val result  = Collections.binarySearch(list, key, ordering)
			if (result < 0) {
				None
			} else {
				Some(result)
			}
		}
	}
	
}