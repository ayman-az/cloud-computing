error id: file://<WORKSPACE>/src/main/scala/com/example/HelloWorld.scala:akka/actor/typed/scaladsl/
file://<WORKSPACE>/src/main/scala/com/example/HelloWorld.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 131
uri: file://<WORKSPACE>/src/main/scala/com/example/HelloWorld.scala
text:
```scala
package com.example

//#imports
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor._
import akka.actor.scal@@adsl.Behaviors
//#imports

//#hello-world-actor
object HelloWorld {
  final case class Greet(whom: String, replyTo: ActorRef[Greeted])
  final case class Greeted(whom: String, from: ActorRef[Greet])

  def apply(): Behavior[Greet] = Behaviors.receive { (context, message) =>
    context.log.info("Hello {}!", message.whom)
    message.replyTo ! Greeted(message.whom, context.self)
    Behaviors.same
  }
}
//#hello-world-actor

//#hello-world-bot
object HelloWorldBot {

  def apply(max: Int): Behavior[HelloWorld.Greeted] = {
    bot(0, max)
  }

  private def bot(greetingCounter: Int, max: Int): Behavior[HelloWorld.Greeted] =
    Behaviors.receive { (context, message) =>
      val n = greetingCounter + 1
      context.log.info("Greeting {} for {}", n, message.whom)
      if (n == max) {
        Behaviors.stopped
      } else {
        message.from ! HelloWorld.Greet(message.whom, context.self)
        bot(n, max)
      }
    }
}
//#hello-world-bot

//#hello-world-main
object HelloWorldMain {

  final case class SayHello(name: String)

  def apply(): Behavior[SayHello] =
    Behaviors.setup { context =>
      val greeter = context.spawn(HelloWorld(), "greeter")

      Behaviors.receiveMessage { message =>
        val replyTo = context.spawn(HelloWorldBot(max = 5), message.name)
        greeter ! HelloWorld.Greet(message.name, replyTo)
        Behaviors.same
      }
    }

  //#hello-world-main
  def main(args: Array[String]): Unit = {
    
    val system: ActorSystem[HelloWorldMain.SayHello] =
      ActorSystem(HelloWorldMain(), "hello")

    system ! HelloWorldMain.SayHello("World")
    system ! HelloWorldMain.SayHello("Akka")
    

    Thread.sleep(3000)
    system.terminate()
  }
  //#hello-world-main
}
//#hello-world-main

```


#### Short summary: 

empty definition using pc, found symbol in pc: 