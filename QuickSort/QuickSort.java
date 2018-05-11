import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class QuickSort {

	/**
	 * 双端扫描交换 Double-End Scan and Swap
	 *
	 * @param items
	 *            待排序数组
	 */
	public void deScanSwapSort(int[] items) {
		deScanSwapSort(items, 0, items.length - 1);
	}

	public void deScanSwapSort(int[] items, int start, int end) {
		if (start < end) {
			int pivot = items[start];

			int i = start + 1, j = end;
			while (i <= j) {
				while (i <= j && items[i] < pivot)
					i++;
				while (i <= j && items[j] >= pivot)
					j--;
				if (i <= j) {
					swap(items, i, j);
				}
			}
			swap(items, start, j);// 将中心点交换到中间。
			deScanSwapSort(items, start, j - 1);// 中心点左半部分递归
			deScanSwapSort(items, j + 1, end);// 中心点右半部分递归
		}
	}

	/**
	 * 赋值填充方式
	 *
	 * @param items
	 *            待排序数组
	 */
	public void fillSort(int[] items) {
		fillSort(items, 0, items.length - 1);
	}

	public void fillSort(int[] items, int start, int end) {
		if (start < end) {
			int pivot = items[start];
			int i = start, j = end;
			while (i < j) {
				while (i < j && items[j] > pivot)
					j--;
				items[i] = items[j];
				while (i < j && items[i] <= pivot)
					i++;
				items[j] = items[i];
			}
			// 相遇后i == j，此处是个坑
			items[i] = pivot;
			fillSort(items, start, i - 1);
			fillSort(items, i + 1, end);
		}
	}

	/**
	 * 单向扫描交换
	 *
	 * @param items
	 *            待排序数组
	 */
	public void forwardScanSort(int[] items) {
		forwardScanSort(items, 0, items.length - 1);
	}

	public void forwardScanSort(int[] items, int start, int end) {
		if (start < end) {
			int pivot = items[start];
			int i = start, j = start + 1;
			while (j <= end) {
				if (items[j] < pivot) {
					i++;
					swap(items, i, j);
				}
				j++;
			}
			swap(items, start, i);
			forwardScanSort(items, start, i - 1);
			forwardScanSort(items, i + 1, end);
		}
	}

	/**
	 * 三分单向扫描
	 */
	public void div3ScanSort(int[] items) {
		div3ScanSort(items, 0, items.length - 1);
	}

	public void div3ScanSort(int[] items, int start, int end) {
		if (start < end) {
			int pivot = items[start];
			int i = start, j = end, k = start + 1;
			while (k <= j) {
				if (items[k] < pivot) {
					swap(items, i, k);
					i++;
					k++;
				} else if (items[k] > pivot) {
					swap(items, j, k);
					j--;
				} else {
					k++;
				}
			}
			div3ScanSort(items, start, i - 1);
			div3ScanSort(items, j + 1, end);
		}
	}

	/**
	 * 双端扫描三分排序
	 */
	public void div3DeScanSort(int[] items) {
		div3DeScanSort(items, 0, items.length - 1);
	}

	public void div3DeScanSort(int[] items, int start, int end) {
		if (start < end) {
			int pivot = items[start];
			int i = start, j = end, k = start + 1;

			OUT_LOOP: while (k <= j) {
				if (items[k] < pivot) {
					swap(items, i, k);
					i++;
					k++;
				} else if (items[k] == pivot) {
					k++;
				} else {
					// j向左扫描，直到一个不大于pivot的元素
					while (items[j] > pivot) {
						j--;
						if (k > j) {
							// 后面的待排元素全大于pivot，直接结束排序
							break OUT_LOOP;
						}
					}
					if (items[j] < pivot) {
						swap(items, j, k);
						swap(items, i, k);
						i++;
					} else {
						swap(items, j, k);
					}
					k++;
					j--;
				}
			}
			div3DeScanSort(items, start, i - 1);
			div3DeScanSort(items, j + 1, end);
		}
	}

	/**
	 * 双轴快排
	 *
	 * @param items
	 */
	public void dualPivotQuickSort(int[] items) {
		dualPivotQuickSort(items, 0, items.length - 1);
	}

	public void dualPivotQuickSort(int[] items, int start, int end) {
		if (start < end) {
			if (items[start] > items[end]) {
				swap(items, start, end);
			}
			int pivot1 = items[start], pivot2 = items[end];
			int i = start, j = end, k = start + 1;
			OUT_LOOP: while (k < j) {
				if (items[k] < pivot1) {
					swap(items, ++i, k++);
				} else if (items[k] <= pivot2) {
					k++;
				} else {
					while (items[--j] > pivot2) {
						if (j <= k) {
							// 扫描终止
							break OUT_LOOP;
						}
					}

					if (items[j] < pivot1) {
						swap(items, j, k);
						swap(items, ++i, k);
					} else {
						swap(items, j, k);
					}
					k++;
				}
			}
			swap(items, start, i);
			swap(items, end, j);

			dualPivotQuickSort(items, start, i - 1);
			dualPivotQuickSort(items, i + 1, j - 1);
			dualPivotQuickSort(items, j + 1, end);
		}
	}

	// 在这个值范围内使用插入排序
	private static final int INSERT_THRESHOLD = 8;

	public void advDualPivotQuickSort(int[] items) {
		advDualPivotQuickSort(items, 0, items.length - 1);
	}

	// 双轴快排
	public void advDualPivotQuickSort(int[] items, int start, int end) {
		if (start < end) {
			if (end - start < INSERT_THRESHOLD) {
				insertSort(items, start, end);
				return;
			}
			if (items[start] > items[end]) {
				swap(items, start, end);
			}
			int pivot1 = items[start], pivot2 = items[end];
			int i = start, j = end, k = start + 1;
			OUT_LOOP: while (k < j) {
				if (items[k] < pivot1) {
					swap(items, ++i, k++);
				} else if (items[k] <= pivot2) {
					k++;
				} else {
					while (items[--j] > pivot2) {
						if (j <= k) {
							// 扫描终止
							break OUT_LOOP;
						}
					}

					if (items[j] < pivot1) {
						swap(items, j, k);
						swap(items, ++i, k);
					} else {
						swap(items, j, k);
					}
					k++;
				}
			}
			swap(items, start, i);
			swap(items, end, j);

			advDualPivotQuickSort(items, start, i - 1);
			advDualPivotQuickSort(items, i + 1, j - 1);
			advDualPivotQuickSort(items, j + 1, end);
		}
	}

	// 插入排序
	private void insertSort(int[] items, int start, int end) {
		for (int i = start + 1; i <= end; i++) {
			int curr = items[i];
			int j = i - 1;
			while (j >= start && curr < items[j]) {
				items[j + 1] = items[j];
				j--;
			}
			items[j + 1] = curr;
		}
	}

	/**
	 * 赋值填充方式
	 *
	 * @param items
	 *            待排序数组
	 */
	public void advFillQuickSort(int[] items) {
		advFillQuickSort(items, 0, items.length - 1);
	}

	public void advFillQuickSort(int[] items, int start, int end) {
		if (start < end) {
			if (end - start < INSERT_THRESHOLD) {
				insertSort(items, start, end);
				return;
			}
			int pivot = items[start];
			int i = start, j = end;
			while (i < j) {
				while (i < j && items[j] > pivot)
					j--;
				items[i] = items[j];
				while (i < j && items[i] <= pivot)
					i++;
				items[j] = items[i];
			}
			// 相遇后i == j，此处是个坑
			items[i] = pivot;
			advFillQuickSort(items, start, i - 1);
			advFillQuickSort(items, i + 1, end);
		}
	}

	// ------------------
	// Test

	private static final boolean PRINT = false;

	private static String INPUT_FILE = "D:\\sort\\input.txt";

	private static String OUTPUT_FILE = "D:\\sort.txt";

	private int[] container = new int[1000];

	long start, end;

	@Before
	public void setup() throws FileNotFoundException {
		Scanner sc = new Scanner(new BufferedInputStream(new FileInputStream(INPUT_FILE)));
		int pos = 0;
		while (sc.hasNext() && pos < container.length) {
			container[pos++] = sc.nextInt();
		}
		sc.close();
		if (PRINT) {
			try {
				System.setOut(new PrintStream(new FileOutputStream(OUTPUT_FILE)));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public void testDeScanSwapSort() {
		start = System.nanoTime();
		deScanSwapSort(container);
		end = System.nanoTime();
		if (PRINT) {
			print(container);
		}
		System.out.println();
		System.out.println(end - start);
	}

	public void testForwardScanSort() {
		start = System.nanoTime();
		forwardScanSort(container);
		end = System.nanoTime();
		if (PRINT) {
			print(container);
		}
		System.out.println();
		System.out.println(end - start);
	}

	public void testFillSort() {
		start = System.nanoTime();
		fillSort(container);
		end = System.nanoTime();
		if (PRINT) {
			print(container);
		}
		System.out.println();
		System.out.println(end - start);
	}

	public void testDiv3ScanSort() {
		start = System.nanoTime();
		div3ScanSort(container);
		end = System.nanoTime();
		if (PRINT) {
			print(container);
		}
		System.out.println();
		System.out.println(end - start);
	}

	public void testDiv3DeScanSort() {
		start = System.nanoTime();
		div3DeScanSort(container);
		end = System.nanoTime();
		if (PRINT) {
			print(container);
		}
		System.out.println();
		System.out.println(end - start);
	}

	public void testDualPivotQuickSort() {
		start = System.nanoTime();
		dualPivotQuickSort(container);
		end = System.nanoTime();
		if (PRINT) {
			print(container);
		}
		System.out.println();
		System.out.println(end - start);
	}

	public void testAdvDualPivotQuickSort() {
		start = System.nanoTime();
		advDualPivotQuickSort(container);
		end = System.nanoTime();
		if (PRINT) {
			print(container);
		}
		System.out.println();
		System.out.println(end - start);
	}

	public void testArraysSort() {
		start = System.nanoTime();
		Arrays.sort(container);
		end = System.nanoTime();
		if (PRINT) {
			print(container);
		}
		System.out.println();
		System.out.println(end - start);
	}

	public void testInsertSort() {
		start = System.nanoTime();
		insertSort(container, 0, container.length - 1);
		end = System.nanoTime();
		if (PRINT) {
			print(container);
		}
		System.out.println();
		System.out.println(end - start);
	}

	public void testAdvFillQuickSort() {
		start = System.nanoTime();
		advFillQuickSort(container);
		end = System.nanoTime();
		if (PRINT) {
			print(container);
		}
		System.out.println();
		System.out.println(end - start);
	}

	@Test
	public void testQuickSort() {
		testAdvFillQuickSort();
	}

	private void print(int[] items) {
		for (int i = 0; i < items.length; i++) {
			System.out.println(items[i]);
		}
	}

	private void swap(int[] items, int i, int j) {
		int tmp = items[i];
		items[i] = items[j];
		items[j] = tmp;
	}
}
