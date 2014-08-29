package org.awong

import scala.collection.mutable.Buffer

package object sorting {
	def swap[T](a: Array[T], i: Int, j: Int): Array[T] = {
		val temp = a(i)
		a(i) = a(j)
		a(j) = temp
		a
	}
	
	object MergeBU
	object Quick3way
	
}