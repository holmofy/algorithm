package cn.hff.sort;

import org.junit.Test;
import java.util.Arrays;

/**
 * 计数排序
 * 
 * @author Holmofy
 *
 */
public class CountSort implements SortAlgorithm {

	@Override
	public void sort(int[] items) {
		int[] result = countSort(items);
		System.arraycopy(result, 0, items, 0, result.length);
	}

	public int[] countSort(int[] items) {
		int[] result = new int[items.length];

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

		// 这里k的大小是要排序的数组中，元素大小的极值差+1
		int k = max - min + 1;
		int counter[] = new int[k];

		// 计数
		for (int i = 0; i < items.length; i++) {
			counter[items[i] - min]++;// 优化过的地方，减小了数组c的大小
		}

		// 求计数和
		for (int i = 1; i < counter.length; i++) {
			counter[i] = counter[i] + counter[i - 1];
		}

		// 整理出新序列
		for (int i = items.length - 1; i >= 0; i--) {
			int item = items[i];
			result[--counter[item - min]] = item;// 按存取的方式取出c的元素
		}
		return result;
	}

	private final int[] testItems = new int[] { -1, -3, 16, 2, 10, 14, 7, 9, 3, 2, 8, 1, -4 };

	@Test
	public void testCountSort() {
		System.out.println("排序前：" + Arrays.toString(testItems));
		System.out.println("排序后：" + Arrays.toString(countSort(testItems)));
	}

}
