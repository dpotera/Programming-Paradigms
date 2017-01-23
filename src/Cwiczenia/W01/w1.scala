package Cwiczenia.W1

object w1 {
  val x1 = 2 + 8 * 6
  val x2 = 5+2/2

  val y = {
    val x = x1 + x1
    x + x + {
      val x = 2
      x + 10
    }
  }

//  Krotka
  val k1 = (2.0, 5, 1==1)
  k1._3

// LISTY
  val lst = 1.0 :: 2.5 :: 10.0 :: Nil
  lst == List(1.0, 2.5, 10.0)
  lst.head
  lst.tail

  lst ++ List(13.0)


  def silnia(n:Int):Int =
    if(n==0) 1
    else if(n>0) n*silnia(n-1)
    else throw new Exception("negative argument")

  // TASK 1

  def flatten(lst:List[List[Int]]):List[Int] = {
    if (lst == Nil) Nil
    else lst.head ++ flatten(lst.tail)
  }

  flatten(List(List(1,2),List(3,4),List(5,6)))

  // TASK 2

  def count[A](a:A,list:List[A]):Int = {
    if(list==Nil) 0
    else {if(a==list.head) 1 else 0} + count(a,list.tail)
  }

  count(1,List(1,2,3,1,2,1,1,5,6,1))

  // TASK 3

  def replicate[A](elem:A,n:Int):List[A] = {
    if(n==0) Nil
    else if(n>0) List(elem) ++ replicate(elem,n-1)
    else throw new Exception("invalid replicate number")
  }

  replicate("Dominik",4)
  replicate(2,5)

  // TASK 4

  def sqrList(list:List[Int]):List[Int] = {
    if(list==Nil) Nil
    else List(list.head*list.head) ++ sqrList(list.tail)
  }

  sqrList(List(2,4,8,6))

  // TASK 5

  def palindrome[A](list:List[A]) = list == list.reverse

  palindrome(List(1,2,3))
  palindrome(List(4,3,4))

  // TASK 6

  def listLength[A](list: List[A]):Int = {
    if (list==Nil) 0 else 1+listLength(list.tail)
  }

  listLength(List(1,2,3,4,5))
  listLength(List(10))
  listLength(List())


}