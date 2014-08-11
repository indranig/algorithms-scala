package org.awong.fundamentals

object FundamentalsData {
	def testTransactions: Seq[Transaction] = {
		Seq(
			Transaction("Turing", "6/17/1990", 655.08),
			Transaction("vonNeumann", "3/26/2002 ", 4121.85),
			Transaction("vonNeumann","3/26/2002",4121.85),
			Transaction("Dijkstra","8/22/2007",2678.40),
			Transaction("vonNeumann","1/11/1999",4409.74),
			Transaction("Dijkstra","11/18/1995",837.42),
			Transaction("Hoare","5/10/1993",3229.27),
			Transaction("vonNeumann ","2/12/1994",4732.35),
			Transaction("Hoare","8/18/1992",4381.21),
			Transaction("Turing","1/11/2002",66.10),
			Transaction("Thompson","2/27/2000",4747.08),
			Transaction("Turing","2/11/1991",2156.86),
			Transaction("Hoare","8/12/2003",1025.70),
			Transaction("vonNeumann","10/13/1993",2520.97),
			Transaction("Dijkstra", "9/10/2000", 708.95),
			Transaction("Turing", "10/12/1993", 3532.36),
			Transaction("Hoare", "2/10/2005", 4050.20)
		)
	}
	
}