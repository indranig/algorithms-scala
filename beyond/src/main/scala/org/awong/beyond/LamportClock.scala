package org.awong.beyond

/**
 * An alternative to using time since the epoch for synchronizing distributed systems.
 * 
 * @see http://the-paper-trail.org/blog/distributed-systems-theory-for-the-distributed-systems-engineer/
 */
case class LamportClock(value: Int = 1) {
	def increment(): LamportClock = {
		LamportClock(value + 1)
	}
	def merge(other: LamportClock): LamportClock = {
		LamportClock(value.max(other.value) + 1)
	}
}

object VectorClock {
	def apply[N](nodes: Iterable[N]): VectorClock[N] = {
		val vector = (for (n <- nodes) yield (n, LamportClock())).toMap
		VectorClock(vector)
	}
}

case class VectorClock[N](vector: Map[N, LamportClock] = Map[N,LamportClock]()) {
	def value(node: N): Option[Int] = {
		for (clock <- vector.get(node)) yield clock.value
	}
	
	private def adjust[A,B](m: Map[A,B], key: A)(f: B=>B): Map[A,B] = m.updated(key, f(m(key)))
	
	def increment(node: N): VectorClock[N] = {
		vector.get(node) match {
			case None => {
				this
			}
			case Some(clock) => {
				val newVectors = adjust(vector, node){ _.increment() }
				VectorClock(newVectors)
			}
		}
	}
	
	def merge(that: VectorClock[N]): VectorClock[N] = {
		val unionNodes = this.vector.keySet union that.vector.keySet
		
		val newVector = unionNodes.foldLeft(Map[N,LamportClock]()){ case(newMap, eachNode) =>
			this.vector.get(eachNode) match {
				case None =>
					that.vector.get(eachNode) match {
						case None =>
							sys.error("This should never happen to union nodes")
							newMap
						case Some(thatClock) =>
							newMap + (eachNode -> thatClock)
					}
				case Some(thisClock) =>
					that.vector.get(eachNode) match {
						case None =>
							newMap + (eachNode -> thisClock)
						case Some(thatClock) =>
							newMap + (eachNode -> LamportClock(thisClock.value.max(thatClock.value)))
					}
			}
		}
		VectorClock(newVector)
	}
}