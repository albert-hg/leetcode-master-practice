import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Hash_349_IntersectionOfTwoArrays {
    /* 
    https://leetcode.com/problems/intersection-of-two-arrays/

    Given two integer arrays nums1 and nums2, return an array of their intersection. 
    Each element in the result must be unique and you may return the result in any order.

    Example 1:
    Input: nums1 = [1,2,2,1], nums2 = [2,2]
    Output: [2]

    Example 2:
    Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
    Output: [9,4]
    Explanation: [4,9] is also accepted.
    
    Constraints:
    1 <= nums1.length, nums2.length <= 1000
    0 <= nums1[i], nums2[i] <= 1000
    */

    /* 
    要找重複或交集，就會馬上想到Set，先把其中一個int[]轉為Set，再由另一個去比較即可。
    最大時間複雜度為O(n)
    */
    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        Set<Integer> result = new HashSet<>();
        for (int i : nums2) {
            if (set1.contains(i)) result.add(i);
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    /* 
    或者直接使用Set相關的API
    */
    public static int[] intersection_retainAll(int[] nums1, int[] nums2) {
        Set<Integer> set1 = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        Set<Integer> set2 = Arrays.stream(nums2).boxed().collect(Collectors.toSet());
        set1.retainAll(set2);
        return set1.stream().mapToInt(Integer::intValue).toArray();
    }

    /* 
    還有另外一種作法，是先將兩組數字經過排序，再透過2-pointer尋找重複的數字，
    並將重複的數字在其中一組中，從index=0的位置依序擺放。
    這樣可以減少空間複雜度，並且在執行效率會比較好，因為無須建立Set以及運算Hash，
    但缺點就是會修改到原本的陣列內容。
    */
    public static int[] intersection_2P(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0, k = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) i++;
            else if (nums1[i] > nums2[j]) j++;
            else {
                if (k == 0 || nums1[i] != nums1[k - 1]) {
                    nums1[k++] = nums1[i];
                }
                i++;
                j++;
            }
        }
        return Arrays.copyOf(nums1, k);
    }

    public static boolean test(int[] nums1, int[] nums2, int[] target) {
        int[] output = intersection_2P(nums1, nums2);
        List<Integer> outputArr = Arrays.stream(output).boxed().collect(Collectors.toList());
        for (int t : target) {
            if (!outputArr.contains(t)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(test(new int[]{1,2,2,1},new int[]{2,2},new int[]{2}));
        System.out.println(test(new int[]{4,9,5},new int[]{9,4,9,8,4},new int[]{4,9}));
        System.out.println(test(new int[]{4,9,5},new int[]{9,4,9,8,4},new int[]{9,4}));
    }
}
