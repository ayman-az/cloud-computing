error id: file://<WORKSPACE>/build.sbt:
file://<WORKSPACE>/build.sbt
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 695
uri: file://<WORKSPACE>/build.sbt
text:
```scala
name := "akka-quickstart-scala"

version := "1.0"

scalaVersion := "2.13.15"

resolvers += "Akka library repository".at("https://repo.akka.io/maven")
lazy val akkaVersion = sys.props.getOrElse("akka.version", "2.10.6")

// Run in a separate JVM, to make sure sbt waits until all threads have
// finished before returning.
// If you want to keep the application running while executing other
// sbt tasks, consider https://github.com/spray/sbt-revolver/
fork := true

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  "ch.qos.logback" % "logback-classic" % "1.5.8",
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion % Test,
  "org.scalatest@@" %% "scalatest" % "3.2.15" % Test,
  "com.typesafe.akka" %% "akka-remote" % "2.6.20" % Test
  )
```


#### Short summary: 

empty definition using pc, found symbol in pc: 