package org.joshjoyce.breadbox.model

import com.mongodb.DBObject

import org.joda.time.LocalDate

object Transaction {
   def apply(account: Account, obj: DBObject) = {
     val rawAmount = obj.get("amount").asInstanceOf[Int]
     val date = new LocalDate(obj.get("date").asInstanceOf[Long])
     val accountNumber = obj.get("accountNumber").asInstanceOf[String]
     val desc = if (obj.containsField("description")) Some(obj.get("description").asInstanceOf[String]) else None
     new Transaction(account, Money(rawAmount), date, desc)
   }
}

case class Transaction(account: Account, amount: Money, date: LocalDate, description: Option[String])

case class Transfer(debit: Debit, credit: Credit)

