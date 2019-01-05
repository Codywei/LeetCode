import java.util.Arrays;

/**
 位运算练习
 * */
public class BitOperation {

    /**
     1.基本原理
     0s 表示一串 0，1s 表示一串 1。
     x ^ 0s = x      x & 0s = 0      x | 0s = x
     x ^ 1s = ~x     x & 1s = x      x | 1s = 1s
     x ^ x = 0       x & x = x       x | x = x
     利用 x ^ 1s = ~x 的特点，可以将位级表示翻转；利用 x ^ x = 0 的特点，可以将三个数中重复的两个数去除，只留下另一个数。
     利用 x & 0s = 0 和 x & 1s = x 的特点，可以实现掩码操作。一个数 num 与 mask：00111100 进行位与操作，只保留 num 中与 mask 的 1 部分相对应的位。
     利用 x | 0s = x 和 x | 1s = 1s 的特点，可以实现设值操作。一个数 num 与 mask：00111100 进行位或操作，将 num 中与 mask 的 1 部分相对应的位都设置为 1。

     位与运算技巧：
     n&(n-1) 去除 n 的位级表示中最低的那一位。例如对于二进制表示 10110 100 ，减去 1 得到 10110011，这两个数相与得到 10110000。
     n&(-n) 得到 n 的位级表示中最低的那一位。-n 得到 n 的反码加 1，对于二进制表示 10110 100 ，-n 得到 01001100，相与得到 00000100。
     n-n&(~n+1) 去除 n 的位级表示中最高的那一位。

     移位运算：
     >> n 为算术右移，相当于除以 2n；
     >>> n 为无符号右移，左边会补上 0。
     << n 为算术左移，相当于乘以 2n。

     2. mask 计算
     要获取 111111111，将 0 取反即可，~0。
     要得到只有第 i 位为 1 的 mask，将 1 向左移动 i-1 位即可，1<<(i-1) 。例如 1<<4 得到只有第 5 位为 1 的 mask ：00010000。
     要得到 1 到 i 位为 1 的 mask，(1<<i)-1 即可，例如将 (1<<i)-1 = 00010000-1 = 00001111。
     要得到 1 到 i 位为 0 的 mask，只需将 1 到 i 位为 1 的 mask 取反，即 ~(1<<(i+1)-1)。

     3. Java 中的位操作
     static int Integer.bitCount();           // 统计 1 的数量
     static int Integer.highestOneBit();      // 获得最高位
     static String toBinaryString(int i);     // 转换为二进制表示的字符串
     * */

     /**
      1.判断一个数是不是 2 的 n 次方
      * */
     public boolean isPowerOfTwo(int n) {
         return n > 0 && Integer.bitCount(n) == 1;
     }

     /**
      2.不用额外变量交换两个整数
      * */
     public void swapInteger(int a,int b){
         a=a^b;
         b=a^b;
         a=a^b;
         System.out.println("不用额外变量交换两个整数： "+a+" "+b);
     }


     /**
      3.字符串数组最大乘积

      题目描述：字符串数组的字符串只含有小写字符。求解字符串数组中两个字符串长度的最大乘积，要求这两个字符串不能含有相同字符。
      本题主要问题是判断两个字符串是否含相同字符，由于字符串只含有小写字符，总共 26 位，因此可以用一个 32 位的整数来存储每个字符是否出现过。
      * */
     public int maxProduct(String[] words) {
         int n = words.length;

         //整数数组用于存储表示各个字符串的值
         int[] val = new int[n];
         for (int i = 0; i < n; i++) {
             for (char c : words[i].toCharArray()) {
                 //在该字符对应位上标上“1”
                 val[i] |= 1 << (c - 'a');
             }
         }
         int ret = 0;
         for (int i = 0; i < n; i++) {
             for (int j = i + 1; j < n; j++) {
                 if ((val[i] & val[j]) == 0) {
                     ret = Math.max(ret, words[i].length() * words[j].length());
                 }
             }
         }
         return ret;
     }


     /**
      4.统计从 0 ~ n 每个数的二进制表示中 1 的个数

      对于数字 6(110)，它可以看成是 4(100) 再加一个 2(10)，因此 dp[i] = dp[i&(i-1)] + 1;
      * */
     public int[] countBits(int num) {
         int[] ret = new int[num + 1];
         for(int i = 1; i <= num; i++){
             ret[i] = ret[i&(i-1)] + 1;
         }
         return ret;
     }






    public static void main(String[] args) {
        BitOperation bo=new BitOperation();

        //第一题
        System.out.println("判断一个数是不是 2 的 n 次方： "+bo.isPowerOfTwo(2));

        //第二题
        bo.swapInteger(4,5);


        //第三题
        String[] array={"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
        System.out.println("字符串数组的最大乘积: "+bo.maxProduct(array));


        //第四题
        int[] array2=bo.countBits(6);
        System.out.print("统计从 0 ~ n 每个数的二进制表示中 1 的个数: ");
        Arrays.stream(array2).forEach((result)-> System.out.print((result+" ")));
    }
}
