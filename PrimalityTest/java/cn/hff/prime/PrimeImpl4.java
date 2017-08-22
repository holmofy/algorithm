package cn.hff.prime;

public class PrimeImpl4 implements PrimeTest {
    public boolean isPrime(int n) {
	if (n <= 3 || n == 5)
	    return true;
	else if ((n & 1) == 0 || n % 3 == 0 || n % 5 == 0)
	    return false;
	for (int i = 7; i * i <= n; i += 30) {
	    if (n % i == 0 || n % (i + 4) == 0 || n % (i + 6) == 0
		    || n % (i + 10) == 0 || n % (i + 12) == 0
		    || n % (i + 16) == 0 || n % (i + 22) == 0
		    || n % (i + 24) == 0)
		return false;
	}
	return true;
    }
}