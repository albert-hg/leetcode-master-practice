import java.util.Arrays;

public class Array_027_RemoveElement {
    /* 
    https://leetcode.com/problems/remove-element/

    Given an integer array nums and an integer val, remove all occurrences of val in nums in-place.
    The relative order of the elements may be changed.

    Since it is impossible to change the length of the array in some languages, you must instead have 
    the result be placed in the first part of the array nums. More formally, if there are k elements 
    after removing the duplicates, then the first k elements of nums should hold the final result. 
    It does not matter what you leave beyond the first k elements.

    Return k after placing the final result in the first k slots of nums.

    Do not allocate extra space for another array. You must do this by modifying the input array 
    in-place with O(1) extra memory.

    Custom Judge:

    The judge will test your solution with the following code:

    int[] nums = [...]; // Input array
    int val = ...; // Value to remove
    int[] expectedNums = [...]; // The expected answer with correct length.
                                // It is sorted with no values equaling val.

    int k = removeElement(nums, val); // Calls your implementation

    assert k == expectedNums.length;
    sort(nums, 0, k); // Sort the first k elements of nums
    for (int i = 0; i < actualLength; i++) {
        assert nums[i] == expectedNums[i];
    }
    If all assertions pass, then your solution will be accepted.

    
    Example 1:
    Input: nums = [3,2,2,3], val = 3
    Output: 2, nums = [2,2,_,_]
    Explanation: Your function should return k = 2, with the first two elements of nums being 2.
    It does not matter what you leave beyond the returned k (hence they are underscores).

    Example 2:
    Input: nums = [0,1,2,2,3,0,4,2], val = 2
    Output: 5, nums = [0,1,4,0,3,_,_,_]
    Explanation: Your function should return k = 5, with the first five elements of nums containing 0, 0, 1, 3, and 4.
    Note that the five elements can be returned in any order.
    It does not matter what you leave beyond the returned k (hence they are underscores).
    */

    /* 
    這一題主要是使用 2-pointer 的方法。
    2-pointer 的主要使用觀念有兩個：
        1. 一個「快」一個「慢」，大部分都是快的遍歷全部，取慢的為結果
        2. 一個「左」一個「右」，大部分都是在左指針與右指針相交的時候為結果
    */
    public static int removeElement(int[] nums, int val) {
        int pSlow = 0;
        for (int pFast = 0; pFast < nums.length; pFast++) {
            if (nums[pFast] != val) {
                nums[pSlow] = nums[pFast];
                pSlow++;
            }
        }
        return pSlow;
    }

    public static boolean test(TestData testData) {
        int[] nums = testData.iNums;
        int val = testData.iVal;
        int[] expectedNums = testData.oNums; 
        int k = removeElement(nums, val);
        if (k != expectedNums.length) return false;
        Arrays.sort(nums, 0, k);
        Arrays.sort(expectedNums);
        for (int i = 0; i < testData.oVal; i++) {
            if (nums[i] != expectedNums[i]) return false;
        }
        return true;
    }
    public static void main(String[] args) {
        TestData testData1 = new TestData(new int[]{3,2,2,3}, 3, 2, new int[]{2,2});
        System.out.println(test(testData1));
        TestData testData2 = new TestData(new int[]{0,1,2,2,3,0,4,2}, 2, 5, new int[]{0,1,4,0,3});
        System.out.println(test(testData2));
    }

    private static class TestData {
        int[] iNums; int iVal; int[] oNums; int oVal;
        TestData (int[] _iNums, int _iVal, int _oVal, int[] _oNums) {
            iNums = _iNums; iVal = _iVal; oNums= _oNums; oVal = _oVal;
        }
    }
}
