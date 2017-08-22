因为1既不是素数也不是合数，所以下面的实现代码中不考虑小于2的情况。

* C语言源码可以到[这里](https://github.com/holmofy/PrimalityTest/blob/master/PrimalityTest%20.c)查看

* Java源码可以点击[这里](https://github.com/holmofy/PrimalityTest/tree/master/java/cn/hff/prime)查看

本文以C语言进行讲解

# 1. 暴力求解

最原始、最粗暴的方法就是从头到尾逐个进行检测，一旦遇到可被整除的数马上返回false

```c
bool is_prime_1(int n) {
	for (int i = 2; i < n; i++) {
		if (n % i == 0) {
			return false;
		}
	}
	return true;
}
```

<!-- more -->

该算法时间复杂度为$\frac{n}2$

# 2. sqrt开方

对于素数求解最简单的优化，就是对n进行开方，减少循环次数。

```c
bool is_prime_2(int n) {
	for (int i = 2; i <= sqrt(n); i++) {
		if (n % i == 0) {
			return false;
		}
	}
	return true;
}
```

该算法时间复杂度为$\sqrt{n}$

# 3.开方优化

使用乘法替代开方。数学库中的sqrt开方无非是使用迭代法实现，CPU可能对这些数学运算进行了优化，但与乘法相比sqrt显得太过耗时。

```c
bool is_prime_3(int n) {
	for (int i = 2; i * i <= n; i++) {
		if (n % i == 0) {
			return false;
		}
	}
	return true;
}
```

# 4.优化偶数检测

很显然除了2这个偶数是素数，其他所有的偶数都是合数，所以可以对输入数进行奇偶检测，优化算法。

下面的奇偶检测，使用二进制位运算进行优化：计算机中的数据以2进制进行存储，如果一个整数是偶数，则最后一位肯定是0，`(n & 1) == 0`即可判断一个数是否为偶数。计算机位运算显然比求余运算速度快。

```c
bool is_prime_4(int n) {
	if (n == 2)
		return true;
	if ((n & 1) == 0)
		return false;
	for (int i = 3; i * i <= n; i += 2) {
		if (n % i == 0) {
			return false;
		}
	}
	return true;
}
```

该算法时间复杂度为$\frac{\sqrt{n}}{2}$

# 5.排除已有素数的倍数

从1开始数，每6个数为1组，其中每一组的2、3、4、6的数可以表示成6k+2、6k+3、6k+4、6k+6，很明显这些数都能被2和3整出，所以我们对2和3进行检测后，这些数就可以不用检测了，而**可能**出现的素数在6k+1和6k+5位置上，这些数并没有被检测过，所以需要我们求取检测(虽然这些检测仍存在重复)。因为1既不是素数也不是合数，所以我们可以把需要检测的数标记为6k-1和6k+1，并从5开始检测。

![素数算法优化](http://img.blog.csdn.net/20170728212159346?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvSG9sbW9meQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

算法实现如下：

```c
bool is_prime_5(int n) {
	if (n <= 3)
		return true;
	else if ((n & 1) == 0 || n % 3 == 0)
		return false;
	for (int i = 5; i * i <= n; i += 6) {
		if (n % i == 0 || n % (i + 2) == 0)
			return false;
	}
	return true;
}
```

该算法的时间复杂度为$\frac{\sqrt{n}}{3}$

## 举一反三

聪明的你也许已经发现，前面说的偶数优化，也是用相同的原理。

![素数算法优化](http://img.blog.csdn.net/20170728212228229?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvSG9sbW9meQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

我们可以根据这个规律更进一步，让分组大小为`2*3*5=30`：

![素数算法优化](http://img.blog.csdn.net/20170728212250573?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvSG9sbW9meQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

我们只需要对7开始出现的素数进行检测即可。

```c
bool is_prime_6(int n) {
	if (n <= 3 || n == 5)
		return true;
	else if ((n & 1) == 0 || n % 3 == 0 || n % 5 == 0)
		return false;
	for (int i = 7; i * i <= n; i += 30) {
		if (n % (i - 7 + 7) == 0 || n % (i - 7 + 11) == 0 || n % (i - 7 + 13) == 0
			|| n % (i - 7 + 17) == 0 || n % (i - 7 + 19) == 0
			|| n % (i - 7 + 23) == 0 || n % (i - 7 + 29) == 0
			|| n % (i - 7 + 31) == 0)
			return false;
	}
	return true;
}
```

# 测试并比较

```c
void compare_and_test() {
	typedef bool(*PFUNC)(int);
	PFUNC pFunc[] = { is_prime_1, is_prime_2, is_prime_3,
		is_prime_4, is_prime_5, is_prime_6, is_prime_7,is_prime_8 };
	size_t length = sizeof(pFunc) / sizeof(PFUNC);
	size_t count = 0;
	struct timespec start, end;
	for (size_t i = 0; i < length; i++) {
		count = 0;
		timespec_get(&start, TIME_UTC);
		for (int j = 2; j < 100000; j++) {
			if ((*pFunc[i])(j)) {
				count++;
			}
		}
		timespec_get(&end, TIME_UTC);
		double duration = compute_duration(&end, &start);
		printf("%d\n%lf\n", count, duration);
	}
}
```

运行结果：

```shell
9592
1778329300.000000
9592
32828300.000000
9592
14184100.000000
9592
9072200.000000
9592
7018500.000000
9592
6040100.000000
```

