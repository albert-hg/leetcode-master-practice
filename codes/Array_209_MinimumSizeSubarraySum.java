public class Array_209_MinimumSizeSubarraySum {
    /* 
    https://leetcode.com/problems/minimum-size-subarray-sum/

    Given an array of positive integers nums and a positive integer target, 
    return the minimal length of a contiguous subarray [numsl, numsl+1, ..., numsr-1, numsr] 
    of which the sum is greater than or equal to target. 
    If there is no such subarray, return 0 instead.

    Example 1:
    Input: target = 7, nums = [2,3,1,2,4,3]
    Output: 2
    Explanation: The subarray [4,3] has the minimal length under the problem constraint.
    
    Example 2:
    Input: target = 4, nums = [1,4,4]
    Output: 1

    Example 3:
    Input: target = 11, nums = [1,1,1,1,1,1,1,1]
    Output: 0
    */

    /* 
    這是一個2-pointer的解法，一律在迴圈內先加上pR的值，再動態調整pL的位置，直到pL~pR之間的值為目標值。
    這也是一種稱之為「Slide Window」的解法，每次移動右窗口後，再依照窗口內的條件來修正左窗口。
    */
    public static int minSubArrayLen(int s, int[] nums) {
        int sum = 0;
        int pL = 0;
        int minSubLength = Integer.MAX_VALUE;
        for (int pR = 0; pR < nums.length; pR++) {
            sum += nums[pR];
            while (sum >= s) {
                sum -= nums[pL];
                minSubLength = Math.min(minSubLength, pR - pL + 1);
                pL++;
            }
        }
        return minSubLength == Integer.MAX_VALUE ? 0 : minSubLength;
    }


    /* 
    但這是一個錯誤的想法，一開始我想要在迴圈裏面比較sum的值，並動態更新pointer，
    後來發現要在一個while裡面館兩個狀態其實沒有比較簡單，反而容易搞混。
    */
    public static int minSubArrayLen_ERROR_THINKING(int s, int[] nums) {
        int sum = 0;
        int pL = 0;
        int pR = 0;
        int minSubLength = Integer.MAX_VALUE;
        while(pR < nums.length) {
            if (sum < s) {
                sum += nums[pR];
                pR++;
            } else {
                sum -= nums[pL];
                minSubLength = Math.min(minSubLength, pR - pL + 1);
                pL++;
            }
        }
        return minSubLength == Integer.MAX_VALUE ? 0 : minSubLength;
    }

    public static boolean test(int[] input, int target, int output) {
        return output == minSubArrayLen(target, input);
    }
    
    public static void main(String[] args) {
        System.out.println(test(new int[]{2,3,1,2,4,3}, 7, 2));
        System.out.println(test(new int[]{1,4,4}, 4, 1));
        System.out.println(test(new int[]{1,1,1,1,1,1,1,1}, 11, 0));
        System.out.println(test(new int[]{1,2,3,4,5}, 11, 3));
    }
}