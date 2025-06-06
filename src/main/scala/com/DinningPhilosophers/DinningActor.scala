// package com.DinningPhilosophers

// import scala.concurrent.duration._
// import scala.concurrent.ExecutionContext.Implicits.global
// import akka.actor.ActorSystem
// import akka.actor.Props

// object Dinner {
//     import PhilosopherActor._

//     val Din=ActorSystem("dinner")
// //     def main(args:Array[String]){
// //         val Forks= for(i<-1 to 5)yield Din.actorOf(Props[ForkActor],s"fork-${i}")
// //         val Philosophers = for ( (name, i) <- List("1", "2", "3", "4", "5").zipWithIndex )yield Din.actorOf(Props(classOf[PhilosopherActor],Forks(i), Forks((i+1) % 5)),s"philosopher-${name}")
// //         Philosophers foreach{_ ! Think}
    
        
// //         // Philosophers.zipWithIndex foreach{
// //         //     case(philosopher,idx)=>
// //         //         if(idx==0){
// //         //         philosopher ! Eat
// //         //         println("-------------")
// //         //         }else 
// //         //             philosopher ! Think
// //         // }
// //     }

// //   // Schedule system shutdown after 1 minute
// //         Din.scheduler.scheduleOnce(20.second) {
// //             println("Shutting down the dinner system...")
// //             Din.terminate()
// //         }

//     def main(args:Array[String]){
//         val fork1= Din.actorSelection("akka://dinner@127.0.0.1:2552/user/fork-1")
//         val fork2= Din.actorSelection("akka://dinner@127.0.0.1:2552/user/fork-2")
//         Din.actorOf(Props(classOf[PhilosopherActor], fork1, fork2), "philosopher-1")
//         Din.scheduler.scheduleOnce(20.second) {
//             println("Shutting down the dinner system...")
//             Din.terminate()
//         }
//     }
// }