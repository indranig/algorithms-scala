package org.awong.context

object ContextData {
	def abra: String = {
		"ABRACADABRA!"
	}
	
	def tinyFN: Seq[String] = {
		Seq(
			"6",
			"8",
			"0 1 2.0",
			"0 2 3.0",
			"1 3 3.0",
			"1 4 1.0",
			"2 3 1.0",
			"2 4 1.0",
			"3 5 2.0",
			"4 5 3.0"
		)
	}
	
	def tinyTale: Seq[String] = {
		org.awong.searching.SearchingData.tinyTale
	}
	def tale: Stream[String] = {
		org.awong.searching.SearchingData.tale
	}
}