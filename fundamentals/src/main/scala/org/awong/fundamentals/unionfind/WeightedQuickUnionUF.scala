package org.awong.fundamentals.unionfind

/**
 * @see Sedgewick & Wayne, Algorithms, 4th Edition (2011), page 227-227
 */
class WeightedQuickUnionUF(n: Int) extends UnionFind {
	var count = n
	var id = (0 to n).toArray
	var sz = for (i <- id) yield 1

	def connected(p: Int, q: Int): Boolean = {
		find(p) == find(q)
	}
	
	// connect p and q
	def union(p: Int, q: Int): Unit = {
		var i = find(p)
		var j = find(q)
		if (i != j) {
			if (sz(i) < sz(j)) {
				id(i) = j
				sz(j) += sz(i)
			} else {
				id(j) = i
				sz(i) += sz(j)
			}
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

object WeightedQuickUnionUF {
	def apply(n: Int) = new WeightedQuickUnionUF(n)
}
