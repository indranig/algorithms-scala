package org.awong.graphs

import collection.mutable.{Map => MMap}


abstract class UndirectedPaths[V](graph: Graph[V], start: V) {
	
	var markedMap = init()
	var edgeTo = MMap[V,V]()
	
	protected def init(): collection.mutable.Map[V, Boolean] = {
			graph.adjacencyList.keys.foldLeft(collection.mutable.Map[V,Boolean]()){ case (map,key) =>
			map + (key -> false)
		}
	}
	protected def addEdgeTo(w: V, v: V): Unit = {
		edgeTo = edgeTo + (w -> v)
	}
	
	protected def mark(vertex: V, mark: Boolean): Unit = {
		markedMap = markedMap + (vertex -> mark)
	}
	
	def marked(vertex: V): Boolean = {
		markedMap.get(vertex).getOrElse(false)
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
		mark(vertex, true)
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
		mark(vertex, true)
		queue.enqueue(vertex)
		while (!queue.isEmpty) {
			val v = queue.dequeue()
			graph.adj(v).foreach { w =>
				if (!marked(w)) {
					addEdgeTo(w,v)
					mark(w, true)
					queue.enqueue(w)
				}
			}
		}
	}
}