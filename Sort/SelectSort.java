package cn.hff.sort;

import org.junit.Test;

/**
 * 选择排序
 * 
 * @author Holmofy
 *
 */
public class SelectSort implements SortAlgorithm {

	@Override
	public void sort(int[] items) {
		for (int i = 0; i < items.length; i++) {
			int minIndex = i;
			for (int j = i; j < items.length; j++) {
				if (items[j] < items[minIndex]) {
					// items[j] < items[maxIndex]
					minIndex = j;
				}
			}
			int temp = items[i];
			items[i] = items[minIndex];
			items[minIndex] = temp;
		}
	}

	public <TYPE extends Comparable<? super TYPE>> void sort(TYPE[] items) {
		for (int i = 0; i < items.length; i++) {
			int minIndex = i;
			for (int j = i; j < items.length; j++) {
				if (items[j].compareTo(items[minIndex]) < 0) {
					// items[j] < items[maxIndex]
					minIndex = j;
				}
			}
			TYPE temp = items[i];
			items[i] = items[minIndex];
			items[minIndex] = temp;
		}
	}

	private final Integer[] testItems = { 3, 6, 2, 5, 9, 0, 1, 7, 4, 8 };

	@Test
	public void testSelectSort() {
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
