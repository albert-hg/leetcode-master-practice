public class Array_977_SquaresOfASortedArray {
    /* 
    https://leetcode.com/problems/squares-of-a-sorted-array/
    
    Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.

    Example 1:
    Input: nums = [-4,-1,0,3,10]
    Output: [0,1,9,16,100]
    Explanation: After squaring, the array becomes [16,1,0,9,100].
    After sorting, it becomes [0,1,9,16,100].

    Example 2:
    Input: nums = [-7,-3,2,3,11]
    Output: [4,9,9,49,121]
    */

    /* 
    這題也是一種 2-pointer 的方法，主要是判別兩個指針相交時的結果。
    需要注意的是，這裡要另外建立一個陣列來儲存平方過後的結果，並從最後一個元素開始儲存。
    */
    public static int[] sortedSquares(int[] nums) {
        int[] result = new int[nums.length];
        int pResult = nums.length - 1;
        int pL = 0;
        int pR = nums.length - 1;
        while (pL <= pR) {
            if (nums[pL] * nums[pL] > nums[pR] * nums[pR]) {
                result[pResult] = nums[pL] * nums[pL];
                pL++;
            } else {
                result[pResult] = nums[pR] * nums[pR];
                pR--;
            }
            pResult--;
        }
        return result;
    }

    public static boolean test(int[] inputArray, int[] targetArray) {
        int[] outputArray = sortedSquares(inputArray);
        if (outputArray.length != targetArray.length) return false;
        for (int i = 0; i < targetArray.length; i++) {
            if (outputArray[i] != targetArray[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(test(new int[]{-4,-1,0,3,10}, new int[]{0,1,9,16,100}));
        System.out.println(test(new int[]{-7,-3,2,3,11}, new int[]{4,9,9,49,121}));
        System.out.println(test(new int[]{0,0,0,0}, new int[]{0,0,0,0}));
    }
}