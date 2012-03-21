name := "BPM API"

version := "0.0.0-SNAPSHOT"

organization := "com.lucasian"

crossPaths := false

publishTo <<= (version) { version: String =>
      Some(Resolver.file("file", new File("/home/iamedu/Code/lucasian/repository") / {
        if  (version.trim.endsWith("SNAPSHOT"))  "snapshots"
        else                                     "releases/" }    ))
}


