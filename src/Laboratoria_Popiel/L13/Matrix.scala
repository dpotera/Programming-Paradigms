package Laboratoria_Popiel.L13

object Matrix {
  private def multiplyMatrix(matrix1: Array[Array[Int]], matrix2: Array[Array[Int]], matrix3: Array[Array[Int]]): Boolean = {
    if (matrix1(0).length == matrix2.length) {
      val tmp: Array[Thread] = new Array[Thread](matrix1.length)
      var i: Int = 0
      while (i < matrix1.length) {
        tmp(i) = new Matrix.rowMultiplier(matrix1, matrix2, matrix3, i)
        tmp(i).start()
        i += 1
      }
      try {
        var i: Int = 0
        while (i < tmp.length) {
          tmp(i).join()
          i += 1
        }
      }
      catch { case e: InterruptedException => e.printStackTrace() }
      return true
    }
    false
  }

  def main(args: Array[String]) {
    val M1: Array[Array[Int]] = Array(Array(-2, -3, 1), Array(-1, 4, 0))
    val M2: Array[Array[Int]] = Array(Array(-2, -1, 2), Array(3, 0, 1), Array(2, 2, -1))
    val M3: Array[Array[Int]] = Array(Array(0,0,0),Array(0,0,0))
    multiplyMatrix(M1, M2, M3)
    print(M3)
  }

  private class rowMultiplier(var matrix1: Array[Array[Int]], var matrix2: Array[Array[Int]], var resultMatrix: Array[Array[Int]], val row: Int) extends Thread {
    override def run {
      var i: Int = 0
      while (i < matrix2(0).length) {
        var j: Int = 0
        while (j < matrix1(0).length) {
          resultMatrix(row)(i) += (matrix1(row)(j) * matrix2(j)(i))
          j += 1
        }
        i += 1
      }
    }
  }

  private def print(arr: Array[Array[Int]]) {
    var i: Int = 0
    while (i < arr.length) {
      var j: Int = 0
      while (j < arr(i).length) {
        System.out.print(arr(i)(j) + ", ")
        j += 1
      }
      System.out.print("\n")
      i += 1
    }
  }
}
