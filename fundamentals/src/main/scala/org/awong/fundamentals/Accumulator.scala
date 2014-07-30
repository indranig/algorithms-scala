package org.awong.fundamentals

case class Accumulator(val n: Int = 0, total: Double = 0.0) {
	def +(value: Double): Accumulator = {
		Accumulator(n + 1, total + value)
	}
	
	def mean: Double = {
		total / n
	}
	
	def print: String = {
		// val xf = String.format("%7.5f", mean)
		val msg = "Mean (" + n + " values): " + mean.toString
		msg
	 }
}

object Accumulator {
	def apply(): Accumulator = Accumulator(0, 0.0)
}