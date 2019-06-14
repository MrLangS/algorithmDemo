package leetcodealgorithm.algorithmicthinking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Division
 * 分治算法
 *
 * @author lang
 * @date 2019-05-31
 */
public class Division {

    /**
     * 241. Different Ways to Add Parentheses (Medium)
     * 给表达式加括号
     *
     * @param input
     * @return
     */
    public static List<Integer> diffWaysToCompute(String input) {
        List<Integer> ways = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '-' || c == '+' || c == '*') {
                List<Integer> left = diffWaysToCompute(input.substring(0, i));
                List<Integer> right = diffWaysToCompute(input.substring(i + 1));
                for (int l : left) {
                    for (int r : right) {
                        switch (c) {
                            case '-':
                                ways.add(l - r);
                                break;
                            case '+':
                                ways.add(l + r);
                                break;
                            case '*':
                                ways.add(l * r);
                                break;
                            default:
                                break;
                        }
                    }
                }

            }
        }
        if (ways.size() == 0) {
            ways.add(Integer.valueOf(input));
        }
        return ways;
    }

    /**
     * 95. Unique Binary Search Trees II (Medium)
     * 给定一个数字 n，要求生成所有值为 1...n 的二叉搜索树。
     *
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        if (n < 1) {
            return new LinkedList<>();
        }
        return generateSubtrees(1, n);
    }

    private List<TreeNode> generateSubtrees(int s, int e) {
        List<TreeNode> res = new LinkedList<TreeNode>();
        if (s > e) {
            res.add(null);
            return res;
        }
        for (int i = s; i <= e; i++) {
            List<TreeNode> left = generateSubtrees(s, i - 1);
            List<TreeNode> right = generateSubtrees(i + 1, e);
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    res.add(root);
                }
            }
        }
        return res;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
