package org.awong.sorting.pq

trait IndexPriorityQueue[K <: Ordered[K]] {
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
