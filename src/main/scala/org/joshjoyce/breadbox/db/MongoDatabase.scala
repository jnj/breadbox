package org.joshjoyce.breadbox.db

import com.mongodb._

import org.joshjoyce.breadbox.model._

import scala.collection.JavaConversions._


class MongoDatabase(val host: String) extends Database {
  
  private val mongo = new Mongo(host)
  private val db = mongo.getDB("breadbox")

  private val ACCT_COLL_KEY = "accounts"

  override def findAllAccounts() = {
    val cursor = db.getCollection(ACCT_COLL_KEY).find()
    cursor.iterator.map(docToAccount(_)).toList
  }
  
  override def save(account: Account) = {
    val doc = new BasicDBObject()
    doc.put("name", account.name)
    doc.put("number", account.number)
    doc.put("accountType", account.accountType.getClass.getSimpleName)
    db.getCollection(ACCT_COLL_KEY).insert(doc)
  }
  
  private def docToAccount(doc: DBObject): Account = {
    val account = new Account
    account.name = doc.get("name").asInstanceOf[String]
    account.number = doc.get("number").asInstanceOf[String]
    account.accountType = AccountType(doc.get("accountType").asInstanceOf[String])
    account
  }
}
