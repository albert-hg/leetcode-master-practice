import java.util.Arrays;

public class ListNode_203_RemoveLinkedListElements {
    /* 
    https://leetcode.com/problems/remove-linked-list-elements/

    Given the head of a linked list and an integer val,
    remove all the nodes of the linked list that has Node.val == val,
    and return the new head.

    Example 1:
    Input: head = [1,2,6,3,4,5,6], val = 6
    Output: [1,2,3,4,5]

    Example 2:
    Input: head = [], val = 1
    Output: []

    Example 3:
    Input: head = [7,7,7,7], val = 7
    Output: []
    */

    /* 
    這題一開始在實作的時候被head卡住了，因為刪除head的方式跟後方的節點刪除的操作不太一樣。
    如果要刪除head，有兩種方式：
        1. 先處理head，再處理後方的ListNode
        2. 在head前加入一個dummy的節點，統一所有刪除的操作，返回結果為dummy.next
    */
    public static ListNode removeElements_2_step(ListNode head, int val) {
        // remove head if the value is the same
        while (head != null && head.val == val) {
            head = head.next;
        }
        // remove after node, now head has been impossible to be the value 'val'
        ListNode cur = head;
        while(cur != null && cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

    public static ListNode removeElements_dummy(ListNode head, int val) {
        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;
        while (cur != null && cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    public static boolean test(int[] input, int val, int[] target) {
        ListNode output = removeElements_dummy(ListNode.transListToListNode(input), val);
        return Arrays.equals(ListNode.toList(output), target);
    }

    public static void main(String[] args) {
        System.out.println(test(new int[]{1,2,6,3,4,5,6}, 6, new int[]{1,2,3,4,5}));
        System.out.println(test(new int[]{1,2,6,6,6,3,4,5,6}, 6, new int[]{1,2,3,4,5}));
        System.out.println(test(new int[]{}, 1, new int[]{}));
        System.out.println(test(new int[]{7,7,7,7}, 7, new int[]{}));
    }
}
