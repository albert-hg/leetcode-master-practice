public class Array_704_BinarySearch {
    /* 
    https://leetcode.com/problems/binary-search/

    Given an array of integers nums which is sorted in ascending order, 
    and an integer target, write a function to search target in nums. 
    If target exists, then return its index. Otherwise, return -1.

    You must write an algorithm with O(log n) runtime complexity.

    Example 1:
    Input: nums = [-1,0,3,5,9,12], target = 9
    Output: 4
    Explanation: 9 exists in nums and its index is 4

    Example 2:
    Input: nums = [-1,0,3,5,9,12], target = 2
    Output: -1
    Explanation: 2 does not exist in nums so return -1 
    */

    /* 
    1. 必須先確定nums是否為經過排序的，這是前提。
    2. 選擇要「[left, right]」還是要「[left, right)」。
    3. 確定查詢區間後，則要依照規則定義邊界位置。
    */
    public static int search(int[] nums, int target) {
        return search_LCRO(nums, target);
    }

    /* 
    此為[left, right]的寫法，亦即左閉又閉。
    此時pR的位置應為最後一個元素。
    每當更新pR時，應為pM-1的位置，以符合[left, right]的定義。
    */
    public static int search_LCRC(int[] nums, int target) {
        int pL = 0;
        int pR = nums.length - 1; //右閉
        while (pL <= pR) {
            int pM = pL + (pR - pL) / 2;
            if (target < nums[pM]) { // 目標在左邊
                pR = pM - 1;
            } else if (target > nums[pM]) { //目標在右邊
                pL = pM + 1;
            } else {
                return pM;
            }
        }
        return -1;
    }

    /* 
    此為[left, right)的寫法，亦即左閉又開。
    此時pR應為nums的大小，亦即最後一個元素的下一個元素。
    每當更新pR時，應為pM的位置，以符合[left, right)的定義。
    */
    public static int search_LCRO(int[] nums, int target) {
        int pL = 0;
        int pR = nums.length;
        while (pL < pR) {
            int pM = pL + (pR - pL) / 2;
            if (target < nums[pM]) {
                pR = pM;
            } else if (target > nums[pM]) {
                pL = pM + 1;
            } else {
                return pM;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(search(new int[]{-1,0,3,5,9,12}, 9) == 4);
        System.out.println(search(new int[]{-1,0,3,5,9,12}, 2) == -1);
        System.out.println(search(new int[]{1,3,5,7,9,11,13,15,17,19,21}, 3) == 1);
    }
}
