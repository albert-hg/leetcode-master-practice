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
    如果要算出相遇的節點，則會需要計算一下。假設：
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
        // TODO: implement
        return head;
    }

    public static boolean test(int[] input, int pos) {
        return false;
    }

    public static void main(String[] args) {
        
    }
}
