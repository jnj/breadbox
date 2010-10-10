import sbt._

class ProjectConfig(info: ProjectInfo) extends DefaultWebProject(info) {
  val snapsRepo = "Nexus Snapshots Repository" at "http://repo.fusesource.com/nexus/content/repositories/snapshots"
  val sonatype = "Sonatype repo" at "http://repository.sonatype.org/content/repositories/central/"
  
  val scalatraCore = "org.scalatra" % "scalatra-project_2.8.0" % "2.0.0.M1"
  val scalatraScalate = "org.scalatra" % "scalatra-scalate_2.8.0" % "2.0.0.M1"
  val scalate = "org.fusesource.scalate" % "scalate-core" % "1.4-SNAPSHOT"

  val jetty7 = "org.eclipse.jetty" % "jetty-webapp" % "7.0.2.RC0" % "test"
  val servlet = "javax.servlet" % "servlet-api" % "2.5" % "provided" 
  val mongo = "org.mongodb" % "mongo-java-driver" % "1.4"
  val log4j = "log4j" % "log4j" % "1.2.16"
  val slf4jLog4j = "org.slf4j" % "slf4j-log4j12" % "1.6.0"
  val slf4j = "org.slf4j" % "slf4j-api" % "1.5.6"
  val httpclient = "commons-httpclient" % "commons-httpclient" % "3.1"
  val jsoup = "org.jsoup" % "jsoup" % "1.3.1"
}
