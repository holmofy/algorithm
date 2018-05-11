package cn.hff.sort;

import java.util.Arrays;
import org.junit.Test;

/**
 * 简单计数排序
 * 
 * @author Holmofy
 *
 */
public class CountingSort {

	public int[] countingSort(int[] items, int max) {
		int[] result = new int[items.length];
		// 假设A中的数据a'有，0<=a' && a' < k并且k=17
		int k = max + 1;
		countingSort(items, result, k);
		return result;
	}

	private void countingSort(int[] items, int[] result, int k) {
		int[] counter = new int[k];

		// 计数
		for (int j = 0; j < items.length; j++) {
			int a = items[j];
			counter[a] += 1;
		}

		// 求计数和
		for (int i = 1; i < k; i++) {
			counter[i] = counter[i] + counter[i - 1];
		}

		// 整理出新序列
		// 注意这里需要从后开始读取
		for (int j = items.length - 1; j >= 0; j--) {
			int a = items[j];
			result[--counter[a]] = a;
		}
	}

	private final int[] testItems = new int[] { 16, 2, 10, 14, 7, 9, 3, 2, 8, 1 };

	@Test
	public void testCountSort() {
		System.out.println("排序前：" + Arrays.toString(testItems));
		System.out.println("排序后：" + Arrays.toString(countingSort(testItems, 16)));
	}
}
