package org.awong.graphs.directed

import org.awong.graphs.Digraph
import org.awong.graphs.DiEdge
import org.awong.stdlib.StdRandom

/**
 * @see http://algs4.cs.princeton.edu/42directed/DigraphGenerator.java
 */
object DigraphGenerator {
	/**
	 * Returns a random simple digraph containing <tt>V</tt> vertices and <tt>E</tt> edges.
	 * @param V the number of vertices
	 * @param E the number of vertices
	 * @return a random simple digraph on <tt>V</tt> vertices, containing a total of <tt>E</tt> edges
	 * @throws IllegalArgumentException if no such simple digraph exists
	 */
	def simple(v: Int, e: Int): Digraph[Int] = {
		val eLong = e.toLong
		val vLong = v.toLong
		
		if (e < 0) {
			throw new IllegalArgumentException("Too few edges");
		}
		if (eLong > vLong*(vLong-1)) {
			throw new IllegalArgumentException("Too many edges");
		}
		val vertices = (1 to v)
		var edges = Set[DiEdge[Int]]()
		var digraph = Digraph[Int]()
		while (digraph.nEdges < e) {
			val zis = StdRandom.uniform(v)
			val zat = StdRandom.uniform(v)
			val edge = DiEdge(zis, zat)
			if (zis != zat && !edges.contains(edge)) {
				edges = edges + edge
				digraph + edge
			}
		}
		digraph
	}
	
	
	/**
	 * Returns a random simple digraph on <tt>V</tt> vertices, with an 
	 * edge between any two vertices with probability <tt>p</tt>. This is sometimes
	 * referred to as the Erdos-Renyi random digraph model.
	 * This implementations takes time propotional to V^2 (even if <tt>p</tt> is small).
	 * @param V the number of vertices
	 * @param p the probability of choosing an edge
	 * @return a random simple digraph on <tt>V</tt> vertices, with an edge between any two vertices with probability <tt>p</tt>
	 * @throws IllegalArgumentException if probability is not between 0 and 1
	 */
	def simple(V: Int, p: Double): Digraph[Int] = {
		if (p < 0.0 || p > 1.0) {
			throw new IllegalArgumentException("Probability must be between 0 and 1");
		}
		val vertices = 1 to V
		var digraph = Digraph[Int]()
		val edges = for (v <- vertices; w <- vertices if (v != w) && StdRandom.bernoulli(p)) yield (v,w)
		edges.foreach { case(v,w) =>
			digraph.addEdge(v, w)
		}
		return digraph
	}
}