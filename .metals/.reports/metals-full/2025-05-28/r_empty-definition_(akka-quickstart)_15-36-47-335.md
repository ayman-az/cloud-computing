error id: file://<WORKSPACE>/src/main/scala/com/DinningPhilosophers/ForkServer.scala:
file://<WORKSPACE>/src/main/scala/com/DinningPhilosophers/ForkServer.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 220
uri: file://<WORKSPACE>/src/main/scala/com/DinningPhilosophers/ForkServer.scala
text:
```scala
package com.DinningPhilosophers
import akka.actor.{ActorSystem, Props}

object ForkServer {
  def main(args: Array[String]): Unit = {
    val n = if (args.nonEmpty) args(0).toInt else 5
    val system = ActorSystem("fork@@System")
    for (i <- 0 until n) {
      system.actorOf(Props[ForkActor], s"fork-$i")
    }
    println(s"Started $n forks.")

  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 