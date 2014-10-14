package org.awong.sorting.pq

/**
 * For example of purely functional PQ, see  http://amitdev.github.io/coding/2014/03/06/Priority-Queue/
 */
trait PriorityQueue[K <: Comparable[K]] {
	def insert(v: K): Unit
	def enqueue(v: K): Unit
	def dequeue(): K
	def isEmpty: Boolean
	def size: Int
}

class MaxPQ[K <: Comparable[K]] extends PriorityQueue[K] {
	// insert a key into the PQ
	def insert(v: K): Unit = {
		???
	}
	// return the largest key
	def max: K = {
		???
	}
	// return and remove the largest key
	def delMax(): K = {
		???
	}
	def dequeue(): K = delMax()
	def enqueue(v: K): Unit = insert(v)
	
	def isEmpty: Boolean = {
		???
	}
	def size: Int = {
		???
	}
}

class MinPQ[K <% Comparable[K]] {
	// insert a key into the PQ
	def insert(v: K): Unit = {
		???
	}
	// return the smallest key
	def min: K = {
		???
	}
	// return and remove the smallest key
	def delMin(): K = {
		???
	}
	def dequeue(): K = delMin()
	def enqueue(v: K): Unit = insert(v)
	
	def isEmpty: Boolean = {
		???
	}
	def size: Int = {
		???
	}
}

trait IndexPriorityQueue[K <: Comparable[K]] {
	def insert(i: Int, key: K): Unit
	def changeKey(k: Int, key: K): Unit
	def contains(i: Int): Boolean
	def delete(i: Int): Unit
	def isEmpty: Boolean
	def size: Int
	def keyOf(i: Int): K
}

trait MaxPQLike[K <: Comparable[K]] {
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

class IndexMaxPQ[K <: Comparable[K]] extends IndexPriorityQueue[K] with MaxPQLike[K] {
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

trait MinPQLike[K <: Comparable[K]] {
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

class IndexMinPQ[K <: Comparable[K]] extends IndexPriorityQueue[K] with MinPQLike[K] {
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

