import java.util.Arrays;

public class ListNode_024_SwapNodesInPairs {
    /* 
    https://leetcode.com/problems/swap-nodes-in-pairs/

    Given a linked list, swap every two adjacent nodes and return its head. 
    You must solve the problem without modifying the values in the list's nodes 
    (i.e., only nodes themselves may be changed.)

    Example 1:
    Input: head = [1,2,3,4]
    Output: [2,1,4,3]

    Example 2:
    Input: head = []
    Output: []

    Example 3:
    Input: head = [1]
    Output: [1]
    */
    /* 
    因為這一題要兩兩交換，但在交換的過程中如果忽略了之前的Node，就會找至無法取得正常的結果。
    所以如果要統一操作規則，可以在前面加一個dummy node。
    這題建議邊做要邊畫圖，不然操作一堆指標會亂掉。
    */
    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy;
        ListNode cur = dummy.next;
        while (cur != null && cur.next != null) {
            ListNode tmp = cur.next.next;
            pre.next = cur.next;
            pre.next.next = cur;
            cur.next = tmp;
            pre = cur;
            cur = cur.next;
        }
        return dummy.next;
    }

    public static boolean test(int[] head, int[] target) {
        int[] output = ListNode.toList(swapPairs(ListNode.transListToListNode(head)));
        return Arrays.equals(output, target);
    }

    public static void main(String[] args) {
        System.out.println(test(new int[]{1,2}, new int[]{2,1}));
        System.out.println(test(new int[]{1,2,3,4}, new int[]{2,1,4,3}));
        System.out.println(test(new int[]{1,2,3,4,5,6}, new int[]{2,1,4,3,6,5}));
        System.out.println(test(new int[]{}, new int[]{}));
        System.out.println(test(new int[]{1}, new int[]{1}));
        
    }
}
