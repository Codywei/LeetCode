import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 �����������㷨һ��
 * */
public class SortBase {

    /**
     ѡ������(��¼��Сֵ������������ĵ�һ��Ԫ�ؽ���λ��)

     ѡ��������е���СԪ�أ�����������ĵ�һ��Ԫ�ؽ���λ�á��ٴ�ʣ�µ�Ԫ����ѡ�����С��Ԫ�أ�����������ĵڶ���Ԫ�ؽ���λ�á����Ͻ��������Ĳ�����ֱ����������������
     ѡ��������Ҫ (N^2)/2 �αȽϺ� ~N �ν�������������ʱ���������޹أ�����ص�ʹ������һ���Ѿ����������Ҳ��Ҫ��ô��ıȽϺͽ���������
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
     ð�����򣨲��Ͻ�������������δ��������Ԫ���ϸ����Ҳࣩ

     �����Ҳ��Ͻ������������Ԫ�أ���һ�ֵ�ѭ��֮�󣬿�����δ��������Ԫ���ϸ����Ҳࡣ
     ��һ��ѭ���У����û�з�����������˵�������Ѿ�������ģ���ʱ����ֱ���˳���
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
     ��������(ÿ�ζ�����ǰԪ�ز��뵽����Ѿ�����������У�ʹ�ò���֮�����������Ȼ����)

     ÿ�ζ�����ǰԪ�ز��뵽����Ѿ�����������У�ʹ�ò���֮�����������Ȼ����
     �������� {3, 5, 2, 4, 1}����������������(3, 2), (3, 1), (5, 2), (5, 4), (5, 1), (2, 1), (4, 1)����������ÿ��ֻ�ܽ�������Ԫ�أ��������������� 1����˲���������Ҫ�����Ĵ���Ϊ����������
     ��������ĸ��Ӷ�ȡ��������ĳ�ʼ˳����������Ѿ����������ˣ�������٣���ô���������ܿ졣
     ƽ������²���������Ҫ (N^2)/4 �Ƚ��Լ� (N^2)/4 �ν�����
     ����������Ҫ (N^2)/2 �Ƚ��Լ� (N^2)/2 �ν������������������ǵ���ģ�
     ��õ��������Ҫ N-1 �αȽϺ� 0 �ν�������õ�������������Ѿ������ˡ�
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
     ϣ������(�����������ľ����ԣ����������ڵ�Ԫ��)

     ���ڴ��ģ�����飬���������������Ϊ��ֻ�ܽ������ڵ�Ԫ�أ�ÿ��ֻ�ܽ������������� 1��
     ϣ������ĳ��־���Ϊ�˽��������������־����ԣ���ͨ�����������ڵ�Ԫ�أ�ÿ�ο��Խ������������ٴ��� 1��
     ϣ������ʹ�ò�������Լ�� h �����н�������ͨ�����ϼ�С h������� h=1���Ϳ���ʹ����������������ġ�

     ϣ�����������ʱ��ﲻ��ƽ������ʹ�õ������� 1, 4, 13, 40, ... ��ϣ����������Ҫ�ıȽϴ������ᳬ�� N �����ɱ����ڵ������еĳ��ȡ�
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
     �Ե����Ϲ鲢����(ѭ������)

     �ȹ鲢��Щ΢�����飬Ȼ��ɶԹ鲢�õ���΢�����顣
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
     �Զ����¹鲢���򣨵ݹ飩

     ��һ��������ֳ�����С����ȥ��⡣
     ��Ϊÿ�ζ�������԰�ֳ����������⣬���ֶ԰�ֵ��㷨���Ӷ�һ��Ϊ O(NlogN)��
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
     �����зַֿ�������

     ����������ԭ�����򣬲���Ҫ�������飬���ǵݹ������Ҫ����ջ��
     ����������õ��������ÿ�ζ������ܽ�����԰�֣������ݹ���ô����������ٵġ���������±Ƚϴ���Ϊ CN=2CN/2+N�����Ӷ�Ϊ O(NlogN)��
     �������£���һ�δ���С��Ԫ���з֣��ڶ��δӵڶ�С��Ԫ���з֣������㡣�������������Ҫ�Ƚ� N2/2��Ϊ�˷�ֹ�����ʼ��������ģ��ڽ��п�������ʱ��Ҫ����������顣
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
     �����зֿ�������

     �����д����ظ�Ԫ�ص����飬���Խ������з�Ϊ�����֣��ֱ��ӦС�ڡ����ںʹ����з�Ԫ�ء�
     �����зֿ����������ֻ�����ɲ�ͬ����������������������ʱ�����������
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
     ������
     �����Ԫ�غ͵�ǰ������������һ��Ԫ�ؽ���λ�ã����Ҳ�ɾ��������ô�Ϳ��Եõ�һ����β��ͷ�ĵݼ����У���������������һ���������У�����Ƕ�����

     �������齨������ֱ�ӵķ����Ǵ����ұ�����������ϸ�������
     һ������Ч�ķ����Ǵ�����������³����������һ���ڵ�������ڵ㶼�Ѿ��Ƕ�������ô�����³���������ʹ������ڵ�Ϊ���ڵ�Ķ�����
     Ҷ�ӽڵ㲻��Ҫ�����³����������Ժ���Ҷ�ӽڵ��Ԫ�أ����ֻ��Ҫ����һ���Ԫ�ؼ��ɡ�
     * */
    static class HeapSort<T extends Comparable<T>> extends Sort<T> {
        /**
         * ����� 0 ��λ�ò�����Ԫ��
         */
        @Override
        public void sort(T[] nums) {
            int N = nums.length - 1;
            //����С����
            for (int k = N / 2; k >= 1; k--) {
                int temp=k;
                sink(nums, temp, N);
            }

            //����С����
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
        System.out.println("����������Ϊ��");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        System.out.println();


        Integer[] arr1=arr.clone();
        Selection selection=new SortBase.Selection<>();
        selection.sort(arr1);
        System.out.println("ѡ���������� ");
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i]);
        }
        System.out.println();


        Integer[] arr2=arr.clone();
        Bubble bubble=new SortBase.Bubble();
        bubble.sort(arr2);
        System.out.println("ð���������� ");
        for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i]);
        }
        System.out.println();



        Integer[] arr3=arr.clone();
        Insertion insertion=new SortBase.Insertion<>();
        insertion.sort(arr3);
        System.out.println("������������ ");
        for (int i = 0; i < arr3.length; i++) {
            System.out.print(arr3[i]);
        }
        System.out.println();


        Integer[] arr4=arr.clone();
        Shell shell=new SortBase.Shell<>();
        shell.sort(arr4);
        System.out.println("ϣ���������� ");
        for (int i = 0; i < arr4.length; i++) {
            System.out.print(arr4[i]);
        }
        System.out.println();


        Integer[] arr5=arr.clone();
        Down2UpMergeSort dumerge=new SortBase.Down2UpMergeSort<>();
        dumerge.sort(arr5);
        System.out.println("�Ե������������� ");
        for (int i = 0; i < arr5.length; i++) {
            System.out.print(arr5[i]);
        }
        System.out.println();

        Integer[] arr6=arr.clone();
        Up2DownMergeSort udmerge=new SortBase.Up2DownMergeSort<>();
        udmerge.sort(arr6);
        System.out.println("�Զ����¹鲢�������� ");
        for (int i = 0; i < arr6.length; i++) {
            System.out.print(arr6[i]);
        }
        System.out.println();


        Integer[] arr7=arr.clone();
        QuickSort quick=new SortBase.QuickSort<>();
        quick.sort(arr7);
        System.out.println("��������������� ");
        for (int i = 0; i < arr7.length; i++) {
            System.out.print(arr7[i]);
        }
        System.out.println();

        Integer[] arr8=arr.clone();
        ThreeWayQuickSort tquick=new SortBase.ThreeWayQuickSort<>();
        tquick.sort(arr8);
        System.out.println("��������������� ");
        for (int i = 0; i < arr8.length; i++) {
            System.out.print(arr8[i]);
        }
        System.out.println();



        Integer[] arr9=new Integer[arr.length+1];
        for (int i = 1; i <= arr.length; i++) {
            arr9[i]=arr[i-1];
        }
        HeapSort heap=new SortBase.HeapSort<>();
        heap.sort(arr9);
        System.out.println("���������� ");
        for (int i = 1; i < arr9.length; i++) {
            System.out.print(arr9[i]);
        }
        System.out.println();
    }


}
