package sword2offer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Algorithm60To69
 * 剑指60~69
 *
 * @author lang
 * @date 2019-05-07
 */
public class Algorithm60To69 {

    /** 60
     * 把 n 个骰子仍在地上，求点数和为 s 的概率。
     * 暂时保留
     * @param n
     * @return
     */
    public List<Map.Entry<Integer, Double>> dicesSum(int n) {
        return null;
    }

    /** 61
     * 五张牌，其中大小鬼为癞子，牌面为 0。判断这五张牌是否能组成顺子。
     * @param nums
     * @return
     */
    public boolean isContinuous(int[] nums) {
        if (nums.length < 5) {
            return false;
        }
        Arrays.sort(nums);
        //统计癞子数量
        int cnt = 0;
        for (int num : nums) {
            if (num == 0) {
                cnt++;
            }
        }
        for (int i = cnt; i < nums.length - 1; i++) {
            if (nums[i + 1] == nums[i]) {
                return false;
            }
            cnt -= nums[i + 1] - nums[i] - 1;
        }
        return cnt >= 0;
    }

    /** 62
     * 让小朋友们围成一个大圈。然后，随机指定一个数 m，让编号为 0 的小朋友开始报数。
     * 每次喊到 m-1 的那个小朋友要出列唱首歌，然后可以在礼品箱中任意的挑选礼物，并且不再回到圈中，
     * 从他的下一个小朋友开始，继续 0...m-1 报数 .... 这样下去 .... 直到剩下最后一个小朋友，可以不用表演。
     *
     * 约瑟夫环：f[i]=(f[i-1]+k)%i = (f[i-1] +m%i) % i = (f[i-1] + m) % i ; (i>1)          f[1]=0;
     * @param n
     * @param m
     * @return
     */
    public int lastRemainingSolution(int n, int m) {
        if(n == 0) {
            return -1;
        }
        if(n == 1) {
            return 0;
        }
        return (lastRemainingSolution(n-1, m) + m) % n;
    }

    /** 63
     * 股票的最大利润
     * 使用贪心策略，假设第 i 轮进行卖出操作，买入操作价格应该在 i 之前并且价格最低。
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if(prices == null && prices.length == 0){
            return 0;
        }
        int soFarMin = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            soFarMin = Math.min(soFarMin, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - soFarMin);
        }
        return maxProfit;
    }

    /** 64
     * 求 1+2+3+...+n,要求不能使用乘除法、for、while、if、else、switch、case 等关键字及条件判断语句 A ? B : C。
     * @param n
     * @return
     */
    public int sumSolution(int n) {
        int sum = n;
        boolean b = (n > 0) && ((sum += sumSolution(n-1)) > 0);
        return sum;
    }

    /** 65
     * 不用加减乘除做加法
     *
     * a ^ b 表示没有考虑进位的情况下两数的和，(a & b) << 1 就是进位。
     * 递归会终止的原因是 (a & b) << 1 最右边会多一个 0，那么继续递归，进位最右边的 0 会慢慢增多，最后进位会变为 0，递归终止。
     * @param a
     * @param b
     * @return
     */
    public int add(int a, int b) {
        return b == 0 ? a : add(a ^ b, (a & b) << 1);
    }

    /** 66
     * 给定一个数组 A[0, 1,..., n-1]，请构建一个数组 B[0, 1,..., n-1]，
     * 其中 B 中的元素 B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。要求不能使用除法。
     * @param a
     * @return
     */
    public int[] multiply(int[] a) {
        int n = a.length;
        int[] B = new int[n];
        //从左往右累乘
        for (int i = 0, product = 1; i < n; product *= a[i], i++){
            B[i] = product;
        }
        //从右往左累乘
        for (int i = n - 1, product = 1; i >= 0; product *= a[i], i--){
            B[i] *= product;
        }
        return B;
    }

    /** 67
     * 将一个字符串转换成一个整数，字符串不是一个合法的数值则返回 0，要求不能使用字符串转换整数的库函数。
     * @param str
     * @return
     */
    public int strToInt(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        boolean isNegative = str.charAt(0) == '-';
        int ret = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i == 0 && (c == '+' || c == '-')) {
                continue;
            }
            if (c < '0' || c > '9') {
                return 0;
            }
            ret = ret * 10 + (c - '0');
        }
        return ret;
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /** 68
     * 树中两个节点的最低公共祖先
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //二叉查找树中查找
        /*
        if (root == null) {
            return root;
        }

        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        return root;*/
        //普通二叉树查找
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }
}
