package sword2offer;

public class Algorithm_20_29 {

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

        ListNode next = head.next;
        head.next = null;
        ListNode newHead = ReverseList(next);
        next.next = head;
        return newHead;
    }
}
