package org.joshjoyce.breadbox.servlet

import org.joshjoyce.breadbox.db._
import org.joshjoyce.breadbox.model._

import org.scalatra._
import org.scalatra.scalate._
import org.fusesource.scalate._

import org.slf4j.{Logger, LoggerFactory}

class BreadBox extends ScalatraServlet with ScalateSupport {
  
  private val log = LoggerFactory.getLogger(getClass())
  private val database = new MongoDatabase("localhost")
  
  before {
    contentType = "text/html"
  }
  
  get("/") {
    val accounts = database.findAllAccounts
    go("index.scaml", ("accounts", accounts))
  }
  
  get("/newaccount") {
    val account = new Account
    go("newaccount.scaml", ("it", account))
  }
                          
  post("/saveaccount") {
    val account = new Account
    account.name = params("name")
    account.number = params("number")
    account.accountType = params("type")
    database.save(account)
    response.sendRedirect("/")
  }

  post("/deleteaccount") {
    val account = new Account
    account.name = params("name")
    account.number = params("number")
    account.accountType = params("type")
    database.delete(account)
    response.sendRedirect("/")
  }
    
  private def forward(uri: String) = {
    servletContext.getRequestDispatcher(uri).forward(request, response)
  }

  private def go(page: String, bindings: (String, Any)*) {
    val ctxt = createRenderContext
    bindings.foreach { case (k, v) => ctxt.attributes(k) = v }
    templateEngine.layout("/WEB-INF/%s".format(page), ctxt)
  }
}
