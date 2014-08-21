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
	
	def isEmpty(): Boolean = {
		size() == 0
	}
	
	def size(): Int
	def keys(): Iterable[Key]
	
	def fail(msg: String) = throw new IllegalArgumentException(msg)
}


trait OrderedSymbolTable[Key <: Ordered[Key], Value] extends SymbolTable[Key,Value] {
	/**
	 * smallest key
	 */
	def min(): Option[Key]
	/**
	 * largest key
	 */
	def max(): Option[Key]
	/**
	 * largest key less than or equal to given key
	 */
	def floor(key: Key): Option[Key]
	/**
	 * smallest key greater than or equal to given key
	 */
	def ceiling(key: Key): Option[Key]
	/**
	 * number of keys less than given key
	 */
	def rank(key: Key): Int
	/**
	 * the key given the rank
	 */
	def select(rank: Int): Option[Key]
	/**
	 * delete the smallest key
	 */
	def deleteMin: Unit = {
		min() match {
			case Some(m) => delete(m)
			case _ =>
		}
	}
	/**
	 * delete the greatest key
	 */
	def deleteMax: Unit = {
		max() match {
			case Some(m) => delete(m)
			case _ =>
		}
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
		(min(), max()) match {
			case (None, None) => Seq[Key]()
			case (None, Some(m)) => Seq[Key](m)
			case (Some(m), None) => Seq[Key](m)
			case (Some(aMin), Some(aMax)) => keys(aMin, aMax)
		}
	}
}

trait MySet[Key] {
	def add(key: Key): Unit
	def delete(key: Key): Unit
	def contains(key: Key): Boolean
	def isEmpty(): Boolean
	def size(): Int
	
}