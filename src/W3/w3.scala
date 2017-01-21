package W3

/**
  * Created by dominik on 12.11.16.
  */
class w3 {

  // TASK 2
  def curry3[a,b,c,d](f:(a,b,c)=>d): a=>b=>c=>d = x=>y=>z=>f(x,y,z)

  def plusC(a:Int,b:Int,c:Int):Int = a+b+c

  curry3(plusC)(3)(4)(5)

  def uncurry3[a,b,c,d](f: a=>b=>c=>d) = (x:a,y:b,z:c) => f(x)(y)(z)

  def plusU (x:Int)(y:Int)(z:Int) = x+y+z

  uncurry3(plusU)(3,4,5)

  // TASK 3
  def sumProd(xs:List[Int]):(Int,Int) =
    xs match {
      case h::t => {val (s,p)=sumProd(t)
        (h+s,h*p)
      }
      case Nil => (0,1)
    }

  def sumProdFold(xs:List[Int]):(Int,Int) = ((0,1) /: xs)((k:(Int,Int),e:Int) => (k._1+e,k._2*e))

  sumProd(List(1,2,3,4))






  def mergesort[A](order:(A, A) => Boolean, list: List[A]): List[A] = {
    val partition = list.length / 2
    if (partition == 0) list
    else {
      def merge(list1: List[A], list2: List[A]): List[A] = (list1, list2) match {
        case(Nil, list2) => list2
        case(list1, Nil) => list1
        case(head1 :: tail1, head2 :: tail2) =>
          if (order(head1, head2)) head1::merge(tail1, list2)
          else head2 :: merge(list1, tail2)
      }
      val (left, right) = list.splitAt(partition)
      merge(mergesort(order, left), mergesort(order, right))
    }
  }

  def myOrder(first:(Int,Int), second:(Int,Int)) =
    first._1 <= second._1

  val x = mergesort(myOrder, List((32,0),(523,0),(5,1),(5,2),(52,0),(5,3)))
}
