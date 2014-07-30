package org.awong.fundamentals

/*************************************************************************
 *  Compilation:  javac Counter.java
 *  Execution:    java Counter N T
 *  Dependencies: StdRandom.java StdOut.java
 *
 *  A mutable data type for an integer counter.
 *
 *  The test clients create N counters and performs T increment
 *  operations on random counters.
 *
 *  % Counter.test(6, 600000)
 *  0: 99870
 *  1: 99948
 *  2: 99738
 *  3: 100283
 *  4: 100185
 *  5: 99976
 *
 *************************************************************************/
case class Counter(name: String, count: Int = 0) extends math.Ordered[Counter] {
	def increment: Counter = Counter(name, count + 1)
	
	def tally: Int = count
	
	def compare(that: Counter): Int = {
		if (this.count < that.count) {
			-1
		} else if (this.count > that.count) {
			+1;
		} else {
			0
		}
	}
	
	override def toString: String = {
		 count + " " + name;
	}
}

object Counter {
	def apply(name: String): Counter = new Counter(name, 0)
	
	def test(n: Int, t: Int): Seq[Counter] = {
		???
	}
}