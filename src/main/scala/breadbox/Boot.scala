package breadbox

import net.liftweb.http.Bootable
import _root_.net.liftweb.http._ 
import S._ 
import _root_.net.liftweb.util._ 
import Helpers._ 
import _root_.scala.xml._ 

class Boot extends Bootable {
  def boot {
    LiftRules.addToPackages("breadbox")
  }
}
