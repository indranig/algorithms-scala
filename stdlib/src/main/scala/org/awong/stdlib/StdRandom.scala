package org.awong.stdlib

object StdRandom {
	var seed: Long = System.currentTimeMillis()
	private var randomGen = new util.Random(seed)

	def setSeed(s: Long): Unit = {
		seed = s
		randomGen = new util.Random(seed)
	}
	
	/**
	 * Return real number uniformly in [0, 1).
	 */
	def uniform(): Double = randomGen.nextDouble()

	/**
	 * Return an integer uniformly between 0 and N-1.
	 */
	def uniform(n: Int): Int = randomGen.nextInt(n)

	/**
	 * Return real number uniformly in [0, 1).
	 */
	def random(): Double = uniform()
	
	/**
	 * Return int uniformly in [a, b).
	 */
	def uniform(a: Int, b: Int): Int = a + uniform(b-a)
	
	/**
	 * Return real number uniformly in [a, b).
	 */
	def uniform(a: Double, b: Double): Double = a + uniform() * (b-a)

	/**
	 * Return a boolean, which is true with probability p, and false otherwise.
	 */
	def bernoulli(p: Double): Boolean = (uniform() < p)
	/**
	 * Return a boolean, which is true with 50%, and false otherwise.
	 */
	def bernoulli(): Boolean = bernoulli(0.5)

	/**
	 * Return a real number with a standard Gaussian distribution.
	 */
	def gaussian(): Double = {
		// use the polar form of the Box-Muller transform
		var r = 0d
		var x = 0d
		var y = 0d
		do {
			x = uniform(-1.0, 1.0)
			y = uniform(-1.0, 1.0)
			r = x*x + y*y
		} while (r >= 1 || r == 0)
		// Remark:  y * Math.sqrt(-2 * Math.log(r) / r) is an independent random gaussian
		x * Math.sqrt(-2 * Math.log(r) / r);
	}
	
	/**
	 * Return a real number from a gaussian distribution with given mean and stddev
	 */
	def gaussian(mean: Double, stddev: Double): Double = {
		mean + stddev * gaussian()
	}
	/**
	 * Return an integer with a geometric distribution with mean 1/p.
	 */
	def geometric(p: Double): Int  = {
		// using algorithm given by Knuth
		val aDouble = Math.ceil(Math.log(uniform()) / Math.log(1.0 - p))
		aDouble.toInt
	}

	/**
	 * Return an integer with a Poisson distribution with mean lambda.
	 */
	def poisson(lambda: Double): Int = {
		// using algorithm given by Knuth
		// see http://en.wikipedia.org/wiki/Poisson_distribution
		var k: Int = 0
		var p  = 1.0d
		var L = Math.exp(-lambda);
		do {
			k = k + 1
			p = p * uniform();
		} while (p >= L);
		k-1;
	}

	/**
	 * Return a real number with a Pareto distribution with parameter alpha.
	 */
	def pareto(alpha: Double): Double = {
		Math.pow(1 - uniform(), -1.0/alpha) - 1.0;
	}

	/**
	 * Return a real number with a Cauchy distribution.
	 */
	def cauchy(): Double = {
		Math.tan(Math.PI * (uniform() - 0.5));
	}

	/**
	 * Return a number from a discrete distribution: i with probability a[i].
	 */
	def discrete(a: Seq[Double]): Int = {
		// precondition: sum of array entries equals 1
		var r = uniform()
		var sum = 0.0d;
		val sums =  a.zipWithIndex.map { case (each, index) =>
			sum = each + sum
			(sum, index)
		}
		val maybeInt = sums.find{ case(dub, index) => dub >= r }
		maybeInt match {
			case Some((_,i)) => i
			case None => -1 // this should not happen
		}
	}

	/**
	 * Return a real number from an exponential distribution with rate lambda.
	 */
	def exp(lambda: Double): Double = -Math.log(1 - uniform()) / lambda

}