name := "breadbox"

organization := "org.joshjoyce"

scalaVersion := "2.10.3"

version := "0.0.1"

seq(webSettings :_*)

libraryDependencies ++= Seq("org.scalatest" %% "scalatest" % "2.1.1" % "test",
                            "org.scalatra" %% "scalatra" % "2.3.0.RC1",
                            "org.scalatra" %% "scalatra-scalate" % "2.3.0.RC1",
                            "org.scalatra" %% "scalatra-scalatest" % "2.3.0.RC1" % "test",
                            "org.fusesource.scalate" %% "scalate-core" % "1.6.1")

libraryDependencies ++= Seq(
  "org.eclipse.jetty" % "jetty-webapp" % "9.1.0.v20131115" % "container;compile",
  "org.eclipse.jetty" % "jetty-plus"   % "9.1.0.v20131115" % "container"
)


