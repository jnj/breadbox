package org.joshjoyce.breadbox.log

import org.apache.log4j.Logger

trait Logging {
  lazy val log = Logger.getLogger(getClass)
}
