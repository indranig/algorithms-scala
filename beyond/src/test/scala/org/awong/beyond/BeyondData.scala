package org.awong.beyond

object BeyondData {
	type Pair = (Int,Int)
	
	case class BeyondInput(pairs: Seq[Pair]) {
		def nPairs = pairs.size
	}
	
	def rs1423: BeyondInput = {
		val lines = org.awong.stdlib.StdIO.readStrings("rs1423.txt":: Nil)
		val pairs = lines.map{ line =>
			val tokens = line.split("\\s",2)
			tokens match {
				case Array(first, second) =>
					(first.toInt, second.toInt)
			}
		}
		BeyondInput(pairs)
	}
}