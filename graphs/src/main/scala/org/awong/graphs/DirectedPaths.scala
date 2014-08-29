package org.awong.graphs

import collection.mutable.{Map => MMap}

abstract class DirectedPaths[V](graph: Digraph[V], start: V) {
	
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

// Same code as DepthFirstOrder
class DirectedDFS[V](graph: Digraph[V], start: V) extends DirectedPaths[V](graph, start) {
	
	import collection.immutable.Queue
	import collection.mutable.DoubleLinkedList
	
	private var preOrder = Queue[V]()
	private var postOrder = Queue[V]()
	private var reversePostOrder = DoubleLinkedList[V]()

	setUp()
	
	private def setUp(): Unit = {
		graph.vertices.foreach { v =>
			if (!marked(v)) {
				dfs(graph, v)
			}
		}
	}
	
	private def dfs(graph: Digraph[V], v: V): Unit = {
		preOrder.enqueue(v)
		
		marked(v, true)
		
		graph.adj(v).foreach { w =>
			if (!marked(w)) {
				dfs(graph, w)
			}
		}
		postOrder.enqueue(v)
		reversePostOrder :+ v
	}
	
	def isReachable(vertex: V): Boolean = marked(vertex)
	
	def pre: Iterable[V] = preOrder.toList
	def post: Iterable[V] = postOrder.toList
	def reversePost: Iterable[V] = reversePostOrder.toList
}


class DepthFirstDirectedPaths[V](graph: Digraph[V], start: V) extends DirectedPaths[V](graph, start) {
	dfs(graph, start)
	
	private def dfs(graph: Digraph[V], vertex: V): Unit = {
		marked(vertex, true)
		graph.adj(vertex).foreach{ w =>
			if (!marked(w)) {
				addEdgeTo(w, vertex)
				dfs(graph, w)
			}
		}
	}
}

class BreadthFirstDirectedPaths[V](graph: Digraph[V], start: V) extends DirectedPaths[V](graph, start) {
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

class DirectedCycle[V](graph: Digraph[V], start: V) extends DirectedPaths[V](graph, start) {
	import collection.mutable.Stack
	
	var maybeCycle: Option[Stack[V]] = None
	var onStackMap = MMap[V, Boolean]()
	
	protected def onStack(vertex: V, mark: Boolean): Unit = {
		onStackMap = onStackMap + (vertex -> mark)
	}
	
	def onStack(vertex: V): Boolean = {
		onStackMap.getOrElse(vertex, false)
	}
	
	def dfs(graph: Digraph[V], v: V): Unit = {
		def loop(x: V, w: V, cycle: Stack[V]): Stack[V] = {
			if (x != w) {
				cycle.push(x)
				loop(edgeTo(x), w, cycle)
			} else {
				cycle
			}
		}
		onStack(v, true)
		marked(v, true)
		graph.adj(v).foreach { w =>
			if (hasCycle) {
				// stop iterating
			} else if (!marked(w)) {
				addEdgeTo(w, v)
				dfs(graph, w)
			} else if (onStack(w)) {
				var aCycle = Stack[V]()
				// run recursion 
				aCycle = loop(v, w, aCycle)
				aCycle.push(w)
				aCycle.push(v)
				
				maybeCycle = Some(aCycle)
			}
		}
		onStack(v, false)
	}
	
	def hasCycle: Boolean = maybeCycle.isDefined
	
	def cycle: Iterable[V] = {
		maybeCycle.getOrElse(Seq[V]())
	}
}


class Topological[V](graph: Digraph[V], start: V) extends DirectedPaths[V](graph, start) {
	lazy val isDAG: Boolean = {
		val cycleFinder = new DirectedCycle(graph, start)
		!cycleFinder.hasCycle
	}
	
	lazy val order: Iterable[V] = {
		if (isDAG) {
			val dfs = new DirectedDFS(graph, start)
			dfs.reversePost
		} else {
			Seq[V]()
		}
	}
}


/**
 * An implementation needed because the textbook coded originally represented
 * graph vertices with integers rather than generically. This uses a symbol
 * table to associated from strings to integers.
 */
object SymbolDigraph

class KosarajuSharirSCC[V](graph: Digraph[V], start: V) extends DirectedPaths[V](graph, start) {
	private var idMap = MMap[V, Int]()
	var count: Int = 0 // number of strong components
	
	setUp()
	
	private def setUp(): Unit = {
		val order = new DirectedDFS(graph.reverse, start)
		order.reversePost.foreach { s =>
			if (!marked(s)) {
				dfs(graph, s)
				count = count + 1
			}
		}
	}
	protected def id(vertex: V, num: Int): Unit = {
		idMap = idMap + (vertex -> num)
	}
	
	def id(vertex: V): Int = {
		idMap.getOrElse(vertex, 0)
	}
	
	private def dfs(graph: Digraph[V], v: V): Unit = {
		marked(v, true)
		id(v, count)
		graph.adj(v).foreach { w =>
			if (!marked(w)) {
				dfs(graph, w)
			}
		}
	}
	
	def stronglyConnected(v: V, w: V): Boolean = {
		id(v) == id(w)
	}
}

class TransitiveClosure[V](graph: Digraph[V], start: V) extends DirectedPaths[V](graph, start) {
	
	private var allMap = MMap[V, DirectedDFS[V]]()
	
	setUp()

	private def setUp(): Unit = {
		graph.vertices.foreach { v =>
			allMap = allMap + (v -> new DirectedDFS(graph, v))
		}
	}
	
	
	
	def reachable(v: V, w: V): Boolean = {
		val dfs = allMap.getOrElse(v, new DirectedDFS(graph, v))
		dfs.marked(w)
	}
}


