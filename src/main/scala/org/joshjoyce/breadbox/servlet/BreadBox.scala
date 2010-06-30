package org.joshjoyce.breadbox.servlet

import com.thinkminimo.step.Step

import org.fusesource.scalate.servlet._

class BreadBox extends Step with ScalateSupport {
  
  lazy val templateEngine = configureTemplateEngine
  
  override def getServletContext() = super.getServletContext
  override def request() = super.request
  override def response() = super.response

  before {
    contentType = "text/html"
  }
  
  get("/") {
    <h1>Test Succeeded</h1>
  }
  
  private def configureTemplateEngine() = {
    val engine = new ServletTemplateEngine(getServletConfig())
    engine
  }
}

