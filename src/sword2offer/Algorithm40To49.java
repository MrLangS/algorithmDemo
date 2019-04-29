package sword2offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Algorithm40To49
 * 剑指 40~49
 * @author lang
 * @date 2019-04-29
 */
public class Algorithm40To49 {
    /** 40
     * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
     * @param input
     * @param k
     * @return
     */
    public ArrayList<Integer> getLeastNumbersSolution(int[] input, int k) {
        ArrayList<Integer> ret = new ArrayList<>();
        if (k > input.length || k <= 0) {
            return ret;
        }
        findKthSmallest(input, k);
        //快速选择算法，先经过快速排序前k个元素
        for (int i = 0; i < k; i++) {
            ret.add(input[i]);
        }
        return ret;
    }

    public void findKthSmallest(int[] nums, int k) {
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int j = partition(nums, l, h);
            if (j == k) {
                break;
            }
            if (j > k) {
                h = j - 1;
            } else {
                l = j + 1;
            }
        }

    }

    private int partition(int[] nums, int l, int h) {
        int p = nums[l];
        int i = l, j = h + 1;
        while (true) {
            while (i != h && nums[++i] < p) {
            }
            while (j != l && nums[--j] > p) {
            }
            if (i >= j) {
                break;
            }
            swap(nums, i, j);
        }
        swap(nums, l, j);
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    /**
     * 大根堆维护小根堆实现top-K
     * @param k
     * @param nums
     * @return
     */
    public ArrayList<Integer> getLeastNumbersSolution(int k, int[] nums) {
        if (k > nums.length || k <= 0){
            return new ArrayList<>();
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(((o1, o2) -> o2 - o1));
        for (int num : nums) {
            maxHeap.add(num);
            if(maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        return new ArrayList<>(maxHeap);
    }

    /** 大顶堆，存储左半边元素 */
    private PriorityQueue<Integer> left = new PriorityQueue<>((o1, o2) -> o2 - o1);
    /** 小顶堆，存储右半边元素，并且右半边元素都大于左半边 */
    private PriorityQueue<Integer> right = new PriorityQueue<>();
    /** 当前数据流读入的元素个数 */
    private int n = 0;
    /** 41.1
     * 数据流中的中位数
     * @return
     */
    public void Insert(Integer num) {
        if(n % 2 == 0) {
            left.add(num);
            right.add(left.poll());
        } else {
            right.add(num);
            left.add(right.poll());
        }
        n++;
    }

    public Double GetMedian() {
        return (double)(n%2 == 0 ? (left.peek()+right.peek())/2 : right.peek());
    }

    private int[] cnts = new int[256];
    private Queue<Character> queue = new LinkedList<>();
    /** 41.2
     * 字符流中第一个不重复的字符
     * @param ch
     */
    public void Insert(char ch) {
        cnts[ch]++;
        queue.add(ch);
        while (!queue.isEmpty() && cnts[queue.peek()] > 1) {
            queue.poll();
        }
    }

    public char FirstAppearingOnce() {
        return queue.isEmpty() ? '#' : queue.peek();
    }
}
