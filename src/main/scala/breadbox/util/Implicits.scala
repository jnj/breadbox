package breadbox.util

import scala.collection.jcl.Buffer

object Implicits {
  implicit def javaListToScalaList[T](l: java.util.List[T]) = Buffer(l) 
}
