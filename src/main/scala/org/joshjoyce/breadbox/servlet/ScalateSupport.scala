package org.joshjoyce.breadbox.servlet

import javax.servlet.ServletContext
import javax.servlet.http._

import org.fusesource.scalate.servlet._

/**
 * Adds support for Scalate templates to a servlet.
 */
trait ScalateSupport {
  
  def templateEngine(): ServletTemplateEngine
  def getServletContext(): ServletContext
  def request(): HttpServletRequest
  def response(): HttpServletResponse
    
  /**
   * Renders a template using the default layout.
   */
  def layoutTemplate(path: String, attributes: (String, Any)*) = {
    val renderContext = new ServletRenderContext(templateEngine, request, response, getServletContext)
    attributes.foreach { case (k, v) => renderContext.attributes(k) = v }
    templateEngine.layout(path, renderContext)
  }
}
