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

    public static void main(String[] args) {
        ArrayandMatrix am=new ArrayandMatrix();

        //第一题
        int[] array={0,1,0,3,12};
        am.moveZeroes(array);
        System.out.print("把数组中的0移到末尾： ");
        Arrays.stream(array).forEach((result)->System.out.print(result+" "));
    }

}
