package Laboratoria_Popiel.L4

class l4 {

  sealed trait Graphs[A]
  case class Graph[A](succ:A => (List[(A,Double)])) extends Graphs[A]





}
