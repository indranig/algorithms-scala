package org.awong.searching

class ImperativeRBTree[Key <: Ordered[Key], Value] extends OrderedSymbolTable[Key,Value] {
	def put(key: Key, value: Value): Unit = {
		???
	}

	def get(key: Key): Option[Value] = {
		???
	}
	
	override def delete(key: Key): Unit = {
		???
	}
	
	def successor[K1 >: Key <% Ordered[K1]](key: K1): Key = {
		???
	}

	def predecessor[K1 >: Key <% Ordered[K1]](key: K1): Key = {
		???
	}
	
	override def contains(key: Key): Boolean = {
		???
	}
	
	
	def size(): Int = {
		???
	}

	def min(): Option[Key] = {
		???
	}

	def max(): Option[Key] = {
		???
	}

	def floor(key: Key): Option[Key] = {
		???
	}

	def ceiling(key: Key): Option[Key] = {
		???
	}

	def rank(key: Key): Int = {
		???
	}

	def select(rank: Int): Option[Key] = {
		???
	}
	
	def keys(low: Key, high: Key): Iterable[Key] = {
		???
	}
	
}