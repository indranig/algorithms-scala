package org.awong.searching.tree

import scala.collection.immutable.Queue

/**
 * This file was part of Scalacaster project, https://github.com/vkostyukov/scalacaster
 * and written by Vladimir Kostyukov, http://vkostyukov.ru
 *
 * Binary Search Tree http://en.wikipedia.org/wiki/Binary_search_tree
 *
 * Insert - O(log n)
 * Lookup - O(log n)
 * Remove - O(log n)
 *
 * -Notes-
 *
 * This is an efficient implementation of binary search tree. This tree guarantees
 * O(log n) running time for ordered operations like 'nthMin', 'nthMax' and 'rank'.
 * The main idea here - is use additional node field that stores size of tree rooted
 * at this node. This allows to get the size of tree in O(1) instead of linear time.
 */
abstract sealed class BinarySearchTree[+A <% Ordered[A]] extends Tree[A] {
	type BST[A] = BinarySearchTree[A]

	def left: BST[A]

	def right: BST[A]


	/**
	 * Adds given element 'x' into this tree.
	 *
	 * Time - O(log n)
	 * Space - O(log n)
	 */
	def add[B >: A <% Ordered[B]](x: B): BST[B] =
		if (isEmpty) BinarySearchTree.make(x)
		else if (x < key) BinarySearchTree.make(key, left.add(x), right)
		else if (x > key) BinarySearchTree.make(key, left, right.add(x))
		else this

	/**
	 * Removes given element 'x' from this tree.
	 *
	 * Time - O(log n)
	 * Space - O(log n)
	 */
	def remove[B >: A <% Ordered[B]](x: B): BST[B] =
		if (isEmpty) fail("Can't find " + x + " in this tree.")
		else if (x < key) BinarySearchTree.make(key, left.remove(x), right)
		else if (x > key) BinarySearchTree.make(key, left, right.remove(x))
		else {
			if (left.isEmpty && right.isEmpty) BinarySearchTree.empty
			else if (left.isEmpty) right
			else if (right.isEmpty) left
			else {
				val succ = right.min
				BinarySearchTree.make(succ, left, right.remove(succ))
			}
		}



	/**
	 * Creates a new tree by mapping this tree to the 'f' function.
	 *
	 * Time - O(n)
	 * Space - O(log n)
	 */
	def map[B <% Ordered[B]](f: (A) => B): BST[B] = {
		if (isEmpty) BinarySearchTree.empty
		else BinarySearchTree.make(f(key), left.map(f), right.map(f))
	}
	
	def flatMap[B <% Ordered[B]](f: (A) => Tree[B]): BST[B] = {
		if (isEmpty) BinarySearchTree.empty
		else ???
	}
	

	/**
	 * Inverts the sign of all the values in this tree.
	 * In other words, builds a mirror tree.
	 *
	 * Time - O(n)
	 * Space - O(log n)
	 */
	def invert[B >: A](implicit num: Numeric[B]): BST[B] = {
		if (isEmpty) BinarySearchTree.empty
		else BinarySearchTree.make(num.negate(key), right.invert(num), left.invert(num))
	}
}

case object Leaf extends BinarySearchTree[Nothing] {
	def key: Nothing = fail("An empty tree.")
	def left: BinarySearchTree[Nothing] = fail("An empty tree.")
	def right: BinarySearchTree[Nothing] = fail("An empty tree.")
	def size: Int = 0

	def isEmpty: Boolean = true
}

case class Branch[A <% Ordered[A]](key: A,
				left: BinarySearchTree[A],
				right: BinarySearchTree[A],
				size: Int) extends BinarySearchTree[A] {
	def isEmpty: Boolean = false
}

object BinarySearchTree {

	/**
	 * An empty tree.
	 */
	def empty[A]: BinarySearchTree[A] = Leaf

	/**
	 * A smart constructor for tree's branch.
	 * 
	 * Time - O(1)
	 * Space - O(1)
	 */
	def make[A <% Ordered[A]](x: A, l: BinarySearchTree[A] = Leaf, r: BinarySearchTree[A] = Leaf): BinarySearchTree[A] =
		Branch(x, l, r, l.size + r.size + 1)

	/**
	 * Creates a new tree from given sequence 'xs'.
	 *
	 * Time - O(n log n)
	 * Space - O(log n)
	 */
	def apply[A <% Ordered[A]](xs: Iterable[A]): BinarySearchTree[A] = {
		var r: BinarySearchTree[A] = BinarySearchTree.empty
		for (x <- xs) r = r.add(x)
		r
	}



	/**
	 * Exercise 2.5a @ PFDS.
	 * 
	 * Generates a complete tree of depth 'd' with 'x' stored in every node.
	 *
	 * Time - O(log n)
	 * Space - O(log n)
	 */
	def complete[A <% Ordered[A]](x: A, d: Int): BinarySearchTree[A] =
		if (d == 0) BinarySearchTree.make(x)
		else {
			val t = BinarySearchTree.complete(x, d - 1)
			BinarySearchTree.make(x, t, t)
		}

	/**
	 * Exercise 2.5b @ PFDS.
	 * 
	 * Generates a balanced tree of given size 's' with 'x' stored in every node.
	 *
	 * NOTES:
	 *
	 * Here, we're trying to reduce the memory footprint by sharing the common structure
	 * of two almost-equivalent trees (in function pair). Instead, we're paying 2 * O(log n)
	 * time for insertion at every level of recursion.
	 *
	 * Time - O(log n)
	 * Space - O(log n)
	 */
	def balanced[A <% Ordered[A]](x: A, s: Int): BinarySearchTree[A] = {
		def pair(ss: Int): (BinarySearchTree[A], BinarySearchTree[A]) =
			if (ss <= 0) (BinarySearchTree.empty, BinarySearchTree.empty)
			else {
				val t = balanced(x, ss - 1)
				(t, t.add(x))
			}

		val (lt, rt) = pair(s / 2) 
		BinarySearchTree.make(x, lt, rt)
	}
}