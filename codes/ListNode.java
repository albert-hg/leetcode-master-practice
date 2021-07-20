import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListNode {
    Integer val;
    ListNode next;
    public ListNode(Integer val, ListNode next) {
        this.val = val;
        this.next = next;
    }
    public ListNode(Integer val) {
        this.val = val;
    }

    public static ListNode transListToListNode(int[] vals) {
        if (vals == null || vals.length == 0) return null;

        ListNode head = new ListNode(vals[0]);
        ListNode temp = head;
        for (int i = 1; i < vals.length; i++) {
            temp.next = new ListNode(vals[i]);
            temp = temp.next;
        }
        return head;
    }

    public static int[] toList(ListNode listNode) {
        List<Integer> result = new ArrayList<>();
        ListNode temp = listNode;
        while (temp != null) {
            result.add(temp.val);
            temp = temp.next;
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    @Override
    public String toString() {
        return Arrays.toString(ListNode.toList(this));
    }

    public static void main(String[] args) {
        int[] i = new int[]{};
        ListNode j = ListNode.transListToListNode(i);
        int[] k = ListNode.toList(j);
        System.out.println(Arrays.toString(k));
    }
}