package org.awong.searching.tree

/**
 * This file was part of Scalacaster project, https://github.com/vkostyukov/scalacaster
 * and written by Vladimir Kostyukov, http://vkostyukov.ru
 *
 * Red-Black Tree http://en.wikipedia.org/wiki/Red-black_tree
 *
 * Insert - O(log n)
 * Lookup - O(log n)
 * Remove - O(log n)
 *
 * -Notes-
 *
 * This is an implementation of Okasaki's red-black tree introduced in his fantastic book
 * "Purely Functional Data Structures and Algorithms". Here is the short link to article:
 * 
 *			www.ccs.neu.edu/course/cs3500wc/jfp99redblack.pdf
 *
 * As well, as Okasaki's tree this implementation doesn't contain a 'remove()' method, 
 * since it totally madness to understand and write such code. There is one CS hero, 
 * who handled that task - Matt Might. He described a new approach of removing node 
 * from red-black tree by using additional node colors (double black, negate black)
 * and new phase - 'bubbling'. Anyway, these things are a bit more then 'just-for-fun' 
 * package. Here is the link to Matt's research:
 *
 *			http://matt.might.net/articles/red-black-delete/
 *
 * Chris Okasaki said in his blog that removing is awkward operation for functional
 * data structures due to persistence. What does 'remove' mean in a functional world?
 * It means that the client wants to get a new collection without one element. In functional 
 * settings we can store both states of collection - without element 'x' (before insertion) 
 * and with 'x' (after insertion). So, when the removing is needed for 'x' the previous
 * state of collection can be used instead.
 *
 * Anyway, red-black trees is a great data structure with fantastic running time 
 * algorithms, but its implementation is so complicated that you might use simple
 * BST instead. This is why, Robert Sedgewick invented left-leaning red-black trees as
 * simple (in terms of implementation) replacement for red-black trees. Here is his 
 * awesome trees description:
 * 
 *			http://www.cs.princeton.edu/~rs/talks/LLRB/LLRB.pdf
 *
 * There are also two ways to improve current implementation. First of them - modify
 * 'balance()' method to expect violations only along the search path. Second - use 
 * 'two-way-comparison' that is invented by Anre Andersonin method 'contains()'.
 * Here is the explanation of this idea:
 *
 *			http://user.it.uu.se/~arnea/ps/searchproc.pdf
 *
 * This tree implementation doesn't inherit loads a useful methods from binary search
 * tree (see 'src/tree/Tree.scala') since is not necessary to have these methods in 
 * both trees (it also brakes DRY principle). The main goal of this project - is to
 * provide a clear functional approaches of implementation functional data structures,
 * not to provide a completely stable collections and algorithms framework.
 *
 *
 * PS: I would be happy, if someone decided to add Matt's 'remove()' method into this
 * implementation.
 */

/** 
 * A color for RB-Tree's nodes. 
 */
abstract sealed class Color
case object Red extends Color
case object Black extends Color

/**
 * A Red-Black Tree.
 */
abstract sealed class RedBlackTree[+A <% Ordered[A]] extends Tree[A] {

	/**
	 * The color of this tree.
	 */
	def color: Color

	/**
	 * The left child of this tree.
	 */
	def left: RedBlackTree[A]

	/**
	 * The right child of this tree.
	 */
	def right: RedBlackTree[A]

	/**
	 * Checks whether this tree is empty or not.
	 */
	def isEmpty: Boolean

	/**
	 * Adds given element 'x' into this tree.
	 *
	 * Exercise 3.10a @ PFDS.
	 *
	 * Time - O(log n)
	 * Space - O(log n)
	 */
	def add[B >: A <% Ordered[B]](x: B): RedBlackTree[B] = {
		def balancedAdd(t: RedBlackTree[A]): RedBlackTree[B] =
			if (t.isEmpty) {
				RedBlackTree.make(Red, x)
			} else if (x < t.key) {
				balanceLeft(t.color, t.key, balancedAdd(t.left), t.right)
			} else if (x > t.key) {
				balanceRight(t.color, t.key, t.left, balancedAdd(t.right))
			} else {
				t
			}

		def balanceLeft(c: Color, x: A, l: RedBlackTree[B], r: RedBlackTree[A]) = (c, l, r) match {
			case (Black, RBBranch(Red, y, RBBranch(Red, z, a, b, _), c, _), d) => {
				RedBlackTree.make(Red, y, RedBlackTree.make(Black, z, a, b), RedBlackTree.make(Black, x, c, d))
			}
			case (Black, RBBranch(Red, z, a, RBBranch(Red, y, b, c, _), _), d) => {
				RedBlackTree.make(Red, y, RedBlackTree.make(Black, z, a, b), RedBlackTree.make(Black, x, c, d))
			}
			case _ => {
				RedBlackTree.make(c, x, l, r)
			}
		}

		def balanceRight(c: Color, x: A, l: RedBlackTree[A], r: RedBlackTree[B]) = (c, l, r) match {
			case (Black, a, RBBranch(Red, y, b, RBBranch(Red, z, c, d, _), _ )) => {
				RedBlackTree.make(Red, y, RedBlackTree.make(Black, x, a, b), RedBlackTree.make(Black, z, c, d))
			}
			case (Black, a, RBBranch(Red, z, RBBranch(Red, y, b, c, _ ), d, _)) => {
				RedBlackTree.make(Red, y, RedBlackTree.make(Black, x, a, b), RedBlackTree.make(Black, z, c, d))
			}
			case _ => {
				RedBlackTree.make(c, x, l, r)
			}
		}

		def blacken(t: RedBlackTree[B]) = {
			RedBlackTree.make(Black, t.key, t.left, t.right)
		}

		blacken(balancedAdd(this))
	}

	def remove[B >: A <% Ordered[B]](x: B): RedBlackTree[B] = {
		???
	}
	
	def map[B <% Ordered[B]](f: (A) => B): RedBlackTree[B] = {
		if (isEmpty) RedBlackTree.empty
		else RedBlackTree.make(color, f(key), left.map(f), right.map(f))
	}
	
	def flatMap[B <% Ordered[B]](f: (A) => Tree[B]): RedBlackTree[B] = {
		if (isEmpty) RedBlackTree.empty
		else ???
	}
	
	def invert[B >: A](implicit num: Numeric[B]): RedBlackTree[B] = {
		if (isEmpty) RedBlackTree.empty
		else RedBlackTree.make(color, num.negate(key), right.invert(num), left.invert(num))
	}
}


case class RBBranch[A <% Ordered[A]](color: Color,
			key: A, 
			left: RedBlackTree[A], 
			right: RedBlackTree[A],
			size: Int) extends RedBlackTree[A] {
	def isEmpty = false
}

case object RBLeaf extends RedBlackTree[Nothing] {
	def color: Color = Black
	def key: Nothing = fail("An empty tree.")
	def left: RedBlackTree[Nothing] = fail("An empty tree.")
	def right: RedBlackTree[Nothing] = fail("An empty tree.")
	def isEmpty = true
	def size = 0
}

object RedBlackTree {

	/**
	 * Returns an empty red-black tree instance.
	 *
	 * Time - O(1)
	 * Space - O(1)
	 */
	def empty[A]: RedBlackTree[A] = RBLeaf

	/**
	 *
	 */
	def make[A <% Ordered[A]](c: Color, x: A, l: RedBlackTree[A] = RBLeaf, r: RedBlackTree[A] = RBLeaf): RedBlackTree[A] = {
		RBBranch(c, x, l, r, l.size + r.size + 1)
	}

	/**
	 * Creates a new red-black tree from given 'xs' sequence.
	 *
	 * Time - O(n log n)
	 * Space - O(log n)
	 */
	def apply[A <% Ordered[A]](xs: A*): RedBlackTree[A] = {
		var r: RedBlackTree[A] = RBLeaf
		for (x <- xs) r = r.add(x)
		r
	}
}