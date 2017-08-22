package cn.hff.prime;

public class PrimeImpl6 implements PrimeTest {
    public boolean isPrime(int n) {
	for (int i = 2; i <= Math.sqrt(n); i++) {
	    if (n % i == 0) {
		return false;
	    }
	}
	return true;
    }
}