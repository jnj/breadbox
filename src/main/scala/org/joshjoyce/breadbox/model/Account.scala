package org.joshjoyce.breadbox.model

class Account {
  type AccountType = String

  var name: String = _
  var number: String = _
  var accountType: AccountType = _
  var transactions: Traversable[Transaction] = _
}
