package org.awong.graphs

class EdgeWeightedGraph[V] {

	var nEdges: Int = 0
	var adjacencyList = Map[V, Seq[Edge[V]]]()
	
	def nVertices: Int = adjacencyList.keySet.size
	
	def adj(v: V): Iterable[Edge[V]] = {
		adjacencyList.get(v) match {
			case Some(edges) => edges
			case None => Seq[Edge[V]]()
		}
	}
	
	def edges: Iterable[Edge[V]] = {
		for (vertex <- adjacencyList.keys; edge <- adj(vertex)) yield edge
	}
	
	protected def add(node: V, edge: Edge[V]): Unit = {
		adjacencyList = adjacencyList.get(node) match {
			case Some(edges) => adjacencyList + (node -> (edge +: edges))
			case None => adjacencyList + (node -> Seq[Edge[V]](edge))
		}
	}
	
	def addEdge(edge: Edge[V]): Unit = {
		val v = edge.either
		val w = edge.other(v)
		add(v, edge)
		add(w, edge)
		nEdges = nEdges + 1
	}
}
case class Edge[V](v: V, w: V, weight: Double) extends Comparable[Edge[V]] {
	def either: V = v
	def other(vertex: V) = {
		vertex match {
			case `v` => w
			case `w` => v
			case _ => throw new RuntimeException(s"${vertex} is not in edge")
		}
	}
	override def toString(): String = {
		"%d - %d %.2f".format(v, w, weight)
	}
	def compareTo(that: Edge[V]): Int = {
		if (this.weight < that.weight) -1
		else if (this.weight > that.weight) 1
		else 0
	}
}

object EdgeWeightedGraph {

}

