package cn.hff.sort;

import java.util.Arrays;

import org.junit.Test;

/**
 * 桶排序
 * 
 * @author Holmofy
 *
 */
public class ArrayBucketSort implements SortAlgorithm {

	@Override
	public void sort(int[] items) {
		bucketSort(items);
	}

	public void bucketSort(int items[]) {
		bucketSort(items, 10);
	}

	public void bucketSort(int items[], int radix) {
		int n = items.length;
		int bucket[][] = new int[radix][n];// 要考虑最坏的情况，所有的元素入同一个桶
		int counter[] = new int[radix];
		int maxLength = 0;
		for (int i = 0; i < items.length; i++) {
			int itemLength = length(items[i], radix);
			if (maxLength < itemLength) {
				maxLength = itemLength;
			}
		}

		for (int i = 0, r = 1; i < maxLength; i++, r *= radix) {
			Arrays.fill(counter, 0);// 上一轮的计数器置空

			// 入桶
			for (int j = 0; j < items.length; j++) {
				int digit = (items[j] / r) % radix;
				bucket[digit][counter[digit]++] = items[j];
			}

			// 出桶
			for (int j = 0, index = 0; j < radix; j++) {
				for (int k = 0; k < counter[j]; k++) {
					items[index++] = bucket[j][k];
				}
			}
		}
	}

	private int length(int num, int radix) {
		int l = 1;
		for (; num >= radix; num /= radix, l++)
			;
		return l;
	}

	// 测试代码
	private final int[] testItems = new int[] { 16, 2, 10, 14, 7, 9, 3, 2, 8, 1 };

	@Test
	public void testCountSort() {
		System.out.println("排序前：" + Arrays.toString(testItems));
		bucketSort(testItems);
		System.out.println("排序后：" + Arrays.toString(testItems));
	}
}
