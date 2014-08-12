package org.awong.searching

trait SymbolTable[Key,Value] {
	def put(key: Key, value: Value): Unit

	def get(key: Key): Option[Value]
	
	def delete(key: Key): Unit = {
		put(key, ???)
	}
	def contains(key: Key): Boolean = {
		get(key).nonEmpty
	}
	
	def isEmpty(key: Key): Boolean = {
		size() == 0
	}
	
	def size(): Int
	def keys(): Iterable[Key]
}


trait OrderedSymbolTable[Key <: Ordered[Key], Value] extends SymbolTable[Key,Value] {
	/**
	 * smallest key
	 */
	def min(): Key
	/**
	 * largest key
	 */
	def max(): Key
	/**
	 * largest key less than or equal to given key
	 */
	def floor(key: Key): Key
	/**
	 * smallest key greater than or equal to given key
	 */
	def ceiling(key: Key): Key
	/**
	 * number of keys less than given key
	 */
	def rank(key: Key): Int
	/**
	 * the key given the rank
	 */
	def select(rank: Int): Int
	/**
	 * delete the smallest key
	 */
	def deleteMin: Unit = {
		delete(min())
	}
	/**
	 * delete the greatest key
	 */
	def deleteMax: Unit = {
		delete(max())
	}
	/**
	 * number of keys from [lo ... high]
	 */
	def size(low: Key, high: Key): Int = {
		if (high < low) {
			0
		} else if (contains(high)){
			rank(high) - rank(low) + 1
		} else {
			rank(high) - rank(low)
		}
	}
	
	/**
	 * keys in [lo ... high] in sorted order
	 */
	def keys(low: Key, high: Key): Iterable[Key]
	
	override def keys(): Iterable[Key] = {
		keys(min(), max())
	}
}

trait MySet[Key] {
	def add(key: Key): Unit
	def delete(key: Key): Unit
	def contains(key: Key): Boolean
	def isEmpty(): Boolean
	def size(): Int
	
}