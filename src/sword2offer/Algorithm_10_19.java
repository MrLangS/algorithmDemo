package sword2offer;

public class Algorithm_10_19 {
    /**
     * 10
     * 求斐波那契数列的第 n 项，n <= 39。
     * @param n
     * @return
     */
    public int Fibonacci(int n) {
        if (n <= 1)
            return n;

        int pre2 = 0, pre1 = 1;
        int fib = 0;
        for(int i=2; i<=n;i++){
            fib = pre1+pre2;
            pre2 = pre1;
            pre1 = fib;
        }
        return fib;
    }
    //由于待求解的 n 小于 40，因此可以将前 40 项的结果先进行计算，之后就能以 O(1) 时间复杂度得到第 n 项的值了。
    public class Solution {

        private int[] fib = new int[40];

        public Solution() {
            fib[1] = 1;
            for (int i = 2; i < fib.length; i++)
                fib[i] = fib[i - 1] + fib[i - 2];
        }

        public int Fibonacci(int n) {
            return fib[n];
        }
    }

    /** 10.3
     * 一只青蛙一次可以跳上 1 级台阶，也可以跳上 2 级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
     * @param n
     * @return
     */
    public int JumpFloor(int n) {
        if (n <= 2)
            return n;
        int pre2 = 1, pre1 = 2;
        int result = 1;
        for (int i = 2; i < n; i++) {
            result = pre2 + pre1;
            pre2 = pre1;
            pre1 = result;
        }
        return result;
    }

    /** 11
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
     * @param nums
     * @return
     */
    public int minNumberInRotateArray(int[] nums) {
        if (nums.length == 0)
            return 0;

        int l = 0, h = nums.length - 1;
        while (l < h) {
            int m = (l + h) / 2 + 1;
            if (nums[l] == nums[m] && nums[m] == nums[h])
                return minNumber(nums, l, h);
            else if (nums[m] <= nums[h])
                h = m;
            else
                l = m + 1;
        }
        return nums[l];
    }

    public int minNumber(int[] nums, int l, int h) {
        for (int i = l; i <= h; i++) {
            if (nums[i] > nums[i+1] )
                return nums[i+1];
        }
        return nums[l];
    }

    /** 12
     * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
     * 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
     * 如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。
     *
     */
    private final static int[][] next = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    private int rows;
    private int cols;

    public boolean hasPath(char[] array, int rows, int cols, char[] str) {
        if (rows == 0 || cols == 0)
            return false;
        this.rows = rows;
        this.cols = cols;
        boolean[][] marked = new boolean[rows][cols];
        char[][] matrix = buildMatrix(array);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                if (backtracking(matrix, str, marked, 0, i, j))
                    return true;

        return false;
    }

    boolean backtracking(char[][] matrix, char[] str, boolean[][] marked, int pathLen, int r, int c) {
        if (pathLen == str.length)
            return true;
        if (r < 0 || r >= rows || c < 0 || c >= cols || matrix[r][c] != str[pathLen] || marked[r][c])
            return false;
        marked[r][c] = true;
        for (int[] n : next)
            if(backtracking(matrix,str,marked,pathLen+1,r+n[0],c+n[1]))
                return true;
        marked[r][c] = false;
        return false;
    }

    private char[][] buildMatrix(char[] array) {
        char[][] matrix = new char[rows][cols];

        for (int i = 0, idx = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                matrix[i][j] = array[idx++];
        return matrix;

    }

    /** 13
     * 地上有一个 m 行和 n 列的方格。一个机器人从坐标 (0, 0) 的格子开始移动，每一次只能向左右上下四个方向移动一格，
     * 但是不能进入行坐标和列坐标的数位之和大于 k 的格子。
     * @return
     */
    //private static final int[][] next = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    //private int rows;
    //private int cols;
    private int cnt = 0;
    private int threshold;
    private int[][] digitSum;

    public int movingCount(int threshold, int rows, int cols) {

    }
}
