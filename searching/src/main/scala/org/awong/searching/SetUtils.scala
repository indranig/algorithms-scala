package org.awong.searching

object SetUtils {
	def whiteList[A](set: Set[A])(target: Iterable[A]): Iterable[A]= {
		for (e <- target if set.contains(e)) yield e
	}
	def blackList[A](set: Set[A])(target: Iterable[A]): Iterable[A]= {
		for (e <- target if !set.contains(e)) yield e
	}
	
	def deDup[A](target: Iterable[A]): scala.collection.Set[A] = {
		target.toSet
	}
	
	object LookupCSV
	object LookupIndex
	object FileIndex
	object SparseVector
}