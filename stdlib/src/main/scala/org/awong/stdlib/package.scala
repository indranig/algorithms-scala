package org.awong

package object stdlib {
	object Defaults {
		lazy val defaultEncoding = StdIO.config.getString("application.defaultEncoding")
		lazy val defaultLang= StdIO.config.getString("application.lang")
		lazy val defaultCountry = StdIO.config.getString("application.country")
		lazy val defaultLocale = new java.util.Locale(defaultLang, defaultCountry)
	}

	object BinaryDump {
	
	}
	
	object BinaryIn {
	
	}
	
	object BinaryOut {
	
	}
	
	object BinaryStdIn {
	
	}
	
	object BinaryStdInTester {
	
	}
	
	object BinaryStdOut {
	
	}
	
	object BinaryStdOutTester {
	
	}
	
	object Copy {
	
	}
	
	object Draw {
	
	}
	
	object DrawListener {
	
	}
	
	object HexDump {
	
	}
	
	
	object Out {
	
	}
	
	object Picture {
	
	}
	
	object PictureDump {
	
	}
	
	object StdArrayIO {
	
	}
	
	object StdAudio {
	
	}
	
	object StdDraw {
	
	}
	
	object StdDraw3D {
	
	}
	
	object StdIn {
	
	}
	
	object StdOut {
	
	}
	
	object StdRandom {
	
	}
	
	object StdStats {
	
	}
	
	object Stopwatch {
	
	}

}