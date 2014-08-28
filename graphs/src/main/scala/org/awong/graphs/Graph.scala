package org.awong.graphs

class Graph[V] {
	var adjacencyList = Map[V, Seq[V]]()
	var nEdges: Int = 0
	
	def nVertices: Int = adjacencyList.keySet.size
	
	def vertices: Set[V] = adjacencyList.keySet
	
	def adj(v: V): Iterable[V] = {
		adjacencyList.get(v) match {
			case Some(vs) => vs
			case None => Seq[V]()
		}
	}
	
	protected def add(node: V, other: V): Unit = {
		adjacencyList = adjacencyList.get(node) match {
			case Some(nodes) => adjacencyList + (node -> (other +: nodes))
			case None => adjacencyList + (node -> Seq[V](other))
		}
	}
	
	def addEdge(v: V, w: V): Unit = {
		add(v, w)
		add(w, v)
		nEdges = nEdges + 1
	}
}

object Graph {
	def apply[V]: Graph[V] = new Graph[V]()
}