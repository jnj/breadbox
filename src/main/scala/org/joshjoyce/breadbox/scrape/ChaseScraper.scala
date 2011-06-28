package org.joshjoyce.breadbox.scrape

import com.zenkey.net.prowser._
import java.io._
import org.w3c.tidy._

class ChaseScraper {
  
  def go() {
    val res = visitUrl("http://www.chase.com")
    val html = res.getPageSource
    parse(html)
  }

  private def visitUrl(url: String) = {
    val p = new Prowser
    val t = p.createTab
    val res = t.go(url)
    res
  }
  
  private def parse(html: String) = {
    val is = new ByteArrayInputStream(html.getBytes("UTF-8"))
    val tidy = new Tidy
    val dom = tidy.parseDOM(is, null)
    println(dom)
  }
}
