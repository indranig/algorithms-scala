package org.awong.searching

import org.awong.AbstractWordSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class SearchingSpec extends AbstractWordSpec {
  
  
	def testSearchTree(symbolTable: SymbolTable[String,Int], lines: Iterable[String], minLength: Int)
		(expectedWord: String, expectedCount: Int): Unit =
	{
		def countWords(): SymbolTable[String,Int] = {
			val words = for (line <- lines; word <- line.split("\\s") if word.length > minLength) yield word
			words.foldLeft(symbolTable){ case(st,word) =>
				if (st.contains(word)) {
					val maybeCount = st.get(word)
					st.put(word,  maybeCount.get + 1)
					st
				} else {
					st.put(word,1)
					st
				}
			}
		}
		val wordCount = countWords()
		// now find word with maximum count
		val tuples = for (word <- wordCount.keys) yield (word, wordCount.get(word).get)
		val map = tuples.toMap
		val maxTuple = map.maxBy{ case(word,count) => count }
		maxTuple._1 should be (expectedWord)
		maxTuple._2 should be (expectedCount)
	}
	
	"This" should {
		"read medTale correctly" in {
			SearchingData.medTale.size should be (99)
		}
		"read tale.txt correctly" in {
			SearchingData.tale should have size (16039)
		}
		"read movies.txt correctly" in {
			SearchingData.movies should have size (4188)
		}
		
	}
	
	"SequentialSearchBST" should {
		"count a tinyTale.txt" in {
			fail()
		}
	}
}