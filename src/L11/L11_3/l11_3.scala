package L11.L11_3

trait heap[T]{
  def insert(elem:T)
  def top():T
  def pop():T
  def check(elem:T):Boolean
}

class heapMax[T <: Comparable[T]:Manifest] extends heap[T]{
  val tab = new Array[T](100)
  var size = 0

  def swap(i:Int,j:Int){
    val aux = tab(i)
    tab(i) = tab(j)
    tab(j) = aux
  }

  override def insert(elem: T){
    var index = size
    tab(index) = elem
    while(index > 0 && tab(index).compareTo(tab(index/2)) > 0){
      swap(index,index/2); index /= 2
    }
    size += 1
  }

  override def top(): T = if(size>0) tab(0) else throw new Exception("Empty Heap")

  override def pop(): T = if(size == 0) throw new Exception("Empty Heap") else {
    def sink(i:Int): Unit = {
      if(2*i < size && tab(i).compareTo(tab(2*i)) < 0){
        swap(i,2*i); sink(2*i)
      }
      if(2*i+1 < size && tab(i).compareTo(tab(2*i+1)) < 0){
        swap(i,2*i+1); sink(2*i+1)
      }
    }
    val top = tab(0)
    tab(0) = tab(size-1)
    size -= 1
    sink(0)
    top
  }

  override def check(elem: T): Boolean = {
    var i = 0
    while(i < size) {
      if(tab(i) == elem) return true
      i += 1
    }
    false
  }
}

class heapNormal[T:Manifest] extends heap[T]{
  val tab = new Array[T](100)
  var size = 0

  def swap(i:Int,j:Int){
    val aux = tab(i)
    tab(i) = tab(j)
    tab(j) = aux
  }

  override def insert(elem: T): Unit = {
    tab(size) = elem
    size += 1
  }

  override def top(): T = if(size>0) tab(0) else throw new Exception("Empty Heap")

  override def pop(): T = {
    if(size <= 0) throw new Exception("Empty Heap")
    val top = tab(0)
    swap(0,size-1)
    size -= 1
    top
  }

  override def check(elem: T): Boolean = {
    var i = 0
    while(i < size) {
      if(tab(i) == elem) return true
      i += 1
    }
    false
  }
}

object run extends App{
  var heap = new heapMax[Integer]
  heap.insert(5)
  heap.insert(8)
  heap.insert(2)
  heap.insert(20)
  heap.insert(12)
  println(heap.pop())
  println(heap.pop())
  println(heap.pop())
  println(heap.isInstanceOf[heap[Integer]])
}
