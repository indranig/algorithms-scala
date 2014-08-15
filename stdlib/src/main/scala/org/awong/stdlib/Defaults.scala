package org.awong.stdlib

import com.typesafe.config._

object Defaults {
	// lazy val settings = new DefaultSettings()
	
	lazy val defaultEncoding = "UTF-8"
	lazy val defaultLang= "en"
	lazy val defaultCountry = "US"
	lazy val defaultLocale = new java.util.Locale(defaultLang, defaultCountry)
}



// Whenever you write a library, allow people to supply a Config but
// also default to ConfigFactory.load if they don't supply one.
// Libraries generally have some kind of Context or other object
// where it's convenient to place the configuration.

// we have a constructor allowing the app to provide a custom Config
class DefaultSettings(val config: Config) extends org.awong.Logging {

	lazy val namespace = "algorithms-scala"
	// This verifies that the Config is sane and has our
	// reference config. Importantly, we specify the "simple-lib"
	// path so we only validate settings that belong to this
	// library. Otherwise, we might throw mistaken errors about
	// settings we know nothing about.
	config.checkValid(ConfigFactory.defaultReference(), namespace)

	// This uses the standard default Config, if none is provided,
	// which simplifies apps willing to use the defaults
	def this() {
		this(ConfigFactory.load())
	}
	
	lazy val defaultEncoding = config.getString(namespace + ".defaultEncoding")
	lazy val defaultLang= config.getString(namespace +  ".lang")
	lazy val defaultCountry = config.getString(namespace +  ".country")
	
	lazy val defaultLocale = new java.util.Locale(defaultLang, defaultCountry)
}

