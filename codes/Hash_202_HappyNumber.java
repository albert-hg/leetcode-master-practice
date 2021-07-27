import java.util.HashSet;
import java.util.Set;

public class Hash_202_HappyNumber {
    /* 
    https://leetcode.com/problems/happy-number/

    Write an algorithm to determine if a number n is happy.

    A happy number is a number defined by the following process:

    Starting with any positive integer, replace the number by the sum of the squares of its digits.
    Repeat the process until the number equals 1 (where it will stay), 
    or it loops endlessly in a cycle which does not include 1.
    Those numbers for which this process ends in 1 are happy.
    Return true if n is a happy number, and false if not.

    Example 1:
    Input: n = 19
    Output: true
    Explanation:
    1^2 + 9^2 = 82
    8^2 + 2^2 = 68
    6^2 + 8^2 = 100
    1^2 + 0^2 + 0^2 = 1

    Example 2:
    Input: n = 2
    Output: false

    Constraints:
    1 <= n <= 231 - 1
    */

    /* 
    這題如果單純用巢狀雙迴圈暴力解會遇到無窮迴圈的問題，例如16算到最後也會出現16。
    所以如果要避免重複，則必須使用Set來紀錄已經出現過的值。
    */
    public static boolean isHappy(int n) {
        Set<Integer> appearedNums = new HashSet<>();
        int m = n;
        while (m > 3) {
            if (appearedNums.contains(m)) {
                return false;
            } else {
                appearedNums.add(m);
            }
            int sum = 0;
            while (m != 0) {
                sum += ((m % 10) * (m % 10));
                m = m / 10;
            }
            m = sum;
        }
        return m == 1;
    }

    /* 
    這一題找了一下，也有人用2-pointer來做，因為在這個題目中，其實就是一個ListNode的感覺，例如：
    2 -> 4 -> 16 -> 37 -> 58 -> 89 -> 145 -> 42 -> 20
           ↖─────────────────────────────────────↙
    其實就是找環內相遇的問題，如果找到環內相遇，則回傳false。

    用2-pointer的執行速率會比一直對Set進行Hash的轉換來的更有效率，並且在空間複雜度上也會來的更小。
    */
    public static boolean isHappy_2P(int n) {
        int pSlow = n;
        int pFast = getNext(n);
        while (pSlow != 1 && pSlow != pFast) {
            pSlow = getNext(pSlow);
            pFast = getNext(getNext(pFast));
        }
        return pSlow == 1;
    }

    private static int getNext(int m) {
        int sum = 0;
        while (m != 0) {
            sum += ((m % 10) * (m % 10));
            m = m / 10;
        }
        return sum;
    }

    public static boolean test(int intput, boolean target) {
        return !(isHappy(intput) ^ target);
    }

    public static void main(String[] args) {
        System.out.println(test(1, true));
        System.out.println(test(4, false));
        System.out.println(test(7, true));
        System.out.println(test(19, true));
        System.out.println(test(2, false));
    }
}
