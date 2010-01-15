import sbt._

class ProjectConfig(info: ProjectInfo) extends DefaultWebProject(info) {

  val lift = "net.liftweb" % "lift-core" % "1.0"
  val jetty6 = "org.mortbay.jetty" % "jetty" % "6.1.14"
  val servlet = "javax.servlet" % "servlet-api" % "2.5"
  val derby = "org.apache.derby" % "derby" % "10.2.2.0"

  // required because Ivy doesn't pull repositories from poms
  val smackRepo = "m2-repository-smack" at "http://maven.reucon.com/public"
}
