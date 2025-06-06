error id: file://<WORKSPACE>/src/main/scala/com/DinningPhilosophers/ForkActor.scala:
file://<WORKSPACE>/src/main/scala/com/DinningPhilosophers/ForkActor.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 1061
uri: file://<WORKSPACE>/src/main/scala/com/DinningPhilosophers/ForkActor.scala
text:
```scala
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

    var isAvailable: Boolean = true
    def available: Receive ={
        
        case PickUp => 
            if(isAvailable){
                log.info(s"Fork picked up by philosopher ${sender().path.name}")
                isAvailable = false
                sender() ! ForkTaken
                context.become(taken(sender))
            } else {
                log.info("Fork is already picked up, cannot pick it up again.")
            }
    }

    def taken(philosopher:ActorRef):Receive = {
        case PickUp => 
            log.warning(s"Fork is already taken by ${sender().path.name} ")
            sender ! ForkInUse
        case PutDown => 
            if(sender == philosopher){
                log.info(s"Fork is putted down by philoso@@pher ${philosopher.path.name}")
                context.become(available)
            }
    }
    def receive = available
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: 