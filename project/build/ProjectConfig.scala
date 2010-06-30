import sbt._

class ProjectConfig(info: ProjectInfo) extends DefaultWebProject(info) {
  val snapsRepo = "Nexus Snapshots Repository" at "http://repo.fusesource.com/nexus/content/repositories/snapshots"
  val scalate = "org.fusesource.scalate" % "scalate-core" % "1.2-SNAPSHOT"
  val jetty7 = "org.eclipse.jetty" % "jetty-webapp" % "7.0.2.RC0" % "test"
  val servlet = "javax.servlet" % "servlet-api" % "2.5" % "provided" 
  val mongo = "org.mongodb" % "mongo-java-driver" % "1.4"
  val logback = "ch.qos.logback" % "logback-classic" % "0.9.18"
  val slf4j = "org.slf4j" % "slf4j-api" % "1.5.6"
}
