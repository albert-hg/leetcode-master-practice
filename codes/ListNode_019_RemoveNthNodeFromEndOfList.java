import java.util.Arrays;

public class ListNode_019_RemoveNthNodeFromEndOfList {
    /* 
    https://leetcode.com/problems/remove-nth-node-from-end-of-list/

    Given the head of a linked list, 
    remove the nth node from the end of the list and return its head.

    Example 1:
    Input: head = [1,2,3,4,5], n = 2
    Output: [1,2,3,5]

    Example 2:
    Input: head = [1], n = 1
    Output: []

    Example 3:
    Input: head = [1,2], n = 1
    Output: [1]
    */

    /* 
    這是一個比較好的做法，用一組slow-fast的2-pointer，
    讓2-pointer的間距為n，當fast到達尾端，則可以得到倒數的目標節點。
    */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode pFast = dummy;
        ListNode pSlow = dummy;
        while (pFast != null) {
            pSlow = n-- < 0 ? pSlow.next : pSlow;
            pFast = pFast.next;
        }
        // 在跨過的同時也要注意被刪除的Node.next要被設定為null，否則不會被GC回收。
        ListNode del = pSlow.next;
        pSlow.next = del.next;
        del.next = null;
        return dummy.next;
    }

    /* 
    這是第一次看到這個題目的思路，沒有想到要用2-pointer，反而是選了一個比較麻煩偏向暴力解的方法。
    先把ListNode做一次reverse，接著再還原個過程中計數，並刪除目標Node。
    但這樣的作法時間複雜度為O(2n)，且要大量修改指針的位置。
    */
    public static ListNode removeNthFromEnd_BAD(ListNode head, int n) {
        // reverse
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        cur = pre;
        pre = null;
        // reverse agagin
        while (cur != null) {
            cur = --n == 0 ? cur.next : cur; // 當計數已經歸零時，跳過這個Node
            if (cur == null) return null;
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return head;
    }

    public static boolean test(int[] head, int n, int[] target) {
        int[] output = ListNode.toList(removeNthFromEnd(ListNode.transListToListNode(head), n));
        return Arrays.equals(output, target);
    }
    public static void main(String[] args) {
        System.out.println(test(new int[]{1,2,3,4,5}, 2, new int[]{1,2,3,5}));
        System.out.println(test(new int[]{1}, 1, new int[]{}));
        System.out.println(test(new int[]{1,2}, 1, new int[]{1}));
    }

}
