package Laboratoria_Popiel.L3

class l3{

  type Set = Int => Boolean

  def contains(s:Set, elem:Int):Boolean = s(elem)

  def singletonSet(elem:Int):Set = {param: Int => param == elem}

  contains(singletonSet(2),3)

  def union(x:Set, y:Set):Set = {param:Int => x(param) || y(param)}

  def intersect(x:Set, y:Set):Set = {param:Int => x(param) && y(param)}

  def diff(x:Set, y:Set):Set = {param:Int => x(param) && !y(param)}

  def filter(x:Set, p:Int => Boolean):Set = {param:Int => x(param) && p(param)}

  val set = union(union(singletonSet(1),singletonSet(2)),union(singletonSet(3),singletonSet(4)))

  contains(set,2)
  contains(set,3)
  contains(set,4)
  contains(set,5)

  def map(s:Set, f:Int => Int):Set = {
    def loop(i: Int, newSet:Set):Set = {
      if (i>100) newSet
      else if(contains(s,i)) loop(i+1,union(newSet,singletonSet(f(i))))
      else loop(i+1,newSet)
    }
    loop(0,diff(singletonSet(1),singletonSet(1)))
  }

  val set2 = map(set,{x:Int => x*x})

  set2(2)
  set2(3)
  set2(4)
  set2(5)

  set2(4)
  set2(9)
  set2(16)
  set2(25)

}
