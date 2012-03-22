name := "BPM API"

version := "0.0.0-SNAPSHOT"

organization := "com.lucasian"

crossPaths := false

libraryDependencies ++= Seq(
	"com.googlecode.lambdaj" %  "lambdaj"         % "2.3.3",
	"javax.inject"           %  "javax.inject"    % "1",
	"ch.qos.logback"         %  "logback-classic" % "1.0.1",	
	"org.springframework"    %  "spring-core"     % "3.1.1.RELEASE",
	"org.springframework"    %  "spring-context"  % "3.1.1.RELEASE",
	"org.specs2"             %% "specs2"          % "1.8.2" % "test"
)

publishTo <<= (version) { version: String =>
      Some(Resolver.file("file", new File("/home/iamedu/Code/lucasian/repository") / {
        if  (version.trim.endsWith("SNAPSHOT"))  "snapshots"
        else                                     "releases/" }    ))
}


