import sbt._
import Keys._

object BpmBuild extends Build {
    lazy val root = Project(id = "bpm",
                            base = file(".")) aggregate(api, bos)

    lazy val api = Project(id = "bpm-api",
                           base = file("bpm-api"))

    lazy val bos = Project(id = "bpm-bos",
                           base = file("bpm-bos")) dependsOn(api)

}
