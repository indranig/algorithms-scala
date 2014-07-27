package org.awong

import org.scalatest._

import org.scalatest.matchers.MustMatchers
import org.scalatest.matchers.ShouldMatchers

trait AbstractSpec
	extends Suite
	with ShouldMatchers
	with MustMatchers
	with BeforeAndAfter
	with Logging

trait AbstractFlatSpec
	extends FlatSpec
	with BeforeAndAfter
	with AbstractSpec



abstract class AbstractWordSpec
	extends WordSpec
	with AbstractSpec
	with BeforeAndAfter
	with BeforeAndAfterAll
