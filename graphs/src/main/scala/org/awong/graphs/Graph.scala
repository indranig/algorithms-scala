package org.awong.graphs

class Graph[V] {
	var adjacencyList = Map[V, Seq[V]]()
	var nEdges: Int = 0
	val isDirected = false
	
	def nVertices: Int = vertices.size
	
	def vertices: Set[V] = adjacencyList.keySet
	
	def adj(v: V): Iterable[V] = {
		adjacencyList.get(v) match {
			case Some(vs) => vs
			case None => Seq[V]()
		}
	}
	
	def degree(v: V): Int = {
		adjacencyList.get(v).getOrElse(Seq[V]()).size
	}
	
	def maxDegree(v: V): Int = {
		vertices.map(v => degree(v)).max
	}
	def nSelfLoops(v: V): Int = {
		val selfLoops = for (w <- adj(v) if w == v) yield w
		selfLoops.size
	}
	def nSelfLoops(): Int = {
		val allSelfLoops = for (v <- vertices) yield nSelfLoops(v)
		val sum = allSelfLoops.sum
		if (isDirected) {
			sum
		} else {
			sum / 2
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