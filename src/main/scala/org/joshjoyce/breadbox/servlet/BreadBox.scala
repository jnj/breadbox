package org.joshjoyce.breadbox.servlet

import org.scalatra.scalate.ScalateSupport
import org.scalatra.ScalatraServlet

class BreadBox extends ScalatraServlet with ScalateSupport {
  
  before {
    contentType = "text/html"
  }
  
  get("/") {
    val renderContext = createRenderContext
    templateEngine.layout("/WEB-INF/index.scaml", renderContext)
  }
}

