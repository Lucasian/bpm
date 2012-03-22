name := "BPM BOS"

version := "0.0.0-SNAPSHOT"

organization := "com.lucasian"

libraryDependencies ++= Seq(
	"org.ow2.bonita" %  "bonita-server" % "5.6.2" withSources() withJavadoc(),
	"org.specs2"     %% "specs2"        % "1.8.2" % "test"
)

resolvers += "JBoss Thirdparty Releases" at "https://repository.jboss.org/nexus/content/repositories/thirdparty-releases"

publishTo <<= (version) { version: String =>
      Some(Resolver.file("file", new File("/home/iamedu/Code/lucasian/repository") / {
        if  (version.trim.endsWith("SNAPSHOT"))  "snapshots"
        else                                     "releases/" }    ))
}


