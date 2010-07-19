package org.joshjoyce.breadbox.model

object AccountType {
  def apply(name: String) = {
    name match {
      case "Checking" => Checking()
      case "Savings" => Savings()
      case _ => throw new IllegalArgumentException(name);
    }
  }
}

abstract class AccountType
case class Checking() extends AccountType
case class Savings() extends AccountType

class Account {
  var name: String = _
  var number: String = _
  var accountType: AccountType = _
}
