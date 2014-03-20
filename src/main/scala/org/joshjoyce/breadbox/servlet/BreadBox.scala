package org.joshjoyce.breadbox.servlet

import org.scalatra.ScalatraServlet

class BreadBox extends ScalatraServlet {
  get("/") {
    <html>
      <body>
        <h1>Welcome to Breadbox!</h1>
      </body>
    </html>
  }
}
