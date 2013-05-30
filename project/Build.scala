import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

    val appName         = "Qreview"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      // Add your project dependencies here,
      javaCore,javaJdbc,javaJpa,
      "org.apache.poi" % "poi" % "3.8",
      "org.tmatesoft.svnkit" % "svnkit" % "1.7.8"
    )

  //  val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
    val main = play.Project(appName, appVersion, appDependencies).settings(
      // Add your own project settings here     
      resolvers += "jpm repository" at "http://repo-proxy.jpmchase.net/maven/content/groups/jpmc-public/"
      

    )

}
