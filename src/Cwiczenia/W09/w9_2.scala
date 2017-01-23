/*
class Time(private var h:Int, private var m:Int){
	require(0<=h && h<24)
	require(0<=m && m<60)

	def hour = h

	def hour_= (newHour:Int){
		require(0<=newHour && newHour<24)
		h = newHour
	}

	def minute = m

	def minute_= (newMinute:Int){
		require(0<=newMinute && newMinute<60)
		m = newMinute
	}

	def before(other:Time) = h<other.h || h==other.h && m<other.m
}


class Time(h:Int,m:Int){
	require(0<=h && h<24)
	require(0<=m && m<60)

	private var minutes = h*60 + m

	def hour = minutes/60

	def hour_= (newHour:Int){
		require(0<=newHour && newHour<24)
		minutes = minute + newHour*60
	}

	def minute = minutes%60

	def minute_= (newMinute:Int){
		require(0<=newMinute && newMinute<60)
		minutes = hour*60 + newMinute
	}

	def before(other:Time) = minutes < other.minutes
}

object Program{
	def main(args:Array[String]): Unit = {
		val t1 = new Time1(12,34)
		val t2 = new Time1(12,55)
		println(t1.hour+" "+t1.minute)
		t1.hour = 13
		t1.minute = 26
		println(t1.hour+" "+t1.minute)
		print(t1.before(t2))
	}
}
*/