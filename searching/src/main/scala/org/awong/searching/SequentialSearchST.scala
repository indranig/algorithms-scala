package org.awong.searching

/**
 * This essentially uses an inefficient linked list
 */
class SequentialSearchST[Key,Value] extends SymbolTable[Key,Value] {
	case class Node(key: Key, var value: Value)
	
	var list: List[Node] = Nil
	
	private def find(remainder: List[Node])(p: Node => Boolean): Option[Node] = {
		remainder match {
			case head::tail if p(head) => Some(head)
			case head::tail if !p(head) => find(tail)(p)
			case Nil => None
		}
	}
	
	private def findFirst(key: Key, list: List[Node]): Option[Node] = {
		find(list){ _.key == key }
	}
	
	private def filter(remainder: List[Node], result: List[Node])(p: Node => Boolean): List[Node] = {
		remainder match {
			case head::tail if p(head) => head::result
			case head::tail if !p(head) => filter(tail, result)(p)
			case Nil => result
		}
	}
	
	def get(key: Key): Option[Value] = {
		for (n <- findFirst(key, list)) yield n.value
	}
	
	def put(key: Key, value: Value): Unit = {
		findFirst(key, list) match {
			case Some(update) => update.value = value
			case None => list = Node(key, value) :: list
		}
	}
	
	def size(): Int = {
		list.size
	}
	
	def keys(): Iterable[Key] = {
		for (each <- list) yield each.key
	}
	
	override def delete(key: Key): Unit = {
		if (!list.isEmpty) {
			list = filter(list, Nil){ _.key != key }
		}
	}
	

}

object SequentialSearchST
