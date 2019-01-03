import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 搜索策略
 * */
public class SearchStrategy {
    /**
     1.给表达式加符号（插一道分治的题）
     * */
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> ways = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                //递归求出左右表达式所有可能取值
                List<Integer> left = diffWaysToCompute(input.substring(0, i));
                List<Integer> right = diffWaysToCompute(input.substring(i + 1));
                for (int l : left) {
                    for (int r : right) {
                        switch (c) {
                            case '+':
                                ways.add(l + r);
                                break;
                            case '-':
                                ways.add(l - r);
                                break;
                            case '*':
                                ways.add(l * r);
                                break;
                            default:
                                ;
                        }
                    }
                }
            }
        }
        //当递归到某侧表达式只剩一个元素时，无法分解为左右表达式，ways结果为空，就直接返回input结果。
        if (ways.size() == 0) {
            ways.add(Integer.valueOf(input));
        }
        return ways;
    }

    /**
     2.BFS练习

     每一层遍历的节点都与根节点距离相同。设 di 表示第 i 个节点与根节点的距离，推导出一个结论：对于先遍历的节点 i 与后遍历的节点 j，有 di <= dj。
     利用这个结论，可以求解最短路径等最优解问题：第一次遍历到目的节点，其所经过的路径为最短路径。
     应该注意的是，使用 BFS 只能求解无权图的最短路径。

     在程序实现 BFS 时需要考虑以下问题：
     队列：用来存储每一轮遍历得到的节点；
     标记：对于遍历过的节点，应该将它标记，防止重复遍历。
     * */



    /**
     2.1 计算在网格中从原点到特定点的最短路径长度（BFS）
     [[1,1,0,1],
     [1,0,1,0],
     [1,1,1,1],
     [1,0,1,1]]
     * */
    public int minPathLength(int[][] grids, int tr, int tc) {
        final int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        final int m = grids.length, n = grids[0].length;
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(0, 0));
        int pathLength = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            pathLength++;
            while (size-- > 0) {
                Pair<Integer, Integer> cur = queue.poll();
                int cr = cur.getKey(), cc = cur.getValue();
                // 标记
                grids[cr][cc] = 0;
                for (int[] d : direction) {
                    int nr = cr + d[0], nc = cc + d[1];
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n || grids[nr][nc] == 0) {
                        continue;
                    }
                    if (nr == tr && nc == tc) {
                        return pathLength;
                    }
                    queue.add(new Pair<>(nr, nc));
                }
            }
        }
        return -1;
    }

    /**
     3.DFS练习

     深度优先搜索在得到一个新节点时立即对新节点进行遍历，如此反复以这种方式遍历新节点，直到没有新节点了，此时返回。
     从一个节点出发，使用 DFS 对一个图进行遍历时，能够遍历到的节点都是从初始节点可达的，DFS 常用来求解这种 可达性 问题。

     在程序实现 DFS 时需要考虑以下问题：
     栈：用栈来保存当前节点信息，当遍历新节点返回时能够继续遍历当前节点。可以使用递归栈。
     标记：和 BFS 一样同样需要对已经遍历过的节点进行标记。
     * */

    /**
     3.1 查找最大的连通面积
     * */
    private int m, n;
    private int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        m = grid.length;
        n = grid[0].length;
        int maxArea = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxArea = Math.max(maxArea, dfs(grid, i, j));
            }
        }
        return maxArea;
    }

    private int dfs(int[][] grid, int r, int c) {
        if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] == 0) {
            return 0;
        }
        grid[r][c] = 0;
        int area = 1;
        for (int[] d : direction) {
            area += dfs(grid, r + d[0], c + d[1]);
        }
        return area;
    }

    /**
     4.Backtracking
     Backtracking（回溯）属于 DFS。

     普通 DFS 主要用在 可达性问题 ，这种问题只需要执行到特点的位置然后返回即可。
     而 Backtracking 主要用于求解 排列组合 问题，例如有 { 'a','b','c' } 三个字符，求解所有由这三个字符排列得到的字符串，这种问题在执行到特定的位置返回之后还会继续执行求解过程。
     因为 Backtracking 不是立即就返回，而要继续求解，因此在程序实现时，需要注意对元素的标记问题：

     在访问一个新元素进入新的递归调用时，需要将新元素标记为已经访问，这样才能在继续递归调用时不用重复访问该元素；
     但是在递归返回时，需要将元素标记为未访问，因为只需要保证在一个递归链中不同时访问一个元素，可以访问已经访问过但是不在当前递归链中的元素。
     * */


    /**
     4.1 数字键盘组合（Backtracking）
     * */
    private static final String[] KEYS = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return combinations;
        }
        doCombination(new StringBuilder(), combinations, digits);
        return combinations;
    }

    private void doCombination(StringBuilder prefix, List<String> combinations, final String digits) {
        if (prefix.length() == digits.length()) {
            combinations.add(prefix.toString());
            return;
        }
        int curDigits = digits.charAt(prefix.length()) - '0';
        String letters = KEYS[curDigits];
        for (char c : letters.toCharArray()) {
            // 添加
            prefix.append(c);
            doCombination(prefix, combinations, digits);
            // 删除
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }



    public static void main(String[] args) {
        SearchStrategy ss=new SearchStrategy();

        //第一题
        String input="2-1-1";
        System.out.print("表达式的不同方式求值： ");
        ss.diffWaysToCompute(input).forEach((result)->System.out.print(result+"  "));
        System.out.println();

        //BFS 第一题
        int[][] arrays={{1,1,0,1},{1,0,1,0},{1,1,1,1},{1,0,1,1}};
        System.out.println("网格中最短路径长度： "+ss.minPathLength(arrays,3,2));

        //DFS 第一题
        int[][] arrays2={{1,1,0,1},{1,0,1,0},{1,1,1,1},{1,0,1,1}};
        System.out.println("最大的连通面积： "+ss.maxAreaOfIsland(arrays2));


        //Backtracking 第一题
        String digits="23";
        System.out.print("数字键盘的组合有： ");
        ss.letterCombinations(digits).forEach((result)->System.out.print(result+"  "));
        System.out.println();
    }
}
