/**
 字符串练习
 **/
public class StringPractice {
    /**
     1.两个字符串包含的字符是否完全相同
     * */
    public boolean isAnagram(String s, String t) {
        int[] cnts = new int[26];
        for (char c : s.toCharArray()) {
            cnts[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            cnts[c - 'a']--;
        }
        for (int cnt : cnts) {
            if (cnt != 0) {
                return false;
            }
        }
        return true;
    }


    /**
     2.计算一组字符集合可以组成的回文字符串的最大长度

     使用长度为 256 的整型数组来统计每个字符出现的个数，每个字符有偶数个可以用来构成回文字符串。

     因为回文字符串最中间的那个字符可以单独出现，所以如果有单独的字符就把它放到最中间。
     * */
    public int longestPalindrome(String s) {
        int[] cnts = new int[256];
        for (char c : s.toCharArray()) {
            cnts[c]++;
        }
        int palindrome = 0;
        for (int cnt : cnts) {
            palindrome += (cnt / 2) * 2;
        }
        if (palindrome < s.length()) {
            // 这个条件下 s 中一定有单个未使用的字符存在，可以把这个字符放到回文的最中间
            palindrome++;
        }
        return palindrome;

    }

    /**
     3.回文子字符串个数
     **/
    private int cnt = 0;

    public int countSubstrings(String s) {
        for (int i = 0; i < s.length(); i++) {
            // 奇数长度
            extendSubstrings(s, i, i);
            // 偶数长度
            extendSubstrings(s, i, i + 1);
        }
        return cnt;
    }

    private void extendSubstrings(String s, int start, int end) {
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
            cnt++;
        }
    }


    /**
     4.判断一个整数是否是回文数

     要求不能使用额外空间，也就不能将整数转换为字符串进行判断。

     将整数分成左右两部分，右边那部分需要转置，然后判断这两部分是否相等。
     * */
    public boolean isPalindrome(int x) {
        if (x == 0) {
            return true;
        }
        if (x < 0 || x % 10 == 0) {
            return false;
        }
        int right = 0;
        while (x > right) {
            right = right * 10 + x % 10;
            x /= 10;
        }
        return x == right || x == right / 10;
    }


    /**
     5.统计二进制字符串中连续 1 和连续 0 数量相同的子字符串个数

     注意题意：是连续1和连续0, ‘0101’虽然个数相同但是里头的数是不连续的。
     */
    public int countBinarySubstrings(String s) {
        int preLen = 0, curLen = 1, count = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                curLen++;
            } else {
                preLen = curLen;
                curLen = 1;
            }

            //只要前者长度大于等于后者就说明前者的一部分可以和后者构成子字符串。
            if (preLen >= curLen) {
                count++;
            }
        }
        return count;
    }



    public static void main(String[] args) {
        StringPractice sp=new StringPractice();


        //第一题
        String s1="net";
        String s2="ten";
        System.out.println("两个字符串包含的字符是否完全相同: "+sp.isAnagram(s1,s2));


        //第二题
        String s3="abccccdd";
        System.out.println("计算一组字符集合可以组成的回文字符串的最大长度: "+sp.longestPalindrome(s3));

        //第三题
        String s4="aaa";
        System.out.println("回文字符串个数: "+sp.countSubstrings(s4));

        //第四题
        System.out.println("判断一个整数是否是回文数: "+sp.isPalindrome(121));

        //第五题
        String s5="00110011";
        System.out.println("回文字符串个数: "+sp.countBinarySubstrings(s5));
    }
}
