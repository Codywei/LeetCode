import java.util.Arrays;
import java.util.HashSet;

/**
 双指针练习
 * */
public class DoublePointer {

    private final static HashSet<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

    /**
     1.两数平方和
     题目描述：判断一个数是否为两个数的平方和。
     * */
    public boolean judgeSquareSum(int c) {
        int i=0;
        int j=(int) Math.sqrt(c);
        while(i<=j){
            int powsum=i*i+j*j;
            if(c==powsum){
                return true;
            }else if(c<powsum){
                j--;
            }else{
                i++;
            }
        }
        return false;
    }


    /**
     2.反转字符串中的元音字符
     使用双指针指向待反转的两个元音字符，一个指针从头向尾遍历，一个指针从尾到头遍历。
     此题应用了i++等操作，所以在循环中需要将取出的元素先存成方法局部变量
     * */
    public String reverseVowels(String s) {
        int i = 0, j = s.length() - 1;
        char[] result = new char[s.length()];
        while (i <= j) {
            //将取出的元素存成局部变量，避免索引自增后导致取出的元素发生变化
            char ci = s.charAt(i);
            char cj = s.charAt(j);
            if (!vowels.contains(ci)) {
                result[i++] = ci;
            } else if (!vowels.contains(cj)) {
                result[j--] = cj;
            } else {
                result[i++] = cj;
                result[j--] = ci;
            }
        }
        return new String(result);
    }



    /**
     3.回文字符串
     题目描述：可以删除一个字符，判断是否能构成回文字符串。
     * */
    public boolean validPalindrome(String s) {
        int i=-1;
        int j=s.length();
        while(++i<--j){
            if(s.charAt(i)!=s.charAt(j)){
                return isPalindrome(s,i+1,j)||isPalindrome(s,i,j-1);
            }
        }
        return true;
    }

    private boolean isPalindrome(String s, int i, int j) {
        while(i<j){
            if(s.charAt(i++)<s.charAt(j--)){
                return false;
            }
        }
        return true;
    }



    /**
     4.归并两个有序数组
     题目描述：把归并结果存到第一个数组上。
     需要从尾开始遍历，否则在 nums1 上归并得到的值会覆盖还未进行归并比较的值。
     * */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index1 = m - 1, index2 = n - 1;
        int indexMerge = m + n - 1;
        while (index1 >= 0 || index2 >= 0) {
            if (index1 < 0) {
                nums1[indexMerge--] = nums2[index2--];
            } else if (index2 < 0) {
                nums1[indexMerge--] = nums1[index1--];
            } else if (nums1[index1] > nums2[index2]) {
                nums1[indexMerge--] = nums1[index1--];
            } else {
                nums1[indexMerge--] = nums2[index2--];
            }
        }
    }



    /**
     5.判断链表是否存在环
     使用双指针，一个指针每次移动一个节点，一个指针每次移动两个节点，如果存在环，那么这两个指针一定会相遇。
     * */
    public boolean hasCycle(ListNode head) {
        if(head==null){
            return false;
        }
        ListNode l1=head;
        ListNode l2=head.next;
        while(l1!=null&&l2!=null&&l2.next!=null){
            if(l1==l2){
                return true;
            }
            l1=l1.next;
            l2=l2.next.next;
        }
        return false;
    }



    /**
     6.最长子序列
     题目描述：删除 s 中的一些字符，使得它构成字符串列表 d 中的一个字符串，找出能构成的最长字符串。
     如果有多个相同长度的结果，返回字典序的最大(最靠前)字符串。
     * */
    public String findLongestWord(String s, String[] d) {

        String longestword="";
        for(String target:d) {
            int L1 = longestword.length();
            int L2 = target.length();
            //如果当前最长子序列已经比当前目标串长或者长度相同的情况下字典序更靠前，则跳过该目标串
            if(L1>L2||(L1==L2&&longestword.compareTo(target)<0)){
                continue;
            }
            if(isValid(s,target)){
                longestword=target;
            }
        }
        return longestword;
    }

    private boolean isValid(String s, String target) {
        int i=0;
        int j=0;
        while(i<s.length()&&j<target.length()){
            if(s.charAt(i)==target.charAt(j)){
                j++;
            }
            i++;
        }
        return j==target.length();

    }


    public static void main(String[] args) {
        DoublePointer dp=new DoublePointer();

        //第一题
        System.out.println("两数平方和： "+dp.judgeSquareSum(6));

        //第二题
        System.out.println("反转字符串中的元音字符： "+dp.reverseVowels("leetcode"));

        //第三题
        System.out.println("回文字符串变种： "+dp.validPalindrome("abca"));

        //第四题
        int[] num1={1,2,3,0,0,0};
        int[] num2={2,5,6};
        dp.merge(num1,3,num2,3);
        System.out.print("归并有序数组： ");
        for (int i = 0; i < num1.length; i++) {
          System.out.print(num1[i]+" ");
        }
        System.out.println();

        //第五题
        ListNode l1=new ListNode(1);
        ListNode l2=new ListNode(2);
        ListNode l3=new ListNode(3);
        ListNode l4=new ListNode(4);
        ListNode l5=new ListNode(5);
        l1.next=l2;
        l2.next=l3;
        l3.next=l4;
        l4.next=l5;
        l5.next=l3;
        System.out.println("链表是否有环： "+dp.hasCycle(l1));

        //第六题
        String s="abpcplea";
        String[] d={"ale","apple","monkey","plea"};
        System.out.println("最长子序列： "+dp.findLongestWord(s,d));
    }

}
