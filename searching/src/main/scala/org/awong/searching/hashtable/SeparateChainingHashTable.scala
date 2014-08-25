package org.awong.searching.hashtable

import org.awong.searching._

class SeparateChainingHashTable[Key,Value](var m: Int, var tables: Array[SymbolTable[Key,Value]] = Array[SymbolTable[Key,Value]]())
	extends SymbolTable[Key,Value]
{
	private def hash(key: Key): Int = {
		(key.hashCode() & 0x7fffFFFF) % m
	}
	
	def get(key: Key): Option[Value] = {
		tables(hash(key)).get(key)
	}
	
	private def resize(): Unit = {
		???
	}
	
	def put(key: Key, value: Value): Unit = {
		resize()
		tables(hash(key)).put(key, value)
	}
	
	def size(): Int = {
		tables.foldLeft(0){ case (sz,table) =>
			sz + table.size
		}
	}
	
	def keys(): Iterable[Key] = {
		tables.foldLeft(List[Key]()){ case (accrued,table) =>
			table.keys.toList ++ accrued
		}
	}
	
	override def delete(key: Key): Unit = {
		resize()
		if (contains(key)) {
			tables(hash(key)).delete(key)
		}
	}

}

object SeparateChainingHashTable {
	def apply[Key,Value]: SeparateChainingHashTable[Key,Value] = {
		val initialM = 997
		val xf = for (n <- (1 to initialM)) yield SequentialSearchST[Key,Value]()
		new SeparateChainingHashTable(initialM, xf.toArray)
	}
}
