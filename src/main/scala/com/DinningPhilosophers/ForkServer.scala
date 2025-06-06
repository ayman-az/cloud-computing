package com.DinningPhilosophers
import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory

object ForkServer {
  import ForkActor._
  def main(args: Array[String]): Unit = {
    val n = if (args.nonEmpty) args(0).toInt else 5
    val system = ActorSystem("dinner", ConfigFactory.load("fork.conf")) // Fixed: .conf not .config
    for (i <- 0 until n) {
      val fork = system.actorOf(Props[ForkActor], s"fork-$i")
      println(s"Created fork actor at path: ${fork.path}")
    }
    println(s"Started $n forks.")
  }
}