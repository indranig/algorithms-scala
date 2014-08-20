package org.awong.searching.tree

abstract class Tree[+A <% Ordered[A]] {
	/**
	 * The value of this tree.
	 */
	def key: A

	/**
	 * The left child of this tree.
	 */
	def left: Tree[A]

	/**
	 * The right child of this tree.
	 */
	def right: Tree[A]

	/**
	 * The size of this tree.
	 */
	def size: Int

	/**
	 * Checks whether this tree is empty or not.
	 */
	def isEmpty: Boolean
	
	/**
	 * Fails with given message.
	 */
	def fail(m: String) = throw new NoSuchElementException(m)
	
	/**
	 * Converts this tree into the string representation.
	 *
	 * Time - O(n)
	 * Space - O(log n)
	 */
	override def toString: String = {
		if (isEmpty) "."
		else "{" + left + key + right + "}"
	}

	def add[B >: A <% Ordered[B]](x: B): Tree[B]
	
	def remove[B >: A <% Ordered[B]](x: B): Tree[B]
	
	/**
	 * Checks whether this tree is a binary search tree or not.
	 *
	 * Time - O(n)
	 * Space - O(log n)
	 */
	def isValid: Boolean = {
		if (isEmpty) true
		else if (left.isEmpty && right.isEmpty) true
		else if (left.isEmpty) right.key >= key && right.isValid
		else if (right.isEmpty) left.key <= key && left.isValid
		else left.key <= key && right.key >= key && left.isValid && right.isValid
	}

	/**
	 * Checks whether this tree is balanced or not.
	 *
	 * Time - O(n)
	 * Space - O(log n)
	 */
	def isBalanced: Boolean = {
		def loop(t: Tree[A]): Int = 
			if (t.isEmpty) { 0
			} else {
				val l = loop(t.left)
				if (l == -1) -1
				else {
					val r = loop(t.right)
					if (r == -1) -1
					else if (math.abs(l - r) > 1) -1
					else 1 + math.max(l, r)
				}
			}

		!(loop(this) == -1)
	}

	/**
	 * Checks whether this tree contains element 'x' or not.
	 *
	 * Exercise 2.1 @ PFDS.
	 *
	 * According to the Anderson's paper (1991) we can reduce the number of comparisons
	 * from 2d to d + 1 in the worst case by keeping track of candidate elements that might
	 * be equal to the query.
	 *
	 * Time - O(log n)
	 * Space - O(log n)
	 */
	def contains[B >: A <% Ordered[B]](searchKey: B): Boolean = {
		def loop(current: Tree[A], c: Option[A]): Boolean = 
			if (current.isEmpty) false
			else {
				if (searchKey < current.key) loop(current.left, Some(current.key))
				else if (searchKey > current.key) loop(current.right, Some(current.key))
				else true
			}
		loop(this, None)
	}

	/**
	 * Returns the sub-tree of this tree with root element 'x'.
	 *
	 * Time - O(log n)
	 * Space - O(log n)
	 */
	def subtree[B >: A <% Ordered[B]](x: B): Tree[B] =
		if (isEmpty) fail("Can't find " + x + " in this tree.")
		else if (x < key) left.subtree(x)
		else if (x > key) right.subtree(x)
		else this

	/**
	 * Checks whether the 't' tree is a subtree of this tree.
	 *
	 * NOTE: This task can be done in O(n + m) running time 
	 * by using the following algorithm:
	 *
	 * 1. convert this tree into string representation using pre-order and in-order walks - O(n)
	 * 2. convert other tree into string representation using pre-order and in-order walks - O(m)
	 * 3. check whether second in-order string is substring of the first in-order string - O(log n)
	 * 3. check whether second pre-order string is substring of the first pre-order string - O(log n)
	 *
	 * HINT: 'isSubstring' checking can be done with suffix-tree in O(m) but requires O(n) time for
	 *			 it's building. 
	 *
	 * Time - O(n log n)
	 * Space - O(log n)
	 */
	def isSubtree[B >: A <% Ordered[B]](t: Tree[B]): Boolean = {
		def loop(a: Tree[B], b: Tree[B]): Boolean = 
			if (a.isEmpty && b.isEmpty) true
			else if (a.isEmpty || b.isEmpty) false
			else a.key == b.key && loop(a.left, b.left) && loop(a.right, b.right)

		loop(subtree(t.key), t)
	}

	/**
	 * Merges this tree with given 't' tree.
	 *
	 * NOTE: This task can be done in O(n + m) running time by using
	 * the following algorithm:
	 *
	 * 1. convert this tree into list - O(n)
	 * 2. convert other tree into list - (m)
	 * 3. merge these list into one - O(n + m)
	 * 4. build a new tree from sorted list - O(n + m)
	 *
	 * Time - O(n log n)
	 * Space - O(log n)
	 */
	def merge[B >: A <% Ordered[B]](t: Tree[B]): Tree[B] = {
		def loop(s: Tree[B], d: Tree[B]): Tree[B] = 
			if (s.isEmpty) d
			else loop(s.right, loop(s.left, d.add(s.key)))

		loop(this, t)
	}
	
	/**
	 * Applies the 'f' function to the each element of this tree.
	 *
	 * Time - O(n)
	 * Space - O(log n)
	 */
	def foreach(f: (A) => Unit): Unit = {
		if (!isEmpty) {
			left.foreach(f)
			f(key)
			right.foreach(f)
		}
	}

	/**
	 * Combines all elements of this tree into value.
	 *
	 * Time - O(n)
	 * Space - O(log n)
	 */
	def fold[B](n: B)(op: (B, A) => B): B = {
		def loop(t: Tree[A], a: B): B = {
			if (t.isEmpty) a
			else loop(t.right, op(loop(t.left, a), t.key))
		}

		loop(this, n)
	}

	def map[B <% Ordered[B]](f: (A) => B): Tree[B]
	
	def flatMap[B <% Ordered[B]](f: (A) => Tree[B]): Tree[B]

	/**
	 * Calculates the sum of all elements of this tree.
	 *
	 * Time - O(n)
	 * Space - O(log n)
	 */
	def sum[B >: A](implicit num: Numeric[B]): B = fold(num.zero)(num.plus)

	/**
	 * Calculates the product of all elements of this list.
	 *
	 * Time - O(n)
	 * Space - O(log n)
	 */
	def product[B >: A](implicit num: Numeric[B]): B = fold(num.one)(num.times)
	
	def invert[B >: A](implicit num: Numeric[B]): Tree[B]

	/**
	 * Searches for the minimal element of this tree.
	 * 
	 * Time - O(log n)
	 * Space - O(log n)
	 */
	def min: A = {
		def loop(t: Tree[A], m: A): A = {
			if (t.isEmpty) m
			else loop(t.left, t.key)
		}

		if (isEmpty) fail("An empty tree.")
		else loop(left, key)
	}

	/**
	 * Searches for the maximal element of this tree.
	 *
	 * Time - O(log n)
	 * Space - O(log n)
	 */
	def max: A = {
		def loop(t: Tree[A], m: A): A = {
			if (t.isEmpty) m
			else loop(t.right, t.key)
		}

		if (isEmpty) fail("An empty tree.")
		else loop(right, key)
	}
	
	/**
	 * Calculates the height of this tree.
	 *
	 * Time - O(n)
	 * Space - O(log n)
	 */
	def height: Int = {
		if (isEmpty) 0
		else 1 + math.max(left.height, right.height)
	}
	
	/**
	 * Calculates the depth for given element 'x'.
	 *
	 * Time - O(log n)
	 * Space - O(log n)
	 */
	def depth[B >: A <% Ordered[B]](x: B): Int = {
		if (isEmpty) fail("Can't find " + x + " in this tree.")
		else if (x < key) 1 + left.depth(x)
		else if (x > key) 1 + right.depth(x)
		else 0
	}

	/**
	 * Searches for the successor of given element 'x'.
	 *
	 * Time - O(log n)
	 * Space - O(log n)
	 */
	def successor[B >: A <% Ordered[B]](x: B): A = {
		def forward(t: Tree[A], p: List[Tree[A]]): A =
			if (t.isEmpty) fail("Can't find " + x + " in this tree.")
			else if (x < t.key) forward(t.left, t :: p)
			else if (x > t.key) forward(t.right, t :: p)
			else if (!t.right.isEmpty) t.right.min
			else backward(t, p)

		def backward(t: Tree[A], p: List[Tree[A]]): A = 
			if (p.isEmpty) fail("The " + x + " doesn't have an successor.")
			else if (t == p.head.right) backward(p.head, p.tail)
			else p.head.key

		forward(this, Nil)
	}

	/**
	 * Searches for the predecessor of given element 'x'.
	 *
	 * Time - O(log n)
	 * Space - O(log n)
	 */
	def predecessor[B >: A <% Ordered[B]](x: B): A = {
		def forward(t: Tree[A], p: List[Tree[A]]): A =
			if (t.isEmpty) fail("Can't find " + x + " in this tree.")
			else if (x < t.key) forward(t.left, t :: p)
			else if (x > t.key) forward(t.right, t :: p)
			else if (!t.left.isEmpty) t.left.max
			else backward(t, p)

		def backward(t: Tree[A], p: List[Tree[A]]): A = 
			if (p.isEmpty) fail("The " + x + " doesn't have an predecessor.")
			else if (t == p.head.left) backward(p.head, p.tail)
			else p.head.key

		forward(this, Nil)
	}


	/**
	 * Searches for the first common ancestor of two given elements 'x' and 'y'.
	 *
	 * Time - O(log n)
	 * Space - O(log n)
	 */
	def ancestor[B >: A <% Ordered[B]](x: B, y: B): A = {
		def loop(t: Tree[A]): A = {
			if (x < t.key && y < t.key) loop(t.left)
			else if (x > t.key && y > t.key) loop(t.right)
			else t.key
		}
		
		if (isEmpty) fail("Tree is empty.")
		else if (!contains(x)) fail("Tree doesn't contain " + x + ".")
		else if (!contains(y)) fail("Tree doesn't contain " + y + ".")
		else loop(this)
	}


	/**
	 * Searches for the lower bound element of given 'x'.
	 *
	 * Time - O(log n)
	 * Space - O(log n)
	 */
	def lowerBound[B >: A <% Ordered[B]](x: B): A =
		if (isEmpty) fail("Tree is empty.")
		else if (x < key)
			if (!left.isEmpty) left.lowerBound(x)
			else key
		else if (x > key)
			if (!right.isEmpty) { val v = right.lowerBound(x); if (v > x) key else v }
			else key
		else key

	/**
	 * Calculates the number of elements that less or equal to given 'x'.
	 *
	 * Time - O(log n)
	 * Space - O(log n)
	 */
	def rank[B >: A <% Ordered[B]](x: B): Int =
		if (isEmpty) 0
		else if (x < key) left.rank(x)
		else if (x > key) 1 + left.size + right.rank(x)
		else left.size

	/**
	 * Searches for the upper bound element of given 'x'.
	 *
	 * Time - O(log n)
	 * Time - O(log n)
	 */
	def upperBound[B >: A <% Ordered[B]](x: B): A = 
		if (isEmpty) fail("Tree is empty.")
		else if (x < key)
			if (!left.isEmpty) { val v = left.upperBound(x); if (v < x) key else v }
			else key
		else if (x > key)
			if (!right.isEmpty) right.upperBound(x)
			else key
		else key


	/**
	 * Calculates the path for given element 'x'.
	 *
	 * Time - O(log n)
	 * Space - O(log n)
	 */
	def path[B >: A <% Ordered[B]](x: B): List[Tree[A]] = 
		if (isEmpty) fail("Can't find " + x + " in this tree.")
		else if (x < key) this :: left.path(x)
		else if (x > key) this :: right.path(x)
		else List(this)

	/**
	 * Calculates the trace for given element 'x'.
	 *
	 * Time - O(log n)
	 * Space - O(log n)
	 */
	def trace[B >: A <% Ordered[B]](x: B): List[A] = 
		if (isEmpty) fail("Can't find " + x + " in this tree.")
		else if (x < key) this.key :: left.trace(x)
		else if (x > key) this.key :: right.trace(x)
		else List(this.key)

	/**
	 * Searches for the n-th element of this tree.
	 *
	 * Time - O(log n)
	 * Space - O(log n)
	 */
	def apply(n: Int): A = 
		if (isEmpty) fail("Tree doesn't contain a " + n + "th element.")
		else {
			val size = left.size
			if (n < size) left(n)
			else if (n > size) right(n - size - 1)
			else key
		}



	/**
	 * Searches for the n-th maximum element of this tree.
	 *
	 * Time - O(log n)
	 * Time - O(log n)
	 */
	def nthMax(n: Int): A = apply(size - n - 1)

	/**
	 * Searches for the n-th minimum element of this tree.
	 *
	 * Time - O(log n)
	 * Space - O(log n)
	 */
	def nthMin(n: Int): A = apply(n)

	/**
	 * Constructs the list of 'n' largest elements of this tree.
	 *
	 * Note: We suppose that list.size runs in O(1).
	 *
	 * Time - O(n)
	 * Space - O(log n)
	 */
	def takeLargest(n: Int): List[A] = {
		def loop(t: Tree[A], l: List[A]): List[A] = 
			if (t.isEmpty || l.size == n) l
			else {
				val ll = loop(t.right, l)
				if (ll.size == n) ll
				else loop(t.left, t.key :: ll)
			}

		loop(this, Nil).reverse
	}

	/**
	 * Constructs the list of 'n' smallest elements of this tree.
	 *
	 * Note: We suppose that list.size runs in O(1).
	 *
	 * Time - O(n)
	 * Space - O(log n)
	 */
	def takeSmallest(n: Int): List[A] = {
		def loop(t: Tree[A], l: List[A]): List[A] = 
			if (t.isEmpty || l.size == n) l
			else {
				val ll = loop(t.left, l)
				if (ll.size == n) ll
				else loop(t.right, t.key :: ll)
			}

		loop(this, Nil).reverse
	}


	/**
	 * Searches the longest possible leaf-to-leaf path in this tree.
	 *
	 * Time - O(log^2 n)
	 * Space - O(log n)
	 */
	def diameter: List[A] = {
		def build(t: Tree[A], p: List[A]): List[A] = 
			if (t.isEmpty) p
			else if (t.left.height > t.right.height) build(t.left, t.key :: p)
			else build(t.right, t.key :: p)

		if (isEmpty) Nil
		else {
			val ld = left.diameter
			val rd = right.diameter
			val md = if (ld.length > rd.length) ld else rd
			if (1 + left.height + right.height > md.length)
				build(right, key :: build(left, Nil).reverse).reverse
			else md
		}
	}

	/**
	 * Converts this tree into linked list.
	 *
	 * Time - O(n)
	 * Space - O(log n)
	 */
	def toList: List[A] = {
		def loop(t: Tree[A], l: List[A]): List[A] = 
			if (t.isEmpty) l
			else loop(t.left, t.key :: loop(t.right, l))

		loop(this, Nil)
	}
	

	/**
	 * Performs the DFS and dumps values to the list. 
	 *
	 * Time - O(n)
	 * Space - O(log n)
	 */
	def valuesByDepth: List[A] = {
		def loop(s: List[Tree[A]]): List[A] = 
			if (s.isEmpty) Nil
			else if (s.head.isEmpty) loop(s.tail)
			else s.head.key :: loop(s.head.right :: s.head.left :: s.tail)

		loop(List(this))
	}

	/**
	 * Performs BFS and dumps values to the list.
	 *
	 * Time - O(n)
	 * Space - O(log n)
	 */
	def valuesByBreadth: List[A] = {
		import scala.collection.immutable.Queue
		def loop(q: Queue[Tree[A]]): List[A] = 
			if (q.isEmpty) Nil
			else if (q.head.isEmpty) loop(q.tail)
			else q.head.key :: loop(q.tail :+ q.head.left :+ q.head.right)

		loop(Queue(this))
	}

	/**
	 * Performs Zig-Zag (spiral) traversal and dumps values to the list.
	 *
	 * http://www.geeksforgeeks.org/level-order-traversal-in-spiral-form/
	 *
	 * Time - O(n)
	 * Space - O(log n)
	 */
	def valuesByZigZag: List[A] = {
		def zig(ltr: List[Tree[A]], rtl: List[Tree[A]]): List[A] = 
			if (ltr.isEmpty && rtl.isEmpty) Nil
			else if (ltr.isEmpty) zag(ltr, rtl)
			else if (ltr.head.isEmpty) zig(ltr.tail, rtl)
			else ltr.head.key :: zig(ltr.tail, ltr.head.left :: ltr.head.right :: rtl)

		def zag(ltr: List[Tree[A]], rtl: List[Tree[A]]): List[A] = 
			if (ltr.isEmpty && rtl.isEmpty) Nil
			else if (rtl.isEmpty) zig(ltr, rtl)
			else if (rtl.head.isEmpty) zag(ltr, rtl.tail)
			else rtl.head.key :: zag(rtl.head.right :: rtl.head.left :: ltr, rtl.tail)

		zig(List(this), Nil)
	}

	/**
	 * Builds all the possible root-to-leaf paths that sum to given value.
	 *
	 * Time - O(n)
	 * Space - O(log n)
	 */
	def rootToLeafPathsWithSum[B >: A](sum: B)(implicit num: Numeric[B]): List[List[B]] = {
		def loop(t: Tree[A], p: List[B]): List[List[B]] = {
			if (t.isEmpty)
				if (p.sum == sum) List(p) else Nil
			else loop(t.left, t.key :: p) ::: loop(t.right, t.key :: p)
		}

		loop(this, Nil)
	}
	
}