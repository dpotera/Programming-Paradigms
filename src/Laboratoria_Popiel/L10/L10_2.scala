package L10_2

import scala.util.Try

class Operand{
  var isValue = false
  var value = 0
  var operator:Operator = null

  def this(i:Int) = {
    this()
    isValue = true
    value = i
  }

  def this(op:Operator) = {
    this()
    isValue = false
    operator = op
  }
}

trait Operator{
   def calc(a:Int, b:Int):Int
}

class Adder extends Operator{
  override def calc(a: Int, b: Int):Int = a + b
}

class Substracter extends Operator{
  override def calc(a: Int, b: Int): Int = a - b
}

class Multipler extends Operator{
  override def calc(a: Int, b: Int): Int = a * b
}

class Divider extends Operator{
  override def calc(a: Int, b: Int): Int = a / b
}

class Stack[A]{
  var list:List[A] = Nil

  def push(e:A) = list = e::list

  def pop() = list match {
    case Nil => throw new Exception("Empty Stack!")
    case h::t => list = t; h
  }

  def isEmpty() = list == Nil
}

class CalculatorGUI{
  def getOperand():Operand = {
    scala.Console.println("Add new operand:\n<Number>. Value\n+. Add\n-. Substract\n*. Multiply\n/. Divide")
    val opt = scala.io.StdIn.readLine()
    val isInt = Try(opt.toInt).isSuccess
    if(isInt) {
      new Operand(opt.toInt)
    } else {
      scala.Console.println("Enter 2 Operand arguments for your operator: ")
      opt match {
        case "+" => new Operand(new Adder())
        case "-" => new Operand(new Substracter())
        case "*" => new Operand(new Multipler())
        case "/" => new Operand(new Divider())
        case _ => throw new Exception("invalid option choosen")
      }
    }
  }

  def loadExpression():Stack[Operand] = {
    val stack = new Stack[Operand]()
    var i = 1
    do {
      val op = getOperand()
      stack.push(op)
      if(!op.isValue) i += 2
      i -= 1
    } while(i > 0)
    stack
  }

}

object Calculator {
  val gui = new CalculatorGUI()
  val stack = gui.loadExpression()

  def main(args:Array[String]):Unit = {
    scala.Console.println("RESULT: "+calculateValue())
  }

  def calculateValue() = {
    val values = new Stack[Int]()
    while(!stack.isEmpty()) {
      val op = stack.pop()
      if(op.isValue) values.push(op.value)
      else {
        val a = values.pop()
        values.push(op.operator.calc(a,values.pop()))
      }
    }
    values.pop()
  }

}