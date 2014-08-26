package org.awong.graphs

class Digraph[V] extends Graph[V] {

	override def addEdge(v: V, w: V): Unit = {
		add(v, w)
		nEdges = nEdges + 1
	}
	
	def reverse: Digraph[V] = {
		val r = new Digraph[V]()
		for (v <- adjacencyList.keys; w <- adj(v)) r.addEdge(w, v)
		r
	}
}

object Digraph {
	def apply[V]: Digraph[V] = new Digraph[V]()
}