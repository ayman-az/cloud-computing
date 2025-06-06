error id: file://<WORKSPACE>/src/main/scala/com/DinningPhilosophers/PhilosopherServer.scala:local2
file://<WORKSPACE>/src/main/scala/com/DinningPhilosophers/PhilosopherServer.scala
empty definition using pc, found symbol in pc: 
found definition using semanticdb; symbol local2
empty definition using fallback
non-local guesses:

offset: 747
uri: file://<WORKSPACE>/src/main/scala/com/DinningPhilosophers/PhilosopherServer.scala
text:
```scala
package com.DinningPhilosophers

import akka.actor.{ActorSystem, Props}
import scala.concurrent.duration._
import com.typesafe.config.ConfigFactory

object PhilosopherServer {
  def main(args: Array[String]): Unit = {
    val n = if (args.nonEmpty) args(0).toInt else 5
    val forkHost = if (args.length > 1) args(1) else "127.0.0.1"
    val forkPort = if (args.length > 2) args(2) else "2552"
    val system = ActorSystem("dinner", ConfigFactory.load("philosopher")) // PhilosopherServer)
    import PhilosopherActor._

    val philosophers = for (i <- 0 until n) yield {
      val leftFork = system.actorSelection(s"akka://dinner@127.0.0.1:2552/user/fork-$i")
      val rightFork = system.actorSelection(s"akka://dinner@$forkHost:$forkPort/user@@/fork-${(i + 1) % n}")
      // system.actorOf(Props(classOf[PhilosopherActor], leftFork, rightFork), s"philosopher-$i")
      println(s"${leftFork.pathString}--${rightFork.pathString}")
      system.actorOf(Props(new PhilosopherActor(leftFork, rightFork)),s"philosopher-$i")

    }

    philosophers.foreach(_ ! Think)

    // Optional: shutdown after 1 minute
    import system.dispatcher
    system.scheduler.scheduleOnce(1.minute) {
      println("Shutting down philosophers system...")
      system.terminate()
    }
  }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: 