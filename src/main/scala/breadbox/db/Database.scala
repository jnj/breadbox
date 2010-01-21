package breadbox.db

import net.liftweb.http._ 
import net.liftweb.util._ 
import net.liftweb.http.Bootable

import scala.xml._ 

import Helpers._ 
import S._ 

import com.mongodb._
import breadbox.util.Implicits._

object MongoConnectionFactory {
  def connect(address: String, port: Int, name: String) = {
    val db = Mongo.connect(new DBAddress(address, port, name))
    new MongoConnection(db)
  }
}

class MongoConnection(private val db: DB) {
  def accounts = {
    db.getCollection("accounts").find().toArray
  }
}
