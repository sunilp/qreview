import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

    val appName         = "Qreview"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      // Add your project dependencies here,
      javaCore,javaJdbc,javaJpa, javaEbean,
      "org.apache.poi" % "poi" % "3.8",
      "org.tmatesoft.svnkit" % "svnkit" % "1.7.8",
        "postgresql" % "postgresql" % "9.1-901.jdbc4",
       "com.amazonaws" % "aws-java-sdk" % "1.3.11" 
    )

  //  val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
    val main = play.Project(appName, appVersion, appDependencies).settings(
      // Add your own project settings here     
     

    )

}
