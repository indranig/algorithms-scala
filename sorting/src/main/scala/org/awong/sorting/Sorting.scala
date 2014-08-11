package org.awong.sorting

trait Sorting {
	type A
	
	def sortWith(xs: Seq[A])(lt: (A, A) => Boolean): Seq[A] = sort(xs)(Ordering fromLessThan lt)

	def sortBy[B](xs: Seq[A])(f: A => B)(implicit ord: Ordering[B]): Seq[A] = sort(xs)(ord on f)

	def sort[B >: A](xs: Seq[A])(implicit ord: Ordering[B]): Seq[A]
}