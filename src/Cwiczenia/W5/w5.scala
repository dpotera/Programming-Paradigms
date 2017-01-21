package Cwiczenia.W5

class w5 {

  // TASK 1

  def sRepeat[A](n:Int,stream:Stream[A]):Stream[A] = {
    def repeat(v:A,r:Int,tl:Stream[A]):Stream[A] = {
      if (r==0) tl else repeat(v,r-1,v #:: tl)
    }
    stream match {
      case Stream.Empty => Stream.Empty
      case v #:: tl => repeat(v,n,sRepeat(n,tl))
    }
  }

  sRepeat(3,List(1,2,3,4).toStream).toList

  // TASK 2

  def lfib():Stream[Int] = {
    def fib(a:Int,b:Int):Stream[Int] = a #:: fib(b,a+b)
    fib(1,1)
  }

  lfib().take(15).toList

  // TASK 3

  sealed trait lBT[+A]
  case object LEmpty extends lBT[Nothing]
  case class LNode[+A](elem:A, left:()=>lBT[A], right:()=>lBT[A]) extends lBT[A]

  def lTree(n:Int):lBT[Int] = LNode(n,()=>lTree(2*n),()=>lTree(2*n+1))

  def lTreeToList[A](tree:lBT[A]):Stream[A] = {
    def toLlist(list: List[lBT[A]]):Stream[A] = list match {
      case Nil => Stream.Empty
      case LEmpty :: tl => toLlist(tl)
      case LNode(v,l,r) :: tl => v #:: toLlist(tl ++ (l()::r()::Nil))
    }
    toLlist(List(tree))
  }

  lTreeToList(lTree(2)).take(10).toList

}
