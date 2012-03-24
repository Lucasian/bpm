name := "BPM BOS"

version := "0.0.1-SNAPSHOT"

organization := "com.lucasian"

libraryDependencies ++= Seq(
	"org.ow2.bonita" %  "bonita-server" % "5.3.1" withSources() withJavadoc(),
	"org.specs2"     %% "specs2"        % "1.8.2" % "test"
)

seq(osgiSettings: _*)

seq(net.virtualvoid.sbt.graph.Plugin.graphSettings: _*)

resolvers += "JBoss Thirdparty Releases" at "https://repository.jboss.org/nexus/content/repositories/thirdparty-releases"

resolvers += "sonatype-oss-repository"   at "https://oss.sonatype.org/content/groups/public/"

publishTo <<= (version) { version: String =>
      Some(Resolver.file("file", new File("/home/iamedu/Code/lucasian/repository") / {
        if  (version.trim.endsWith("SNAPSHOT"))  "snapshots"
        else                                     "releases/" }    ))
}


