package cn.hff.sort;

import java.util.Arrays;
import org.junit.Test;

/**
 * 桶排序
 * 
 * @author Holmofy
 *
 */
public class LinkBucketSort implements SortAlgorithm {

	@Override
	public void sort(int[] items) {
		bucketSort(items);
	}

	private class Node {
		Node(Node next, int value) {
			this.next = next;
			this.value = value;
		}

		Node next;
		int value;
	}

	public void bucketSort(int items[]) {
		bucketSort(items, 10);
	}

	public void bucketSort(int items[], int radix) {
		Node[] bucket = new Node[radix];
		int maxLength = 0;
		for (int i = 0; i < items.length; i++) {
			int itemLength = length(items[i], radix);
			if (maxLength < itemLength) {
				maxLength = itemLength;
			}
		}

		for (int i = 0, r = 1; i < maxLength; i++, r *= radix) {
			// 入桶
			for (int j = 0; j < items.length; j++) {
				int digit = (items[j] / r) % radix;
				enter(bucket, digit, items[j]);
			}

			// 出桶
			for (int j = 0, index = 0; j < radix; j++) {
				for (Node curr = bucket[j]; curr != null; curr = curr.next) {
					items[index++] = curr.value;
				}
				bucket[j] = null;// 把桶清空
			}
		}
	}

	// 入桶
	private void enter(Node[] bucket, int bucketNum, int value) {
		Node curr = bucket[bucketNum];
		if (curr == null) {
			bucket[bucketNum] = new Node(null, value);
			return;
		}
		while (curr.next != null)
			curr = curr.next;
		curr.next = new Node(null, value);
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
