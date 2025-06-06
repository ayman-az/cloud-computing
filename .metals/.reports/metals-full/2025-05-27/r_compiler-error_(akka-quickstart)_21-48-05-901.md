error id: 05241094BB104D1DC98D0C2A962F6EAE
file://<WORKSPACE>/src/main/scala/com/DinningPhilosophers/PhilosopherActor.scala
### scala.MatchError: implicit class <error> extends  (of class scala.reflect.internal.Trees$ClassDef)

occurred in the presentation compiler.



action parameters:
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
implicit prival ExecutionContext = context.dispatcher

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
        context.system.scheduler.scheduleOnce(eatingTime,self,Think)
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
override def unhandled(msg: Any): Unit = {
msg match {
    case m =>
    // we get quite a bit unhandled "Put" messages. Change debug to info to see them
    log debug ("Chopstick %s currently doesn't handle %s from %s".format(name, m, sender.path.name))
    super.unhandled(m)
}
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
scala.tools.nsc.typechecker.Unapplies.constrParamss(Unapplies.scala:90)
	scala.tools.nsc.typechecker.Unapplies.factoryMeth(Unapplies.scala:155)
	scala.tools.nsc.typechecker.Unapplies.factoryMeth$(Unapplies.scala:152)
	scala.meta.internal.pc.MetalsGlobal$MetalsInteractiveAnalyzer.factoryMeth(MetalsGlobal.scala:77)
	scala.tools.nsc.typechecker.MethodSynthesis$MethodSynth.enterImplicitWrapper(MethodSynthesis.scala:238)
	scala.tools.nsc.typechecker.MethodSynthesis$MethodSynth.enterImplicitWrapper$(MethodSynthesis.scala:237)
	scala.tools.nsc.typechecker.Namers$Namer.enterImplicitWrapper(Namers.scala:58)
	scala.tools.nsc.interactive.InteractiveAnalyzer$InteractiveNamer.enterExistingSym(Global.scala:95)
	scala.tools.nsc.interactive.InteractiveAnalyzer$InteractiveNamer.enterExistingSym$(Global.scala:81)
	scala.tools.nsc.interactive.InteractiveAnalyzer$$anon$2.enterExistingSym(Global.scala:51)
	scala.tools.nsc.typechecker.Namers$Namer.standardEnterSym(Namers.scala:292)
	scala.tools.nsc.typechecker.AnalyzerPlugins.pluginsEnterSym(AnalyzerPlugins.scala:500)
	scala.tools.nsc.typechecker.AnalyzerPlugins.pluginsEnterSym$(AnalyzerPlugins.scala:499)
	scala.meta.internal.pc.MetalsGlobal$MetalsInteractiveAnalyzer.pluginsEnterSym(MetalsGlobal.scala:77)
	scala.tools.nsc.typechecker.Namers$Namer.enterSym(Namers.scala:266)
	scala.tools.nsc.typechecker.Typers$Typer.enterSym(Typers.scala:2061)
	scala.tools.nsc.typechecker.Typers$Typer.enterSyms(Typers.scala:2056)
	scala.tools.nsc.typechecker.Typers$Typer.typedTemplate(Typers.scala:2116)
	scala.tools.nsc.typechecker.Typers$Typer.typedClassDef(Typers.scala:1978)
	scala.tools.nsc.typechecker.Typers$Typer.typed1(Typers.scala:6227)
	scala.tools.nsc.typechecker.Typers$Typer.typed(Typers.scala:6320)
	scala.tools.nsc.typechecker.Typers$Typer.typedStat$1(Typers.scala:6398)
	scala.tools.nsc.typechecker.Typers$Typer.$anonfun$typedStats$10(Typers.scala:3544)
	scala.tools.nsc.typechecker.Typers$Typer.typedStats(Typers.scala:3544)
	scala.tools.nsc.typechecker.Typers$Typer.typedPackageDef$1(Typers.scala:5901)
	scala.tools.nsc.typechecker.Typers$Typer.typed1(Typers.scala:6230)
	scala.tools.nsc.typechecker.Typers$Typer.typed(Typers.scala:6320)
	scala.tools.nsc.typechecker.Analyzer$typerFactory$TyperPhase.apply(Analyzer.scala:125)
	scala.tools.nsc.Global$GlobalPhase.applyPhase(Global.scala:483)
	scala.tools.nsc.interactive.Global$TyperRun.applyPhase(Global.scala:1369)
	scala.tools.nsc.interactive.Global$TyperRun.typeCheck(Global.scala:1362)
	scala.tools.nsc.interactive.Global.typeCheck(Global.scala:680)
	scala.meta.internal.pc.Compat.$anonfun$runOutline$1(Compat.scala:74)
	scala.collection.IterableOnceOps.foreach(IterableOnce.scala:619)
	scala.collection.IterableOnceOps.foreach$(IterableOnce.scala:617)
	scala.collection.AbstractIterable.foreach(Iterable.scala:935)
	scala.meta.internal.pc.Compat.runOutline(Compat.scala:66)
	scala.meta.internal.pc.Compat.runOutline(Compat.scala:35)
	scala.meta.internal.pc.Compat.runOutline$(Compat.scala:33)
	scala.meta.internal.pc.MetalsGlobal.runOutline(MetalsGlobal.scala:36)
	scala.meta.internal.pc.ScalaCompilerWrapper.compiler(ScalaCompilerAccess.scala:18)
	scala.meta.internal.pc.ScalaCompilerWrapper.compiler(ScalaCompilerAccess.scala:13)
	scala.meta.internal.pc.ScalaPresentationCompiler.$anonfun$semanticTokens$1(ScalaPresentationCompiler.scala:195)
```
#### Short summary: 

scala.MatchError: implicit class <error> extends  (of class scala.reflect.internal.Trees$ClassDef)