package org.awong.searching

class SeparateChainingHashTable [Key,Value] extends SymbolTable[Key,Value] {
	def get(key: Key): Option[Value] = {
		???
	}
	
	def put(key: Key, value: Value): Unit = {
		???
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

object SeparateChainingHashTable
