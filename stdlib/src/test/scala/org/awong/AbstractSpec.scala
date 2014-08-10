package org.awong

import org.scalatest._


trait AbstractSpec
	extends Suite
	with Matchers
	with BeforeAndAfter
	with Logging

trait AbstractFlatSpec
	extends FlatSpecLike
	with BeforeAndAfter
	with AbstractSpec



abstract class AbstractWordSpec
	extends WordSpecLike
	with AbstractSpec
	with BeforeAndAfter
	with BeforeAndAfterAll
