package leetcodealgorithm.algorithmicthinking;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * DoublePointer
 * 双指针算法思想
 *
 * @author lang
 * @date 2019-05-09
 */
public class DoublePointer {

    /**
     * Leetcode ：167. Two Sum II - Input array is sorted (Easy)
     * 在有序数组中找出两个数，使它们的和为 target。
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        int i = 0, j = numbers.length - 1;
        while (i < j) {
            int sum = numbers[i] + numbers[j];
            if (sum == target) {
                return new int[]{i + 1, j + 1};
            } else if (sum < target) {
                i++;
            } else {
                j--;
            }
        }
        return null;
    }

    /**
     * 633. Sum of Square Numbers (Easy)
     * 判断一个数是否为两个数的平方和。
     *
     * @param c
     * @return
     */
    public boolean judgeSquareSum(int c) {
        int i = 0, j = (int) Math.sqrt(c);
        while (i < j) {
            int powSum = i * i + j * j;
            if (powSum == c) {
                return true;
            } else if (powSum < c) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }

    private final static HashSet<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

    /**
     * 345. Reverse Vowels of a String (Easy)
     * 使用双指针指向待反转的两个元音字符，一个指针从头向尾遍历，一个指针从尾到头遍历
     *
     * @param s
     * @return
     */
    public String reverseVowels(String s) {
        int i = 0, j = s.length() - 1;
        char[] result = new char[s.length()];
        while (i <= j) {
            char ci = s.charAt(i);
            char cj = s.charAt(j);
            if (!vowels.contains(ci)) {
                result[i++] = ci;
            } else if (!vowels.contains(cj)) {
                result[j--] = cj;
            } else {
                result[i++] = cj;
                result[j--] = ci;
            }
        }
        return new String(result);
    }

    /**
     * 680. Valid Palindrome II (Easy)
     * 题目描述：可以删除一个字符，判断是否能构成回文字符串。
     *
     * @param s
     * @return
     */
    public boolean validPalindrome(String s) {
        int i = -1, j = s.length();
        while (++i < j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return isPalindrome(s, i + 1, j) || isPalindrome(s, i, j - 1);
            }
        }
        return true;
    }

    private boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 88. Merge Sorted Array (Easy)
     * 归并两个有序数组
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index1 = m - 1, index2 = n - 1;
        int indexMerge = m + n - 1;
        while (index1 >= 0 || index2 >= 0) {
            if (index1 < 0) {
                nums1[indexMerge--] = nums2[index2--];
            } else if (index2 < 0) {
                nums1[indexMerge--] = nums1[index1--];
            } else if (nums1[index1] > nums2[index2]) {
                nums1[indexMerge--] = nums1[index1--];
            } else {
                nums1[indexMerge--] = nums2[index2--];
            }
        }
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * 141. Linked List Cycle (Easy)
     * 判断链表是否存在环
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode l1 = head, l2 = head.next;
        while (l1 != null && l2 != null && l2.next != null) {
            if (l1 == l2) {
                return true;
            }
            l1 = l1.next;
            l2 = l2.next.next;
        }
        return false;
    }

    /**
     * 524. Longest Word in Dictionary through Deleting (Medium)
     * 删除 s 中的一些字符，使得它构成字符串列表 d 中的一个字符串，
     * 找出能构成的最长字符串。如果有多个相同长度的结果，返回字典序的最小字符串。
     *
     * @param s
     * @param d
     * @return
     */
    public String findLongestWord(String s, List<String> d) {
        String longestWord = "";
        for (String target : d) {
            int l1 = longestWord.length(), l2 = target.length();
            boolean nolonger = l1 > l2 || (l1 == l2 && longestWord.compareTo(target) < 0);
            if (nolonger) {
                continue;
            }
            if (isValid(s, target)) {
                longestWord = target;
            }
        }
        return longestWord;
    }

    private boolean isValid(String s, String target) {
        int i = 0, j = 0;
        while (i < s.length() && j < target.length()) {
            if (s.charAt(i) == target.charAt(j)) {
                j++;
            }
            i++;
        }
        return j == target.length();
    }

}
