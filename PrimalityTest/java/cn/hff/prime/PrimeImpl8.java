package cn.hff.prime;

public class PrimeImpl8 implements PrimeTest {
    public boolean isPrime(int n) {
	if (n <= 3)
	    return true;
	else if ((n & 1) == 0 || n % 3 == 0)
	    return false;
	for (int i = 5; i <= Math.sqrt(n); i += 6) {
	    if (n % i == 0 || n % (i + 2) == 0)
		return false;
	}
	return true;
    }
}