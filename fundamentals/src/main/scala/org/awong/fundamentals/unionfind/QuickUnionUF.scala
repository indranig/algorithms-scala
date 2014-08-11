package org.awong.fundamentals.unionfind

/**
 * @see Sedgewick & Wayne, Algorithms, 4th Edition (2011), page 224-227
 */
class QuickUnionUF(n: Int) extends UnionFind {
	var count = n
	var id = (0 to n).toArray

	def connected(p: Int, q: Int): Boolean = {
		find(p) == find(q)
	}
	
	// connect p and q
	def union(p: Int, q: Int): Unit = {
		var i = find(p)
		var j = find(q)
		if (i != j) {
			id(i) = j
			count = count - 1
		}
	}

	// returns component identifier for p
	def find(p: Int): Int = {
		var x = p
		// traverse links until you find the root, which is connected to itself
		while (x != id(x)) {
			x = id(x)
		}
		x
	}
}

object QuickUnionUF {
	def apply(n: Int) = new QuickUnionUF(n)
}