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
        System.out.print("从有序链表中删除重复节点");
        while(head!=null){
            System.out.print(head.val+" ");
            head=head.next;
        }
    }
}
