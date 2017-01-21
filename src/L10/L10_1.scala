package L10

class Person {
  def say(){scala.Console.print("I'm Person")}
}

class Student extends Person{
  var age:Int = 0

  def setAge(newAge:Byte){ age = newAge }

  override def say(){scala.Console.print("I'm Student")}
  def say(name:String){scala.Console.print("I'm "+name)}
}

class Program{
  val p = new Person()
  val s = new Student()

  p.say()
  s.say()
  s.say("Dominik")

  s.setAge(20)

  scala.Console.print(Array(' ',' ',' ').toString)

}
