package org.awong.sorting.pq

import java.util.NoSuchElementException

/**
 * For example of purely functional PQ, see  http://amitdev.github.io/coding/2014/03/06/Priority-Queue/
 */
abstract class PriorityQueue[K](implicit val ord: Ordering[K]) {
	import ord._
	
	import collection.mutable.ArrayBuffer
	
	var pq = ArrayBuffer[K]()

	def insert(v: K): Unit = {
		pq = pq :+ v
		swim(size)
	}
	
	def enqueue(v: K): Unit
	def dequeue(): K
	
	def isEmpty: Boolean = {
		pq.isEmpty
	}
	def size: Int = {
		pq.size
	}
	
	protected def swim(in: Int): Unit = {
		var k = in
		while (k > 1 && cmp(k/2, k)) {
			exch(k, k/2)
			k = k/2
		}
	}
	
	protected def sink(in: Int): Unit  = {
		var k = in
		while (2*k <= size) {
			var j = 2*k
			if (j < size && cmp(j, j+1)) {
				j = j + 1
			}
			if (!cmp(k,j)) {
				k = size
			} else {
				exch(k,j)
				k = j
			}
		}
	}
	
	protected def cmp(i: Int, j: Int): Boolean
	
	protected def less(i: Int, j: Int): Boolean = {
		pq(i) < pq(j)
	}
	
	protected def greater(i: Int, j: Int): Boolean = {
		pq(i) > pq(j)
	}
	
	protected def exch(i: Int, j: Int): Unit = {
		val swap = pq(i)
		pq(i) = pq(j)
		pq(j) = swap
	}
}

/**
 * @see http://algs4.cs.princeton.edu/24pq/MaxPQ.java.html
 */
class MaxPQ[K <: Ordered[K]] extends PriorityQueue[K] {
	import ord._
	
	protected def cmp(i: Int, j: Int): Boolean = {
		less(i,j)
	}
	
	// return the largest key
	def max: K = {
		if (isEmpty) {
			throw new NoSuchElementException("PQ underflow")
		}
		pq.head
	}
	// return and remove the largest key
	def delMax(): K = {
		if (isEmpty) {
			throw new NoSuchElementException("PQ underflow")
		}
		var n = size
		val maxKey = max
		n = n - 1
		exch(1, n)
		sink(1)
		// to avoid loitering and aid GC
		pq.remove(n + 1)
		maxKey
	}
	def dequeue(): K = delMax()
	def enqueue(v: K): Unit = insert(v)
	
	private def isMaxHeap(k: Int): Boolean = {
		if (k > size) true
		else {
			val left = 2*k
			val right = 2*k + 1
			if (left  <= size && cmp(k,left)) false
			else if (right <= size && cmp(k, right)) false
			else isMaxHeap(left) && isMaxHeap(right)
		}
	}
}

object MaxPQ {

}

/**
 * @see http://algs4.cs.princeton.edu/24pq/MinPQ.java.html
 */
class MinPQ[K <: Ordered[K]] extends PriorityQueue[K] {

	protected def cmp(i: Int, j: Int): Boolean = {
		greater(i,j)
	}

	// return the smallest key
	def min: K = {
		if (isEmpty) {
			throw new NoSuchElementException("PQ underflow")
		}
		pq.head
	}
	// return and remove the smallest key
	def delMin(): K = {
		if (isEmpty) {
			throw new NoSuchElementException("PQ underflow")
		}
		var n = size
		exch(1,n)
		n = n - 1
		val minKey = pq(n)
		sink(1)
		// to avoid loitering and aid GC
		pq.remove(n + 1)
		minKey
	}
	def dequeue(): K = delMin()
	def enqueue(v: K): Unit = insert(v)
	
	private def isMinHeap(k: Int): Boolean = {
		if (k > size) true
		else {
			val left = 2*k
			val right = 2*k + 1
			if (left <= size && cmp(k,left)) false
			else if (right <= size && cmp(k, right)) false
			else isMinHeap(left) && isMinHeap(right)
		}
	}
}


object MinPQ {
  
}

