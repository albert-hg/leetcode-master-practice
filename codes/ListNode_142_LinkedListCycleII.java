import java.util.HashSet;
import java.util.Set;

public class ListNode_142_LinkedListCycleII {
    /* 
    https://leetcode.com/problems/linked-list-cycle-ii/ 

    Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

    There is a cycle in a linked list if there is some node in the list 
    that can be reached again by continuously following the next pointer. 

    Internally, pos is used to denote the index of the node that tail's next pointer is connected to. 
    Note that pos is not passed as a parameter.

    Notice that you should not modify the linked list.


    Example 1:
    3 -> 2 -> 0 -> -4
          ↖←←←←←←←↙
    Input: head = [3,2,0,-4], pos = 1
    Output: tail connects to node index 1
    Explanation: There is a cycle in the linked list, where tail connects to the second node.

    Example 2:
    Input: head = [1,2], pos = 0
    Output: tail connects to node index 0
    Explanation: There is a cycle in the linked list, where tail connects to the first node.

    Example 3:
    Input: head = [1], pos = -1
    Output: no cycle
    Explanation: There is no cycle in the linked list.
    */

    /* 
    做這題如果要用2-pointer的方式則會需要分成兩個步驟做：
        1. 先求出環內相遇點
        2. 用起點與相遇點求出環的入口
    如果要算出環的入口，則會需要計算一下。假設：
        環外節點數 = L 個
        環內節點數 = K 個
        環內相遇點為 i，剩下為 j，則 K = i+j
    現在有兩個節點：slow 與 fast，slow 每次走 1 個節點，fast 每次走 2 個節點，所以當相遇時
        fast 所走的步數 = L+nK+i
        slow 所走的步數 = L+i
    因為 2*slow = 1*fast，所以：
        2(L+i)=L+nK+i   =>  L=nK-i=nK-(K-j)=(n-1)K+j
    如果 n=1，則 L=j，又因為 K=i+j。
    這代表著如果 fast 走了一圈後在環內所相遇的節點，到環形入口的節點數，會剛好是是環外節點數。
    
    因此就可以善用這種特性，用一組2-pointer，一個為環內相遇點的節點，一個為鏈結的起點，
    同時步進所相遇的點，就為環形入口。
    */
    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        // 求得環內相遇點
        ListNode pSlow = head;
        ListNode pFast = head.next;
        while (pSlow != pFast) {
            pSlow = pSlow.next;
            if (pFast == null || pFast.next == null) return null;
            pFast = pFast.next.next;
        }
        // 已經確定有環。從起點與環內相遇點開始逼近，找到下一次的相遇點
        pSlow = head;
        pFast = pFast.next;
        while (pSlow != pFast) {
            pSlow = pSlow.next;
            pFast = pFast.next;
        }
        return pSlow;
    }

    /* 
    這一題的最一開始最直接想到的是：
        因為要判斷是否重複，所以把走過的node放進set比較出現第二次的結果就是環的入口」，最大空間複雜度為O(n)。
    但因為題目期望我們使用的最大空間複雜度為O(1)，所以不適用。不過還是紀錄一下我的想法。
    */
    public static ListNode detectCycle_Set(ListNode head) {
        Set<ListNode> nodes = new HashSet<>();
        ListNode cur = head;
        while (cur != null) {
            if (nodes.contains(cur)) return cur;
            else nodes.add(cur);
            cur = cur.next;
        }
        return cur;
    }

    public static boolean test(int[] input, int pos) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        ListNode tailNext = null;
        for (int i = 0; i < input.length; i++) {
            ListNode node = new ListNode(input[i]);
            if (i == pos) tailNext = node;
            cur.next = node;
            cur = cur.next;
        }
        cur.next = tailNext;
        ListNode ouput = detectCycle_Set(dummyHead.next);
        return ouput == tailNext;
    }

    public static void main(String[] args) {
        System.out.println(test(new int[]{3,2,0,-4}, 1));
        System.out.println(test(new int[]{1,2}, 0));
        System.out.println(test(new int[]{1}, 0));
        System.out.println(test(new int[]{1}, -1));
    }
}
