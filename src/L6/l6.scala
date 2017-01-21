package L6

class l6 {

  // ZADANIE 2

  def primes = {
    def sieve(s:Stream[Int]):Stream[Int] = {
      val p #:: xs = s
      p #:: sieve(xs filter (n => n%p != 0))
    }
    sieve(Stream.from(2))
  }

  def primeFactors(n:Int):List[Int] = {
    def primef(n:Int,primes:List[Int]):List[Int] = primes match {
      case Nil => Nil
      case h::t => if(n%h==0) h::primef(n/h,primes) else primef(n,t)
    }
    primef(n,primes.takeWhile(x => x<Math.sqrt(n)).toList)
  }

  primeFactors(315)

  // ZADANIE 3

  sealed trait Graphs[A]
  case class Graph[A](succ:A => (List[(A,Int)])) extends Graphs[A]

  val g = Graph((i: Int) => i match {
    case 0 => List((3,2))
    case 1 => List((0,3),(2,1),(4,6))
    case 2 => List((1,5))
    case 3 => Nil
    case 4 => List((0,8),(2,3))
    case n => throw new Exception("Graph g: node " + n + " doesn't exist")
  })


  def nodeWeight[A](g:Graph[A],n:A):Int = ((g succ n) foldLeft  0)((sum,k) => sum + k._2)

  nodeWeight(g,1)
  nodeWeight(g,3)
  nodeWeight(g,4)




}
