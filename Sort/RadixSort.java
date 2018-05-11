package cn.hff.sort;

import java.util.Arrays;
import org.junit.Test;

/**
 * 基数排序
 * 
 * @author Holmofy
 *
 */
public class RadixSort implements SortAlgorithm {

	@Override
	public void sort(int[] items) {
		radixSort(items);
	}

	public void radixSort(int[] items) {
		radixSort(items, 10);
	}

	// 可以使用其他基数值
	public void radixSort(int[] items, int radix) {
		int maxLength = 0;
		for (int i = 0; i < items.length; i++) {
			int itemLength = length(items[i], radix);
			if (maxLength < itemLength) {
				maxLength = itemLength;
			}
		}

		int[] counter = new int[radix];
		int[] result = new int[items.length];

		for (int i = 0, r = 1; i < maxLength; i++, r *= radix) {
			Arrays.fill(counter, 0);

			// 内部就是一个计数排序

			// 计数
			for (int j = 0; j < items.length; j++) {
				counter[(items[j] / r) % radix]++;
			}
			// 计数和
			for (int j = 1; j < radix; j++) {
				counter[j] += counter[j - 1];
			}
			// 整理出新序列
			for (int j = items.length - 1; j >= 0; j--) {
				int item = items[j];
				result[--counter[(item / r) % radix]] = item;
			}

			System.arraycopy(result, 0, items, 0, items.length);
		}
	}

	private int length(int num, int radix) {
		int l = 1;
		for (; num >= radix; num /= radix, l++)
			;
		return l;
	}

	private final int[] testItems = new int[] { 17, 256, 100, 404, 7, 9, 333, 2048, 10086, 100 };

	@Test
	public void testRadixSort() {
		System.out.println("排序前：" + Arrays.toString(testItems));
		radixSort(testItems);
		System.out.println("排序后：" + Arrays.toString(testItems));
	}
}
