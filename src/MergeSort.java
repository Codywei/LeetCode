public abstract class MergeSort<T extends Comparable<T>> extends Sort<T> {

    protected T[] aux;


    protected void merge(T[] nums, int l, int m, int h) {

        int i = l, j = m + 1;

        for (int k = l; k <= h; k++) {
            // 将数据复制到辅助数组
            aux[k] = nums[k];
        }

        for (int k = l; k <= h; k++) {
            if (i > m) {
                nums[k] = aux[j++];

            } else if (j > h) {
                nums[k] = aux[i++];

            } else if (aux[i].compareTo(nums[j]) <= 0) {
                // 先进行这一步，保证排序稳定性（保持相等元素先后顺序和未排序之前一致）
                nums[k] = aux[i++];

            } else {
                nums[k] = aux[j++];
            }
        }
    }
}
