public class ListNode_160_IntersectionOfTwoLinkedLists {
    /* 
    https://leetcode.com/problems/intersection-of-two-linked-lists/

    Given the heads of two singly linked-lists headA and headB, 
    return the node at which the two lists intersect. 
    If the two linked lists have no intersection at all, return null.

    Example 1:
    Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
    Output: Intersected at '8'
    Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).
    From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5]. 
    There are 2 nodes before the intersected node in A; 
    There are 3 nodes before the intersected node in B.

    Example 2:
    Input: intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
    Output: Intersected at '2'
    Explanation: The intersected node's value is 2 (note that this must not be 0 if the two lists intersect).
    From the head of A, it reads as [1,9,1,2,4]. From the head of B, it reads as [3,2,4]. 
    There are 3 nodes before the intersected node in A; 
    There are 1 node before the intersected node in B.

    Example 3:
    Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
    Output: No intersection
    Explanation: From the head of A, it reads as [2,6,4]. From the head of B, 
    it reads as [1,5]. Since the two lists do not intersect, intersectVal must be 0, 
    while skipA and skipB can be arbitrary values.
    Explanation: The two lists do not intersect, so return null.
    */

    /* 
    這一題的「相交」讓我誤會了。這一提的意思是「兩個ListNode在哪一個點開始，可以交會成一條」。
    假設 A=[1,2,3,4,5] B=[5,4,3,4,5]，那麼交會的結果就會是：
    A => 1, 2, ↘
                3, 4, 5
    B => 5, 4, ↗
    但如果今天是 A=[1,2,3,4,5,6] B=[5,4,3,4,5]，那麼這兩條ListNode則沒有相交。
    所以其實只要將兩條ListNode對齊尾端，再比較從哪一個點開始是交會點就可以了。
    */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int aLength = getListNodeLength(headA);
        int bLength = getListNodeLength(headB);
        if (aLength > bLength) {
            headA = getIndex(headA, aLength - bLength);
        } else if (bLength > aLength) {
            headB = getIndex(headB, bLength - aLength);
        }
        ListNode intersectionNode = null;
        while (headA != null) {
            if (headA == headB) {
                intersectionNode = intersectionNode == null ? headA : intersectionNode;
            } else {
                intersectionNode = null;
            }
            headA = headA.next;
            headB = headB.next;
        }
        return intersectionNode;
    }

    public static int getListNodeLength(ListNode head) {
        int i = 0;
        while (head != null) {
            i++;
            head = head.next;
        }
        return i;
    }

    public static ListNode getIndex(ListNode head, int index) {
        while (index-- > 0) {
            head = head.next;
        }
        return head;
    }

    /* 
    另外有一種很有趣的解法，他有點類似加法的交換率的感覺。
    假設 A=[1,2,3,4] B=[3,3,4]，
    若 A'=A+B=[1,2,3,4,3,3,4]
    且 B'=B+A=[3,3,4,1,2,3,4]
    如此一來就可以直接比對交集，而無須透過長度差的方式來解決此問題了。
    實際上這種應用不會直接真的在後面接起另外的ListNode，只要善用交替循環就可以。
    */
    public static ListNode getIntersectionNode_2_Round(ListNode headA, ListNode headB) {
        ListNode intersectionNode = null;
        ListNode curA = headA;
        ListNode curB = headB;
        while (curA != null || curB != null) {
            if (curA == null) curA = headB;
            else if (curB == null) curB = headA;
            
            if (curA == curB) intersectionNode = intersectionNode == null ? curA : intersectionNode;
            else intersectionNode = null;

            curA = curA.next;
            curB = curB.next;
        }
        return intersectionNode;
    }


    public static boolean test(int[] headA, int skipA, int[] headB, int skipB) {
        ListNode dummyHeadA = new ListNode(0);
        ListNode dummyHeadB = new ListNode(0);
        ListNode dummyIntersection = new ListNode(0);
        ListNode tmpA = dummyHeadA;
        ListNode tmpB = dummyHeadB;
        ListNode tmpI = dummyIntersection;
        for (int i = 0; i < skipA; i++) {
            tmpA.next = new ListNode(headA[i]);
            tmpA = tmpA.next;
        }
        for (int i = 0; i < skipB; i++) {
            tmpB.next = new ListNode(headB[i]);
            tmpB = tmpB.next;
        }
        for (int i = skipA; i < headA.length; i++) {
            tmpI.next = new ListNode(headA[i]);
            tmpI = tmpI.next;
        }
        tmpA.next = dummyIntersection.next;
        tmpB.next = dummyIntersection.next;
        ListNode output = getIntersectionNode_2_Round(dummyHeadA.next, dummyHeadB.next);
        return output == dummyIntersection.next;
    }
    public static void main(String[] args) {
        System.out.println(test(new int[]{4,1,8,4,5}, 2, new int[]{7,6,1,8,4,5}, 3));
        System.out.println(test(new int[]{1,9,1,2,4}, 3, new int[]{3,2,4}, 1));
        System.out.println(test(new int[]{2,6,4}, 3, new int[]{1,5}, 2));
    }
}
