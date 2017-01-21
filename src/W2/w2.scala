package W2

class w2 {

  // TASK 2

  def fib(n:Int):Int = if(n==0 || n==1) n else fib(n-2) + fib(n-1)

  def fibRec(n:Int):Int = {
    def fib(n:Int,a:Int,b:Int):Int = if(n==0) a else fib(n-1,b,a+b)
    fib(n,0,1)
  }

  fib(42)
  fibRec(42)

  // TASK 3

  val e = 0.000000000000001

  def root3(n:Double):Double = {
    def root3_tail(n:Double,x:Double):Double = {
      if(Math.abs(x*x*x - n) <= e*Math.abs(n)) x
      else root3_tail(n,x + (n/(x*x) - x)/3)
    }
    root3_tail(n,if(n>1) n/3 else n)
  }

  root3(2.0)


  // TASK 4 a)
  val a = List(-2,-1,0,1,2)
  val List(_,_,x1,_,_) = a
  // TASK 4 b)
  val b = List((1,2),(0,1))
  val List(_,(x2,_)) = b

  // TASK 5

  def initSegment[A](a:List[A],b:List[A]):Boolean = {
    if(a==Nil) true
    else if(a.head != b.head) false
    else initSegment(a.tail,b.tail)
  }

  initSegment(List(1,2),List(1,2,3,4))
  initSegment(List(11,12),List(1,2,3,4))
  initSegment(List(),List(1,2,3,4))
  initSegment(List(1,2,3,4),List(1,2,3,4))
  initSegment(List(1,2,3,5),List(1,2,3,4))


  // TASK 6

  def replaceNth[A](list:List[A],n:Int,r:A):List[A] = {
    def repl(list: List[A], n: Int, r: A, i: Int): List[A] = list match {
      case f :: rest => if (i == n) r :: rest else f :: repl(rest, n, r, i + 1)
      case Nil => list
    }
    repl(list,n,r,0)
  }

  replaceNth(List('a','b','c','d'),3,'X')

}
