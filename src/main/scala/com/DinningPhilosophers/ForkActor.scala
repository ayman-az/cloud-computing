package com.DinningPhilosophers

import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.ActorLogging

object ForkActor {
    case object PickUp
    case object PutDown
    case object ForkInUse
    case object ForkTaken
}

class ForkActor extends Actor with ActorLogging {
    import ForkActor._

    def available: Receive ={
        case PickUp => 
         
            //log.info(s"Fork picked up by philosopher ${sender().path.name}")
            println(s"fork picled up by philosopher ${sender().path.name}")
		sender ! ForkTaken
            context.become(taken(sender))
        
    }

    def taken(philosopher:ActorRef):Receive = {
        case PickUp => 
            //log.warning(s"Fork is already cannot be taken by ${sender().path.name} , already in user by  ${philosopher.path.name}")
        	println(s"Fork is already cannot be taken by ${sender().path.name} , already in user by  ${philosopher.path.name}")    
	sender ! ForkInUse
        case PutDown => 
            if(sender == philosopher){
              //  log.info(s"Fork is putted down by philosopher ${philosopher.path.name}")
               println(s"Fork is putted down by philosopher ${philosopher.path.name}")
		 context.become(available)
            }
    }
    override def unhandled(msg: Any): Unit = {
    msg match {
      case m =>
        // we get quite a bit unhandled "Put" messages. Change debug to info to see them
        log debug ("Fork %s currently doesn't handle %s from %s".format(sender.path.name, m, sender.path.name))
	super.unhandled(m)
    }
  }
    def receive = available
}
