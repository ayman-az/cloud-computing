error id: ECD122A711EB9035B3FF31BA2D72502F
file://<WORKSPACE>/src/main/scala/com/DinningPhilosophers/PhilosopherActor.scala
### scala.reflect.internal.FatalError: 
  ThisType(method think) for sym which is not a class
     while compiling: file://<WORKSPACE>/src/main/scala/com/DinningPhilosophers/PhilosopherActor.scala
        during phase: globalPhase=<no phase>, enteringPhase=parser
     library version: version 2.13.15
    compiler version: version 2.13.15
  reconstructed args: -release:11 -classpath <WORKSPACE>/.bloop/akka-quickstart/bloop-bsp-clients-classes/classes-Metals--PVVGrYFTPafOlbDyjYdLA==:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/sourcegraph/semanticdb-javac/0.10.4/semanticdb-javac-0.10.4.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.15/scala-library-2.13.15.jar:<HOME>/.cache/coursier/v1/https/repo.akka.io/maven/com/typesafe/akka/akka-actor-typed_2.13/2.10.6/akka-actor-typed_2.13-2.10.6.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/ch/qos/logback/logback-classic/1.5.8/logback-classic-1.5.8.jar:<HOME>/.cache/coursier/v1/https/repo.akka.io/maven/com/typesafe/akka/akka-actor_2.13/2.10.6/akka-actor_2.13-2.10.6.jar:<HOME>/.cache/coursier/v1/https/repo.akka.io/maven/com/typesafe/akka/akka-slf4j_2.13/2.10.6/akka-slf4j_2.13-2.10.6.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/slf4j/slf4j-api/2.0.17/slf4j-api-2.0.17.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/ch/qos/logback/logback-core/1.5.8/logback-core-1.5.8.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/typesafe/config/1.4.3/config-1.4.3.jar -Xplugin-require:semanticdb -Yrangepos -Ymacro-expand:discard -Ycache-plugin-class-loader:last-modified -Ypresentation-any-thread

  last tree to typer: Ident(Eat)
       tree position: line 62 of file://<WORKSPACE>/src/main/scala/com/DinningPhilosophers/PhilosopherActor.scala
              symbol: <none>
   symbol definition: <none> (a NoSymbol)
      symbol package: <none>
       symbol owners: 
           call site: <none> in <none>

== Source file context for tree position ==

    59     case ForkTaken=>
    60         log.info(s"philosopher ${self.path.name} toke the second fork ${sender.path.name}")
    61         log.info(s"philosopher ${self.path.name} start eating with ${leftFork.path.name} and ${rightFork.path.name}")
    62         context.system.scheduler.scheduleOnce(_CURSOR_duration,self,Eat)
    63         context.become(eating)
    64 }
    65 def thinking:Receive ={

occurred in the presentation compiler.



action parameters:
offset: 1769
uri: file://<WORKSPACE>/src/main/scala/com/DinningPhilosophers/PhilosopherActor.scala
text:
```scala
package com.DinningPhilosophers

import akka.actor.Actor
import akka.actor.ActorLogging
import akka.actor.ActorRef
import scala.concurrent.duration.FiniteDuration
import scala.concurrent.duration.DurationInt

object PhilosopherActor{
    case object Think
    case object Eat
}

class PhilosopherActor(val leftFork : ActorRef , val rightFork: ActorRef) extends Actor with ActorLogging{
import PhilosopherActor._
import ForkActor._
implicit val ExecutionContext = context.dispatcher

val eatingTime = 1500.millis
val thinkinTime = 2500.millis
val repeatTime= 10.millis
def receive:Receive= {
    case Think=>
        log.info(s"philosopher ${self.path.name} start thinking")

    // think(10)
    case Eat=>
        takeForks
}

def takeForks={
rightFork ! PickUp
leftFork ! PickUp
}
def putForks={
    rightFork ! PutDown
    leftFork ! PutDown
}

private def think(duration : FiniteDuration)={
    context.system.scheduler.scheduleOnce(duration,self,Eat)
    context.become(thinking)
}
private def handleMissingFork(fork:ActorRef){
    log.debug(s"philosopher ${self.path.name} got a forkInUse from ${fork.path.name}")
    putForks
    think(repeatTime)
}
private def hungry:Receive={
    case forkInUse => 
        handleMissingFork(sender)
    case forkTaken => 
        log.info(s"philosopher ${self.path.name} toke the first fork ${sender.path.name}")
        context.become(waitingForOtherFork)
}
def waitingForOtherFork:Receive={
    case ForkInUse=>
        handleMissingFork(sender)
    case ForkTaken=>
        log.info(s"philosopher ${self.path.name} toke the second fork ${sender.path.name}")
        log.info(s"philosopher ${self.path.name} start eating with ${leftFork.path.name} and ${rightFork.path.name}")
        context.system.scheduler.scheduleOnce(@@duration,self,Eat)
        context.become(eating)
}
def thinking:Receive ={
    case Eat=>{
        log.info(s"phylosopher ${self.path.name} need to eat")
        takeForks
        context.become(hungry)
    }
    log.info("thinking")
}
def eating:Receive={
    case Think=>
        log.info(s"phylosopher ${self.path.name} start thinkig again")
        log.info(s"phylosopher ${self.path.name} put forks ${leftFork.path.name} and ${rightFork.path.name}")
        putForks
        think(thinkinTime)
}


}
```


presentation compiler configuration:
Scala version: 2.13.15
Classpath:
<WORKSPACE>/.bloop/akka-quickstart/bloop-bsp-clients-classes/classes-Metals--PVVGrYFTPafOlbDyjYdLA== [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/sourcegraph/semanticdb-javac/0.10.4/semanticdb-javac-0.10.4.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.15/scala-library-2.13.15.jar [exists ], <HOME>/.cache/coursier/v1/https/repo.akka.io/maven/com/typesafe/akka/akka-actor-typed_2.13/2.10.6/akka-actor-typed_2.13-2.10.6.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/ch/qos/logback/logback-classic/1.5.8/logback-classic-1.5.8.jar [exists ], <HOME>/.cache/coursier/v1/https/repo.akka.io/maven/com/typesafe/akka/akka-actor_2.13/2.10.6/akka-actor_2.13-2.10.6.jar [exists ], <HOME>/.cache/coursier/v1/https/repo.akka.io/maven/com/typesafe/akka/akka-slf4j_2.13/2.10.6/akka-slf4j_2.13-2.10.6.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/slf4j/slf4j-api/2.0.17/slf4j-api-2.0.17.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/ch/qos/logback/logback-core/1.5.8/logback-core-1.5.8.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/typesafe/config/1.4.3/config-1.4.3.jar [exists ]
Options:
-Yrangepos -Xplugin-require:semanticdb -release 11




#### Error stacktrace:

```
scala.reflect.internal.Reporting.abort(Reporting.scala:70)
	scala.reflect.internal.Reporting.abort$(Reporting.scala:66)
	scala.reflect.internal.SymbolTable.abort(SymbolTable.scala:28)
	scala.reflect.internal.Types$ThisType.<init>(Types.scala:1394)
	scala.reflect.internal.Types$UniqueThisType.<init>(Types.scala:1414)
	scala.reflect.internal.Types$ThisType$.apply(Types.scala:1418)
	scala.meta.internal.pc.AutoImportsProvider$$anonfun$1.applyOrElse(AutoImportsProvider.scala:89)
	scala.meta.internal.pc.AutoImportsProvider$$anonfun$1.applyOrElse(AutoImportsProvider.scala:74)
	scala.collection.immutable.List.collect(List.scala:268)
	scala.meta.internal.pc.AutoImportsProvider.autoImports(AutoImportsProvider.scala:74)
	scala.meta.internal.pc.ScalaPresentationCompiler.$anonfun$autoImports$1(ScalaPresentationCompiler.scala:388)
```
#### Short summary: 

scala.reflect.internal.FatalError: 
  ThisType(method think) for sym which is not a class
     while compiling: file://<WORKSPACE>/src/main/scala/com/DinningPhilosophers/PhilosopherActor.scala
        during phase: globalPhase=<no phase>, enteringPhase=parser
     library version: version 2.13.15
    compiler version: version 2.13.15
  reconstructed args: -release:11 -classpath <WORKSPACE>/.bloop/akka-quickstart/bloop-bsp-clients-classes/classes-Metals--PVVGrYFTPafOlbDyjYdLA==:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/sourcegraph/semanticdb-javac/0.10.4/semanticdb-javac-0.10.4.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.15/scala-library-2.13.15.jar:<HOME>/.cache/coursier/v1/https/repo.akka.io/maven/com/typesafe/akka/akka-actor-typed_2.13/2.10.6/akka-actor-typed_2.13-2.10.6.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/ch/qos/logback/logback-classic/1.5.8/logback-classic-1.5.8.jar:<HOME>/.cache/coursier/v1/https/repo.akka.io/maven/com/typesafe/akka/akka-actor_2.13/2.10.6/akka-actor_2.13-2.10.6.jar:<HOME>/.cache/coursier/v1/https/repo.akka.io/maven/com/typesafe/akka/akka-slf4j_2.13/2.10.6/akka-slf4j_2.13-2.10.6.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/slf4j/slf4j-api/2.0.17/slf4j-api-2.0.17.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/ch/qos/logback/logback-core/1.5.8/logback-core-1.5.8.jar:<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/typesafe/config/1.4.3/config-1.4.3.jar -Xplugin-require:semanticdb -Yrangepos -Ymacro-expand:discard -Ycache-plugin-class-loader:last-modified -Ypresentation-any-thread

  last tree to typer: Ident(Eat)
       tree position: line 62 of file://<WORKSPACE>/src/main/scala/com/DinningPhilosophers/PhilosopherActor.scala
              symbol: <none>
   symbol definition: <none> (a NoSymbol)
      symbol package: <none>
       symbol owners: 
           call site: <none> in <none>

== Source file context for tree position ==

    59     case ForkTaken=>
    60         log.info(s"philosopher ${self.path.name} toke the second fork ${sender.path.name}")
    61         log.info(s"philosopher ${self.path.name} start eating with ${leftFork.path.name} and ${rightFork.path.name}")
    62         context.system.scheduler.scheduleOnce(_CURSOR_duration,self,Eat)
    63         context.become(eating)
    64 }
    65 def thinking:Receive ={