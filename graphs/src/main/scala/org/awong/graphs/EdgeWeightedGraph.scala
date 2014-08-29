package org.awong.graphs

class EdgeWeightedGraph[V] {
	var nEdges: Int = 0
	val isDirected = false

	var adjacencyList = Map[V, Seq[WeightedEdge[V]]]()
	
	def nVertices: Int = vertices.size
	
	def vertices: Iterable[V] = adjacencyList.keySet
	
	def adj(v: V): Iterable[WeightedEdge[V]] = {
		adjacencyList.get(v) match {
			case Some(edges) => edges
			case None => Seq[WeightedEdge[V]]()
		}
	}
	
	def edges: Iterable[WeightedEdge[V]] = {
		for (vertex <- vertices; edge <- adj(vertex)) yield edge
	}
	
	protected def add(node: V, edge: WeightedEdge[V]): Unit = {
		adjacencyList = adjacencyList.get(node) match {
			case Some(edges) => adjacencyList + (node -> (edge +: edges))
			case None => adjacencyList + (node -> Seq[WeightedEdge[V]](edge))
		}
	}
	
	def addEdge(v: V, w: V, weight: Double): Unit = {
		add(WeightedEdge(v,w,weight))
	}
	
	def +(edge: WeightedEdge[V]): Unit = add(edge)
	
	def add(edge: WeightedEdge[V]): Unit = {
		val v = edge.either
		val w = edge.other(v)
		add(v, edge)
		add(w, edge)
		nEdges = nEdges + 1
	}
}

case class WeightedEdge[V](v: V, w: V, weight: Double) extends Comparable[WeightedEdge[V]] with WeightedEdgeLike[V]
{
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
	// rank weighted edges from smallest weight to biggest weight
	def compareTo(that: WeightedEdge[V]): Int = {
		if (this.weight < that.weight) +1
		else if (this.weight > that.weight) -1
		else 0
	}
}

object EdgeWeightedGraph {

}

