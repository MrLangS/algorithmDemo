package sword2offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.PriorityQueue;

/**
 * Algorithm50To59
 * 剑指50~59
 *
 * @author lang
 * @date 2019-05-07
 */
public class Algorithm50To59 {
    /** 50
     * 在一个字符串中找到第一个只出现一次的字符，并返回它的位置。
     *
     * @param str
     * @return
     */
    public int firstNotRepeatingChar(String str) {
        int[] cnts = new int[256];
        for (int i = 0; i < str.length(); i++) {
            cnts[str.charAt(i)]++;
        }
        for (int i = 0; i < str.length(); i++) {
            if (cnts[str.charAt(i)] == 1) {
                return i;
            }
        }
        //return -1;
        //使用两个比特位存储信息
        BitSet bs1 = new BitSet(256);
        BitSet bs2 = new BitSet(256);
        for (char c : str.toCharArray()) {
            if (!bs1.get(c) && !bs2.get(c)) {
                bs1.set(c);
            } else if (bs1.get(c) && !bs2.get(c)) {
                bs2.set(c);
            }
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (bs1.get(c) && !bs2.get(c)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 51 暂时略过
     */
    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /** 52
     * 两个链表的第一个公共结点
     * @param pHead1
     * @param pHead2
     * @return
     */
    public ListNode findFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode l1 = pHead1,l2 = pHead2;
        while (l1 != l2) {
            l1 = (l1 == null) ? pHead2 : l1.next;
            l2 = (l2 == null) ? pHead1 : l2.next;
        }
        return l1;
    }

    /** 53
     * 数字在排序数组中出现的次数
     * @param nums
     * @param k
     * @return
     */
    public int getNumberOfK(int[] nums, int k) {
        int first = binarySearch(nums, k);
        int last = binarySearch(nums, k + 1);
        return (first == nums.length || nums[first] != k) ? 0 : last - first;
    }

    private int binarySearch(int[] nums, int k) {
        int l = 0, h = nums.length;
        while (l < h) {
            int m = (l + h) / 2;
            if (nums[m] < k) {
                l = m + 1;
            } else {
                h = m;
            }
        }
        return l;
    }

    private TreeNode ret;
    private int cnt = 0;
    /** 54
     * 二叉查找树的第 K 个结点
     * @param pRoot
     * @param k
     * @return
     */
    public TreeNode kThNode(TreeNode pRoot, int k) {
        inOrder(pRoot,k);
        return  ret;
    }

    private void inOrder(TreeNode root, int k) {
        if(root == null || cnt >= k) {
            return;
        }
        inOrder(root.left,k);
        cnt++;
        if(cnt == k) {
            ret = root;
        }
        inOrder(root.right,k);
    }

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    /** 55.1
     * 从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
     * @param root
     * @return
     */
    public int treeDepth(TreeNode root) {
        return root == null ? 0 : 1 + Math.max(treeDepth(root.left),treeDepth(root.right));
    }

    private boolean isBalanced = true;
    /** 55.2
     * 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
     * @param root
     * @return
     */
    public boolean isBalancedSolution(TreeNode root) {
        height(root);
        return isBalanced;
    }

    private int height(TreeNode root) {
        if (root == null || !isBalanced) {
            return 0;
        }
        int left = height(root.left);
        int right = height(root.right);
        if (Math.abs(left - right) > 1) {
            isBalanced = false;
        }
        return 1 + Math.max(left, right);
    }

    /** 56
     * 一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
     * @param nums
     * @param num1
     * @param num2
     */
    public void findNumsAppearOnce(int[] nums, int[] num1, int[] num2) {
        int diff = 0;
        for (int num : nums){
            diff ^= num;
        }
        diff &= -diff;
        for (int num : nums) {
            if ((num & diff) == 0){
                num1[0] ^= num;
            }
            else{
                num2[0] ^= num;
            }
        }
    }

    /** 57.1
     * 输入一个递增排序的数组和一个数字 S，在数组中查找两个数，使得他们的和正好是 S。如果有多对数字的和等于 S，输出两个数的乘积最小的。
     * @param array
     * @param sum
     * @return
     */
    public ArrayList<Integer> findNumbersWithSum(int[] array, int sum) {
        int i = 0, j = array.length-1;
        while(i < j) {
            int cur = array[i] + array[j];
            if(cur == sum) {
                return new ArrayList<>(Arrays.asList(array[i],array[j]));
            }
            if(cur <= sum) {
                i++;
            } else {
                j--;
            }
        }
        return new ArrayList<>();
    }

    /** 57.2
     * 输出所有和为 S 的连续正数序列。
     * @param sum
     * @return
     */
    public ArrayList<ArrayList<Integer>> findContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        int start = 1, end = 2;
        int curSum = 3;
        while (end < sum) {
            if(curSum > sum) {
                curSum-=start;
                start++;
            } else if(curSum < sum) {
                end++;
                curSum+=end;
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                for(int i = start; i <= end; i++) {
                    list.add(i);
                }
                ret.add(list);
                curSum-=start;
                start++;
                end++;
                curSum+=end;
            }
        }
        return ret;
    }

    /** 58.1
     * 翻转单词顺序列
     * @param str
     * @return
     */
    public String reverseSentence(String str) {
        int n = str.length();
        char[] chars = str.toCharArray();
        int i = 0, j = 0;
        while (j <= n) {
            if (j == n || chars[j] == ' ') {
                reverse(chars, i, j - 1);
                i = j + 1;
            } else {
                j++;
            }
        }
        reverse(chars, 0, n - 1);
        return new String(chars);
    }

    private void reverse(char[] c, int i, int j) {
        while (i < j) {
            swap(c, i++, j--);
        }
    }

    private void swap(char[] c, int i, int j) {
        char t = c[i];
        c[i] = c[j];
        c[j] = c[i];
    }

    /** 58.2
     * 现在有个简单的任务，就是用字符串模拟这个指令的运算结果。对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。
     * @param str
     * @param n
     * @return
     */
    public String leftRotateString(String str, int n) {
        if(n >= str.length()){
            return str;
        }
        char[] chars = str.toCharArray();
        reverse(chars, 0,n-1);
        reverse(chars, n, chars.length-1);
        reverse(chars, 0, chars.length-1);
        return new String(chars);
    }

    /** 59
     * 滑动窗口的最大值
     * @param num
     * @param size
     * @return
     */
    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> ret = new ArrayList<>();
        if(size > num.length || size < 1) {
            return ret;
        }
        //大顶堆
        PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> o2-o1);
        for (int i = 0; i<size; i++) {
            heap.add(num[i]);
        }
        ret.add(heap.peek());
        for (int i = 0, j = i+size; j < num.length; i++,j++) {
            heap.remove(num[i]);
            heap.add(num[j]);
            ret.add(heap.peek());
        }
        return ret;
    }
}
