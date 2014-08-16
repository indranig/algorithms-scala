package org.awong.graphs

object GraphsData extends org.awong.stdlib.ModuleData {
	def jobs: Seq[String] = {
		Seq(
			"Algorithms/Theoretical CS/Databases/Scientific Computing",
			"Introduction to CS/Advanced Programming/Algorithms",
			"Advanced Programming/Scientific Computing",
			"Scientific Computing/Computational Biology",
			"Theoretical CS/Computational Biology/Artificial Intelligence",
			"Linear Algebra/Theoretical CS",
			"Calculus/Linear Algebra",
			"Artificial Intelligence/Neural Networks/Robotics/Machine Learning",
			"Machine Learning/Neural Networks"
		)
	}
	
	type UnweightedEdge = (Int, Int)
	type WeightedEdge = (Int, Int, Double)
	
	sealed trait GraphInput {
		def nNodes: Int
		def nEdges: Int
	}
	case class UnweightedGraphInput(nNodes: Int, edges: Seq[UnweightedEdge]) extends GraphInput {
		def nEdges = edges.size
	}
	case class WeightedGraphInput(nNodes: Int, edges: Seq[WeightedEdge]) extends GraphInput {
		def nEdges = edges.size
	}
	
	def getUnweightedNodes(edges: Seq[UnweightedEdge]): Stream[Int] = {
		edges.foldLeft(Stream[Int]()){ case (accum, (left, right)) =>
			var result = accum
			if (!accum.contains(left)) {
				result = left #:: result 
			}
			if (!accum.contains(right)) {
				result = right #:: result 
			}
			result
		}
	}
	def getWeightedNodes(edges: Seq[WeightedEdge]): Stream[Int] = {
		edges.foldLeft(Stream[Int]()){ case (accum, (left, right, _)) =>
			var result = accum
			if (!accum.contains(left)) {
				result = left #:: result 
			}
			if (!accum.contains(right)) {
				result = right #:: result 
			}
			result
		}
	}
	
	def tinyCG: UnweightedGraphInput = {
		val edges = Seq(
			(0,5),
			(2,4),
			(2,3),
			(1,2),
			(0,1),
			(3,4),
			(3,5),
			(0,2)
		)
		UnweightedGraphInput(6,edges)
	}
	
	def tinyDAG: UnweightedGraphInput = {
		val edges = Seq(
			(2,3),
			(0,6),
			(0,1),
			(2,0),
			(11,12),
			(9,12),
			(9,10),
			(9,11),
			(3,5),
			(8,7),
			(5,4),
			(0,5),
			(6,4),
			(6,9),
			(7,6)
		)
		UnweightedGraphInput(13,edges)
	}
	
	def tinyDG: UnweightedGraphInput = {
		val edges = Seq(
			(4,2),
			(2,3),
			(3,2),
			(6,0),
			(0,1),
			(2,0),
			(11,12),
			(12,9),
			(9,10),
			(9,11),
			(7,9),
			(10,12),
			(11,4),
			(4,3),
			(3,5),
			(6,8),
			(8,6),
			(5,4),
			(0,5),
			(6,4),
			(6,9),
			(7,6)
		)
		UnweightedGraphInput(13, edges)
	}
	
	def tinyEWD: WeightedGraphInput = {
		val edges = Seq(
			(4,5,0.35),
			(5,4,0.35),
			(4,7,0.37),
			(5,7,0.28),
			(7,5,0.28),
			(5,1,0.32),
			(0,4,0.38),
			(0,2,0.26),
			(7,3,0.39),
			(1,3,0.29),
			(2,7,0.34),
			(6,2,0.40),
			(3,6,0.52),
			(6,0,0.58),
			(6,4,0.93)
		)
		WeightedGraphInput(8, edges)
	}
	
	def tinyEWDAG: WeightedGraphInput = {
		val edges = Seq(
			(5,4,0.35),
			(4,7,0.37),
			(5,7,0.28),
			(5,1,0.32),
			(4,0,0.38),
			(0,2,0.26),
			(3,7,0.39),
			(1,3,0.29),
			(7,2,0.34),
			(6,2,0.40),
			(3,6,0.52),
			(6,0,0.58),
			(6,4,0.93)
		)
		WeightedGraphInput(8, edges)
	}
	
	def tinyEWDn: WeightedGraphInput = {
		val edges = Seq(
			(4,5,0.35),
			(5,4,0.35),
			(4,7,0.37),
			(5,7,0.28),
			(7,5,0.28),
			(5,1,0.32),
			(0,4,0.38),
			(0,2,0.26),
			(7,3,0.39),
			(1,3,0.29),
			(2,7,0.34),
			(6,2,-1.20),
			(3,6,0.52),
			(6,0,-1.40),
			(6,4,-1.25)
		)
		WeightedGraphInput(8, edges)
	}
	
	def tinyEWDnc: WeightedGraphInput = {
		val edges = Seq(
			(4,5,0.35),
			(5,4,-0.66),
			(4,7,0.37),
			(5,7,0.28),
			(7,5,0.28),
			(5,1,0.32),
			(0,4,0.38),
			(0,2,0.26),
			(7,3,0.39),
			(1,3,0.29),
			(2,7,0.34),
			(6,2,0.40),
			(3,6,0.52),
			(6,0,0.58),
			(6,4,0.93)
		)
		WeightedGraphInput(8, edges)
	}
	
	def tinyEWG: WeightedGraphInput = {
		val edges = Seq(
			(4,5,0.35),
			(4,7,0.37),
			(5,7,0.28),
			(0,7,0.16),
			(1,5,0.32),
			(0,4,0.38),
			(2,3,0.17),
			(1,7,0.19),
			(0,2,0.26),
			(1,2,0.36),
			(1,3,0.29),
			(2,7,0.34),
			(6,2,0.40),
			(3,6,0.52),
			(6,0,0.58),
			(6,4,0.93)
		)
		WeightedGraphInput(8, edges)
	}
	
	def tinyG: UnweightedGraphInput = {
		val edges = Seq(
			(0,5),
			(4,3),
			(0,1),
			(9,12),
			(6,4),
			(5,4),
			(0,2),
			(11,12),
			(9,10),
			(0,6),
			(7,8),
			(9,11),
			(5,3)
		)
		UnweightedGraphInput(13, edges)
	}
	
	def jobsPC: Seq[String] = {
		Seq(
			"10",
			"41.0  3  1 7 9",
			"51.0  1  2",
			"50.0  0",
			"36.0  0",
			"38.0  0",
			"45.0  0",
			"21.0  2  3 8",
			"32.0  2  3 8",
			"32.0  1  2",
			"29.0  2  4 6"
		)
	}
	
	def rates: Seq[String] = {
		Seq(
			"5",
			"USD 1      0.741  0.657  1.061  1.005",
			"EUR 1.349  1      0.888  1.433  1.366",
			"GBP 1.521  1.126  1      1.614  1.538",
			"CHF 0.942  0.698  0.619  1      0.953",
			"CAD 0.995  0.732  0.650  1.049  1"
		)
	}
	
	def routes: Seq[String] = {
		Seq(
			"JFK MCO",
			"ORD DEN",
			"ORD HOU",
			"DFW PHX",
			"JFK ATL",
			"ORD DFW",
			"ORD PHX",
			"ATL HOU",
			"DEN PHX",
			"PHX LAX",
			"JFK ORD",
			"DEN LAS",
			"DFW HOU",
			"ORD ATL",
			"LAS LAX",
			"ATL MCO",
			"HOU MCO",
			"LAS PHX"
		)
	}
	
	
	def mediumEWD: Seq[String] = {
		resourceAsStrings("mediumEWD.txt")
	}
	
	def mediumEWG: Seq[String] = {
		resourceAsStrings("mediumEWG.txt")
	}
	def mediumG: Seq[String] = {
		resourceAsStrings("mediumG.txt")
	}
	
	def movies = {
		org.awong.searching.SearchingData.movies
	}
}