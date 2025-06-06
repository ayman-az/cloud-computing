error id: E5D7DFD9F010CB2FE372B4DEF323609E
file://<WORKSPACE>/src/main/scala/com/DinningPhilosophers/ForkServer.scala
### java.lang.NullPointerException: Cannot invoke "scala.reflect.internal.Types$Type.memberType(scala.reflect.internal.Symbols$Symbol)" because the return value of "scala.reflect.internal.Trees$Tree.tpe()" is null

occurred in the presentation compiler.



action parameters:
offset: 225
uri: file://<WORKSPACE>/src/main/scala/com/DinningPhilosophers/ForkServer.scala
text:
```scala
package com.DinningPhilosophers
import akka.actor.{ActorSystem, Props}

object ForkServer {
  def main(args: Array[String]): Unit = {
    val n = if (args.nonEmpty) args(0).toInt else 5
    val system = ActorSystem("dinner" ,@@ConfigFactory.load("fork")) // ForkServer)
    for (i <- 0 until n) {
      val fork= system.actorOf(Props[ForkActor], s"fork-$i")
      println(fork.path)
    }
    println(s"Started $n forks.")

  }
}

```


presentation compiler configuration:
Scala version: 2.13.15
Classpath:
<WORKSPACE>/.bloop/akka-quickstart/bloop-bsp-clients-classes/classes-Metals-qCw6aqoZSy2VHPY0NJ_uPQ== [exists ], <HOME>/.cache/bloop/semanticdb/com.sourcegraph.semanticdb-javac.0.10.4/semanticdb-javac-0.10.4.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.15/scala-library-2.13.15.jar [exists ], <HOME>/.cache/coursier/v1/https/repo.akka.io/maven/com/typesafe/akka/akka-actor_2.13/2.10.6/akka-actor_2.13-2.10.6.jar [exists ], <HOME>/.cache/coursier/v1/https/repo.akka.io/maven/com/typesafe/akka/akka-remote_2.13/2.10.6/akka-remote_2.13-2.10.6.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/ch/qos/logback/logback-classic/1.5.8/logback-classic-1.5.8.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/typesafe/config/1.4.3/config-1.4.3.jar [exists ], <HOME>/.cache/coursier/v1/https/repo.akka.io/maven/com/typesafe/akka/akka-stream_2.13/2.10.6/akka-stream_2.13-2.10.6.jar [exists ], <HOME>/.cache/coursier/v1/https/repo.akka.io/maven/com/typesafe/akka/akka-pki_2.13/2.10.6/akka-pki_2.13-2.10.6.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/agrona/agrona/1.22.0/agrona-1.22.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/ch/qos/logback/logback-core/1.5.8/logback-core-1.5.8.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/slf4j/slf4j-api/2.0.17/slf4j-api-2.0.17.jar [exists ], <HOME>/.cache/coursier/v1/https/repo.akka.io/maven/com/typesafe/akka/akka-protobuf-v3_2.13/2.10.6/akka-protobuf-v3_2.13-2.10.6.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/reactivestreams/reactive-streams/1.0.4/reactive-streams-1.0.4.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/hierynomus/asn-one/0.6.0/asn-one-0.6.0.jar [exists ]
Options:
-Yrangepos -Xplugin-require:semanticdb -release 11




#### Error stacktrace:

```
scala.meta.internal.pc.SignatureHelpProvider$MethodCall.$anonfun$alternatives$1(SignatureHelpProvider.scala:164)
	scala.collection.immutable.List.map(List.scala:247)
	scala.meta.internal.pc.SignatureHelpProvider$MethodCall.alternatives(SignatureHelpProvider.scala:164)
	scala.meta.internal.pc.SignatureHelpProvider$MethodCallTraverser.$anonfun$fromTree$2(SignatureHelpProvider.scala:309)
	scala.meta.internal.pc.SignatureHelpProvider$MethodCallTraverser.$anonfun$fromTree$2$adapted(SignatureHelpProvider.scala:308)
	scala.Option$WithFilter.$anonfun$withFilter$1(Option.scala:362)
	scala.Option$WithFilter.$anonfun$withFilter$1$adapted(Option.scala:362)
	scala.Option$WithFilter.map(Option.scala:319)
	scala.meta.internal.pc.SignatureHelpProvider$MethodCallTraverser.fromTree(SignatureHelpProvider.scala:308)
	scala.meta.internal.pc.SignatureHelpProvider.$anonfun$signatureHelp$3(SignatureHelpProvider.scala:31)
	scala.Option.flatMap(Option.scala:283)
	scala.meta.internal.pc.SignatureHelpProvider.$anonfun$signatureHelp$2(SignatureHelpProvider.scala:29)
	scala.Option.flatMap(Option.scala:283)
	scala.meta.internal.pc.SignatureHelpProvider.signatureHelp(SignatureHelpProvider.scala:27)
	scala.meta.internal.pc.ScalaPresentationCompiler.$anonfun$signatureHelp$1(ScalaPresentationCompiler.scala:423)
```
#### Short summary: 

java.lang.NullPointerException: Cannot invoke "scala.reflect.internal.Types$Type.memberType(scala.reflect.internal.Symbols$Symbol)" because the return value of "scala.reflect.internal.Trees$Tree.tpe()" is null