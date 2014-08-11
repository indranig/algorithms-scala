package org.awong.fundamentals.unionfind

trait UnionFind {
	def count: Int
	// connect p and q
	def union(p: Int, q: Int): Unit
	// returns true iff p and q are in the same component
	def connected(p: Int, q: Int): Boolean
	// component identifier for p
	def find(p: Int): Int
}


