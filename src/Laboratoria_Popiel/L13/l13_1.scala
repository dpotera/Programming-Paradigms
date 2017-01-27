package Laboratoria_Popiel.L13

import akka.actor._
import scala.collection.mutable.TreeSet
import scala.collection.mutable.ListBuffer
import scala.util.Random

case object GetCard
case object StartGame
case object PrintResult

case class Result(name:String, score:Int, diff:Int) extends Comparable[Result]{
  override def compareTo(o: Result): Int = {
    val diffComparision = diff.compareTo(o.diff)
    if(diffComparision==0) name.compareTo(o.name) else diffComparision
  }
}

class Server(val players:Int) extends Actor{
  var list = ListBuffer[Int]()
  for(x <- 2 to 11) list += x += x += x += x
  for(x <- 2 to 4)  list += x += x += x += x
  list = Random.shuffle(list)
  var winners = new TreeSet[Result]()

  override def receive:Receive = {
    case result:Result =>
      this.synchronized{
        winners += result
        println(result.name+" scored "+result.score)
        if(winners.size == players){
          val gameWinners = winners.toStream.filter(result => result.score < 22).toList
          if(gameWinners.nonEmpty) {
            val best = gameWinners.toStream.filter(result => result.diff == gameWinners.head.diff).toList
            if(best.size == 1) println("Winnner: "+gameWinners.head.name)
            else {
              print("Draw, winners: ")
              best.toStream.foreach(result => print(result.name+", "))
            }
          }
          else println("There is no winner")
        }
      }
    case GetCard =>
      if (list.nonEmpty) sender ! list.remove(0)
      else throw new Exception("Out of cards!")
  }
}

class Player(var name:String, server:ActorRef) extends Actor {
  var sum = 0
  var cardsTaken = 0
  val maxSum = 18

  override def receive = {
    case i:Int =>
      sum += i
      cardsTaken += 1
      if(sum < 18) server ! GetCard
      else if(sum==22 && cardsTaken==2) server ! Result(name,sum,0)
      else server ! Result(name,sum,math.abs(21-sum))
    case startGame => server ! GetCard
  }
}



object aplication extends App{
  val system = ActorSystem("System")
  val server = system.actorOf(Props(new Server(2)))
  val player1 = system.actorOf(Props(new Player("Client",server)))
  val player2 = system.actorOf(Props(new Player("Server",server)))

  player1 ! StartGame
  player2 ! StartGame
}
