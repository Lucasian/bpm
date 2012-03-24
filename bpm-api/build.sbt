name := "BPM API"

version := "0.0.1-SNAPSHOT"

organization := "com.lucasian"

crossPaths := false

libraryDependencies ++= Seq(
	"javax.inject"           %  "javax.inject"                                      % "1",
	"org.springframework"    %  "org.springframework.core"                          % "3.1.1.RELEASE",
	"org.springframework"    %  "org.springframework.context"                       % "3.1.1.RELEASE",
	"org.slf4j"              %  "com.springsource.slf4j.org.apache.commons.logging" % "1.6.1",
    "org.apache.felix"       %  "org.osgi.core"                                     % "1.4.0",
	"org.specs2"             %% "specs2"                                            % "1.8.2" % "test"
)

seq(osgiSettings: _*)

resolvers += "SpringSource Enterprise Bundle Repository - SpringSource Bundle Releases" at "http://repository.springsource.com/maven/bundles/release"

resolvers += "SpringSource Enterprise Bundle Repository - External Bundle Releases" at "http://repository.springsource.com/maven/bundles/external"

publishTo <<= (version) { version: String =>
      Some(Resolver.file("file", new File("/home/iamedu/Code/lucasian/repository") / {
        if  (version.trim.endsWith("SNAPSHOT"))  "snapshots"
        else                                     "releases/" }    ))
}


