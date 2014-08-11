package org.awong.fundamentals.unionfind

/**
 * @see Sedgewick & Wayne, Algorithms, 4th Edition (2011), page 222-223
 */
class QuickFindUF(n: Int) extends UnionFind {
	var count = n
	var id = (0 to n).toArray

	def connected(p: Int, q: Int): Boolean = {
		find(p) == find(q)
	}
	
	// connect p and q
	def union(p: Int, q: Int): Unit = {
		if (!connected(p,q)) {
			var pid = id(p)
			var range = 0 until id.length
			range.foreach { i =>
				if (id(i) == pid) {
					id(i) = id(q)
				}
			}
			count = count - 1
		}
	}

	// component identifier for p
	def find(p: Int): Int =  {
		id(p)
	}
}


object QuickFindUF {
	def apply(n: Int) = new QuickFindUF(n)
}