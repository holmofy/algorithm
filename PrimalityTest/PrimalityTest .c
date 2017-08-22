#include<stdbool.h>
#include<stdio.h>
#include<time.h>
#include<math.h>
#include<assert.h>

#define NDEBUG

// 暴力方式
bool is_prime_1(int n) {
	assert(n > 1);
	for (int i = 2; i < n; i++) {
		if (n % i == 0) {
			return false;
		}
	}
	return true;
}

// sqrt开方
bool is_prime_2(int n) {
	assert(n > 1);
	for (int i = 2; i <= sqrt(n); i++) {
		if (n % i == 0) {
			return false;
		}
	}
	return true;
}

// 开方优化
bool is_prime_3(int n) {
	assert(n > 1);
	for (int i = 2; i * i <= n; i++) {
		if (n % i == 0) {
			return false;
		}
	}
	return true;
}

// 优化偶数检测
bool is_prime_4(int n) {
	assert(n > 1);
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

// 排除已有素数的倍数
bool is_prime_5(int n) {
	assert(n > 1);
	if (n <= 3)
		return true;
	else if ((n & 1) == 0 || n % 3 == 0)
		return false;
	for (int i = 5; i * i <= n; i += 6) {
		if (n % (i + 1 - 1) == 0 || n % (i + 1 + 1) == 0)
			return false;
	}
	return true;
}

// 排除已有素数的倍数
bool is_prime_6(int n) {
	assert(n > 1);
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

double compute_duration(struct timespec *end, struct timespec *start) {
	double d_sec = difftime(end->tv_sec, start->tv_sec);
	long d_nsec = end->tv_nsec - start->tv_nsec;
	return d_sec*1E9 + d_nsec;
}

void compare_and_test() {
	typedef bool(*PFUNC)(int);
	PFUNC pFunc[] = { is_prime_1, is_prime_2, is_prime_3,
		is_prime_4, is_prime_5, is_prime_6 };
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

int main() {
	compare_and_test();
}
