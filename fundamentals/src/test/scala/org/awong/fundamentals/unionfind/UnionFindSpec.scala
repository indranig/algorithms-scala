package org.awong.fundamentals.unionfind


import org.awong.fundamentals.FundamentalsData
import org.awong.fundamentals.FundamentalsData._

import org.awong.AbstractWordSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class UnionFindSpec extends AbstractWordSpec {

	def test(uf: UnionFind, data: UnionFindDTO, expectedCount: Int): Unit = {
		data.pairs.foreach{ case (p,q) =>
			uf.union(p, q)
		}
		uf.count should be (expectedCount)
		val randomPair = scala.util.Random.shuffle(data.pairs).head
		uf.connected(randomPair._1, randomPair._2) should be (true)
	}
	
	"QuickFindUF" should {
		"count components in a tiny digraph correctly" in {
			val data = FundamentalsData.tinyUnionFind
			val uf = new QuickFindUF(data.n)
			test(uf, data, 2)
		}
		"count components in a medium digraph correctly" in {
			val data = FundamentalsData.mediumUnionFind
			val uf = new QuickFindUF(data.n)
			test(uf, data, 3)
		}
	}
	"QuickUnionUF" should {
		"count components in a tiny digraph correctly" in {
			val data = FundamentalsData.tinyUnionFind
			val uf = new QuickUnionUF(data.n)
			test(uf, data, 2)
		}
		"count components in a medium digraph correctly" in {
			val data = FundamentalsData.mediumUnionFind
			val uf = new QuickUnionUF(data.n)
			test(uf, data, 3)
		}
	}
	"WeightedQuickUnionUF" should {
		"count components in a tiny digraph correctly" in {
			val data = FundamentalsData.tinyUnionFind
			val uf = new WeightedQuickUnionUF(data.n)
			test(uf, data, 2)
		}
		"count components in a medium digraph correctly" in {
			val data = FundamentalsData.mediumUnionFind
			val uf = new WeightedQuickUnionUF(data.n)
			test(uf, data, 3)
		}
	}
	"WeightedQuickUnionCompressionUF" should {
		"count components in a tiny digraph correctly" in {
			val data = FundamentalsData.tinyUnionFind
			val uf = new WeightedQuickUnionCompressionUF(data.n)
			test(uf, data, 2)
		}
		"count components in a medium digraph correctly" in {
			val data = FundamentalsData.mediumUnionFind
			val uf = new WeightedQuickUnionCompressionUF(data.n)
			test(uf, data, 3)
		}
	}

}