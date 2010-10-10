package org.joshjoyce.breadbox

import org.apache.commons.httpclient._
import org.apache.commons.httpclient.methods._

object WebClient {
  def apply() = {
    val wc = new WebClient
    wc.go
  }
}

class WebClient {
  def go = {
    val client = new HttpClient
    var getMethod = new GetMethod("http://www.chase.com")
    client.executeMethod(getMethod)
    val responseBody = getMethod.getResponseBody
    println(responseBody)
  }
}
