package org.awong.fundamentals

case class Transaction(who: String, when: java.util.Date, amount: Double)

object Transaction {
	def apply(who: String, whenStr: String, amount: Double): Transaction = {
		val sdf = new java.text.SimpleDateFormat("MM/dd/yyyy")
		val when = sdf.parse(whenStr)
		Transaction(who, when, amount)
	}
	
}
