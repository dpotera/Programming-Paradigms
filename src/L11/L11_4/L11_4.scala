package L11.L11_4

import L11.L11_3.{heapMax, heapNormal}

class Para(var x:Int, var y:Int) {

}

class Trojka(x:Int, y:Int, var z:Int) extends Para(x,y){

}

class Czworka(x:Int, y:Int, z:Int, var p:Int) extends Trojka(x,y,z){

}

class Kontener[T <: Para:Manifest]{
  var heap = new heapNormal[T]()

  def addElem(elem:T){
    heap.insert(elem)
  }
}

class Display(kontener: Kontener[Para]){
  def pokazPary(): Unit ={
    var i = 0
    while (i<kontener.heap.size ) {
      if(kontener.heap.tab(i).isInstanceOf[Para])
      println(kontener.heap.tab(i).x+", "+kontener.heap.tab(i).y)
      i += 1
    }
  }

  def pokazTrojki(): Unit ={
    var i = 0
    while (i<kontener.heap.size){
      if(kontener.heap.tab(i).isInstanceOf[Trojka])
        println(kontener.heap.tab(i).x+", "+kontener.heap.tab(i).y+", "+kontener.heap.tab(i).asInstanceOf[Trojka].z)
      i += 1
    }
  }

  def pokazCzworki(): Unit ={
    var i = 0
    while (i<kontener.heap.size){
      if(kontener.heap.tab(i).isInstanceOf[Czworka])
      println(kontener.heap.tab(i).x+", "+kontener.heap.tab(i).y+", "+
        kontener.heap.tab(i).asInstanceOf[Trojka].z+", "+kontener.heap.tab(i).asInstanceOf[Czworka].p)
      i += 1
    }
  }
}

object app extends App{
  var kontener = new Kontener[Para]
  kontener.addElem(new Para(1,2))
  kontener.addElem(new Trojka(2,5,8))
  kontener.addElem(new Para(2,9))
  kontener.addElem(new Trojka(7,8,9))
  kontener.addElem(new Czworka(115,7,8,9))
  val display = new Display(kontener)
  display.pokazPary()
  display.pokazTrojki()
  display.pokazCzworki()
}