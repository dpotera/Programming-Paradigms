package L5

class l5 {

  // TASK 3

  def sort[A](stream: Stream[Stream[A]]): Stream[Stream[A]] = stream.sortWith(_.size > _.size)

  def sortApp[A](stream:Stream[Stream[A]]):Stream[Stream[A]] = {
    val mapped = stream.map(_.size)
    stream.sortWith( (s1,s2) => mapped.count(x => x==s1.size) < mapped.count(x => x==s2.size))
  }

  val stream = Stream(Stream('a','b','c'),Stream('d','e'),Stream('f','g','h'),
    Stream('d','e'),Stream('i','j','k','l'),Stream('m','n'),Stream('o'))

  stream

  sort(stream).toList

  sortApp(stream).toList

  // TASK 4

  def combinations[A](n:Int,l:List[A]):Stream[Stream[A]] = {
    def com(n: Int, s: Stream[A]): Stream[Stream[A]] = {
      if (n == 1) s.map(Stream(_)) else s match {
        case Stream.Empty => Stream.Empty
        case _ => com(n - 1, s.tail).map(s.head #:: _) #::: com(n, s.tail)
      }
    }
    com(n,l.toStream)
  }

  val str = combinations(3,List('a','b','c','d'))

}
