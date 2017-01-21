package Laboratoria_Popiel.L7

class l7 {


  type Set = Int => Boolean

  def contains(s:Set, elem:Int):Boolean = s(elem)

  def singletonSet(elem:Int):Set = {param: Int => param == elem}

  def union(x:Set, y:Set):Set = {param:Int => x(param) || y(param)}

  def intersect(x:Set, y:Set):Set = {param:Int => x(param) && y(param)}

  def diff(x:Set, y:Set):Set = {param:Int => x(param) && !y(param)}

  def filter(x:Set, p:Int => Boolean):Set = {param:Int => x(param) && p(param)}

  def emptySet():Set =diff(singletonSet(1),singletonSet(1))

  def map(s:Set, f:Int => Int):Set = {
    def loop(i: Int, newSet:Set):Set = {
      if (i>100) newSet
      else if(contains(s,i)) loop(i+1,union(newSet,singletonSet(f(i))))
      else loop(i+1,newSet)
    }
    loop(0,emptySet())
  }

  val set = union(union(singletonSet(1),singletonSet(2)),union(singletonSet(3),singletonSet(4)))

  def printSet(set:Set) = {
    scala.Console.print("Set contains: ")
    for (i<-0 to 100){
      if(contains(set,i)) scala.Console.print(i+", ")
    }
  }

  val x = singletonSet(1)

  printSet(set)

  def iContains(s:Set):Boolean = {
    scala.Console.print("Check if set contains: ")
    s(scala.io.StdIn.readInt())
  }

  //iContains(set)

  def createSet():Set = {
    scala.Console.print("How large set you want to create: ")
    val n = scala.io.StdIn.readInt()
    var set = emptySet()
    for(i <- 1 to n) {
      scala.Console.print("Add elem: ")
      set = union(set,singletonSet(scala.io.StdIn.readInt()))
    }
    set
  }

  def getSetsPair():(Set,Set) = {
    (createSet(),createSet())
  }

  def unionSets():Set = {
    val sets = getSetsPair()
    union(sets._1,sets._2)
  }

  def intersectSets():Set = {
    val sets = getSetsPair()
    intersect(sets._1,sets._2)
  }

  def diffSets():Set = {
    val sets = getSetsPair()
    diff(sets._1,sets._2)
  }

  def squareSet() = {
    val sqrSet = map(createSet(), x => x*x)
    sqrSet
  }

  def interface() = {
    scala.Console.print("1. Union Sets\n2. Intersect Sets\n3. Substract Sets\n4. Map set to ^2\nAny other key to exit")
    val action = scala.io.StdIn.readInt()
    val result = {
      action match {
        case 1 => unionSets()
        case 2 => intersectSets()
        case 3 => diffSets()
        case 4 => squareSet()
        case _ => emptySet()
      }
    }
    printSet(result)
  }

  printSet(unionSets())
  squareSet()
  interface()

}
