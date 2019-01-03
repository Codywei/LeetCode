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

    public static void main(String[] args) {
        StringPractice sp=new StringPractice();


        //第一题
        String s1="net";
        String s2="net";
        System.out.println("两个字符串包含的字符是否完全相同: "+sp.isAnagram(s1,s2));
    }
}
