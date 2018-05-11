package cn.hff.sort;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

/**
 * 排序算法大比拼
 * 
 * @author Holmofy
 *
 */
public class Test {

    private static SortAlgorithm[] s = { // 为了方便测试抽出一个接口
            new SelectSort(), new HeapSort(), // 选择排序
            new InsertSort(), new ShellSort(), // 插入排序
            new BubbleSort(), new AdvBubbleSort(),
            new CocktailSort(), new QuickSort(), // 交换排序
            new MergeSort(), // 归并排序
            new CountSort(), new RadixSort(), // 计数排序,基数排序
            new PigeonholeSort(), // 鸽巢排序
            new ArrayBucketSort(), new LinkBucketSort()// 桶排序
    };

    public static void main(String[] args) {
        // 输出重定向
        try {
            System.setOut(
                    new PrintStream(new FileOutputStream("D:\\sort.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int[] testItems = new int[300];
        System.out.print("排序前：");
        for (int i = 0; i < testItems.length; i++) {
            testItems[i] = (int) (Math.random() * 10000);
            System.out.print(testItems[i] + " ");
        }
        int[] tmp = new int[testItems.length];

        long start, end;
        for (int i = 0; i < s.length; i++) {
            System.arraycopy(testItems, 0, tmp, 0, tmp.length);
            start = System.nanoTime();
            s[i].sort(tmp);
            end = System.nanoTime();
            System.out.println();
            // print(tmp);
            System.out.print(s[i].getClass().getSimpleName() + "耗时："
                    + ((double) (end - start)) / (Math.pow(10, 6)));
        }

        // JDK中的Arrays.sort排序
        System.arraycopy(testItems, 0, tmp, 0, tmp.length);
        start = System.nanoTime();
        Arrays.sort(tmp);
        end = System.nanoTime();
        System.out.println();
        // print(tmp);
        System.out.print("Arrays.sort耗时："
                + ((double) (end - start)) / (Math.pow(10, 6)));

    }

    // private static void print(int[] items) {
    // for (int i = 0; i < items.length; i++) {
    // System.out.print(items[i] + " ");
    // }
    // }
}
