import sbt._

class ProjectConfig(info: ProjectInfo) extends DefaultWebProject(info) {

  val lift = "net.liftweb" % "lift-core" % "1.0.2" % "compile"
  val jetty6 = "org.mortbay.jetty" % "jetty" % "6.1.14" % "test"
  val servlet = "javax.servlet" % "servlet-api" % "2.5" % "provided" 

  // required because Ivy doesn't pull repositories from poms
  val smackRepo = "m2-repository-smack" at "http://maven.reucon.com/public"
}
