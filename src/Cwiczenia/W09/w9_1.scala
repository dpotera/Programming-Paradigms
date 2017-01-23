
class Time(private var h:Int){
	if(h<0) h = 0

	def hour = h

	def hour_= (newHour:Int){
		h = if(newHour>0) newHour else 0
	}

}

object Time {
	def apply(h:Int) = new Time(h)
}



