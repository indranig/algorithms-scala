package org.awong.beyond


object BeyondData extends org.awong.stdlib.ModuleData {
	
	type Pair = (Int,Int)
	
	case class BeyondInput(pairs: Seq[Pair]) {
		def nPairs = pairs.size
	}
	
	def rs1423: BeyondInput = {
		val lines = super.resourceAsStringStream("rs1423.txt")
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