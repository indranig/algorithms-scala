package org.awong.graphs.mst

import org.awong.graphs.EdgeWeightedGraph
import org.awong.graphs.WeightedEdge
import org.awong.sorting.pq.MinPQ

import collection.mutable.{Map => MMap}
import collection.mutable.Queue

trait MinimumSpanningTreeLike[V] {
	def edges: Iterable[WeightedEdge[V]]
	def weight(): Double
	def graph: EdgeWeightedGraph[V]
}

class LazyPrimMST[V](ewg: EdgeWeightedGraph[V], start: V) extends MinimumSpanningTreeLike[V] {
	private var markedMap = MMap[V,Boolean]()
	private var mst = Queue[WeightedEdge[V]]()
	private var pq = new MinPQ[WeightedEdge[V]]()
	
	def marked(v: V): Boolean = {
		markedMap.getOrElse(v, false)
	}
	def marked(v: V, value: Boolean): Unit = {
		markedMap = markedMap + (v -> value)
	}
	
	private def setUp(): Unit = {
		/// assumes graph is connected (see Ex 4.3.22)
		visit(graph, start)
		while (!pq.isEmpty) {
			// get lowest weight edge from pq
			val e = pq.dequeue
			
			val v = e.either
			val w = e.other(v)
			if (!marked(v) && marked(w)) {
				// continue ... i.e. skip if ineligible
			} else {
				// add edge to tree
				mst.enqueue(e)
				// add either v or w to tree
				if (!marked(v)) visit(graph,v)
				if (!marked(w)) visit(graph,w)
			}
		}
	}
	
	private def visit(graph: EdgeWeightedGraph[V], v: V) : Unit = {
		marked(v, true)
		graph.adj(v).foreach { e =>
			if (!marked(e.other(v))) {
				pq.enqueue(e)
			}
		}
	}
	
	def edges: Iterable[WeightedEdge[V]] = mst
	def weight(): Double = {
		// see ex 4.3.31
		???
	}
	def graph: EdgeWeightedGraph[V] = ewg
}
