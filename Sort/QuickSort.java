package cn.hff.sort;

import org.junit.Test;

/**
 * 快速排序
 * 
 * @author Holmofy
 *
 */
public class QuickSort implements SortAlgorithm {

	@Override
	public void sort(int[] items) {
		quickSort(items, 0, items.length - 1);
	}

	public <TYPE extends Comparable<? super TYPE>> void sort(TYPE[] items) {
		quickSort(items, 0, items.length - 1);
	}

	private <TYPE extends Comparable<? super TYPE>> void quickSort(TYPE[] items, int start, int end) {
		if (start < end) {
			TYPE pivot = items[start];

			int i = start;
			int j = end;
			while (i < j) {
				// 找到一个小于中心点
				while (i < j) {
					if (items[j].compareTo(pivot) < 0) {
						items[i] = items[j];
						break;
					}
					j--;
				}

				// 找到一个大于中心点
				while (i < j) {
					if (items[i].compareTo(pivot) > 0) {
						items[j] = items[i];
						break;
					}
					i++;
				}
			}
			items[i] = pivot;
			quickSort(items, start, i - 1);
			quickSort(items, i + 1, end);
		}
	}

	private void quickSort(int[] items, int start, int end) {
		if (start < end) {
			int pivot = items[start];

			int i = start;
			int j = end;
			while (i < j) {
				// 找到一个小于中心点
				while (i < j) {
					if (items[j] < pivot) {
						items[i] = items[j];
						break;
					}
					j--;
				}

				// 找到一个大于中心点
				while (i < j) {
					if (items[i] > pivot) {
						items[j] = items[i];
						break;
					}
					i++;
				}
			}
			items[i] = pivot;
			quickSort(items, start, i - 1);
			quickSort(items, i + 1, end);
		}
	}

	private final Integer[] testItems = { 3, 6, 2, 5, 9, 0, 1, 7, 4, 8 };

	@Test
	public void testQuickSort() {
		System.out.print("排序前：");
		for (int i = 0; i < testItems.length; i++) {
			testItems[i] = (int) (Math.random() * 100);
			System.out.print(testItems[i] + " ");
		}
		sort(testItems);

		System.out.print("\n排序后：");
		for (int i = 0; i < testItems.length; i++) {
			System.out.print(testItems[i] + " ");
		}
	}
}
