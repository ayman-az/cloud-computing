error id: file://<WORKSPACE>/src/main/scala/com/DinningPhilosophers/DinningActor.scala:
file://<WORKSPACE>/src/main/scala/com/DinningPhilosophers/DinningActor.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 258
uri: file://<WORKSPACE>/src/main/scala/com/DinningPhilosophers/DinningActor.scala
text:
```scala
package com.DinningPhilosophers

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import akka.actor.ActorSystem
import akka.actor.Props

object Dinner {
    import PhilosopherActor._

    val Din=ActorSystem("dinne@@r")
    
    def main(args:Array[String]){
        val Forks= for(i<-1 to 5)yield Din.actorOf(Props[ForkActor],s"fork-${i}")
        val Philosophers = for ( (name, i) <- List("aristoteles", "plato", "decartes", "kant", "nitzsche").zipWithIndex )yield Din.actorOf(Props(classOf[PhilosopherActor],Forks(i), Forks((i+1) % 5)),s"philosopher-${name}")
        Philosophers foreach{_ ! Think}
        Din.schedular.scheduleOnce(15.second)(Din.shutdown())
    }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: 