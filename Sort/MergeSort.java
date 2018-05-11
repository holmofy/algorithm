package cn.hff.sort;

import org.junit.Test;

/**
 * 归并排序
 * 
 * @author Holmofy
 *
 */
public class MergeSort implements SortAlgorithm {

	@Override
	public void sort(int[] items) {
		int[] tmpArray = new int[items.length];
		mergeSort(items, tmpArray, 0, items.length - 1);
	}

	public <TYPE extends Comparable<? super TYPE>> void sort(TYPE[] items) {
		@SuppressWarnings("unchecked")
		// 后面递归调用都使用这个临时缓冲区。
		TYPE[] tmpArray = (TYPE[]) new Comparable[items.length];
		mergeSort(items, tmpArray, 0, items.length - 1);
	}

	private void mergeSort(int[] items, int[] tmpArray, int startIndex, int endIndex) {
		if (startIndex < endIndex) {
			int centerIndex = (startIndex + endIndex) >> 1;
			mergeSort(items, tmpArray, startIndex, centerIndex);
			mergeSort(items, tmpArray, centerIndex + 1, endIndex);

			merge(items, tmpArray, startIndex, centerIndex, endIndex);
		}
	}

	private <TYPE extends Comparable<? super TYPE>> void mergeSort(TYPE[] items, TYPE[] tmpArray, int startIndex,
			int endIndex) {
		if (startIndex < endIndex) {
			int centerIndex = (startIndex + endIndex) >> 1;
			mergeSort(items, tmpArray, startIndex, centerIndex);
			mergeSort(items, tmpArray, centerIndex + 1, endIndex);

			merge(items, tmpArray, startIndex, centerIndex, endIndex);
		}
	}

	private <TYPE extends Comparable<? super TYPE>> void merge( // 合并左右序列
			TYPE[] items, // 原始数组
			TYPE[] tmpArray, // 临时数组，用来合并
			int leftPos, // 左序列起始位置
			int leftEnd, // 左序列结束位置
			int rightEnd // 右序列结束位置
	) {
		int rightPos = leftEnd + 1; // 右序列的起始位置
		int tmpPos = leftPos; // 合并时的临时索引
		int eleCount = rightEnd - leftPos + 1; // 左右序列元素的总数

		while (leftPos <= leftEnd && rightPos <= rightEnd) {
			if (items[leftPos].compareTo(items[rightPos]) < 0) {
				// 左序列的值较小，将左序列元素放入临时数组
				tmpArray[tmpPos++] = items[leftPos++];
			} else {
				// 右序列的值较小，将右序列元素放入临时数组
				tmpArray[tmpPos++] = items[rightPos++];
			}
		}

		// 左序列剩余元素放入临时数组
		while (leftPos <= leftEnd) {
			tmpArray[tmpPos++] = items[leftPos++];
		}

		// 右序列剩余元素放入临时数组
		while (rightPos <= rightEnd) {
			tmpArray[tmpPos++] = items[rightPos++];
		}

		// 将合并后的数据拷贝回原始数组
		int startIndex = rightEnd - eleCount + 1;
		System.arraycopy(tmpArray, startIndex, items, startIndex, eleCount);
	}

	private void merge( // 合并左右序列
			int[] items, // 原始数组
			int[] tmpArray, // 临时数组，用来合并
			int leftPos, // 左序列起始位置
			int leftEnd, // 左序列结束位置
			int rightEnd // 右序列结束位置
	) {
		int rightPos = leftEnd + 1; // 右序列的起始位置
		int tmpPos = leftPos; // 合并时的临时索引
		int eleCount = rightEnd - leftPos + 1; // 左右序列元素的总数

		while (leftPos <= leftEnd && rightPos <= rightEnd) {
			if (items[leftPos] < items[rightPos]) {
				// 左序列的值较小，将左序列元素放入临时数组
				tmpArray[tmpPos++] = items[leftPos++];
			} else {
				// 右序列的值较小，将右序列元素放入临时数组
				tmpArray[tmpPos++] = items[rightPos++];
			}
		}

		// 左序列剩余元素放入临时数组
		while (leftPos <= leftEnd) {
			tmpArray[tmpPos++] = items[leftPos++];
		}

		// 右序列剩余元素放入临时数组
		while (rightPos <= rightEnd) {
			tmpArray[tmpPos++] = items[rightPos++];
		}

		// 将合并后的数据拷贝回原始数组
		int startIndex = rightEnd - eleCount + 1;
		System.arraycopy(tmpArray, startIndex, items, startIndex, eleCount);
	}

	private final Integer[] testItems = { 3, 6, 2, 5, 9, 0, 1, 7, 4, 8 };

	@Test
	public void testMergeSort() {
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
