package sword2offer;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Algorithm_3_9 {
    /** 03
     * 在一个长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字是重复的，也不知道每个数字重复几次。
     * 请找出数组中任意一个重复的数字。
     * 要求是时间复杂度 O(N)，空间复杂度 O(1)。
     * @param nums
     * @param length
     * @param duplication
     * @return
     */
    public boolean duplicate(int[] nums, int length, int[] duplication){
        if(nums == null || length<=0){
            return false;
        }

        for(int i=0; i<length; i++){
            while(nums[i]!=i){
                if(nums[i]==nums[nums[i]]){
                    duplication[0]=nums[i];
                    return true;
                }
                swap(nums,i,nums[i]);
            }
        }

        return false;
    }

    public void swap(int[] nums, int i, int j){
        int t = nums[j];
        nums[j]=nums[i];
        nums[i]=t;
    }

    /** 04
     * 该二维数组中的一个数，它左边的数都比它小，下边的数都比它大。
     * 因此，从右上角开始查找，就可以根据 target 和当前元素的大小关系来缩小查找区间，当前元素的查找区间为左下角的所有元素。
     * 要求时间复杂度 O(M + N)，空间复杂度 O(1)。
     * @param target
     * @param matrix
     * @return
     */
    public boolean Find(int target, int[][] matrix) {
        if(matrix==null || matrix.length ==0 || matrix[0].length == 0){
            return false;
        }

        int rows = matrix.length,cols = matrix[0].length;
        int i = 0,j = cols-1;

        while(i < rows && j >=0){
            if(target > matrix[i][j])
                i++;
            else if(target < matrix[i][j])
                j--;
            else
                return true;
        }
        return false;
    }

    /** 05
     * 将一个字符串中的空格替换成 "%20"。
     * @param str
     * @return
     */
    public String replaceSpace(StringBuffer str) {
        int p1 = str.length()-1;
        for(int i=0 ; i<p1 ; i++){
            if(str.charAt(i)==' ')
                str.append("   ");
        }

        int p2 = str.length()-1;
        while(p1 >= 0 && p2 > p1){
            char c = str.charAt(p1--);
            if(c==' '){
                str.setCharAt(p2--,'0');
                str.setCharAt(p2--,'2');
                str.setCharAt(p2--,'%');
            }else
                str.setCharAt(p2--,c);
        }
        return str.toString();
    }

    /** 06
     * 从尾到头反过来打印出每个结点的值。
     * @param listNode
     * @return
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        //递归实现
        ArrayList<Integer> ret = new ArrayList<>();
        if(listNode != null){
            ret.addAll(printListFromTailToHead(listNode.next));
            ret.add(listNode.val);
        }
        //return ret;

        //头插法
        ListNode head = new ListNode(-1);
        while (listNode != null) {
            ListNode memo = listNode.next;
            listNode.next = head.next;
            head.next = listNode;
            listNode = memo;
        }
            // 构建 ArrayList
        head = head.next;
        while (head != null) {
            ret.add(head.val);
            head = head.next;
        }
        //return ret;

        //栈实现
        Stack<Integer> stack = new Stack<>();
        while(listNode != null) {
            stack.add(listNode.val);
            listNode = listNode.next;
        }
        while(!stack.isEmpty())
            ret.add(stack.pop());
        return ret;
    }

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /** 07
     * 根据二叉树的前序遍历和中序遍历的结果，重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     * @param pre
     * @param in
     * @return
     */
    // 缓存中序遍历数组每个值对应的索引
    private Map<Integer, Integer> indexForInOrders = new HashMap<>();

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {

    }

    /** 08
     * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
     * @param pNode
     * @return
     */
    public class TreeLinkNode {

        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }

    public TreeLinkNode GetNext(TreeLinkNode pNode) {

    }

    /** 09
     * 用两个栈来实现一个队列，完成队列的 Push 和 Pop 操作。
     */
    Stack<Integer> in = new Stack<Integer>();
    Stack<Integer> out = new Stack<Integer>();

    public void push(int node) {
        in.push(node);
    }

    public int pop() throws Exception {
        if (out.isEmpty())
            while (!in.isEmpty())
                out.push(in.pop());

        if (out.isEmpty())
            throw new Exception("queue is empty");

        return out.pop();
    }
}
