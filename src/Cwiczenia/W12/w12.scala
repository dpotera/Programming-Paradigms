package Cwiczenia.W12

import akka.actor.{Actor, ActorSystem, Props}

class w12 extends Actor{
  override def receive = {
    case "hi" => println("hi")
    case _ => println("_")
  }
}

object w12_app extends App {
  val system = ActorSystem("System")
  val actor = system.actorOf(Props[w12])

  actor ! "hi"
  actor ! "anything"
}