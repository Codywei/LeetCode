import java.util.Arrays;

/**
 ����;�����ϰ
 * */
public class ArrayandMatrix {
    /**
     1.�������е�0�Ƶ�ĩβ
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

        //��һ��
        int[] array={0,1,0,3,12};
        am.moveZeroes(array);
        System.out.print("�������е�0�Ƶ�ĩβ�� ");
        Arrays.stream(array).forEach((result)->System.out.print(result+" "));
    }

}
