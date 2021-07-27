import java.util.HashMap;
import java.util.Map;

public class Hash_454_4SumII {
    /* 
    https://leetcode.com/problems/4sum-ii/

    Given four integer arrays nums1, nums2, nums3, and nums4 all of length n, 
    return the number of tuples (i, j, k, l) such that:

    0 <= i, j, k, l < n
    nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
    
    Example 1:
    Input: nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
    Output: 2
    Explanation:
    The two tuples are:
    1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
    2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0

    Example 2:
    Input: nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
    Output: 1
    
    Constraints:
    n == nums1.length
    n == nums2.length
    n == nums3.length
    n == nums4.length
    1 <= n <= 200
    -2^28 <= nums1[i], nums2[i], nums3[i], nums4[i] <= 2^28
    */

    /* 
    原本想說「3Sum或4Sum都沒有做過，就要直接做4SumII是對的嗎?」，
    但如果仔細深究，會發現這題會比3Sum或4Sum都還要更容易使用Map來處理，因為它可以接受完全重複的值。

    如果用暴力解，那最大時間複雜度是O(n^4)。

    比較好的解法為：
    先將任意兩個nums加總，並把加總結果的出現次數用Map紀錄，
    接著在將另外兩的nums加總，比較家總結果是否有相反數存在於Map中。
    如此一來最大時間複雜度就可以下降至O(n^2)
    */
    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> sumToCount = new HashMap<>();
        for(int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                Integer count = sumToCount.get(nums1[i] + nums2[j]);
                sumToCount.put(nums1[i] + nums2[j], count == null ? 1 : count + 1);
            }
        }
        int counter = 0;
        for(int i = 0; i < nums3.length; i++) {
            for (int j = 0; j < nums4.length; j++) {
                Integer count = sumToCount.get(0 - (nums3[i] + nums4[j]));
                if (count != null) counter += count;
            }
        }
        return counter;   
    }

    public static boolean test(int[] nums1, int[] nums2, int[] nums3, int[] nums4, int target) {
        return target == fourSumCount(nums1, nums2, nums3, nums4);
    }

    public static void main(String[] args) {
        // System.out.println(test(new int[]{1,2},new int[]{-2,-1},new int[]{-1,2},new int[]{0,2},2));
        // System.out.println(test(new int[]{0},new int[]{0},new int[]{0},new int[]{0},1));
        System.out.println(test(new int[]{-1,-1},new int[]{-1,1},new int[]{-1,1},new int[]{1,-1},6));
    }
}
