package org.awong.sorting

object Shell {

	def sort[T](xs: Array[T])(implicit ordering: Ordering[T]): Array[T] = {
		import ordering._
		
		def incSeq(len:Int): Iterator[Int] =new Iterator[Int] {
			private[this] var x:Int=len/2
			def hasNext=x>0
			def next()={x=if (x==2) 1 else x*5/11; x}
		}
		def insertionSort(a:Array[T], inc:Int)={
			for (i <- inc until a.length; temp=a(i)){
				var j=i;
				while (j>=inc && a(j-inc) > temp) {
					a(j)=a(j-inc)
					j=j-inc
				}
				a(j)=temp
			}
		}
		
		var a = xs
		for (inc<-incSeq(a.length)) {
			insertionSort(a, inc)
		}
		a
	}
}