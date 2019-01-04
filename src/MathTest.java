/**
 数学练习
 * */
public class MathTest {

    /**
     1.生成素数序列

     埃拉托斯特尼筛法在每次找到一个素数时，将能被素数整除的数排除掉。
     * */
    public int countPrimes(int n) {
        boolean[] notPrimes = new boolean[n + 1];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (notPrimes[i]) {
                continue;
            }
            count++;
            // 从 i * i 开始，因为如果 k < i，那么 k * i 在之前就已经被去除过了
            for (long j = (long) (i) * i; j < n; j += i) {
                notPrimes[(int) j] = true;
            }
        }
        return count;
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
     5.进制转换（16进制）

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


    /**
     6.26进制

     1 -> A
     2 -> B
     3 -> C
     ...
     26 -> Z
     27 -> AA
     28 -> AB
     因为是从 1 开始计算的，而不是从 0 开始，因此需要对 n 执行 -1 操作。
     * */
    public String convertToTitle(int n) {
        if (n == 0) {
            return "";
        }
        n--;
        return convertToTitle(n / 26) + (char) (n % 26 + 'A');
    }


    public static void main(String[] args) {
       MathTest mt=new MathTest();

       //第一题
        System.out.println("生成素数序列： "+mt.countPrimes(7));

        //第二题
        System.out.println("最大公约数： "+mt.gcd(6,8));

        //第三题
        System.out.println("最小公倍数： "+mt.lcm(6,8));

        //第四题
        System.out.println("7进制： "+mt.convertToBase7(8));

        //第五题
        System.out.println("16进制： "+mt.toHex(20));

        //第六题
        System.out.println("26进制： "+mt.convertToTitle(26));

    }
}
