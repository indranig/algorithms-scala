package org.awong.searching

object SearchingData {
	def tinyST: String = {
		"""S E A R C H E X A M P L E"""
	}
	
	def tinyTale: Seq[String] = {
		Seq(
			"it was the best of times it was the worst of times",
			"it was the age of wisdom it was the age of foolishness",
			"it was the epoch of belief it was the epoch of incredulity",
			"it was the season of light it was the season of darkness",
			"it was the spring of hope it was the winter of despair"
		)
	}
	
	def tale: Stream[String] = {
		org.awong.stdlib.StdIO.readStrings("tale.txt"::Nil)
	}
	
	def listTxt: String = {
		"was it the of"
	}
	
	def ex1: String = {
		"it was the best of times"
	}
	def ex2: String = {
		"it was the worst of times"
	}
	def ex3: String = {
		"it was the age of wisdom"
	}
	def ex4: String = {
		"it was the age of foolishness"
	}
	def aminol: Seq[String] = {
		Seq(
			"Alanine,AAT,AAC,GCT,GCC,GCA,GCG",
			"Arginine,CGT,CGC,CGA,CGG,AGA,AGG",
			"Aspartic Acid,GAT,GAC",
			"Cysteine,TGT,TGC",
			"Glutamic Acid,GAA,GAG",
			"Glutamine,CAA,CAG",
			"Glycine,GGT,GGC,GGA,GGG",
			"Histidine,CAT,CAC",
			"Isoleucine,ATT,ATC,ATA",
			"Leucine,TTA,TTG,CTT,CTC,CTA,CTG",
			"Lysine,AAA,AAG",
			"Methionine,ATG",
			"Phenylalanine,TTT,TTC",
			"Proline,CCT,CCC,CCA,CCG",
			"Serine,TCT,TCA,TCG,AGT,AGC",
			"Stop,TAA,TAG,TGA",
			"Threonine,ACT,ACC,ACA,ACG",
			"Tyrosine,TAT,TAC",
			"Tryptophan,TGG",
			"Valine,GTT,GTC,GTA,GTG"
		)
	}
}