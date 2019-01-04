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
      2.最大公约数
      * */
     public int gcd(int a,int b){
         return b==0?a:gcd(b,a%b);
     }

     /**
      3.最小公倍数

      最小公倍数为两数的乘积除以最大公约数
      * */
     public int lcm(int a,int b){
         return a*b/(gcd(a,b));
     }

     /**
      4.进制转换（7进制）
      * */
     public String convertToBase7(int num) {
         if (num == 0) {
             return "0";
         }
         StringBuilder sb = new StringBuilder();
         boolean isNegative = num < 0;
         if (isNegative) {
             num = -num;
         }
         while (num > 0) {
             sb.append(num % 7);
             num /= 7;
         }
         String ret = sb.reverse().toString();
         return isNegative ? "-" + ret : ret;

         //Java 中 static String toString(int num, int radix) 可以将一个整数转换为 radix 进制表示的字符串。
         // return Integer.toString(num, 7);
     }


    /**
     4.进制转换（16进制）

     Input:
     26
     Output:
     "1a"

     Input:
     -1
     Output:
     "ffffffff"

     负数要用它的补码形式。

     0b1111：二进制1111，十进制15
     0234：八进制234，十进制156
     456：十进制456
     0x789：十六进制789，十进制1929

     * */
    public String toHex(int num) {
        char[] map = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        if (num == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            sb.append(map[num & 0b1111]);
            // 因为考虑的是补码形式，因此符号位就不能有特殊的意义，需要使用无符号右移，左边填 0
            num >>>= 4;
        }
        return sb.reverse().toString();
    }


    public static void main(String[] args) {
        BitOperation bo=new BitOperation();

        //第一题
        System.out.println("判断一个数是不是 2 的 n 次方： "+bo.isPowerOfTwo(2));

        //第二题
        System.out.println("最大公约数： "+bo.gcd(6,8));

        //第三题
        System.out.println("最小公倍数： "+bo.lcm(6,8));

        //第四题
        System.out.println("7进制： "+bo.convertToBase7(8));

        //第五题
        System.out.println("16进制： "+bo.toHex(20));



    }
}
