package org.joshjoyce.breadbox.db

import org.joshjoyce.breadbox.model._

trait Database {

  def findAllAccounts(): Traversable[Account]
  def save(account: Account)
}

