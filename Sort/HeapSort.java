package cn.hff.sort;

import org.junit.Test;

/**
 * 堆排序
 * 
 * @author Holmofy
 *
 */
public class HeapSort implements SortAlgorithm {

	@Override
	public void sort(int[] items) {
		for (int i = items.length / 2 - 1; i >= 0; i--) {
			adjust(items, i, items.length);
		}

		for (int i = items.length - 1; i > 0; i--) {
			// 将最大值与最后项进行交换
			int temp = items[0];
			items[0] = items[i];
			items[i] = temp;

			// 取出最大值之后，重新调整二叉堆
			// 二叉堆也随着变量i慢慢缩小
			adjust(items, 0, i);
		}
	}

	public <TYPE extends Comparable<? super TYPE>> void sort(TYPE[] items) {
		// 构造二叉堆
		// 从最末端也就是最下面的非叶子结点开始进行调整
		// 从而使得最大值上移到二叉堆顶端
		for (int i = items.length / 2 - 1; i >= 0; i--) {
			adjust(items, i, items.length);
		}

		for (int i = items.length - 1; i > 0; i--) {
			// 将最大值与最后项进行交换
			TYPE temp = items[0];
			items[0] = items[i];
			items[i] = temp;

			// 取出最大值之后，重新调整二叉堆
			// 二叉堆也随着变量i慢慢缩小
			adjust(items, 0, i);
		}
	}

	private <TYPE extends Comparable<? super TYPE>> void adjust(TYPE[] items, int index, int heapSize) {
		int leftChild = 2 * index + 1;
		int rightChild = 2 * index + 2;

		// 在三个结点中选出最大的结点
		int indexOfMax = index;

		if (leftChild < heapSize) {// 左子树存在性检验
			if (items[leftChild].compareTo(items[indexOfMax]) > 0) {
				indexOfMax = leftChild;
			}
		}
		if (rightChild < heapSize) {// 右子树存在性检验
			if (items[rightChild].compareTo(items[indexOfMax]) > 0) {
				indexOfMax = rightChild;
			}
		}

		if (indexOfMax != index) {
			// 将较大值上移
			TYPE temp = items[index];
			items[index] = items[indexOfMax];
			items[indexOfMax] = temp;

			// 千万别漏了这个等号，我调试了半天才发现这个错误
			if (indexOfMax <= heapSize / 2 - 1) {
				// 如果被调整后的结点也是非叶子结点
				// 需要对该子树进行调整
				adjust(items, indexOfMax, heapSize);
			}
		}
	}

	private void adjust(int[] items, int index, int heapSize) {
		int leftChild = 2 * index + 1;
		int rightChild = 2 * index + 2;

		// 在三个结点中选出最大的结点
		int indexOfMax = index;

		if (leftChild < heapSize) {// 左子树存在性检验
			if (items[leftChild] > items[indexOfMax]) {
				indexOfMax = leftChild;
			}
		}
		if (rightChild < heapSize) {// 右子树存在性检验
			if (items[rightChild] > items[indexOfMax]) {
				indexOfMax = rightChild;
			}
		}

		if (indexOfMax != index) {
			// 将较大值上移
			int temp = items[index];
			items[index] = items[indexOfMax];
			items[indexOfMax] = temp;

			// 千万别漏了这个等号，我调试了半天才发现这个错误
			if (indexOfMax <= heapSize / 2 - 1) {
				// 如果被调整后的结点也是非叶子结点
				// 需要对该子树进行调整
				adjust(items, indexOfMax, heapSize);
			}
		}
	}

	private final Integer[] testItems = { 3, 6, 2, 5, 9, 0, 1, 7, 4, 8 };

	@Test
	public void testHeapSort() {
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
