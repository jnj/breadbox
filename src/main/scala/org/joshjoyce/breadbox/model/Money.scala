package org.joshjoyce.breadbox.model

import scala.math.abs

object Money {
  def apply(cents: Int): Money = {
    val absCents = abs(cents)
    val dollars = absCents / 100
    val change = absCents % 100
    val sign = if (cents < 0) -1 else 1
    Money(sign * dollars, change)
  }
}

case class Money(dollars: Int, cents: Int) {
  def asCents = (dollars * 100) + cents
}

