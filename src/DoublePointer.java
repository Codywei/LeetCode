import java.util.Arrays;
import java.util.HashSet;

public class DoublePointer {
    private final static HashSet<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

    /**
     1.����ƽ����
     ��Ŀ�������ж�һ�����Ƿ�Ϊ��������ƽ���͡�
     * */
    public boolean judgeSquareSum(int c) {
        int i=0;
        int j=(int)Math.sqrt(c);
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
     2.��ת�ַ����е�Ԫ���ַ�
     ʹ��˫ָ��ָ�����ת������Ԫ���ַ���һ��ָ���ͷ��β������һ��ָ���β��ͷ������
     ����Ӧ����i++�Ȳ�����������ѭ������Ҫ��ȡ����Ԫ���ȴ�ɷ����ֲ�����
     * */
    public String reverseVowels(String s) {
        int i = 0, j = s.length() - 1;
        char[] result = new char[s.length()];
        while (i <= j) {
            //��ȡ����Ԫ�ش�ɾֲ�����������������������ȡ����Ԫ�ط����仯
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
     3.�����ַ���
     ��Ŀ����������ɾ��һ���ַ����ж��Ƿ��ܹ��ɻ����ַ�����
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
     4.�鲢������������
     ��Ŀ�������ѹ鲢����浽��һ�������ϡ�
     ��Ҫ��β��ʼ������������ nums1 �Ϲ鲢�õ���ֵ�Ḳ�ǻ�δ���й鲢�Ƚϵ�ֵ��
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
     5.�ж������Ƿ���ڻ�
     ʹ��˫ָ�룬һ��ָ��ÿ���ƶ�һ���ڵ㣬һ��ָ��ÿ���ƶ������ڵ㣬������ڻ�����ô������ָ��һ����������
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
     6.�������
     ��Ŀ������ɾ�� s �е�һЩ�ַ���ʹ���������ַ����б� d �е�һ���ַ������ҳ��ܹ��ɵ���ַ�����
     ����ж����ͬ���ȵĽ���������ֵ�������(�ǰ)�ַ�����
     * */
    public String findLongestWord(String s, String[] d) {

        String longestword="";
        for(String target:d) {
            int L1 = longestword.length();
            int L2 = target.length();
            //�����ǰ��������Ѿ��ȵ�ǰĿ�괮�����߳�����ͬ��������ֵ������ǰ����������Ŀ�괮
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

        //��һ��
        System.out.println("����ƽ���ͣ� "+dp.judgeSquareSum(6));

        //�ڶ���
        System.out.println("��ת�ַ����е�Ԫ���ַ��� "+dp.reverseVowels("leetcode"));

        //������
        System.out.println("�����ַ������֣� "+dp.validPalindrome("abca"));

        //������
        int[] num1={1,2,3,0,0,0};
        int[] num2={2,5,6};
        dp.merge(num1,3,num2,3);
        System.out.print("�鲢�������飺 ");
        for (int i = 0; i < num1.length; i++) {
          System.out.print(num1[i]+" ");
        }
        System.out.println();

        //������
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
        System.out.println("�����Ƿ��л��� "+dp.hasCycle(l1));

        //������
        String s="abpcplea";
        String[] d={"ale","apple","monkey","plea"};
        System.out.println("������У� "+dp.findLongestWord(s,d));
    }

}
