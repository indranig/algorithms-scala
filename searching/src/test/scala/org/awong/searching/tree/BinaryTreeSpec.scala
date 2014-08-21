package org.awong.searching.tree


import org.awong.AbstractWordSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class BinaryTreeSpec extends AbstractWordSpec {

	"A binary tree" should {
		"be able to create a 0-node empty tree" in {
			val tree = BinarySearchTree.empty
			tree.isEmpty should be (true)
			tree.isBalanced should be (true)
			tree.isValid should be (true)
			tree.contains(100) should be (false)
		}
		
		"be able to create a 1-node tree" in {
			val xs = Seq(1)
			val tree = BinarySearchTree(xs)
			tree.isEmpty should be (false)
			tree.isValid should be (true)
			tree.isBalanced should be (true)
			tree.left.isEmpty should be (true)
			tree.right.isEmpty should be (true)
			tree.size should be (xs.size)
			
			tree.contains(xs.head) should be (true)
		}
		
		"be able to create a 3-node tree and add/remove an element" in {
			var xs = Seq(2,1,3)
			var tree = BinarySearchTree(xs)
			tree.isEmpty should be (false)
			tree.isValid should be (true)
			tree.isBalanced should be (true)
			tree.size should be (xs.size)
			
			tree.contains(util.Random.shuffle(xs).head) should be (true)
			
			val add = 4
			xs = xs :+ add
			tree = tree.add(add)
			tree.size should be (xs.size)
			tree.contains(add) should be (true)
			tree.isValid should be (true)
			tree.isBalanced should be (true)
			
			tree = tree.remove(add)
			xs = xs.filterNot( each => each == add)
			tree.size should be (xs.size)
			
		}
		
		"have correct subtree functionality" in {
			var tree = BinarySearchTree(Seq(2,1,3))
			val subtree = tree.subtree(1)
			subtree.isEmpty should be (false)
			subtree.left.isEmpty should be (true)
			subtree.right.isEmpty should be (true)
			tree.isSubtree(BinarySearchTree.empty) should be (true)
			tree.isSubtree(subtree) should be (true)
			val totallyDifferent = BinarySearchTree(Seq(4))
			tree.isSubtree(totallyDifferent) should be (false)
		}
	}
}