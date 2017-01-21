package W6

class w6 {


  def whileLoop(cond: =>Boolean, expr: =>Unit) {
    if(cond) { expr; whileLoop(cond,expr)}
  }

  var x = 1
  whileLoop(x <= 5,{ println(x); x+=1; })


  def swap(tab:Array[Int],i:Int,j:Int) = {
    val aux = tab(i); tab(i) = tab(j); tab(j) = aux
  }

  def choose_pivot(tab:Array[Int],m:Int,n:Int) = tab((m+n)/2)

  def partition(tab:Array[Int],l:Int,r:Int) = {
    var i = l; var j = r; val pivot = choose_pivot(tab,l,r)
    while(i<=j){
      while (tab(i)<pivot) i+=1
      while (pivot<tab(j)) j-=1
      if (i<=j){
        swap(tab,i,j); i+=1; j-=1
      }
    }
    (i,j)
  }

  def quick(tab:Array[Int],l:Int,r:Int):Unit = {
    if(l<r){
      val (i,j) = partition(tab,l,r)
      if(j-1<r-i) {quick(tab,l,j); quick(tab,i,r)}
      else {quick(tab,i,r); quick(tab,l,j)}
    } else ()
  }

  def quicksort(tab:Array[Int]) = quick(tab,0,tab.length-1)

  val arr = Array(2,8,1,4,7,5,6,3)
  quicksort(arr)
  arr

}
