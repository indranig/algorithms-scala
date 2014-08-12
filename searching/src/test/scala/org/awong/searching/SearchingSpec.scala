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
	
	"SequentialSearchBST" should {
		"count a tinyTale.txt" in {
			fail()
		}
	}
	"This" should {
		"pass a trivial test" in {
			1 should equal (1 + 0)
		}
	}
	"This" should {
		"fail a trivial test" in {
			fail
		}
	}
}