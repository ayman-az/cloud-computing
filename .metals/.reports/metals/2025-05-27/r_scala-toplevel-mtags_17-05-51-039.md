error id: file://<WORKSPACE>/src/main/scala/com/DinningPhilosophers/PhilosopherActor.scala:[971..974) in Input.VirtualFile("file://<WORKSPACE>/src/main/scala/com/DinningPhilosophers/PhilosopherActor.scala", "package com.DinningPhilosophers

import akka.actor.Actor
import akka.actor.ActorLogging
import akka.actor.ActorRef
import scala.concurrent.duration.FiniteDuration

object PhilosopherActor{
    case object Think
    case object Eat
}

class PhilosopherActor(val leftFork : ActorRef , val rightFork: ActorRef) extends Actor with ActorLogging{
import PhilosopherActor._
import ForkActor._

def receive:Receive= {
    case Think=>
        log.info(s"philosopher ${self.path.name} start thinking")
    // think(10)
    case Eat=>
        takeFork
}

def takeFork={
rightFork ! PickUp
leftFork ! PickUp
}
def putFork={
    rightFork ! PutDown
    leftFork ! PutDown
}

def think(duration : FiniteDuration)={
    context.system.scheduler.scheduleOnce(duration, self, Eat)
    context.become(thinking)
}
def handleMissingFork(fork:ActorRef){
    log.debug(s"philosopher ${self.path.name} got a forkInUse from ${fork.path.name}")
    putFork
    context.become(retryTime)
}
def 

def thinking{
    log.info("thinking")
}
def Eat{
    log.info()
}

}")
file://<WORKSPACE>/file:<WORKSPACE>/src/main/scala/com/DinningPhilosophers/PhilosopherActor.scala
file://<WORKSPACE>/src/main/scala/com/DinningPhilosophers/PhilosopherActor.scala:45: error: expected identifier; obtained def
def thinking{
^
#### Short summary: 

expected identifier; obtained def