package L10_3

class TicTacToe{
  var array:Array[Array[Char]] = Array(Array(' ',' ',' '),Array(' ',' ',' '),Array(' ',' ',' '))

  def makeMove(x:Byte,y:Byte,value:Char):Boolean = {
    if(array(x)(y) == ' ') {array(x)(y) = value; true}
    else false
  }

  def isFull():Boolean = {
    for(i <- 0 to 2)
      for(j <- 0 to 2)
        if (array(i)(j)==' ') return false
    true
  }

  def isDraw() = isFull() && checkWin() == ' '

  def checkWin():Char = {
    for(i<-0 to 2) {
      if(array(i)(0) == array(i)(1) && array(i)(1) == array(i)(2) && array(i)(2) != ' ') return array(i)(0)
      if(array(0)(i) == array(1)(i) && array(1)(i) == array(2)(i) && array(2)(i) != ' ') return array(0)(i)
    }
    if(array(0)(0) == array(1)(1) && array(1)(1) == array(2)(2) && array(2)(2) != ' ') return array(0)(0)
    if(array(0)(2) == array(1)(1) && array(1)(1) == array(2)(0) && array(2)(0) != ' ') return array(2)(0)
    ' '
  }

  def checkBounds(x:Int) = 0 <= x  && x <= 2

}

class TTTGui {
  var turn = 'X'
  val ttt = new TicTacToe()

  def printBoard(){
    val line = "\n -------------\n"
    var board = line
    for(i <- 0 to 2)
      for(j <- 0 to 2) {
        board += " | " + ttt.array(i)(j) + (if(j==2) " |"+line else "")
    }
    scala.Console.print(board)
  }

  def readMove() = {
    var x:Byte = 0
    var y:Byte = 0
    var succ = false
    do{
      do {
        scala.Console.print("Type position X: ")
        x = scala.io.StdIn.readByte()
        if(!ttt.checkBounds(x)) scala.Console.print("Invalid X. Enter x from 0 to 2!\n")
      } while(!ttt.checkBounds(x))
      do {
        scala.Console.print("Type position Y: ")
        y = scala.io.StdIn.readByte()
        if(!ttt.checkBounds(y)) scala.Console.print("Invalid Y. Enter y from 0 to 2!\n")
      } while(!ttt.checkBounds(y))
      succ = ttt.makeMove(y,x,turn)
      if(!succ) scala.Console.print("Position Taken! Enter new one\n")
    } while (!succ)
  }

  def play() = {
    do{
      scala.Console.print("            "+turn+" trun!\n")
      readMove()
      printBoard()
      if(ttt.checkWin()!=' ') scala.Console.print(turn+" is a WINNER!\n")
      if(ttt.isDraw()) scala.Console.print("DRAW!\n")
      turn = if(turn=='X') 'O' else 'X'
    } while (!ttt.isFull() && ttt.checkWin()==' ')
  }
}

object Program{
  def main(args:Array[String]): Unit = {
    val t = new TTTGui()
    t.play()
  }
}
