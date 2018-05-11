package cn.hff.sort;

import java.util.Arrays;
import org.junit.Test;

/**
 * 鸽巢排序
 * 
 * @author Holmofy
 *
 */
public class PigeonholeSort implements SortAlgorithm {

	@Override
	public void sort(int[] items) {
		pigeonholeSort(items);
	}

	public void pigeonholeSort(int[] items) {
		// 遍历一遍来获取序列的最大值和最小值
		int max = items[0], min = items[0];
		for (int i : items) {
			if (i > max) {
				max = i;
			}
			if (i < min) {
				min = i;
			}
		}
		int size = max - min + 1;
		int[] hole = new int[size];
		for (int i = 0; i < items.length; i++)
			hole[items[i] - min]++;

		for (int i = 0, j = 0; i < hole.length; i++)
			for (int k = 0; k < hole[i]; k++)
				items[j++] = i + min;
	}

	private final int[] testItems = new int[] { 16, 2, 10, 14, 7, 9, 3, 2, 8, 1 };

	@Test
	public void testPigeonholeSort() {
		System.out.println("排序前：" + Arrays.toString(testItems));
		pigeonholeSort(testItems);
		System.out.println("排序后：" + Arrays.toString(testItems));
	}
}
