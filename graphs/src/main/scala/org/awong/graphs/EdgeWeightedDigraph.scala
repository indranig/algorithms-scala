package org.awong.graphs

class EdgeWeightedDigraph[V] {
	var nEdges: Int = 0
	var adjacencyList = Map[V, Seq[DirectedEdge[V]]]()
	
	def nVertices: Int = adjacencyList.keySet.size
	
	def adj(v: V): Iterable[DirectedEdge[V]] = {
		adjacencyList.get(v) match {
			case Some(edges) => edges
			case None => Seq[DirectedEdge[V]]()
		}
	}
	
	def edges: Iterable[DirectedEdge[V]] = {
		for (vertex <- adjacencyList.keys; edge <- adj(vertex)) yield edge
	}
	
	protected def add(node: V, edge: DirectedEdge[V]): Unit = {
		adjacencyList = adjacencyList.get(node) match {
			case Some(edges) => adjacencyList + (node -> (edge +: edges))
			case None => adjacencyList + (node -> Seq[DirectedEdge[V]](edge))
		}
	}
	
	def addEdge(edge: DirectedEdge[V]): Unit = {
		val v = edge.from
		add(v, edge)
		nEdges = nEdges + 1
	}
}

case class DirectedEdge[V](v: V, w: V, weight: Double) {
	def from: V = v
	def to: V = w
	
	override def toString(): String = {
		"%d -> %d %.2f".format(v, w, weight)
	}
}

object EdgeWeightedDigraph
