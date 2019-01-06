import java.util.Arrays;

/**
 数组和矩阵练习
 * */
public class ArrayandMatrix {
    /**
     1.把数组中的0移到末尾
     * */
    public void moveZeroes(int[] nums) {
        int idx = 0;
        for (int num : nums) {
            if (num != 0) {
                nums[idx++] = num;
            }
        }
        while (idx < nums.length) {
            nums[idx++] = 0;
        }
    }


    /**
     2.找出数组中最长的连续1
     * */
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0, cur = 0;
        for (int x : nums) {
            cur = x == 0 ? 0 : cur + 1;
            max = Math.max(max, cur);
        }
        return max;
    }


    /**
     3.有序矩阵的 Kth Element
     二分查找
     * */
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int lo = matrix[0][0], hi = matrix[m - 1][n - 1];
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cnt = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n && matrix[i][j] <= mid; j++) {
                    cnt++;
                }
            }
            if (cnt < k) {
                lo = mid + 1;
            }
            else{
                hi = mid - 1;
            }
        }
        return lo;
    }

    /**
     4.找出数组中重复的数，数组值在 [1, n] 之间

     要求不能修改数组，也不能使用额外的空间。

     二分查找(这边界条件真是令人服气。。。)
     * */
    public int findDuplicate(int[] nums) {
        int l = 1, h = nums.length - 1;
        while (l <= h) {
            int mid = l + (h - l) / 2;
            int cnt = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid){
                    cnt++;
                }
            }
            if (cnt > mid){
                h = mid-1;
            }
            else {
                l = mid+1 ;
            }
        }
        return l;
    }



    /**
     5.嵌套数组

     题目描述：S[i] 表示一个集合，集合的第一个元素是 A[i]，第二个元素是 A[A[i]]，如此嵌套下去。求最大的 S[i]。

     这里的数组应该是不允许重复
     * */
    public int arrayNesting(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int cnt = 0;
            for (int j = i; nums[j] != -1; ) {
                cnt++;
                int t = nums[j];
                // 标记该位置已经被访问
                nums[j] = -1;
                j = t;

            }
            max = Math.max(max, cnt);
        }
        return max;
    }

    public static void main(String[] args) {
        ArrayandMatrix am=new ArrayandMatrix();

        //第一题
        int[] array={0,1,0,3,12};
        am.moveZeroes(array);
        System.out.print("把数组中的0移到末尾： ");
        Arrays.stream(array).forEach((result)->System.out.print(result+" "));
        System.out.println();

        //第二题
        int[] array2={1,1,1,0,1,0,1,1};
        System.out.println("找出数组中的连续1： "+am.findMaxConsecutiveOnes(array2));

        //第三题
        int[][] matrix={{1,5,9},{10,11,13},{12,13,15}};
        System.out.println("有序矩阵的 Kth Element： "+am.kthSmallest(matrix,18));

        //第四题
        int[] array3={3,3,5,2,1,4};
        System.out.println("找出数组中重复的数，数组值在 [1, n] 之间： "+am.findDuplicate(array3));


        //第五题
        int[] array4={5,4,0,3,1,6,2};
        System.out.println("嵌套数组： "+am.arrayNesting(array4));
    }

}
