package cn.hff.prime;

public class Main {
    public static void main(String[] args) {
	PrimeTest[] pt = { new PrimeImpl0(), new PrimeImpl1(), new PrimeImpl2(),
		new PrimeImpl3(), new PrimeImpl4(), new PrimeImpl7(),
		new PrimeImpl6(), new PrimeImpl8() };
	for (int j = 0; j < pt.length; j++) {
	    int count = 0;
	    long start = System.nanoTime();
	    for (int i = 0; i < 100000; i++) {
		if (pt[j].isPrime(i)) {
		    count++;
		}
	    }
	    long end = System.nanoTime();
	    System.out.println(count);
	    System.out.println(end - start);
	}
    }
}
