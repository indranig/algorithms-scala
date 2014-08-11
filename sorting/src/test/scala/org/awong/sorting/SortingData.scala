package org.awong.sorting

import scala.Array.canBuildFrom

object SortingData {
	private def split(xf: String): Array[String] = {
		xf.split(" ")
	}

	def m1: Array[String] = {
		split("A B C F G I I Z")
	}
	
	def m2: Array[String]= {
		split("B D H P Q Q")
	}
	
	def m3: Array[String] = {
		split("A B E F J N")
	}
	
	def tiny: Array[String] = {
		split("S O R T E X A M P L E")
	}
	
	def tinyPQ: Array[String] = {
		split("P Q E - X A M - P L E -")
	}
	def words3: Array[String] = {
		val lines = Array("bed bug dad yes zoo",
				"now for tip ilk dim",
				"tag jot sob nob sky",
				"hut men egg few jay",
				"owl joy rap gig wee",
				"was wad fee tap tar",
				"dug jam all bad yet")
		val words = for (l <- lines; w <- split(l)) yield w
		words
	}
}

