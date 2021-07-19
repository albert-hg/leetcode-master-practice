import java.util.Arrays;

public class ListNode_707_DesignLinkedList {
    /* 
    https://leetcode.com/problems/design-linked-list/

    Implement the MyLinkedList class:

    MyLinkedList() Initializes the MyLinkedList object.
    int get(int index) Get the value of the indexth node in the linked list. If the index is invalid, return -1.
    void addAtHead(int val) Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
    void addAtTail(int val) Append a node of value val as the last element of the linked list.
    void addAtIndex(int index, int val) Add a node of value val before the indexth node in the linked list. If index equals the length of the linked list, the node will be appended to the end of the linked list. If index is greater than the length, the node will not be inserted.
    void deleteAtIndex(int index) Delete the indexth node in the linked list, if the index is valid.
    

    Example 1:
    Input
    ["MyLinkedList", "addAtHead", "addAtTail", "addAtIndex", "get", "deleteAtIndex", "get"]
    [[], [1], [3], [1, 2], [1], [1], [1]]
    Output
    [null, null, null, null, 2, null, 3]

    Explanation
    MyLinkedList myLinkedList = new MyLinkedList();
    myLinkedList.addAtHead(1);
    myLinkedList.addAtTail(3);
    myLinkedList.addAtIndex(1, 2);    // linked list becomes 1->2->3
    myLinkedList.get(1);              // return 2
    myLinkedList.deleteAtIndex(1);    // now the linked list is 1->3
    myLinkedList.get(1);              // return 3
    */
    /*
    * Your MyLinkedList object will be instantiated and called as such:
    * MyLinkedList obj = new MyLinkedList();
    * int param_1 = obj.get(index);
    * obj.addAtHead(val);
    * obj.addAtTail(val);
    * obj.addAtIndex(index,val);
    * obj.deleteAtIndex(index);
    */

    public ListNode dummy;
    int size;

    /** Initialize your data structure here. */
    public ListNode_707_DesignLinkedList() {
        this.dummy = new ListNode(0);
        this.size = 0;
    }

    /** Get the value of the index-th node in the linked list. 
     * If the index is invalid, return -1. */
    public int get(int index) {
        if (index >= size || index < 0) return -1;
        ListNode cur = this.dummy.next;
        while (index > 0) {
            cur = cur.next;
            index--;
        }
        return cur.val;
    }
    
    /** Add a node of value val before the first element of the linked list. 
     * After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        /* 
        其實不用再實作一次，直接使用寫好的 addAtIndex(index,val) 就好。
        this.addAtIndex(0, val);
        */
        ListNode head = new ListNode(val, this.dummy.next);
        this.dummy.next = head;
        this.size++;
    }
    
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        /* 
        其實不用再實作一次，直接使用寫好的 addAtIndex(index,val) 就好。
        this.addAtIndex(this.size, val);
        */
        ListNode cur = this.dummy;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = new ListNode(val);
        this.size++;
    }
    
    /** Add a node of value val before the index-th node in the linked list. 
     * If index equals to the length of linked list, the node will be appended to the end of linked list. 
     * If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index < 0 || index > this.size) return;
        ListNode cur = this.dummy;
        while (index > 0) {
            cur = cur.next;
            index--;
        }
        cur.next = new ListNode(val, cur.next);
        this.size++;
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= this.size) return;
        ListNode cur = this.dummy;
        while (index > 0) {
            cur = cur.next;
            index--;
        }
        cur.next = cur.next.next;
        this.size--;
    }

    public static void test(String[] ope, int[][] vals) {
        ListNode_707_DesignLinkedList myLinkedList = null;
        for (int i = 0; i < ope.length; i++) {
            System.out.printf("ope: %13s,\t value: %s,\t",
                ope[i], Arrays.toString(vals[i])
            );
            if ("MyLinkedList".equals(ope[i])) {
                myLinkedList = new ListNode_707_DesignLinkedList();
            } else if ("get".equals(ope[i])) {
                myLinkedList.get(vals[i][0]);
            } else if ("addAtHead".equals(ope[i])) {
                myLinkedList.addAtHead(vals[i][0]);
            } else if ("addAtTail".equals(ope[i])) {
                myLinkedList.addAtTail(vals[i][0]);
            } else if ("addAtIndex".equals(ope[i])) {
                myLinkedList.addAtIndex(vals[i][0], vals[i][1]);
            } else if ("deleteAtIndex".equals(ope[i])) {
                myLinkedList.deleteAtIndex(vals[i][0]);
            }
            System.out.printf("now: %s\n", Arrays.toString(ListNode.toList(myLinkedList.dummy.next)));
        }
        System.out.println("==========================================================");
    }

    public static void main(String[] args) {
        test(
            new String[]{"MyLinkedList","addAtHead","addAtTail","addAtIndex","get","deleteAtIndex","get"},
            new int[][]{new int[]{},new int[]{1},new int[]{3},new int[]{1,2},new int[]{1},new int[]{1},new int[]{1},}
        );
        test(
            new String[]{"MyLinkedList","addAtHead","addAtHead","addAtHead","addAtIndex","deleteAtIndex","addAtHead","addAtTail","get","addAtHead","addAtIndex","addAtHead"},
            new int[][]{new int[]{},new int[]{7},new int[]{2},new int[]{1},new int[]{3,0},new int[]{2},new int[]{6},new int[]{4},new int[]{4},new int[]{4},new int[]{5,0},new int[]{6}}
        );
    }
}
