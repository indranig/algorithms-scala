package org.awong.searching

import java.util.NoSuchElementException



class ImperativeBST[Key <: Ordered[Key], Value] extends OrderedSymbolTable[Key,Value] {

	var root: Node = Leaf
	
	trait Node {
		def key: Key
		def value: Value
		def left: Node
		def right: Node
		def size: Int
		def isEmpty: Boolean
		def fail(msg: String) = throw new NoSuchElementException(msg)
	}
	case class Branch(key: Key,
				var value: Value,
				left:Node = Leaf,
				right: Node = Leaf)
				extends Node {
		def size: Int = left.size + right.size + 1
		val isEmpty = false
	}
	case object Leaf extends Node {
		def key: Key = fail("Empty tree")
		def value: Value = fail("Empty tree")
		def left: Node = fail("Empty tree")
		def right: Node = fail("Empty tree")
		val isEmpty = true
		val size = 0
	}

	def put(key: Key, value: Value): Unit = {
		def loop(current: Node, key: Key, newValue: Value): Node = {
			current match {
				case Leaf => Branch(key, value)
				case Branch(k,_,left,_) if key < k => loop(left, key, newValue)
				case Branch(k,_,_,right) if key > k => loop(right,key, newValue)
				case b @ Branch(k,v,_,_) if key == k =>
					b.value = newValue
					current
			}
		}
		root = loop(root, key, value)
	}

	def get(key: Key): Option[Value] = {
		def loop(current: Node, key: Key): Option[Value] = {
			current match {
				case Leaf => None
				case Branch(k,v,left,_) if key < k => loop(left,key)
				case Branch(k,v,_,right) if key > k => loop(right,key)
				case Branch(k,v,_,_) if key == k => Some(v)
			}
		}
		loop(root, key)
	}
	
	override def delete(key: Key): Unit = {
		???
	}
	override def contains(key: Key): Boolean = {
		???
	}
	
	
	def size(): Int = {
		root.size
	}

	def min(): Option[Key] = {
		def loop(node: Node): Node = {
			node.left match {
				case Leaf => node
				case _ => loop(node.left)
			}
		}
		root match {
			case Leaf => None
			case Branch(_,_,_,_) => Some(loop(root).key)
		}
	}

	def max(): Option[Key] = {
		def loop(node: Node): Node = {
			node.right match {
				case Leaf => node
				case _ => loop(node.right)
			}
		}
		root match {
			case Leaf => None
			case Branch(_,_,_,_) => Some(loop(root).key)
		}
	}

	def floor(key: Key): Option[Key] = {
		def loop(node: Node, key: Key): Node = {
			node match {
				case Leaf => Leaf
				case b @ Branch(k,_,_,_) if key == k => b
				case Branch(k,_,left,_) if key < k => loop(left, key)
				case Branch(k,_,_,right) if key > k =>
					loop(right,key) match {
						case Leaf => node
						case b @ Branch(_,_,_,_) => b
					}
			}
		}
		loop(root, key) match {
			case Leaf => None
			case Branch(k,_,_,_) => Some(k)
		}
	}

	def ceiling(key: Key): Option[Key] = {
		def loop(node: Node, key: Key): Node = {
			node match {
				case Leaf => Leaf
				case b @ Branch(k,_,_,_) if key == k => b
				case Branch(k,_,_,right) if key > k => loop(right, key)
				case Branch(k,_,left,_) if key < k =>
					loop(left,key) match {
						case Leaf => node
						case b @ Branch(_,_,_,_) => b
					}
			}
		}
		loop(root, key) match {
			case Leaf => None
			case Branch(k,_,_,_) => Some(k)
		}
	}

	def select(rank: Int): Option[Key] = {
		def loop(node: Node, k: Int): Node = {
			node match {
				case Leaf => Leaf
				case current @ Branch(_,_,left,right) =>
					val t = left.size
					if (t > k)  loop(left, k)
					else if (t < k) loop(right, k - t - 1)
					else current
			}
		}
		loop(root, rank) match {
			case Leaf => None
			case Branch(key,_,_,_) => Some(key)
		}
	}
	
	def rank(key: Key): Int = {
		def loop(key: Key, node: Node): Int = {
			node match {
				case Leaf => 0
				case current @ Branch(k,_,left,right) if key < k => loop(key, left)
				case current @ Branch(k,_,left,right) if key > k => 1 + left.size + loop(key, right)
				case current @ Branch(k,_,left,right) if key == k => left.size
			}
		}
		loop(key, root)
	}

	def keys(low: Key, high: Key): Iterable[Key] = {
		???
	}
	

}


object ImperativeBST {
  
  
}