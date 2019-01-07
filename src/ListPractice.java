/**
链表练习
 * */
public class ListPractice {
    /**
     1.从有序链表中删除重复节点
     * */

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        head.next = deleteDuplicates(head.next);
        return head.val == head.next.val ? head.next : head;
    }


    /**
     2.回文链表

     题目要求：以 O(1) 的空间复杂度来求解。

     切成两半，把后半段反转，然后比较两半是否相等。
     * */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null){
            return true;
        }
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 链表有奇数个节点，让 slow 指向下一个节点
        if (fast != null) {
            slow = slow.next;
        }
        // 切成两个链表
        cut(head, slow);
        return isEqual(head, reverse(slow));
    }

    private void cut(ListNode head, ListNode cutNode) {
        while (head.next != cutNode) {
            head = head.next;
        }
        head.next = null;
    }

    private ListNode reverse(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode nextNode = head.next;
            head.next = newHead;
            newHead = head;
            head = nextNode;
        }
        return newHead;
    }

    private boolean isEqual(ListNode l1, ListNode l2) {
        while (l1 != null && l2 != null) {
            if (l1.val != l2.val){
                return false;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        return true;
    }


    public static void main(String[] args) {
        ListPractice lp=new ListPractice();

        //第一题
        ListNode n1=new ListNode(1);
        ListNode n2=new ListNode(1);
        ListNode n3=new ListNode(2);
        ListNode n4=new ListNode(2);
        ListNode n5=new ListNode(3);
        n1.next=n2;
        n2.next=n3;
        n3.next=n4;
        n4.next=n5;
        ListNode head=lp.deleteDuplicates(n1);
        System.out.print("从有序链表中删除重复节点： ");
        while(head!=null){
            System.out.print(head.val+" ");
            head=head.next;
        }
        System.out.println();



        //第二题
        ListNode n21=new ListNode(2);
        ListNode n22=new ListNode(1);
        ListNode n23=new ListNode(0);
        ListNode n24=new ListNode(2);
        ListNode n25=new ListNode(2);
        n21.next=n22;
        n22.next=n23;
        n23.next=n24;
        n24.next=n25;
        System.out.println("判断是否为回文链表： "+lp.isPalindrome(n21));
    }
}
