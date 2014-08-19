package org.awong.searching

/**
 * This essentially uses an inefficient linked list
 */
class SequentialSearchST[Key,Value] extends SymbolTable[Key,Value] {
	case class Node(key: Key, var value: Value, next: Option[Node])
	
	var first: Node = _
	
	private def getImpl(key: Key, current: Node): Option[Node] = {
		if (key.equals(current.key)) {
			Some(current)
		} else {
			current.next match {
				case Some(nextNode) => getImpl(key, nextNode)
				case None => None
			}
		}
	}
	
	def get(key: Key): Option[Value] = {
		for (n <- getImpl(key, first)) yield n.value
	}
	
	def put(key: Key, value: Value): Unit = {
		getImpl(key, first) match {
			case Some(update) => update.value = value
			case None => first = Node(key, value, Some(first))
		}
	}
	
	def size(): Int = {
		???
	}
	
	def keys(): Iterable[Key] = {
		???
	}
	
	override def delete(key: Key): Unit = {
		???
	}
	

}

object SequentialSearchST
