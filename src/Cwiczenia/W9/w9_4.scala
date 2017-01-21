
object UzycieWyjatkow{

  def main(args:Array[String]): Unit ={
    try{
      metoda1()
    } catch {
      case e:Exception =>
        System.err.println(e.getMessage + "\n")
        e.printStackTrace()
    }
  }

  def metoda1(){
    metoda2()
  }

  def metoda2(){
    metoda3()
  }

  def metoda3(){
    throw new Exception("Wyjatek zgloszony w metoda3")
  }

}

//java.lang.Exception: Wyjatek zgloszony w metoda3
//  at UzycieWyjatkow$.metoda3(w9_4.scala:23)
//  at UzycieWyjatkow$.metoda2(w9_4.scala:19)
//  at UzycieWyjatkow$.metoda1(w9_4.scala:15)
//  at UzycieWyjatkow$.main(w9_4.scala:6)
//  at UzycieWyjatkow.main(w9_4.scala)