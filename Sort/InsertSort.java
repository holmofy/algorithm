package cn.hff.sort;

import org.junit.Test;

/**
 * 插入排序
 * 
 * @author Holmofy
 *
 */
public class InsertSort implements SortAlgorithm {
	@Override
	public void sort(int[] items) {
		for (int i = 1; i < items.length; i++) {
			int current = items[i], j = i - 1;
			for (; j >= 0 && current < items[j]; j--)
				items[j + 1] = items[j];
			items[j + 1] = current;
		}
	}

	public <TYPE extends Comparable<? super TYPE>> void sort(TYPE[] items) {
		for (int i = 1; i < items.length; i++) {
			TYPE current = items[i];
			int j = i - 1;
			for (; j >= 0 && current.compareTo(items[j]) < 0; j--)
				items[j + 1] = items[j];
			items[j + 1] = current;
		}
	}

	private final Integer[] testItems = { 3, 6, 2, 5, 9, 0, 1, 7, 4, 8 };

	@Test
	public void testInsertSort() {
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
