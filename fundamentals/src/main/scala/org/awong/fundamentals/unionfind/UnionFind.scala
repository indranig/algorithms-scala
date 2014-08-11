package org.awong.fundamentals.unionfind

/**
 * These algorithms make unfortunate assumption that every node
 * in the digraph has been counted and has an "id" from 0 to n.
 * Revisit treatment in graphs where a graph can be represented
 * with an adjacency list.
 */
trait UnionFind {
	def count: Int
	// connect p and q
	def union(p: Int, q: Int): Unit
	// returns true iff p and q are in the same component
	def connected(p: Int, q: Int): Boolean
	// component identifier for p
	def find(p: Int): Int
}


