package cn.hff.prime;

public class PrimeImpl7 implements PrimeTest {
    public boolean isPrime(int n) {
	if (n == 2)
	    return true;
	if ((n & 1) == 0)
	    return false;
	for (int i = 3; i <= Math.sqrt(n); i += 2) {
	    if (n % i == 0) {
		return false;
	    }
	}
	return true;
    }
}