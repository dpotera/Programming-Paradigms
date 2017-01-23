
class Pojazd(val prod:String, val model:String, var year:Int = -1, var plate:String = ""){

}

object Test extends App {
  new Pojazd("Fiat", "126p", 2016, "ESI123")
  new Pojazd("Fiat", "126p", 2016)
  new Pojazd("Fiat", "126p", plate = "ESI123")
  new Pojazd("Fiat", "126p")
}