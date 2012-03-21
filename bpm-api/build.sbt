name := "BPM API"

version := "0.0.0"

organization := "com.lucasian"

crossPaths := false

libraryDependencies += "com.googlecode.lambdaj" % "lambdaj" % "2.3.3"

publishTo <<= (version) { version: String =>
      Some(Resolver.file("file", new File("/home/iamedu/Code/lucasian/repository") / {
        if  (version.trim.endsWith("SNAPSHOT"))  "snapshots"
        else                                     "releases/" }    ))
}


