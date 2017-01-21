
object w10_4 extends App {

  import scala.collection.mutable

  def copy[T](dest:mutable.Seq[T], src:Seq[T]){
    require(dest.length >= src.length)
    var i = 0
    src.foreach(elem => {
      dest.update(i,elem)
      i = i+1
    })
  }

  val a = mutable.Seq(1, 2)
  val b = mutable.Seq(3, 4)
  copy(a, b)
  print(a)
}
