package org.awong.stdlib

import com.typesafe.config.ConfigFactory

object Defaults {
	lazy val config = ConfigFactory.load
	
	lazy val defaultEncoding = config.getString("application.defaultEncoding")
	lazy val defaultLang= config.getString("application.lang")
	lazy val defaultCountry = config.getString("application.country")
	lazy val defaultLocale = new java.util.Locale(defaultLang, defaultCountry)
}
