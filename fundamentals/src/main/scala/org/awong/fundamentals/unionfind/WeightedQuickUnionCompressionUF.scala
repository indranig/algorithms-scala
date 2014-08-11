package org.awong.fundamentals.unionfind

/**
 */
class WeightedQuickUnionCompressionUF(n: Int) extends UnionFind {
	var count = n
	var id = (0 to n).toArray
	var rank = for (i <- id) yield 0.toByte

	def connected(p: Int, q: Int): Boolean = {
		find(p) == find(q)
	}
	
	// connect p and q
	def union(p: Int, q: Int): Unit = {
		var i = find(p)
		var j = find(q)
		if (i != j) {
			if (rank(i) < rank(j)) {
				id(i) = j
			} else if (rank(i) > rank(j)){
				id(j) = i
			} else {
				id(j) = i
				val b = rank(i)
				rank(i)  = (b + 1).toByte
			}
			count = count - 1
		}
	}

	// returns component identifier for p
	def find(p: Int): Int = {
		var x = p
		// traverse links until you find the root, which is connected to itself
		while (x != id(x)) {
			id(x) = id(id(x))
			x = id(x)
		}
		x
	}
}

object WeightedQuickUnionCompressionUF {
	def apply(n: Int) = new WeightedQuickUnionCompressionUF(n)
}
