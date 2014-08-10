package org.awong

import scala.collection.mutable.Buffer

package object sorting {
	def swap[T](a: Array[T], i: Int, j: Int): Array[T] = {
		val temp = a(i)
		a(i) = a(j)
		a(j) = temp
		a
	}
	
	object IndexMaxPQ
	object IndexMinPQ
	object MaxPQ
	object MergeBU
	object MinPQ
	object Multiway
	object Quick3way
	object TopM
}