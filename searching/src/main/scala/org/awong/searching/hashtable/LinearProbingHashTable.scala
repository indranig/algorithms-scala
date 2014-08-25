package org.awong.searching.hashtable

import org.awong.searching.SymbolTable
import collection.mutable.{Buffer => MBuffer}

class LinearProbingHashTable[Key,Value](var keyArray: MBuffer[Key] = MBuffer[Key](),
		var vals: MBuffer[Value]=MBuffer[Value](),
		var n: Int = 0,
		var m: Int = 16)
	extends SymbolTable[Key,Value]
{
	private def hash(key: Key): Int = {
		(key.hashCode() & 0x7fffFFFF) % m
	}
	
	private def resize(cap: Int) = {
		val ks = List[Key]().toBuffer
	}
	
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

object LinearProbingHashTable {

}
