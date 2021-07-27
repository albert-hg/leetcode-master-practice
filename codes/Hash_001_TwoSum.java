import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Hash_001_TwoSum {
    /* 
    https://leetcode.com/problems/two-sum/

    Given an array of integers nums and an integer target, 
    return indices of the two numbers such that they add up to target.

    You may assume that each input would have exactly one solution, 
    and you may not use the same element twice.

    You can return the answer in any order.

    Example 1:
    Input: nums = [2,7,11,15], target = 9
    Output: [0,1]
    Output: Because nums[0] + nums[1] == 9, we return [0, 1].

    Example 2:
    Input: nums = [3,2,4], target = 6
    Output: [1,2]

    Example 3:
    Input: nums = [3,3], target = 6
    Output: [0,1]


    Constraints:
    2 <= nums.length <= 104
    -109 <= nums[i] <= 109
    -109 <= target <= 109
    Only one valid answer exists.
    */

    /* 
    如果使用暴力解，最大時間複雜度就會是O(n^2)。
    如果使用Map，最大時間複雜度就會是O(n)。
    */
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> diffToIndex = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            Integer index = diffToIndex.get(diff);
            if (index != null) {
                return new int[]{index, i};
            } else {
                diffToIndex.put(nums[i], i);
            }
        }
        return null;
    }

    public static boolean test(int[] nums, int tarNum, int[] tarRes) {
        int[] output = twoSum(nums, tarNum);
        if (output == null) return false;
        Set<Integer> outputSet = Arrays.stream(output).boxed().collect(Collectors.toSet());
        outputSet.retainAll(
            Arrays.stream(tarRes).boxed().collect(Collectors.toSet())
        );
        return outputSet.size() == 2;
    }

    public static void main(String[] args) {
        System.out.println(test(new int[]{2,7,11,15}, 9, new int[]{0,1}));
        System.out.println(test(new int[]{3,2,4}, 6, new int[]{1,2}));
        System.out.println(test(new int[]{3,3}, 6, new int[]{0,1}));
        System.out.println(test(new int[]{3,2,4}, 6, new int[]{1,2}));
        System.out.println(test(new int[]{2,5,5,11}, 10, new int[]{1,2}));
    }
}
