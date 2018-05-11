package cn.hff.sort;

import org.junit.Test;

/**
 * 希尔排序
 * 
 * @author Holmofy
 *
 */
public class ShellSort implements SortAlgorithm {

	@Override
	public void sort(int[] items) {
		for (int step = items.length / 2; step > 0; step /= 2) {
			// 内层其实就是一个步长为step的插入排序
			for (int i = step; i < items.length; i++) {
				int current = items[i];
				int j = i - step;
				do {
					if (current < items[j]) {
						items[j + step] = items[j];// 后移
					} else {
						break;
					}
					j -= step;
				} while (j >= 0);
				items[j + step] = current;// 插入
			}
		}
	}

	public <TYPE extends Comparable<? super TYPE>> void sort(TYPE[] items) {
		for (int step = items.length / 2; step > 0; step /= 2) {
			// 内层其实就是一个步长为step的插入排序
			for (int i = step; i < items.length; i++) {
				TYPE current = items[i];
				int j = i - step;
				do {
					if (current.compareTo(items[j]) < 0) {
						items[j + step] = items[j];// 后移
					} else {
						break;
					}
					j -= step;
				} while (j >= 0);
				items[j + step] = current;// 插入
			}
		}
	}

	private final Integer[] testItems = { 3, 6, 2, 5, 9, 0, 1, 7, 4, 8 };

	@Test
	public void testShellSort() {
		System.out.print("排序前：");
		for (int i = 0; i < testItems.length; i++) {
			System.out.print(testItems[i] + " ");
		}
		sort(testItems);

		System.out.print("\n排序后：");
		for (int i = 0; i < testItems.length; i++) {
			System.out.print(testItems[i] + " ");
		}
	}

}
