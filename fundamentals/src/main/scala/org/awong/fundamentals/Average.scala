package org.awong.fundamentals

object Average {
	def average(doubles: Seq[Double]): Option[AverageResult] = {
		if (!doubles.isEmpty) {
			val size = doubles.size
			val sum = doubles.sum
			val average = sum/size
			Some(AverageResult(size, sum, average))
		} else {
			None
		}
	}
	
	case class AverageResult(number: Int, sum: Double, average: Double)
}