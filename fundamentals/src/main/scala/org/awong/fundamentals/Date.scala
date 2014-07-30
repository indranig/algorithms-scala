package org.awong.fundamentals

case class Date(month: Int, day: Int, year: Int) extends java.lang.Comparable[Date] {
	def next: Date = {
		if (Date.isValid(month, day + 1, year)){
			new Date(month, day + 1, year);
		}
		else if (Date.isValid(month + 1, 1, year)) {
			new Date(month + 1, 1, year);
		}
		else {
			new Date(1, 1, year + 1);
		}
	}


	// is this Date after b?
	def isAfter(b: Date): Boolean = {
		compareTo(b) > 0
	}

	// is this Date a before b?
	def isBefore(b: Date): Boolean  = {
		compareTo(b) < 0
	}

	// compare this Date to that one
	def compareTo(that: Date): Int = {
		if (this.year  < that.year){
			-1
		}
		if (this.year  > that.year){
			1
		}
		if (this.month < that.month) {
			-1
		}
		if (this.month > that.month) {
			+1
		}
		if (this.day   < that.day){
			-1
		}
		if (this.day   > that.day){
			+1
		}
		return 0;
	}

	// return a string representation of this date
	override def toString: String  ={
		month + "/" + day + "/" + year;
	}

	// is this Date equal to x?
	override def equals(x: Any): Boolean = {
		if (x == this) {
			true
		}
		if (x == null) {
			false
		}
		x match {
			case that: Date =>
				(this.month == that.month) && (this.day == that.day) && (this.year == that.year)
			case _ =>
				false
		}
	}

	override def hashCode(): Int = {
		var hash = 17
		hash = 31*hash + month;
		hash = 31*hash + day;
		hash = 31*hash + year;
		hash;
	}	
}

object Date {
	lazy val DAYS = Array[Int](0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
	
	def create(m: Int, d: Int, y: Int): Option[Date] = {
		if (isValid(m, d, y)) {
			Some(new Date(m,d,y))
		} else {
			None
		}
	}
	
	private def isValid(m: Int, d: Int, y: Int): Boolean = {
		if (m < 1 || m > 12) {
			false
		}
		if (d < 1 || d > DAYS(m)) {
			false
		}
		if (m == 2 && d == 29 && !isLeapYear(y)) {
			false
		}
		true
	}
	
	private def isLeapYear(y: Int): Boolean = {
		if (y % 400 == 0) {
			true;
		}
		if (y % 100 == 0) {
			false
		} 
		y % 4 == 0;
	}
}