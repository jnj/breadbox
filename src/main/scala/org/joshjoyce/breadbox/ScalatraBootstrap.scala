package org.joshjoyce.breadbox

import javax.servlet.ServletContext
import org.joshjoyce.breadbox.servlet.BreadBox
import org.scalatra.LifeCycle

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    context.mount(new BreadBox, "/*")
  }
}
