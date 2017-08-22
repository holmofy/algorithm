package cn.hff.prime;

public class PrimeImpl0 implements PrimeTest {
    public boolean isPrime(int n) {
	for (int i = 2; i < n; i++) {
	    if (n % i == 0) {
		return false;
	    }
	}
	return true;
    }
}