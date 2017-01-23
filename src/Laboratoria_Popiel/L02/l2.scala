package Laboratoria_Popiel.L2

class l2{

  // TASK 2

  def less0(str1:String, str2:String):Boolean = str1.compareTo(str2) < 0


  def less(str1:String,str2:String):Boolean = {
    def help(str1: String, str2: String, i: Int):Boolean = {
      (str1.length > i,str2.length > i) match {
        case (_,false) => false
        case (false,_) => true
        case (true,true) =>
          (str1.toList(i)==str2.toList(i),str1.toList(i)>str2.toList(i)) match {
            case (false,true) => false
            case (false,false) => true
            case (true,_) => help(str1,str2,i+1)
          }
      }
    }
    help(str1,str2,0)
  }


  less("my","my")
  less("mysz","my")
  less("myk","mysz")
  less("my","mysz")

  val list = List("dominik","karol","adam","ola","ania")
  list.sortWith(less)



  // TASK 4

  def analyse[A](list:List[A]) = list.groupBy(identity).mapValues(_.size).toList

  analyse(List('a','b','c','c','a'))

  def analyse2[A](list:List[A]) = {
    def help(list:List[A],result:Map[A,Int]):List[(A,Int)] = {
      if(list == Nil) result.toList
      else if(result.contains(list.head)) help(list.tail,result)
      else help(list.tail,result + (list.head -> list.count(_ == list.head)))
    }
    help(list,Map())
  }

  analyse2(List('a','b','c','c','a'))




}