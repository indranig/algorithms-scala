package org

import io.Source

package object awong {

	/**
	 * An alias for the `Nothing` type.
	 * Denotes that the type should be filled in.
	 */
	type ??? = Nothing

	/**
	 * An alias for the `Any` type.
	 * Denotes that the type should be filled in.
	 */
	type *** = Any
	
	/**
	 * Get a resource from the `src/main/resources` directory. Eclipse does not copy
	 * resources to the output directory, then the class loader cannot find them.
	 */
	def resourceAsStreamFromSrc(resourcePath: List[String]): Option[Source] = {
		StdIO.resourceAsStreamFromSrc(resourcePath)
	}
	def resourceAsStringStreamFromSrc(resourcePath: List[String]): Option[Stream[String]] = {
		StdIO.resourceAsStringStreamFromSrc(resourcePath)
	}

}