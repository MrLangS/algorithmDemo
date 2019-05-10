package leetcodealgorithm.algorithmicthinking;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * SortSolution
 * 排序
 *
 * @author lang
 * @date 2019-05-09
 */
public class SortSolution {

    /**
     * 215. Kth Largest Element in an Array (Medium)
     * 找到第 k 大的元素。
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        //排序 时间复杂度 O(NlogN)，空间复杂度 O(1)
        //Arrays.sort(nums);
        //return nums[nums.length - k];

        //堆排序 时间复杂度 O(NlogK)，空间复杂度 O(K)
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int val : nums) {
            pq.add(val);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        //return pq.peek();

        //快速排序
        k = nums.length - k;
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int j = partition(nums, l, h);
            if (j == k) {
                break;
            } else if (j < k) {
                l = j + 1;
            } else {
                h = j - 1;
            }
        }
        return nums[k];

    }

    private int partition(int[] a, int l, int h) {
        int i = l, j = h + 1;
        while (true) {
            while (a[++i] < a[l] && i < h) {} ;
            while (a[--j] > a[l] && j > l) {};
            if (i >= j) {
                break;
            }
            swap(a, i, j);
        }
        swap(a, l, j);
        return j;
    }

    private void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
