import java.util.Arrays;

public class Array_059_SpiralMatrix2 {
    /* 
    https://leetcode.com/problems/spiral-matrix-ii/

    Given a positive integer n, 
    generate an n x n matrix filled with elements from 1 to n2 in spiral order.

    Example 1:
    Input: n = 3
    Output: [[1,2,3],[8,9,4],[7,6,5]]

    Example 2:
    Input: n = 1
    Output: [[1]]
    */

    /* 
    這是一個比較好的解法，主要是用「二分法」來確定每一次加入數值的大小與方向。
    同時這樣也少掉了每一次塞入數值前的判定。
    例如當n=4，則只需要走兩輪就完成了。
    例如當n=5，一樣也需要走兩輪讚補上中心就完成了。
    */
    public static int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int count = 1;
        for (int round = 0; round < n/2; round ++) {
            int fillingSize = n - round * 2 - 1;
            for (int i = 0; i < fillingSize; i++) { // right direction
                result[round][round+i] = count++;
            }
            for (int i = 0; i < fillingSize; i++) { // down direction
                result[round+i][n-round-1] = count++;
            }
            for (int i = 0; i < fillingSize; i++) { // left direction
                result[n-round-1][n-round-i-1] = count++;
            }
            for (int i = 0; i < fillingSize; i++) { // up direction
                result[n-round-i-1][round] = count++;
            }
        }
        if (n % 2 == 1) {
            result[n/2][n/2] = count;
        }
        return result;
    }

    /* 
    第一次看到這個題目的時候覺得可以用不同的模式來代表下一步螺旋的方向，
    所以可以用一個mode來決定方向應為上下左右。
    但其實這個並不是一個好方法，算是暴力解，因為在每一次都還要判斷下一個數字的方向與模式。
    */
    public static int[][] generateMatrix_BAD(int n) {
        int[][] result = new int[n][n];
        int x = 0;  //initial coordinate x
        int y = 0;  //initial coordinate y
        int mode = 0;
        for (int i = 0; i < n * n; i++) {
            result[x][y] = i + 1;
            switch (mode % 4) {
                case 0: //向右
                    if (y + 1 >= n || result[x][y + 1] != 0) {
                        mode++;
                        x++;
                    } else {
                        y++;
                    }
                    break;
                case 1: //向下
                    if (x + 1 >= n || result[x + 1][y] != 0) {
                        mode++;
                        y--;
                    } else {
                        x++;
                    }
                    break;
                case 2: //向左
                    if (y - 1 < 0 || result[x][y - 1] != 0) {
                        mode++;
                        x--;
                    } else {
                        y--;
                    }
                    break;
                case 3: //向上
                    if (x - 1 < 0 || result[x - 1][y] != 0) {
                        mode++;
                        y++;
                    } else {
                        x--;
                    }
                    break;
                default:
                    break;
            }
        }
        System.out.println(Arrays.deepToString(result));
        return result;
    }

    public static boolean test(int n, int[][] target) {
        int[][] output = generateMatrix(n);
        if (output.length != target.length) return false;
        for (int i = 0; i < n; i++) {
            if (output[i].length != target[i].length) return false;
            for (int j = 0; j < n; j++) {
                if (output[i][j] != target[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println(test(1, new int[][]{new int[]{1}}));
        System.out.println(test(2, new int[][]{new int[]{1,2},new int[]{4,3}}));
        System.out.println(test(3, new int[][]{new int[]{1,2,3}, new int[]{8,9,4}, new int[]{7,6,5}}));
        System.out.println(test(4, new int[][]{new int[]{1, 2, 3, 4}, new int[]{12, 13, 14, 5}, new int[]{11, 16, 15, 6}, new int[]{10, 9, 8, 7}}));
    }
}
