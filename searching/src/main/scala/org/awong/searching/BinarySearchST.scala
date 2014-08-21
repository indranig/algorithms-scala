package org.awong.searching

/**
 * Keeps keys and values in parallel arrays. Runs a binary search to execute rank
 */
class BinarySearchST[Key <: Ordered[Key], Value] extends OrderedSymbolTable[Key, Value] {
	type Pair = (Key,Value)
	
	import scala.collection.mutable.{Buffer => MBuffer}
	// order this array by key
	var orderedKeys =  MBuffer[Key]().to
	var values = MBuffer[Value]()
	
	def rank(key: Key): Int = {
		// practically clone of org.awong.fundamentals.BinarySearch except always return an Int
		def bsf(list: Seq[Key], target: Key, start: Int, end: Int): Int = {
			if (start > end) {
				start
			} else {
				val mid = start + (end - start + 1)/2
				list match {
					case (arr: Seq[Key]) if (arr(mid) == target) =>
						mid
					case (arr: Seq[Key]) if (arr(mid) > target) => 
						bsf(list, target, start, mid - 1)
					case (arr: Seq[Key]) if (arr(mid) < target) =>
						bsf(list, target, mid + 1, end)
				}
			}
		}
		bsf(orderedKeys, key, 0, orderedKeys.length-1)
	}
	
	def get(key: Key): Option[Value] = {
		if (isEmpty()) {
			None
		} else {
			val i = rank(key)
			if (i < size() && orderedKeys(i) == key) {
				Some(values(i))
			} else {
				None
			}
		}
	}
	
	def put(key: Key, value: Value): Unit = {
		val i = rank(key)
		if (i < size() && orderedKeys(i) == key) {
			values(i) = value
		} else {
			val (lowKeys, highKeys) = orderedKeys.splitAt(i)
			val (lowValues, highValues) = values.splitAt(i)
			orderedKeys = lowKeys ++ MBuffer(key) ++ highKeys
			values = lowValues ++ MBuffer(value) ++ highValues
		}
	}
	
	def size(): Int = {
		orderedKeys.size
	}
	
	override def delete(key: Key): Unit = {
		???
	}
	
	def min(): Option[Key] = {
		if (orderedKeys.isEmpty) {
			None
		} else {
			Some(orderedKeys(0))
		}
	}
	def max(): Option[Key] = {
		if (orderedKeys.isEmpty) {
			None
		} else {
			Some(orderedKeys(size() - 1))
		}
	}
	
	def floor(key: Key): Option[Key] = {
		???
	}

	def ceiling(key: Key): Option[Key] = {
		if (orderedKeys.isEmpty) {
			None
		} else {
			Some(orderedKeys(rank(key)))
		}
	}

	def select(rank: Int): Option[Key] = {
		if (orderedKeys.isEmpty) {
			None
		} else {
			Some(orderedKeys(rank))
		}
	}
	
	def keys(low: Key, high: Key): Iterable[Key] = {
		val lowRank = rank(low)
		val hiRank = rank(high)
		orderedKeys.slice(lowRank, hiRank)
	}
	
	def successor[K1 >: Key <% Ordered[K1]](key: K1): Key = {
		???
	}

	def predecessor[K1 >: Key <% Ordered[K1]](key: K1): Key = {
		???
	}

}

// naive symbol tables
object BinarySearchST
