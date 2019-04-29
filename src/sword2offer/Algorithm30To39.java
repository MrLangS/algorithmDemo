package sword2offer;

import java.util.*;

/**
 * Algorithm40To49
 * 剑指 30~39
 * @author lang
 * @date 2019-04-29
 */
public class Algorithm30To39 {
    /** 30
     * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
     */
    public Stack<Integer> dataStack = new Stack<>();
    public Stack<Integer> minStack = new Stack<>();

    public void push(int node) {
        dataStack.push(node);
        minStack.push(minStack.isEmpty() ? node : Math.min(minStack.peek(), node));
    }

    public void pop() {
        dataStack.pop();
        minStack.pop();
    }

    public int top() {
        return dataStack.peek();
    }

    public int min() {
        return minStack.peek();
    }

    /** 31
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。
     * @param pushSequence
     * @param popSequence
     * @return
     */
    public boolean isPopOrder(int[] pushSequence, int[] popSequence) {
        int n = pushSequence.length;
        Stack<Integer> stack = new Stack<>();
        for (int pushIndex = 0, popIndex = 0; pushIndex < n; pushIndex++) {
            stack.push(pushSequence[pushIndex]);
            while (popIndex < n && !stack.isEmpty() && stack.peek() == popSequence[popIndex]) {
                stack.pop();
                popIndex++;
            }
        }
        return stack.isEmpty();
    }

    /** 32.1
     * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
     * @param root
     * @return
     */
    public ArrayList<Integer> printFromTopToBottom(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<Integer> ret = new ArrayList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int length = queue.size();
            while (length-->0){
                TreeNode t = queue.poll();
                if(t == null){
                    continue;
                }
                ret.add(t.val);
                queue.offer(t.left);
                queue.offer(t.right);
            }
        }
        return ret;
    }

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    /** 32.2
     * 把二叉树打印成多行
     * @param pRoot
     * @return
     */
    ArrayList<ArrayList<Integer>> print2(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot);
        while (!queue.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<>();
            int cnt = queue.size();
            while (cnt-- > 0) {
                TreeNode node = queue.poll();
                if (node == null){
                    continue;
                }
                list.add(node.val);
                queue.add(node.left);
                queue.add(node.right);
            }
            if (list.size() != 0){
                ret.add(list);
            }
        }
        return ret;
    }

    /** 32.3
     * 按之字形顺序打印二叉树
     * @param pRoot
     * @return
     */
    public ArrayList<ArrayList<Integer>> print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot);
        boolean reverse = false;
        while (!queue.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<>();
            int cnt = queue.size();
            while (cnt-- > 0) {
                TreeNode node = queue.poll();
                if (node == null){
                    continue;
                }
                list.add(node.val);
                queue.add(node.left);
                queue.add(node.right);
            }
            if (reverse){
                Collections.reverse(list);
            }
            reverse = !reverse;
            if (list.size() != 0){
                ret.add(list);
            }
        }
        return ret;
    }

    /** 33
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。假设输入的数组的任意两个数字都互不相同。
     * @param sequence
     * @return
     */
    public boolean verifySquenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length == 0){
            return false;
        }
        return verify(sequence, 0, sequence.length - 1);
    }

    public boolean verify(int[] sequence, int first, int last) {
        int rootVal = sequence[last];
        int curIndex = first;
        while (curIndex < last && sequence[curIndex] <= rootVal){
            curIndex++;
        }
        for (int i = curIndex; i < last; i++) {
            if (sequence[i] < rootVal){
                return false;
            }
        }
        return verify(sequence, first, curIndex - 1) && verify(sequence, curIndex, last - 1);
    }

    private ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
    /** 34
     * 输入一颗二叉树和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
     * @param root
     * @param target
     * @return
     */
    public ArrayList<ArrayList<Integer>> findPath(TreeNode root, int target) {
        backtracking(root, target, new ArrayList<>());
        return ret;
    }

    public void backtracking(TreeNode node, int target, ArrayList<Integer> path) {
        if (node == null) {
            return;
        }
        path.add(node.val);
        target -= node.val;
        if (target == 0 && node.left != null && node.right != null) {
            ret.add(path);
        } else {
            backtracking(node.left, target, path);
            backtracking(node.right, target, path);
        }
        path.remove(path.size() - 1);
    }

    /** 35
     * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），返回结果为复制后复杂链表的 head。
     * @param pHead
     * @return
     */
    public RandomListNode clone(RandomListNode pHead) {
        //插入新节点
        RandomListNode cur = pHead;
        while (cur != null) {
            RandomListNode clone = new RandomListNode(cur.label);
            clone.next = cur.next;
            cur.next = clone;
            cur = clone.next;
        }
        //建立random链接
        cur = pHead;
        while (cur != null) {
            RandomListNode clone = cur.next;
            if (cur.random != null) {
                clone.random = cur.random.next;
            }
            cur = clone.next;
        }
        //拆分节点
        cur = pHead;
        RandomListNode cloneHead = cur.next;
        while (cur.next != null) {
            RandomListNode next = cur.next;
            cur.next = next.next;
            cur = next;
        }
        return cloneHead;
    }

    public class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }

    private TreeNode pre = null;
    private TreeNode head = null;
    /** 36
     * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
     * @param root
     * @return
     */
    public TreeNode convert(TreeNode root) {
        inOrder(root);
        return head;
    }

    public void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        node.left = pre;
        if (pre != null) {
            pre.right = node;
        }
        if (head == null) {
            head = node;
        }
        pre = node;
        inOrder(node.right);
    }

    private String deserializeStr;
    /** 37
     * 请实现两个函数，分别用来序列化和反序列化二叉树。
     * @param root
     * @return
     */
    public String serialize(TreeNode root) {
        if (root == null){
            return "#";
        }
        return root.val + " " + serialize(root.left) + " " + serialize(root.right);
    }

    public TreeNode Deserialize(String str) {
        deserializeStr = str;
        return deSerialize();
    }

    public TreeNode deSerialize() {
        String key = "#";
        if(deserializeStr.length() == 0){
            return null;
        }
        int index = deserializeStr.indexOf(" ");
        String node = index == -1 ? deserializeStr : deserializeStr.substring(0,index);
        deserializeStr = index == -1 ? "" : deserializeStr.substring(index+1);
        if( node.equals(key)){
            return null;
        }
        TreeNode t = new TreeNode(Integer.valueOf(node));
        t.left = deSerialize();
        t.right = deSerialize();
        return t;
    }

    private ArrayList<String> arrList = new ArrayList<>();
    /** 38
     * 输入一个字符串，按字典序打印出该字符串中字符的所有排列。例如输入字符串 abc，
     * 则打印出由字符 a, b, c 所能排列出来的所有字符串 abc, acb, bac, bca, cab 和 cba。
     * @param str
     * @return
     */
    public ArrayList<String> permutation(String str) {
        if (str.length() == 0) {
            return null;
        }
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        backtracking(chars, new boolean[chars.length], new StringBuilder());
        return arrList;
    }
    private void backtracking(char[] chars, boolean[] hasUsed, StringBuilder s) {
        if (s.length() == chars.length) {
            arrList.add(s.toString());
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            if (hasUsed[i]) {
                continue;
            }
            if (i != 0 && chars[i] == chars[i - 1] && !hasUsed[i - 1]) {
                continue;
            }
            s.append(chars[i]);
            hasUsed[i] = true;
            backtracking(chars, hasUsed, s);
            s.deleteCharAt(s.length() - 1);
            hasUsed[i] = false;
        }
    }

    /** 39
     * 数组中出现次数超过一半的数字
     * @param nums
     * @return
     */
    public int MoreThanHalfNum_Solution(int[] nums) {
        int majority = nums[0];
        for (int i = 1, cnt = 1; i < nums.length; i++) {
            cnt = majority == nums[i] ? cnt + 1 : cnt - 1;
            if (cnt == 0) {
                majority = nums[i];
                cnt = 1;
            }
        }
        int cnt = 0;
        for (int val : nums) {
            if (majority == val) {
                cnt++;
            }
        }
        return cnt > nums.length / 2 ? majority : 0;
    }

}
