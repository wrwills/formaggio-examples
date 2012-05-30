import sbt._
import Keys._

object FormaggioBuild extends Build {

  
  val commonSettings: Seq[Setting[_]] = 
    Seq(
      organization := "com.github.wrwills",
//      name := "formaggio"      
      version := "0.2.1",
      scalaVersion := "2.9.1"
      //publishTo := Option(Resolver.file("gitpages-local", Path.userHome / "projects" / "scala" / "formaggio" / "repository"))
      // publishTo := Option(Resolver.file("gitpages-local", Path.userHome / "git" / "repository"))
      //initialCommands := coreConsole
      //shellPrompt  := {(state: State) => coreConsole}
    )

  
  val scalazCore = "org.scalaz" %% "scalaz-core" % "6.0.2"
  val specs2 = "org.specs2" %% "specs2" % "1.4" % "test"

  val formaggioRepo = "formaggio repo" at "http://wrwills.github.com/formaggio/repository/"
  val formaggio = "com.github.wrwills" %% "formaggio" % "0.2.2"

  object Scalatra  {
    val sonatypeNexusSnapshots = "Sonatype Nexus Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
    val sonatypeNexusReleases = "Sonatype Nexus Releases" at "https://oss.sonatype.org/content/repositories/releases"
    
    val repos = Seq(sonatypeNexusSnapshots, sonatypeNexusReleases, formaggioRepo)

    //val scalatraVersion = "2.0.0-SNAPSHOT"
    val scalatraVersion = "2.0.0"
    val scalatra = "org.scalatra" %% "scalatra" % scalatraVersion
    val scalate = "org.scalatra" %% "scalatra-scalate" % scalatraVersion
    //val servletApi = "org.mortbay.jetty" % "servlet-api" % "2.5-20081211" % "provided"
    val jetty = "org.mortbay.jetty" % "jetty" % "6.1.22" % "jetty"
    val servletApi = "javax.servlet" % "servlet-api" % "2.5" % "provided->default"
    // Alternatively, you could use scalatra-specs
    val scalatest = "org.scalatra" %% "scalatra-scalatest" % scalatraVersion % "test"

    // Pick your favorite slf4j binding
    val slf4jBinding = "ch.qos.logback" % "logback-classic" % "0.9.25" % "runtime"

    val deps = Seq(scalatra, scalate, jetty, servletApi, scalatest, slf4jBinding, formaggio)
  }

  
  lazy val root = Project("formaggio-examples", file(".")) aggregate(scalatraExample)

  val coreDeps = Seq(scalazCore, specs2, formaggio)

  val coreConsole =
"""
import scalaz._
import Scalaz._
import formaggio._
import Formlets._
import SampleData._
"""

//  lazy val core = Project("formaggio-core", file("core")) settings((commonSettings ++ Seq (libraryDependencies := coreDeps)) : _*) settings ( (initialCommands := coreConsole) : _* )
  // settings ( (consoleInit := coreConsole) : _* ) TODO: figure out how to use

  lazy val scalatraExample = Project("formaggio-scalatra-example", file("scalatra_example")) settings((commonSettings ++ Seq (libraryDependencies := Scalatra.deps, resolvers := Scalatra.repos)) : _*) settings( WebPlugin.webSettings : _*)

}
