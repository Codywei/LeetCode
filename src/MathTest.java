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


    /**
     7.统计阶乘尾部有多少个 0

     尾部的 0 由 2 * 5 得来，2 的数量明显多于 5 的数量，因此只要统计有多少个 5 即可。
     对于一个数 N，它所包含 5 的个数为：N/5 + N/(5^2) + N/(5^3)+ ...，其中 N/5 表示不大于 N 的数中 5 的倍数贡献一个 5，N/52 表示不大于 N 的数中 52 的倍数再贡献一个 5 ...

     如果统计的是 N! 的二进制表示中最低位 1 的位置，只要统计有多少个 2 即可。和求解有多少个 5 一样，2 的个数为 N/2 + N/(2^2) + N/(2^3) + ...
     * */
    public int trailingZeroes(int n) {
        return n == 0 ? 0 : n / 5 + trailingZeroes(n / 5);
    }


    /**
     8.二进制加法
     * */
    public String addBinary(String a, String b) {
        int i = a.length() - 1, j = b.length() - 1, carry = 0;
        StringBuilder str = new StringBuilder();
        while (carry == 1 || i >= 0 || j >= 0) {
            if (i >= 0 && a.charAt(i--) == '1') {
                carry++;
            }
            if (j >= 0 && b.charAt(j--) == '1') {
                carry++;
            }
            str.append(carry % 2);
            carry /= 2;
        }
        return str.reverse().toString();
    }


    /**
     9.找出数组中的乘积最大的三个数

     给定一个无序数组，包含正数、负数和0，要求从中找出3个数的乘积，使得乘积最大，要求时间复杂度：O(n)，空间复杂度：O(1)
     * */
    public int maximumProduct(int[] nums) {
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE, min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n > max1) {
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (n > max2) {
                max3 = max2;
                max2 = n;
            } else if (n > max3) {
                max3 = n;
            }

            if (n < min1) {
                min2 = min1;
                min1 = n;
            } else if (n < min2) {
                min2 = n;
            }
        }
        return Math.max(max1*max2*max3, max1*min1*min2);
    }


    /**
     10.相遇问题
     改变数组元素使所有的数组元素都相等

     每次可以对一个数组元素加一或者减一，求最小的改变次数。
     这是个典型的相遇问题，移动距离最小的方式是所有元素都移动到中位数。理由如下：
     设 m 为中位数。a 和 b 是 m 两边的两个元素，且 b > a。要使 a 和 b 相等，它们总共移动的次数为 b - a，这个值等于 (b - m) + (m - a)，也就是把这两个数移动到中位数的移动次数。
     设数组长度为 N，则可以找到 N/2 对 a 和 b 的组合，使它们都移动到 m 的位置。

     使用快速选择找到中位数，时间复杂度 O(N)
     * */
    public int minMoves(int[] nums) {
        int move = 0;
        int median = findKthSmallest(nums, nums.length / 2);
        for (int num : nums) {
            move += Math.abs(num - median);
        }
        return move;
    }

    private int findKthSmallest(int[] nums, int k) {
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int j = partition(nums, l, h);
            if (j == k) {
                break;
            }
            if (j < k) {
                l = j + 1;
            } else {
                h = j - 1;
            }
        }
        return nums[k];
    }

    private int partition(int[] nums, int l, int h) {
        int i = l, j = h + 1;
        while (true) {
            while (nums[++i] < nums[l] && i < h){

            } ;
            while (nums[--j] > nums[l] && j > l){

            } ;
            if (i >= j) {
                break;
            }
            swap(nums, i, j);
        }
        swap(nums, l, j);
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
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

        //第七题
        System.out.println("统计阶乘尾部有多少个 0： "+mt.trailingZeroes(6));

        //第八题
        String a="11";
        String b="1";
        System.out.println("二进制加法： "+mt.addBinary(a,b));


        //第九题
        int[] array={1,2,3,4};
        System.out.println("找出数组中的乘积最大的三个数： "+mt.maximumProduct(array));


        //第十题
        int[] array2={1,2,3,4};
        System.out.println("改变数组元素使所有的数组元素都相等： "+mt.minMoves(array2));



    }
}
