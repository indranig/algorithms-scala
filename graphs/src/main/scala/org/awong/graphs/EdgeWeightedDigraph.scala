package org.awong.graphs

class EdgeWeightedDigraph[V] {
	var nEdges: Int = 0
	val isDirected = true

	var adjacencyList = Map[V, Seq[WeightedDiEdge[V]]]()
	
	def nVertices: Int = vertices.size
	
	def vertices: Iterable[V] = adjacencyList.keySet
	
	
	def adj(v: V): Iterable[WeightedDiEdge[V]] = {
		adjacencyList.get(v) match {
			case Some(edges) => edges
			case None => Seq[WeightedDiEdge[V]]()
		}
	}
	
	def edges: Iterable[WeightedDiEdge[V]] = {
		for (vertex <- vertices; edge <- adj(vertex)) yield edge
	}
	
	protected def add(node: V, edge: WeightedDiEdge[V]): Unit = {
		adjacencyList = adjacencyList.get(node) match {
			case Some(edges) => adjacencyList + (node -> (edge +: edges))
			case None => adjacencyList + (node -> Seq[WeightedDiEdge[V]](edge))
		}
	}
	def add(v: V, w: V, weight: Double): Unit = add(WeightedDiEdge(v,w,weight))
	
	def +(edge: WeightedDiEdge[V]): Unit = add(edge)
	
	def add(edge: WeightedDiEdge[V]): Unit = {
		val v = edge.from
		add(v, edge)
		nEdges = nEdges + 1
	}
	
	
}

case class WeightedDiEdge[V](v: V, w: V, weight: Double) extends WeightedEdgeLike[V] {
	def from: V = v
	def to: V = w
	
	override def toString(): String = {
		"%d -> %d %.2f".format(v, w, weight)
	}
}

object EdgeWeightedDigraph
