package org.awong.sorting.pq

abstract class IndexPriorityQueue[K](implicit val ord: Ordering[K]) {
	import ord._
	
	def insert(i: Int, key: K): Unit
	def changeKey(k: Int, key: K): Unit
	def contains(i: Int): Boolean
	def delete(i: Int): Unit
	def isEmpty: Boolean
	def size: Int
	def keyOf(i: Int): K
}

trait IndexedMaxPQ[K <: Ordered[K]] {
	/** 
	 * return the maximum key
	 */
	def maxKey: K = {
		???
	}
	/** 
	 * return the maximum key's index
	 */
	def maxIndex: Int = {
		???
	}
	/** 
	 * remove the maximum key and return its index
	 */
	def delMax(): Int = {
		???
	}
}

/**
 * @see http://algs4.cs.princeton.edu/24pq/MinPQ.java.html
 */
class IndexMaxPQ[K <: Ordered[K]] extends IndexPriorityQueue[K] with IndexedMaxPQ[K] {
	def insert(i: Int, key: K): Unit = {
		???
	}
	def changeKey(k: Int, key: K): Unit = {
		???
	}
	def contains(i: Int): Boolean = {
		???
	}
	def delete(i: Int): Unit = {
		???
	}
	def isEmpty: Boolean = {
		???
	}
	def size: Int = {
		???
	}
	def keyOf(i: Int): K = {
		???
	}
}

trait IndexedMinPQ[K <: Ordered[K]] {
	/** 
	 * return the minimum key
	 */
	def minKey: K = {
		???
	}
	/** 
	 * return the minimum key's index
	 */
	def minIndex: Int = {
		???
	}
	/** 
	 * remove the minimum key and return its index
	 */
	def delMin(): Int = {
		???
	}
}

/**
 * @see http://algs4.cs.princeton.edu/24pq/IndexMinPQ.java.html
 */
class IndexMinPQ[K <: Ordered[K]] extends IndexPriorityQueue[K] with IndexedMinPQ[K] {
	def insert(i: Int, key: K): Unit = {
		???
	}
	def changeKey(k: Int, key: K): Unit = {
		???
	}
	def contains(i: Int): Boolean = {
		???
	}
	def delete(i: Int): Unit = {
		???
	}
	def isEmpty: Boolean = {
		???
	}
	def size: Int = {
		???
	}
	def keyOf(i: Int): K = {
		???
	}
}
