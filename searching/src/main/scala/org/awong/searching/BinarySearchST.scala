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
	
	def min(): Key = {
		orderedKeys(0)
	}
	def max(): Key = {
		orderedKeys(size() - 1)
	}
	
	def floor(key: Key): Key = {
		???
	}

	def ceiling(key: Key): Key = {
		val i = rank(key)
		orderedKeys(i)
	}

	def select(rank: Int): Key = {
		orderedKeys(rank)
	}
	
	def keys(low: Key, high: Key): Iterable[Key] = {
		val lowRank = rank(low)
		val hiRank = rank(high)
		orderedKeys.slice(lowRank, hiRank)
	}

}

// naive symbol tables
object BinarySearchST
