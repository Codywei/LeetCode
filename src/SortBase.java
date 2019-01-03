import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 基本的排序算法一览
 * */
public class SortBase {

    /**
     选择排序(记录最小值，将它与数组的第一个元素交换位置)

     选择出数组中的最小元素，将它与数组的第一个元素交换位置。再从剩下的元素中选择出最小的元素，将它与数组的第二个元素交换位置。不断进行这样的操作，直到将整个数组排序。
     选择排序需要 (N^2)/2 次比较和 N 次交换，它的运行时间与输入无关，这个特点使得它对一个已经排序的数组也需要这么多的比较和交换操作。
     * */
    static class Selection<T extends Comparable<T>> extends Sort<T> {
        @Override
        public void sort(T[] nums) {
            int N = nums.length;
            for (int i = 0; i < N - 1; i++) {
                int min = i;
                for (int j = i + 1; j < N; j++) {
                    if (less(nums[j], nums[min])) {
                        min = j;
                    }
                }
                swap(nums, i, min);
            }
        }
    }


    /**
     冒泡排序（不断交换相邻逆序，让未排序的最大元素上浮到右侧）

     从左到右不断交换相邻逆序的元素，在一轮的循环之后，可以让未排序的最大元素上浮到右侧。
     在一轮循环中，如果没有发生交换，就说明数组已经是有序的，此时可以直接退出。
     * */
    static class Bubble<T extends Comparable<T>> extends Sort<T> {
        @Override
        public void sort(T[] nums) {
            int N = nums.length;
            boolean hasSorted = false;
            for (int i = N - 1; i > 0 && !hasSorted; i--) {
                hasSorted = true;
                for (int j = 0; j < i; j++) {
                    if (less(nums[j + 1], nums[j])) {
                        hasSorted = false;
                        swap(nums, j, j + 1);
                    }
                }
            }
        }
    }



    /**
     插入排序(每次都将当前元素插入到左侧已经排序的数组中，使得插入之后左侧数组依然有序)

     每次都将当前元素插入到左侧已经排序的数组中，使得插入之后左侧数组依然有序。
     对于数组 {3, 5, 2, 4, 1}，它具有以下逆序：(3, 2), (3, 1), (5, 2), (5, 4), (5, 1), (2, 1), (4, 1)，插入排序每次只能交换相邻元素，令逆序数量减少 1，因此插入排序需要交换的次数为逆序数量。
     插入排序的复杂度取决于数组的初始顺序，如果数组已经部分有序了，逆序较少，那么插入排序会很快。
     平均情况下插入排序需要 (N^2)/4 比较以及 (N^2)/4 次交换；
     最坏的情况下需要 (N^2)/2 比较以及 (N^2)/2 次交换，最坏的情况是数组是倒序的；
     最好的情况下需要 N-1 次比较和 0 次交换，最好的情况就是数组已经有序了。
     * */
    static class Insertion<T extends Comparable<T>> extends Sort<T> {
        @Override
        public void sort(T[] nums) {
            int N = nums.length;
            for (int i = 1; i < N; i++) {
                for (int j = i; j > 0 && less(nums[j], nums[j - 1]); j--) {
                    swap(nums, j, j - 1);
                }
            }
        }
    }

    /**
     希尔排序(解决插入排序的局限性，交换不相邻的元素)

     对于大规模的数组，插入排序很慢，因为它只能交换相邻的元素，每次只能将逆序数量减少 1。
     希尔排序的出现就是为了解决插入排序的这种局限性，它通过交换不相邻的元素，每次可以将逆序数量减少大于 1。
     希尔排序使用插入排序对间隔 h 的序列进行排序。通过不断减小 h，最后令 h=1，就可以使得整个数组是有序的。

     希尔排序的运行时间达不到平方级别，使用递增序列 1, 4, 13, 40, ... 的希尔排序所需要的比较次数不会超过 N 的若干倍乘于递增序列的长度。
     * */
     static class Shell<T extends Comparable<T>> extends Sort<T> {
        @Override
        public void sort(T[] nums) {

            int N = nums.length;
            int h = 1;

            while (h < N / 3) {
                // 1, 4, 13, 40, ...
                h = 3 * h + 1;
            }
            while (h >= 1) {
                for (int i = h; i < N; i++) {
                    for (int j = i; j >= h && less(nums[j], nums[j - h]); j -= h) {
                        swap(nums, j, j - h);
                    }
                }
                h = h / 3;
            }
        }
    }


    /**
     自底向上归并排序(循环控制)

     先归并那些微型数组，然后成对归并得到的微型数组。
     * */
    static class Down2UpMergeSort<T extends Comparable<T>> extends MergeSort<T> {
        @Override
        public void sort(T[] nums) {

            int N = nums.length;
            aux = (T[]) new Comparable[N];

            for (int sz = 1; sz < N; sz += sz) {
                for (int lo = 0; lo < N - sz; lo += sz + sz) {
                    merge(nums, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
                }
            }
        }
    }



    /**
     自顶向下归并排序（递归）

     将一个大数组分成两个小数组去求解。
     因为每次都将问题对半分成两个子问题，这种对半分的算法复杂度一般为 O(NlogN)。
     * */
    static class Up2DownMergeSort<T extends Comparable<T>> extends MergeSort<T> {
        @Override
        public void sort(T[] nums) {
            aux = (T[]) new Comparable[nums.length];
            sort(nums, 0, nums.length - 1);
        }

        private void sort(T[] nums, int l, int h) {
            if (h <= l) {
                return;
            }
            int mid = l + (h - l) / 2;
            sort(nums, l, mid);
            sort(nums, mid + 1, h);
            merge(nums, l, mid, h);
        }
    }





    /**
     二向切分分快速排序

     快速排序是原地排序，不需要辅助数组，但是递归调用需要辅助栈。
     快速排序最好的情况下是每次都正好能将数组对半分，这样递归调用次数才是最少的。这种情况下比较次数为 CN=2CN/2+N，复杂度为 O(NlogN)。
     最坏的情况下，第一次从最小的元素切分，第二次从第二小的元素切分，如此这般。因此最坏的情况下需要比较 N2/2。为了防止数组最开始就是有序的，在进行快速排序时需要随机打乱数组。
     * */
    static class QuickSort<T extends Comparable<T>> extends Sort<T> {

        @Override
        public void sort(T[] nums) {
            shuffle(nums);
            sort(nums, 0, nums.length - 1);
        }

        private void sort(T[] nums, int l, int h) {
            if (h <= l) {
                return;
            }
            int j = partition(nums, l, h);
            sort(nums, l, j - 1);
            sort(nums, j + 1, h);
        }

        private void shuffle(T[] nums) {
            List<Comparable> list = Arrays.asList(nums);
            Collections.shuffle(list);
            list.toArray(nums);
        }

        private int partition(T[] nums, int l, int h) {
            int i = l, j = h + 1;
            T v = nums[l];
            while (true) {
                while (less(nums[++i], v) && i != h){

                } ;
                while (less(v, nums[--j]) && j != l){

                } ;
                if (i >= j){
                    break;
                }
                swap(nums, i, j);
            }
            swap(nums, l, j);
            return j;
        }
    }


    /**
     三向切分快速排序

     对于有大量重复元素的数组，可以将数组切分为三部分，分别对应小于、等于和大于切分元素。
     三向切分快速排序对于只有若干不同主键的随机数组可以在线性时间内完成排序。
     * */
    static class ThreeWayQuickSort<T extends Comparable<T>> extends QuickSort<T> {

        protected void sort(T[] nums, int l, int h) {
            if (h <= l) {
                return;
            }
            int lt = l, i = l + 1, gt = h;
            T v = nums[l];
            while (i <= gt) {
                int cmp = nums[i].compareTo(v);
                if (cmp < 0) {
                    swap(nums, lt++, i++);
                } else if (cmp > 0) {
                    swap(nums, i, gt--);
                } else {
                    i++;
                }
            }
            sort(nums, l, lt - 1);
            sort(nums, gt + 1, h);
        }
    }

    /**
     堆排序
     把最大元素和当前堆中数组的最后一个元素交换位置，并且不删除它，那么就可以得到一个从尾到头的递减序列，从正向来看就是一个递增序列，这就是堆排序。

     无序数组建立堆最直接的方法是从左到右遍历数组进行上浮操作。
     一个更高效的方法是从右至左进行下沉操作，如果一个节点的两个节点都已经是堆有序，那么进行下沉操作可以使得这个节点为根节点的堆有序。
     叶子节点不需要进行下沉操作，可以忽略叶子节点的元素，因此只需要遍历一半的元素即可。
     * */
    static class HeapSort<T extends Comparable<T>> extends Sort<T> {
        /**
         * 数组第 0 个位置不能有元素
         */
        @Override
        public void sort(T[] nums) {
            int N = nums.length - 1;
            //创建大根堆
            for (int k = N / 2; k >= 1; k--) {
                int temp=k;
                sink(nums, temp, N);
            }

            //调整大根堆
            while (N > 1) {
                swap(nums, 1, N--);
                sink(nums, 1, N);
            }
        }

        private void sink(T[] nums, int k, int N) {
            while (2 * k <= N) {
                int j = 2 * k;
                if (j < N && less(nums, j, j + 1)) {
                    j++;
                }
                if (!less(nums, k, j)) {
                    break;
                }
                swap(nums, k, j);
                k = j;
            }
        }

        private boolean less(T[] nums, int i, int j) {
            return nums[i].compareTo(nums[j]) < 0;
        }
    }

    public static void main(String[] args) {

        Integer[] arr={3,2,4,7,6,5};
        System.out.println("待排序数组为：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        System.out.println();


        Integer[] arr1=arr.clone();
        Selection selection=new Selection<>();
        selection.sort(arr1);
        System.out.println("选择排序结果： ");
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i]);
        }
        System.out.println();


        Integer[] arr2=arr.clone();
        Bubble bubble=new Bubble();
        bubble.sort(arr2);
        System.out.println("冒泡排序结果： ");
        for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i]);
        }
        System.out.println();



        Integer[] arr3=arr.clone();
        Insertion insertion=new Insertion<>();
        insertion.sort(arr3);
        System.out.println("插入排序结果： ");
        for (int i = 0; i < arr3.length; i++) {
            System.out.print(arr3[i]);
        }
        System.out.println();


        Integer[] arr4=arr.clone();
        Shell shell=new Shell<>();
        shell.sort(arr4);
        System.out.println("希尔排序结果： ");
        for (int i = 0; i < arr4.length; i++) {
            System.out.print(arr4[i]);
        }
        System.out.println();


        Integer[] arr5=arr.clone();
        Down2UpMergeSort dumerge=new Down2UpMergeSort<>();
        dumerge.sort(arr5);
        System.out.println("自底向上排序结果： ");
        for (int i = 0; i < arr5.length; i++) {
            System.out.print(arr5[i]);
        }
        System.out.println();

        Integer[] arr6=arr.clone();
        Up2DownMergeSort udmerge=new Up2DownMergeSort<>();
        udmerge.sort(arr6);
        System.out.println("自顶向下归并排序结果： ");
        for (int i = 0; i < arr6.length; i++) {
            System.out.print(arr6[i]);
        }
        System.out.println();


        Integer[] arr7=arr.clone();
        QuickSort quick=new QuickSort<>();
        quick.sort(arr7);
        System.out.println("二向快速排序结果： ");
        for (int i = 0; i < arr7.length; i++) {
            System.out.print(arr7[i]);
        }
        System.out.println();

        Integer[] arr8=arr.clone();
        ThreeWayQuickSort tquick=new ThreeWayQuickSort<>();
        tquick.sort(arr8);
        System.out.println("三向快速排序结果： ");
        for (int i = 0; i < arr8.length; i++) {
            System.out.print(arr8[i]);
        }
        System.out.println();



        Integer[] arr9=new Integer[arr.length+1];
        for (int i = 1; i <= arr.length; i++) {
            arr9[i]=arr[i-1];
        }
        HeapSort heap=new HeapSort<>();
        heap.sort(arr9);
        System.out.println("堆排序结果： ");
        for (int i = 1; i < arr9.length; i++) {
            System.out.print(arr9[i]);
        }
        System.out.println();
    }


}
