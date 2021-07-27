public class Hash_383_RansomNote {
    /* 
    https://leetcode.com/problems/ransom-note/

    Given two stings ransomNote and magazine, 
    return true if ransomNote can be constructed from magazine and false otherwise.

    Each letter in magazine can only be used once in ransomNote.

    Example 1:
    Input: ransomNote = "a", magazine = "b"
    Output: false

    Example 2:
    Input: ransomNote = "aa", magazine = "ab"
    Output: false

    Example 3:
    Input: ransomNote = "aa", magazine = "aab"
    Output: true

    Constraints:
    1 <= ransomNote.length, magazine.length <= 105
    ransomNote and magazine consist of lowercase English letters.
    */

    /* 
    這題其實已經在242_ValidAnagram做過了，只是題型稍微改變了一下。
    要注意的是題目說明的是要用magazine重組，所以其實可以先把magazine建立字典以及數量，
    再從ransomNote的字元開始扣掉，以檢查是不是有ransomNote有但magazine沒有的字元。
    */
    public static boolean canConstruct(String ransomNote, String magazine) {
        int[] letterCount = new int[26];
        for (char c : magazine.toCharArray()) {
            letterCount[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            letterCount[c - 'a']--;
        }
        for (int i : letterCount) {
            if (i < 0) return false;
        }
        return true;
    }

    public static boolean test(String ransomNote, String magazine, boolean target) {
        return !(canConstruct(ransomNote, magazine) ^ target);
    }

    public static void main(String[] args) {
        System.out.println(test("a", "b", false));
        System.out.println(test("aa", "ab", false));
        System.out.println(test("aa", "aab", true));
    }
}
