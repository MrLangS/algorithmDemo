package sword2offer;

import java.util.ArrayList;

public class Algorithm20To29 {

    /** 20
     * 表示数值的字符串
     * @param str
     * @return
     */
    public boolean isNumeric(char[] str) {
        if (str == null || str.length == 0)
            return false;
        return new String(str).matches("[+-]?\\d*(\\.\\d+)?([eE][+-]?\\d+)?");
    }

    /** 21
     * 调整数组顺序使奇数位于偶数前面
     * @param nums
     */
    public void reOrderArray(int[] nums) {
        int oddCnt = 0;
        for(int n : nums){
            if(n%2 == 1)
                oddCnt++;
        }
        int[] copy = nums.clone();
        int i = 0,j=oddCnt;
        for(int item : copy) {
            if(item%2==1)
                nums[i++]=item;
            else
                nums[j++]=item;
        }
    }

    /** 22
     * 链表中倒数第 K 个结点
     * @param head
     * @param k
     * @return
     */
    public ListNode FindKthToTail(ListNode head, int k) {
        if (head == null)
            return null;
        ListNode p1 = head;
        ListNode p2 = head;
        while (p1 != null && k-- > 0)
            p1 = p1.next;
        if (k > 0)
            return null;
        while (p1.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /** 23
     * 一个链表中包含环，请找出该链表的环的入口结点。要求不能使用额外的空间。
     * @param pHead
     * @return
     */
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if(pHead == null || pHead.next == null)
            return null;
        ListNode fast = pHead,slow = pHead;
        do {
            fast = fast.next.next;
            slow = slow.next;
        } while (fast != slow);
        fast = pHead;
        while (fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /** 24
     * 反转列表
     * @param head
     * @return
     */
    public ListNode ReverseList(ListNode head) {
        //递归
        if(head == null || head.next == null)
            return head;

        /*ListNode next = head.next;
        head.next = null;
        ListNode newHead = ReverseList(next);
        next.next = head;
        return newHead;*/

        //迭代
        ListNode newList = new ListNode(-1);
        while (head != null){
            ListNode next = head.next;
            newList.next = head.next;
            newList.next = head;
            head = next;
        }
        return newList.next;
    }

    /** 25
     * 合并两个排序的链表
     * @param list1
     * @param list2
     * @return
     */
    public ListNode Merge(ListNode list1, ListNode list2) {
        //递归
        /*
        if(list1 == null)
            return list2;
        if(list2 == null)
            return list1;
        if(list1.val < list2.val){
            list1.next = Merge(list1.next,list2);
            return list1;
        } else{
            list2.next = Merge(list1,list2.next);
            return list2;
        }*/
        //迭代
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        while (list1 != null && list2 != null){
            if(list1.val < list2.val){
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        if(list1 == null)
            cur.next = list2;
        if(list2 == null)
            cur.next = list1;
        return head.next;
    }

    /** 26
     * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
     * @param root1
     * @param root2
     * @return
     */
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null)
            return false;
        return isSubtree(root1, root2) || HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
    }

    private boolean isSubtree(TreeNode root1, TreeNode root2) {
        if (root2 == null)
            return true;
        if (root1 == null)
            return false;
        if (root1.val != root2.val)
            return false;
        return isSubtree(root1.left, root2.left) && isSubtree(root1.right, root2.right);
    }

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    /** 27
     * 操作给定的二叉树，将其变换为源二叉树的镜像。
     * @param root
     */
    public void Mirror(TreeNode root) {
        if(root == null)
            return;
        swap(root);
        Mirror(root.right);
        Mirror(root.left);
    }

    private void swap(TreeNode root){
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }

    /** 28
     * 来判断一颗二叉树是不是对称的
     * @param pRoot
     * @return
     */
    boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null)
            return true;
        return isSymmetrical(pRoot.left, pRoot.right);
    }

    boolean isSymmetrical(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null)
            return true;
        if (t1 == null || t2 == null)
            return false;
        if (t1.val != t2.val)
            return false;
        return isSymmetrical(t1.left, t2.right) && isSymmetrical(t1.right, t2.left);
    }

    /** 29
     * 顺时针打印矩阵
     * @param matrix
     * @return
     */
    public ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> ret = new ArrayList<>();
        int r1 = 0, r2 = matrix.length - 1, c1 = 0, c2 = matrix[0].length - 1;
        while (r1 <= r2 && c1 <= c2) {
            for (int i = c1; i <= c2; i++)
                ret.add(matrix[r1][i]);
            for (int i = r1 + 1; i <= r2; i++)
                ret.add(matrix[i][c2]);
            if (c1 != c2) {
                for (int i = c2 - 1; i >= c1; i--)
                    ret.add(matrix[r2][i]);
            }
            if (r1 != r2) {
                for (int i = r2 - 1; i > r1; i--)
                    ret.add(matrix[i][c1]);
            }
            r1++;
            c1++;
            r2--;
            c2--;
        }
        return ret;
    }
}
