package co.mglvl

object Euler50 extends App{

  def isPrime(b: BigInt) = b.isProbablePrime(100)

  def primesBetween(i: Int, j: Int) =
    (BigInt(i) to BigInt(j) by BigInt(1)).filter(isPrime)

  def sumArray(xs: Iterable[BigInt]) = xs.scanLeft(BigInt(0))(_+_).toArray

  def findBiggestSumPrime() = {
    val max = 1000000
    val primes = primesBetween(0, max/100 /*Para acortar el espacio de busqueda*/)
    val sums = sumArray(primes)
    val _max = sums.length
    val indixes = (for {
      i <- (0 to _max)
       j <- (_max - 1 to i+1 by -1)
    } yield (i,j)).sortBy { case (i,j) => i - j }.toStream

    val (i1,i2) = indixes
      .filter{ case (i,j) => sums(j) - sums(i) <= max }
      .find{ case (i,j) => isPrime( sums(j) - sums(i) ) }.get
    (primes(i1),primes(i2), sums(i2)-sums(i1),i2-i1)
  }

  println(findBiggestSumPrime())

}
