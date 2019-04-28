package sword2offer;

public class Algorithm10To19 {
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
        this.rows = rows;
        this.cols = cols;
        this.threshold = threshold;
        initDigitSum();
        boolean[][] marked = new boolean[rows][cols];

        return 0;
    }

    public void dfs(boolean[][] marked, int r, int c) {
        if (r < 0 || r >= rows || c < 0 || c >= cols || marked[r][c])
            return;
        marked[r][c] = true;
        if (digitSum[r][c] > threshold)
            return;
        cnt++;
        for (int[] n : next) {
            dfs(marked, r + n[0], r + n[1]);
        }
    }

    public void initDigitSum() {
        int[] cellDigitSum = new int[Math.max(rows, cols)];
        for (int i = 0; i < cellDigitSum.length; i++) {
            int n = i;
            while (n > 0) {
                cellDigitSum[i] += n % 10;
                n /= 10;
            }
        }
        this.digitSum = new int[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                this.digitSum[i][j] = cellDigitSum[i] + cellDigitSum[j];

    }

    /** 14
     * 把一根绳子剪成多段，并且使得每段的长度乘积最大。
     * @param n
     * @return
     */
    public int integerBreak(int n) {
        if (n < 2)
            return 0;
        if (n == 2)
            return 1;
        if (n == 3)
            return 2;
        int timesOf3 = n / 3;
        if (n - timesOf3 * 3 == 1)
            timesOf3--;
        int timesOf2 = (n - timesOf3 * 3) / 2;
        return (int) (Math.pow(3, timesOf3)) * (int) (Math.pow(2, timesOf2));

        //动态规划
        /*
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++)
            for (int j = 1; j < i; j++)
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), dp[j] * (i - j)));
        return dp[n];*/
    }

    /** 15
     * 输入一个整数，输出该数二进制表示中 1 的个数。
     * @param n
     * @return
     */
    public int NumberOf1(int n) {
        int count = 0;
        while (n != 0) {
            count += 1;
            n &= n - 1;
        }
        return count;
        //return Integer.bitCount(n);
    }

    /** 16
     * 给定一个 double 类型的浮点数 base 和 int 类型的整数 exponent，求 base 的 exponent 次方。
     * @param base
     * @param exponent
     * @return
     */
    public double Power(double base, int exponent) {
        if (exponent == 0)
            return 1;
        if (exponent == 1)
            return base;
        boolean isNegative = false;
        if (exponent < 0) {
            isNegative = true;
            exponent = -exponent;
        }

        double pow = Power(base * base, exponent / 2);
        if (exponent % 2 == 1)
            pow = base * pow;
        return isNegative ? 1 / pow : pow;
    }

    /** 17
     * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数即 999。
     * @param n
     */
    public void print1ToMaxOfNDigits(int n) {
        if(n<=0)
            return;
        char[] number = new char[n];
        print1ToMaxOfNDigits(number,0);
    }

    private void print1ToMaxOfNDigits(char[] number, int digit) {
        if (digit == number.length) {
            printNumber(number);
            return;
        }

        for (int i = 0; i < number.length; i++) {
            number[i] = (char) (i + '0');
            print1ToMaxOfNDigits(number, digit + 1);
        }
    }

    private void printNumber(char[] number) {
        int index = 0;
        while (index < number.length && number[index] == '0')
            index++;
        while (index < number.length)
            System.out.print(number[index++]);
        System.out.println();
    }

    /** 18.1
     * 在 O(1) 时间内删除链表节点
     * @param head
     * @param tobeDelete
     * @return
     */
    public ListNode deleteNode(ListNode head, ListNode tobeDelete) {
        if (head == null || tobeDelete == null)
            return null;

        if (tobeDelete.next != null) {
            ListNode next = tobeDelete.next;
            tobeDelete.val = next.val;
            tobeDelete.next = next.next;
        } else {
            if (head == tobeDelete) {
                return null;
            } else {
                ListNode cur = head;
                while (cur.next != tobeDelete)
                    cur = cur.next;
                cur.next = null;
            }
        }
        return head;
    }

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /** 18.2
     * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针
     * @param pHead
     * @return
     */
    public ListNode deleteDuplication(ListNode pHead) {
        if(pHead == null || pHead.next == null)
            return pHead;
        ListNode next = pHead.next;
        if(pHead.val == next.val){
            while (next != null && pHead.val == next.val)
                next = next.next;
            return deleteDuplication(next);
        }else {
            pHead.next = deleteDuplication(pHead.next);
            return pHead;
        }
    }

    /** 19
     * 请实现一个函数用来匹配包括 '.' 和 '*' 的正则表达式。模式中的字符 '.' 表示任意一个字符，而 '*' 表示它前面的字符可以出现任意次（包含 0 次）。
     * @param str
     * @param pattern
     * @return
     */
    public boolean match(char[] str, char[] pattern) {
        return false;
    }
}
