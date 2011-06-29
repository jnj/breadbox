package org.joshjoyce.breadbox.db
import org.joshjoyce.breadbox.log.Logging

import com.mongodb._

import org.joshjoyce.breadbox.model._

import scala.collection.JavaConversions._

class MongoDatabase(val host: String) extends Logging {
  
  private val mongo = new Mongo(host)
  private val db = mongo.getDB("breadbox")

  private val ACCT_COLL_KEY = "accounts"
  private val TXN_COLL_KEY = "transactions"

  def findAllAccounts() = {
    val cursor = db.getCollection(ACCT_COLL_KEY).find()
    cursor.iterator.map(docToAccount(_)).toList
  }

  def findTransactionsFor(account: Account) = {
    val collection = db.getCollection(TXN_COLL_KEY)
    val queryObj = new BasicDBObjectBuilder
    queryObj.append("accountNumber", account.number)
    val cursor = collection.find(queryObj.get)
    cursor
  }

  def delete(account: Account) {
    val doc = new BasicDBObject
    doc.put("name", account.name)
    doc.put("number", account.number)
    doc.put("accountType", account.accountType)
    log.info("deleting account %s".format(doc.toString))
    db.getCollection(ACCT_COLL_KEY).remove(doc)
  }
  
  def save(account: Account) {
    val doc = new BasicDBObject()
    doc.put("name", account.name)
    doc.put("number", account.number)
    doc.put("accountType", account.accountType)
    db.getCollection(ACCT_COLL_KEY).insert(doc)
  }
  
  def docToAccount(doc: DBObject): Account = {
    val account = new Account
    account.name = doc.get("name").asInstanceOf[String]
    account.number = doc.get("number").asInstanceOf[String]
    account.accountType = doc.get("accountType").asInstanceOf[String]
    account
  }
}
