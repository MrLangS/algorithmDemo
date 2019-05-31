package leetcodealgorithm.algorithmicthinking;

/**
 * BinarySearch
 * 二分查找
 *
 * @author Lang
 * @date 2019-05-31
 */
public class BinarySearch {

    /**
     * 69. Sqrt(x) (Easy)
     * 求开方
     *
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if (x == 1) {
            return 1;
        }
        int l = 1, h = x;
        while (l <= h) {
            int m = l + (h - l) / 2;
            int sqrt = x / m;
            if (sqrt == m) {
                return m;
            } else if (m > sqrt) {
                h = m - 1;
            } else {
                l = m + 1;
            }
        }
        return h;

    }

    /**
     * 744. Find Smallest Letter Greater Than Target (Easy)
     * 大于给定元素的最小元素
     *
     * @param letters
     * @param target
     * @return
     */
    public char nextGreatestLetter(char[] letters, char target) {
        int n = letters.length;
        int l = 0, h = n - 1;
        while (l <= h) {
            int m = l + (h - l) / 2;
            if (letters[m] <= target) {
                l = m + 1;
            } else {
                h = m - 1;
            }
        }
        return l < n ? letters[l] : letters[0];
    }

    /**
     * 540. Single Element in a Sorted Array (Medium)
     *
     * @param nums
     * @return
     */
    public int singleNonDuplicate(int[] nums) {
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int m = l + (h - l) / 2;
            //保证其为偶数
            m = (m % 2 == 1) ? m-- : m;
            if (nums[m] == nums[m + 1]) {
                l = m + 2;
            } else {
                h = m;
            }
        }
        return l;
    }

    /**
     * 278. First Bad Version (Easy)
     *
     * @param n
     * @return
     */
    public int firstBadVersion(int n) {
        int l = 1, h = n;
        /*while (l < h) {
            int mid = l + (h - l) / 2;
            if (isBadVersion(mid)) {
                h = mid;
            } else {
                l = mid + 1;
            }
        }*/
        return l;
    }

    /**
     * 153. Find Minimum in Rotated Sorted Array (Medium)
     *
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (nums[m] < nums[h]) {
                h = m;
            } else {
                l = m + 1;
            }
        }
        return nums[l];
    }

    /**
     * 34. Find First and Last Position of Element in Sorted Array
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int first = binarySearch(nums, target);
        int last = binarySearch(nums, target + 1) - 1;
        if(first == nums.length || nums[first] != target) {
            return new int[]{1,1};
        } else {
            return new int[]{first,last};
        }
    }

    private int binarySearch(int[] nums, int target) {
        int l = 0, h = nums.length;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (nums[m] >= target) {
                h = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }
}
