package cn.hff.sort;

import org.junit.Test;

/**
 * 冒泡排序
 * 
 * @author Holmofy
 *
 */
public class BubbleSort implements SortAlgorithm {
	public void sort(int[] items) {
		for (int i = 0; i < items.length; i++) {
			for (int j = 0; j < items.length - i - 1; j++) {
				if (items[j] > items[j + 1]) {
					int temp = items[j];
					items[j] = items[j + 1];
					items[j + 1] = temp;
				}
			}
		}
	}

	public <TYPE extends Comparable<? super TYPE>> void sort(TYPE[] items) {
		for (int i = 0; i < items.length; i++) {
			for (int j = 0; j < items.length - i - 1; j++) {
				if (items[j].compareTo(items[j + 1]) > 0) {
					TYPE temp = items[j];
					items[j] = items[j + 1];
					items[j + 1] = temp;
				}
			}
		}
	}

	private final Integer[] testItems = { 3, 6, 2, 5, 9, 0, 1, 7, 4, 8 };

	@Test
	public void testBubbleSort() {
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
