package W4

/**
  * Created by dominik on 13.11.16.
  */
class w4 {

  sealed trait BT[+A]
  case object Empty extends BT[Nothing]
  case class Node[+A](elem:A, left:BT[A], right:BT[A]) extends BT[A]

  // TASK 3

  def breadthBT[A](t:BT[A]):List[A] = {
    def breadth(list:List[BT[A]]):List[A] = list match {
      case Nil => Nil
      case Empty::tl => breadth(tl)
      case Node(v, l, r)::tl => v :: breadth(tl ++ (l::r::Nil))
    }
    breadth(List(t))
  }


  val tree =Node(1,
    Node(2,
      Node(4,Empty,Empty),
      Empty),
    Node(3,
      Node(5, Empty,
        Node(6,Empty,Empty)),
      Empty))

  breadthBT(tree)

  // TASK 4

  def internal[A](t:BT[A]):Int = {
    def inter(t:BT[A],acc:Int):Int = t match {
      case Empty => throw new IllegalArgumentException()
      case Node(v, Empty, Empty) => acc
      case Node(v, l, Empty) => acc + inter(l, acc + 1)
      case Node(v, Empty, r) => acc + inter(r, acc + 1)
      case Node(v, l, r) => acc + inter(l, acc + 1) + inter(r, acc + 1)
    }
    inter(t,0)
  }

  internal(tree)

  def external[A](t:BT[A]):Int = {
    def exter(t:BT[A],acc:Int):Int = t match{
      case Empty => acc
      case Node(v,Empty,Empty) => 2*(acc+1)
      case Node(v,l,Empty) => acc+1 + exter(l,acc+1)
      case Node(v,Empty,r) => acc+1 + exter(r,acc+1)
      case Node(v,l,r) => exter(l,acc+1) + exter(r,acc+1)
    }
    exter(t,0)
  }

  external(tree)

  // TASK 5

  sealed trait Graphs[A]
  case class Graph[A](succ: A=>List[A]) extends Graphs[A]

  val g = Graph((i: Int) => i match {
    case 0 => List(3)
    case 1 => List(0,2,4)
    case 2 => List(1)
    case 3 => Nil
    case 4 => List(0,2)
    case n => throw new Exception("Graph g: node " + n + " doesn't exist")
  })

  def breadthSearch[A] (g: Graph[A]) (startNode: A): List[A] = {
    def search(visited: List[A])(toVisit: List[A]): List[A] = toVisit match {
      case Nil => Nil
      case h::t =>
        if (visited contains h) search(visited)(t)
        else h::search(h::visited)(t ++ (g succ h))
    }
    search (Nil) (List(startNode))
  }


  def depthSearch[A] (g: Graph[A]) (startNode: A): List[A] = {
    def search(visited: List[A])(toVisit: List[A]): List[A] = toVisit match {
      case Nil => Nil
      case h::t =>
        if (visited contains h) search(visited)(t)
        else h::search(h::visited)((g succ h) ++ t)
    }
    search (Nil) (List(startNode))
  }

  breadthSearch(g)(4)

  depthSearch(g)(4)


}
