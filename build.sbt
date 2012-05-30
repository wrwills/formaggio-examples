name := "formaggio-examples"

organization := "com.github.wrwills"

scalaVersion := "2.9.1"

version := "0.2.2"

seq(webSettings :_*)

libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra" % "2.0.4",
  "org.scalatra" %% "scalatra-scalate" % "2.0.4",
  "org.scalatra" %% "scalatra-specs2" % "2.0.4" % "test",
  "ch.qos.logback" % "logback-classic" % "1.0.0" % "runtime",
  "org.eclipse.jetty" % "jetty-webapp" % "7.6.0.v20120127" % "container",
  "javax.servlet" % "servlet-api" % "2.5" % "provided",
  "com.github.wrwills" %% "formaggio" % "0.2.2"	
)

resolvers += "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"

resolvers += "formaggio repo" at "http://wrwills.github.com/formaggio/repository/"

initialCommands := """
import scalaz._
import Scalaz._
import formaggio._
import Formlets._
import SampleData._
"""