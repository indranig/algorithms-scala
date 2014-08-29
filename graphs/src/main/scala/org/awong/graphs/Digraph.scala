package org.awong.graphs

class Digraph[V] extends Graph[V] {
	override val isDirected = true

	override def addEdge(v: V, w: V): Unit = {
		add(v, w)
		nEdges = nEdges + 1
	}
	
	def +(edge: DiEdge[V]): Unit = add(edge)
	
	def add(edge: DiEdge[V]): Unit = addEdge(edge.v, edge.w)
	
	def reverse: Digraph[V] = {
		val r = new Digraph[V]()
		for (v <- vertices; w <- adj(v)) r.addEdge(w, v)
		r
	}
}

object Digraph {
	def apply[V](): Digraph[V] = new Digraph[V]()
}

case class DiEdge[V <% Ordered[V]](v: V, w: V) extends EdgeLike[V] with Comparable[DiEdge[V]] {
	def from: V = v
	def to: V = w
	
	override def toString(): String = {
		"%d -> %d %.2f".format(v, w)
	}
	
	def compareTo(that: DiEdge[V]): Int = {
		if (this.v < that.v) -1
		else if (this.v > that.v) +1
		else if (this.w < that.w) -1
		else if (this.w > that.w) +1
		else 0
	}
}
