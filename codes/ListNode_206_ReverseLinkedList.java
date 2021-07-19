import java.util.Arrays;

public class ListNode_206_ReverseLinkedList {
    /* 
    https://leetcode.com/problems/reverse-linked-list/

    Given the head of a singly linked list, reverse the list, and return the reversed list.

    Example 1:
    Input: head = [1,2,3,4,5]
    Output: [5,4,3,2,1]

    Example 2:
    Input: head = [1,2]
    Output: [2,1]

    Example 3:
    Input: head = []
    Output: []
    */

    /* 
    這有兩種方式可以解決：
        1. 使用迴圈跑2-pointer
        2. 遞迴
    */
    public static ListNode reverseList_2_pointer(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    public static ListNode reverseList_recursive(ListNode head) {
        return reverseList_recursive(null, head);
    }

    public static ListNode reverseList_recursive(ListNode pre, ListNode cur) {
        if (cur == null) {
            return pre;
        }
        ListNode tmp = cur.next;
        cur.next = pre;
        pre = cur;
        cur = tmp;
        return reverseList_recursive(pre, cur);
    }


    public static boolean test(int[] head, int[] target) {
        int[] output = ListNode.toList(reverseList_2_pointer(ListNode.transListToListNode(head)));
        return Arrays.equals(output, target);
    }

    public static void main(String[] args) {
        System.out.println(test(new int[]{1,2,3,4,5}, new int[]{5,4,3,2,1}));
        System.out.println(test(new int[]{1,2}, new int[]{2,1}));
        System.out.println(test(new int[]{}, new int[]{}));
    }
}
