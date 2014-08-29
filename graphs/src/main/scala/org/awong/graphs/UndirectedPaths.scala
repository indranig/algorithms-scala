package org.awong.graphs

import collection.mutable.{Map => MMap}


abstract class UndirectedPaths[V](graph: Graph[V], start: V) {
	
	protected var markedMap = init()
	protected var edgeTo = MMap[V,V]()
	
	protected def init(): MMap[V, Boolean] = {
			graph.vertices.foldLeft(MMap[V,Boolean]()){ case (map,key) =>
			map + (key -> false)
		}
	}
	protected def addEdgeTo(w: V, v: V): Unit = {
		edgeTo = edgeTo + (w -> v)
	}
	
	protected def marked(vertex: V, mark: Boolean): Unit = {
		markedMap = markedMap + (vertex -> mark)
	}
	
	def marked(vertex: V): Boolean = {
		markedMap.getOrElse(vertex, false)
	}
	
	def hasPathTo(vertex: V): Boolean = {
		marked(vertex)
	}
	
	def pathTo(v: V): Iterable[V] = {
		def loop(vertex: V, seq: Seq[V]): Seq[V] = {
			edgeTo.get(vertex) match {
				case Some(x) if x != start => {
					loop(x, x +: seq)
				}
				case _ => seq
			}
		}
		if (!hasPathTo(v)) {
			Seq[V]()
		} else {
			val paths = loop(v, Seq[V]())
			start +: paths
		}
	}
}

class DepthFirstPaths[V](graph: Graph[V], start: V) extends UndirectedPaths[V](graph, start) {
	
	dfs(graph, start)
	
	private def dfs(graph: Graph[V], vertex: V): Unit = {
		marked(vertex, true)
		graph.adj(vertex).foreach{ w =>
			if (!marked(w)) {
				addEdgeTo(w, vertex)
				dfs(graph, w)
			}
		}
	}
}

class BreadthFirstPaths[V](graph: Graph[V], start: V) extends UndirectedPaths[V](graph, start) {
	
	bfs(graph, start)
	
	private def bfs(graph: Graph[V], vertex: V): Unit = {
		import collection.mutable.Queue
		
		var queue = Queue[V]()
		marked(vertex, true)
		queue.enqueue(vertex)
		while (!queue.isEmpty) {
			val v = queue.dequeue()
			graph.adj(v).foreach { w =>
				if (!marked(w)) {
					addEdgeTo(w,v)
					marked(w, true)
					queue.enqueue(w)
				}
			}
		}
	}
}


class ConnectedComponents[V](graph: Graph[V], start: V) extends UndirectedPaths[V](graph, start) {

	protected var idMap = MMap[V,Int]()
	var count: Int = 0
	
	setUp()
	
	private def setUp(): Unit = {
		graph.vertices.foreach{ vertex =>
			if (!marked(vertex)) {
				dfs(graph, vertex)
				count = count + 1
			}
		}
	}
	
	protected def count(vertex: V, count: Int): Unit = {
		idMap = idMap + (vertex -> count)
	}
	
	def id(vertex: V): Int = {
		idMap.getOrElse(vertex, -1)
	}
	
	private def dfs(graph: Graph[V], vertex: V): Unit = {
		marked(vertex, true)
		count(vertex, count)
		graph.adj(vertex).foreach { w =>
			if (!marked(w)) {
				dfs(graph, w)
			}
		}
	}

	def connected(thiz: V, that: V): Boolean = {
		id(thiz) == id(that)
	}
}

/*
 * detect whether the given graph has a cycle assuming
 * no self-loops or parallel edges
 */
class AcyclicDetection[V](graph: Graph[V], start: V) extends UndirectedPaths[V](graph, start) {
	var hasCycle: Boolean = false

	setUp()
	
	private def setUp(): Unit = {
		graph.vertices.foreach{ s =>
			if (!marked(s)) {
				dfs(graph, s, s)
			}
		}
	}
	private def dfs(graph: Graph[V], v: V, u: V): Unit = {
		marked(v, true)
		// change this recursion so that it terminates when hasCycle = true
		graph.adj(v).foreach { w =>
			if (!marked(w)) {
				dfs(graph, w, v)
			} else if (w != u) {
				hasCycle = true
			}
		}
	}
	
}

/*
 * Detect whether the given graph is a bipartite, which is just to say
 * that its vertices can be assigned to one of two colors in such a way that
 * no edge connects vertices of the same color.
 */
class BipartiteDetection[V](graph: Graph[V], start: V) extends UndirectedPaths[V](graph, start) {
	var isTwoColorable: Boolean = true
	var colorMap = MMap[V, Boolean]()
	
	setUp()
	
	private def setUp(): Unit = {
		graph.vertices.foreach{ s =>
			if (!marked(s)) {
				dfs(graph, s)
			}
		}
	}
	
	
	protected def color(vertex: V, colored: Boolean): Unit = {
		colorMap = colorMap + (vertex -> colored)
	}
	
	def color(vertex: V): Boolean = {
		colorMap.getOrElse(vertex, false)
	}
	
	private def dfs(graph: Graph[V], v: V): Unit = {
		marked(v, true)
		// change this recursion so that it terminates when isTwoColorable = true
		graph.adj(v).foreach { w =>
			if (!marked(w)) {
				val newColor = !color(v)
				color(w, newColor)
				dfs(graph, w)
			} else if (color(w) == color(v)) {
				isTwoColorable = false
			}
		}
	}
	
	def isBipartite: Boolean = isTwoColorable
}


/**
 * An implementation needed because the textbook coded originally represented
 * graph vertices with integers rather than generically. This uses a symbol
 * table to associated from strings to integers.
 * 
 * see p.552
 */
object SymbolGraph

/**
 * Teaser of idea to calculate the Erdos numbers for a graph
 * 
 * see p.555
 */
object DegreesOfSeparation

