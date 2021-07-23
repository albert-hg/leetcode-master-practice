import java.util.HashMap;
import java.util.Map;

public class Hash_242_ValidAnagram {
    /* 
    https://leetcode.com/problems/valid-anagram/

    Given two strings s and t, return true if t is an anagram of s, and false otherwise.

    Example 1:
    Input: s = "anagram", t = "nagaram"
    Output: true

    Example 2:
    Input: s = "rat", t = "car"
    Output: false

    1 <= s.length, t.length <= 5 * 104
    s and t consist of lowercase English letters.
    */

    /* 
    因為題目的限制，確定都是英文小寫，所以也可以使用一個固定大小的array來記錄出現的次數
    */
    public static boolean isAnagram(String s, String t) {
        int[] counter = new int[26];
        for (char i : s.toCharArray()) {
            counter[i - 'a']++;
        }
        for (char i : t.toCharArray()) {
            counter[i - 'a']--;
        }
        for (int i : counter) {
            if (i != 0) return false;
        }
        return true;
    }

    /* 
    這一題一開始很直覺的用HashMap紀錄出現的字母的數字，但這樣必須同時記錄字母以及出現的次數。
    */
    public static boolean isAnagram_Map(String s, String t) {
        Map<Character, Integer> charCount = new HashMap<>();
        for (char i : s.toCharArray()) {
            Integer j = charCount.get(i);
            charCount.put(i, j == null ? 1 : j + 1);
        }
        for (char i : t.toCharArray()) {
            Integer j = charCount.get(i);
            charCount.put(i, j == null ? -1 : j - 1);
            if (j != null && j - 1 == 0) charCount.remove(i);
        }
        return charCount.size() == 0;
    }

    public static boolean test(String s, String t, boolean target) {
        boolean output = isAnagram_Map(s, t);
        return !(target ^ output);
    }

    public static void main(String[] args) {
        System.out.println(test("anagram", "nagaram", true));
        System.out.println(test("rat", "car", false));
        System.out.println(test("rat", "tar", true));
    }
}