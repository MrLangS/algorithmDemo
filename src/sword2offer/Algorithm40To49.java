package sword2offer;

import java.util.*;

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

    /**
     * 大顶堆，存储左半边元素
     */
    private PriorityQueue<Integer> left = new PriorityQueue<>((o1, o2) -> o2 - o1);
    /**
     * 小顶堆，存储右半边元素，并且右半边元素都大于左半边
     */
    private PriorityQueue<Integer> right = new PriorityQueue<>();
    /**
     * 当前数据流读入的元素个数
     */
    private int n = 0;
    /** 41.1
     * 数据流中的中位数
     * @return
     */
    public void insert(Integer num) {
        if (n % 2 == 0) {
            left.add(num);
            right.add(left.poll());
        } else {
            right.add(num);
            left.add(right.poll());
        }
        n++;
    }

    public Double getMedian() {
        return (double) (n % 2 == 0 ? (left.peek() + right.peek()) / 2 : right.peek());
    }

    private int[] cnts = new int[256];
    private Queue<Character> queue = new LinkedList<>();
    /** 41.2
     * 字符流中第一个不重复的字符
     * @param ch
     */
    public void insert(char ch) {
        cnts[ch]++;
        queue.add(ch);
        while (!queue.isEmpty() && cnts[queue.peek()] > 1) {
            queue.poll();
        }
    }

    public char firstAppearingOnce() {
        return queue.isEmpty() ? '#' : queue.peek();
    }

    /** 42
     * 连续子数组的最大和
     * @param nums
     * @return
     */
    public int findGreatestSumOfSubArray(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        int greatestSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int val : nums) {
            sum = sum <= 0 ? val : sum + val;
            greatestSum = Math.max(greatestSum, sum);
        }
        return greatestSum;
    }

    /** 43
     * ********************************************************************************************************
     * 从 1 到 n 整数中 1 出现的次数
     * @param n
     * @return
     */
    public int numberOf1Between1AndN_Solution(int n) {
        int cnt = 0;
        for (int m = 1; m <= n; m *= 10) {
            int a = n / m, b = n % m;
            cnt += (a + 8) / 10 * m + (a % 10 == 1 ? b + 1 : 0);
        }
        return cnt;
    }

    /** 44
     * 数字以 0123456789101112131415... 的格式序列化到一个字符串中，求这个字符串的第 index 位。
     * @param index
     * @return
     */
    public int getDigitAtIndex(int index) {
        if (index < 0) {
            return -1;
        }
        int place = 1;
        while (true) {
            int amount = getAmountOfPlace(place);
            int totalAmount = amount * place;
            if (index < totalAmount) {
                return getDigitAtIndex(index, place);
            }
            index -= totalAmount;
            place++;
        }
    }

    private int getAmountOfPlace(int place) {
        //获取place位数的数字组成的字符串长度 10，90，900...
        if (place == 1) {
            return 10;
        }
        return (int) Math.pow(10, place - 1) * 9;
    }

    private int getBeginNumberOfPlace(int place) {
        //place位数的起始数字 0,10,100...
        if (place == 1) {
            return 0;
        }
        return (int) Math.pow(10, place - 1);
    }

    private int getDigitAtIndex(int index, int place) {
        int beginNumber = getBeginNumberOfPlace(place);
        int shiftNumber = index / place;
        String number = (beginNumber + shiftNumber) + "";
        int count = index % place;
        return number.charAt(count) - '0';

    }

    /**
     * 45
     * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
     * 例如输入数组 {3，32，321}，则打印出这三个数字能排成的最小数字为 321323。
     * @param numbers
     * @return
     */
    public String printMinNumber(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return "";
        }
        String[] nums = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            nums[i] = numbers[i] + "";
        }
        Arrays.sort(nums, (s1, s2) -> (s1 + s2).compareTo(s2 + s1));
        StringBuilder ret = new StringBuilder();
        for (String str : nums) {
            ret.append(str);
        }
        return ret.toString();
    }

    /** 46
     * 给定一个数字，按照如下规则翻译成字符串：1 翻译成“a”，2 翻译成“b”... 26 翻译成“z”。一个数字有多种翻译可能，
     * 例如 12258 一共有 5 种，分别是 abbeh，lbeh，aveh，abyh，lyh。实现一个函数，用来计算一个数字有多少种不同的翻译方法。
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i <= n; i++) {
            int one = Integer.valueOf(s.substring(i - 1, i));
            if (one != 0) {
                dp[i] += dp[i - 1];
            }
            if (s.charAt(i - 2) == '0') {
                continue;
            }
            int two = Integer.valueOf(s.substring(i - 2, i));
            if (two <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }

    /** 47
     * 在一个 m*n 的棋盘的每一个格都放有一个礼物，每个礼物都有一定价值（大于 0）。
     * 从左上角开始拿礼物，每次向右或向下移动一格，直到右下角结束。给定一个棋盘，求拿到礼物的最大价值
     * @param values
     * @return
     */
    public int getMost(int[][] values) {
        if(values == null || values.length == 0 || values[0].length == 0) {
            return 0;
        }
        int n = values[0].length;
        int[] dp = new int[n];
        for (int[] value : values){
            dp[0]+=value[0];
            for (int i = 1;i<n;i++){
                dp[i] = Math.max(dp[i],dp[i-1])+value[i];
            }
        }
        return dp[n-1];
    }

    /** 48
     * 输入一个字符串（只包含 a~z 的字符），求其最长不含重复字符的子字符串的长度。
     * 例如对于 arabcacfr，最长不含重复字符的子字符串为 acfr，长度为 4。
     * @param str
     * @return
     */
    public int longestSubStringWithoutDuplication(String str) {
        int curLen = 0;
        int maxLen = 0;
        int[] preIndexs = new int[26];
        Arrays.fill(preIndexs, -1);
        for (int curI = 0; curI < str.length(); curI++) {
            int c = str.charAt(curI) - 'a';
            int preI = preIndexs[c];
            if (preI == -1 || curI - preI > curLen) {
                curLen++;
            } else {
                maxLen = Math.max(maxLen, curLen);
                curLen = curI - preI;
            }
            preIndexs[c] = curI;
        }
        maxLen = Math.max(maxLen, curLen);
        return maxLen;
    }

    /** 49
     * 把只包含因子 2、3 和 5 的数称作丑数（Ugly Number）。
     * 例如 6、8 都是丑数，但 14 不是，因为它包含因子 7。习惯上我们把 1 当做是第一个丑数。求按从小到大的顺序的第 N 个丑数。
     * @param n
     * @return
     */
    public int getUglyNumberSolution(int n) {
        if (n < 6) {
            return n;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        int i2 = 0, i3 = 0, i5 = 0;
        for (int i = 0; i < n; i++) {
            int next2 = dp[i2] * i2, next3 = dp[i3] * i3, next5 = dp[i5] * i5;
            dp[i] = Math.min(next2, Math.min(next3, next5));
            if (dp[i] == next2) {
                i2++;
            }
            if (dp[i] == next3) {
                i3++;
            }
            if (dp[i] == next5) {
                i5++;
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        Algorithm40To49 test = new Algorithm40To49();
        test.longestSubStringWithoutDuplication("arabcacfr");
    }

}
