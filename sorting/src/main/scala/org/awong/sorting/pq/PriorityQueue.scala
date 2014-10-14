package org.awong.sorting.pq

/**
 * For example of purely functional PQ, see  http://amitdev.github.io/coding/2014/03/06/Priority-Queue/
 */
trait PriorityQueue[K <: Ordered[K]] {
	def insert(v: K): Unit
	def enqueue(v: K): Unit
	def dequeue(): K
	def isEmpty: Boolean
	def size: Int
}

class MaxPQ[K <: Ordered[K]] extends PriorityQueue[K] {
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

class MinPQ[K <: Ordered[K]] {
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


