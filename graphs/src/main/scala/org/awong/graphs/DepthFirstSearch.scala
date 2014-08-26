package org.awong.graphs

/**
 * simple dfs that marks vertices
 */
class DepthFirstSearch[V](graph: Graph[V], start: V) {
	import collection.mutable.{Map => MMap}
	
	var markedMap = init()
	var count: Int = 0
	
	dfs(graph, start)
	
	private def init(): MMap[V, Boolean] = {
		graph.adjacencyList.keys.foldLeft(MMap[V,Boolean]()){ case (map,key) =>
			map + (key -> false)
		}
	}
	
	private def mark(vertex: V, mark: Boolean): Unit = {
		markedMap = markedMap + (vertex -> mark)
	}
	
	def dfs(graph: Graph[V], vertex: V): Unit = {
		mark(vertex, true)
		count = count + 1
		graph.adj(start).foreach{ w =>
			if (!marked(w)) {
				dfs(graph, w)
			}
		}
	}
	
	def marked(vertex: V): Boolean = {
		markedMap.get(vertex).getOrElse(false)
	}
}