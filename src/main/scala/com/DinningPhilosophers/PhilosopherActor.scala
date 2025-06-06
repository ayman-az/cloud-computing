package com.DinningPhilosophers

import akka.actor.Actor
import akka.actor.ActorLogging
import akka.actor.ActorRef
import scala.concurrent.duration.FiniteDuration
import scala.concurrent.duration.DurationInt
import akka.actor.ActorSelection

object PhilosopherActor{
    case object Think
    case object Eat
}

class PhilosopherActor(val leftFork : ActorSelection , val rightFork: ActorSelection) extends Actor with ActorLogging{
import PhilosopherActor._
import ForkActor._
implicit private val executionContext = context.dispatcher

val eatingTime = 1500.millis
val thinkinTime = 2500.millis
val repeatTime= 10.millis
def receive:Receive= {
    case Think=>
//        log.info(s"philosopher ${self.path.name} start thinking")
	println(s"philosopher ${self.path.name} start thinking")
        think(thinkinTime)
    case Eat=>
        takeForks
}

def takeForks={
  // log.info(s"try to take fork ${rightFork} and ${leftFork}")
    println(s"try to take fork ${rightFork.pathString} and ${leftFork.pathString}")
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
    //log.debug(s"philosopher ${self.path.name} got a forkInUse from ${fork.path.name}")
	println(s"philosopher ${self.path.name} got a forkInUse from ${fork.path.name}")
    putForks
    Thread.sleep(10000)
    think(repeatTime)
}

private def hungry: Receive = {
    case ForkInUse =>
        handleMissingFork(sender())
    case ForkTaken =>
        //log.info(s"philosopher ${self.path.name} took the second fork ${sender().path.name}") // Fixed: took instead of toke
        //log.info(s"philosopher ${self.path.name} start eating with ${leftFork.pathString} and ${rightFork.pathString}")
       println(s"philosopher ${self.path.name} took the second fork ${sender().path.name}") // Fixed: took instead of toke
        println(s"philosopher ${self.path.name} start eating with ${leftFork.pathString} and ${rightFork.pathString}")
	 context.system.scheduler.scheduleOnce(eatingTime, self, Think)
        context.become(eating)
}
def waitingForOtherFork:Receive={
    case ForkInUse=>
        handleMissingFork(sender)
        
    case ForkTaken=>
        //log.info(s"philosopher ${self.path.name} toke the second fork ${sender.path.name}")
        //log.info(s"philosopher ${self.path.name} start eating with ${leftFork.pathString} and ${rightFork.pathString}")
            println(s"philosopher ${self.path.name} took the second fork ${sender().path.name}") // Fixed: took instead of toke
        println(s"philosopher ${self.path.name} start eating with ${leftFork.pathString} and ${rightFork.pathString}")
	context.system.scheduler.scheduleOnce(eatingTime,self,Think)
        context.become(eating)
}
def thinking:Receive ={
    case Eat=>{
        //log.info(s"phylosopher ${self.path.name} need to eat")
        println(s"phylosopher ${self.path.name} need to eat")
	takeForks
        context.become(hungry)
    }
    log.info("thinking")
}
def eating:Receive = {
  case Think =>
    //log.info(s"philosopher ${self.path.name} start thinking again") // Fixed: thinking instead of thinkig
    //log.info(s"philosopher ${self.path.name} put forks ${leftFork.pathString} and ${rightFork.pathString}")
    println(s"philosopher ${self.path.name} start thinking again") // Fixed: thinking instead of thinkig
    println(s"philosopher ${self.path.name} put forks ${leftFork.pathString} and ${rightFork.pathString}")
	putForks
    think(thinkinTime)
}
override def unhandled(msg: Any): Unit = {
msg match {
    case m =>
    // we get quite a bit unhandled "Put" messages. Change debug to info to see them
    log debug ("Chopstick %s currently doesn't handle %s from %s".format(self.path.name, m, sender.path.name))
    super.unhandled(m)
}
}

}
