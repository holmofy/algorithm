package cn.hff.sort;

import org.junit.Test;

/**
 * 鸡尾酒排序
 * 
 * @author Holmofy
 *
 */
public class CocktailSort implements SortAlgorithm {

	@Override
	public void sort(int[] items) {
		int left = 0, right = items.length - 1;
		while (left < right) {
			for (int i = left; i < right; i++) {
				if (items[i] > items[i + 1]) {
					int temp = items[i];
					items[i] = items[i + 1];
					items[i + 1] = temp;
				}
			}
			right--;
			for (int i = right; i > left; i--) {
				if (items[i - 1] > items[i]) {
					int temp = items[i];
					items[i] = items[i - 1];
					items[i - 1] = temp;
				}
			}
			left++;
		}
	}

	public <TYPE extends Comparable<? super TYPE>> void sort(TYPE[] items) {
		int left = 0, right = items.length - 1;
		while (left < right) {
			for (int i = left; i < right; i++) {
				if (items[i].compareTo(items[i + 1]) > 0) {
					TYPE temp = items[i];
					items[i] = items[i + 1];
					items[i + 1] = temp;
				}
			}
			right--;
			for (int i = right; i > left; i--) {
				if (items[i - 1].compareTo(items[i]) > 0) {
					TYPE temp = items[i];
					items[i] = items[i - 1];
					items[i - 1] = temp;
				}
			}
			left++;
		}
	}

	private final Integer[] testItems = { 3, 6, 2, 5, 9, 0, 1, 7, 4, 8 };

	@Test
	public void testCocktailSort() {
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
