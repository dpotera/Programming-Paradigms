package L1

class l1 {

  /*  PASCAL TRIANGLE
  * c - collumn
  * r - row
  * */
  def pascal(c:Int, r:Int):Int = {
    if (c>r) throw new IllegalArgumentException()
    if(c==0 || c==r) 1
    else pascal(c-1,r-1) + pascal(c,r-1)
  }

  pascal(2,10)
  pascal(8,10)
  pascal(8,2)

  /*
    CHECK BRACKETS
   */

  def balance(string:String) = {
    def checkBrackets(str: List[Char], b: Int): Int = {
      if (b < 0) -1
      else if (str == Nil) b
      else if (str.head == '(') checkBrackets(str.tail, b + 1)
      else if (str.head == ')') checkBrackets(str.tail, b - 1)
      else checkBrackets(str.tail, b)
    }
    checkBrackets(string.toList,0) == 0
  }

  balance("(if (zero? x) max (/ 1 x))")
  balance("())(")
  balance(")()(")



}