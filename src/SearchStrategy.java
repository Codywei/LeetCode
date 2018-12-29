import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 ��������
 * */
public class SearchStrategy {
    /**
     1.�����ʽ�ӷ��ţ���һ�����ε��⣩
     * */
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> ways = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                //�ݹ�������ұ��ʽ���п���ȡֵ
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
        //���ݹ鵽ĳ����ʽֻʣһ��Ԫ��ʱ���޷��ֽ�Ϊ���ұ��ʽ��ways���Ϊ�գ���ֱ�ӷ���input�����
        if (ways.size() == 0) {
            ways.add(Integer.valueOf(input));
        }
        return ways;
    }

    /**
     2.BFS��ϰ

     ÿһ������Ľڵ㶼����ڵ������ͬ���� di ��ʾ�� i ���ڵ�����ڵ�ľ��룬�Ƶ���һ�����ۣ������ȱ����Ľڵ� i �������Ľڵ� j���� di <= dj��
     ����������ۣ�����������·�������Ž����⣺��һ�α�����Ŀ�Ľڵ㣬����������·��Ϊ���·����
     Ӧ��ע����ǣ�ʹ�� BFS ֻ�������Ȩͼ�����·����

     �ڳ���ʵ�� BFS ʱ��Ҫ�����������⣺
     ���У������洢ÿһ�ֱ����õ��Ľڵ㣻
     ��ǣ����ڱ������Ľڵ㣬Ӧ�ý�����ǣ���ֹ�ظ�������
     * */



    /**
     2.1 �����������д�ԭ�㵽�ض�������·�����ȣ�BFS��
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
                // ���
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
     3.DFS��ϰ

     ������������ڵõ�һ���½ڵ�ʱ�������½ڵ���б�������˷��������ַ�ʽ�����½ڵ㣬ֱ��û���½ڵ��ˣ���ʱ���ء�
     ��һ���ڵ������ʹ�� DFS ��һ��ͼ���б���ʱ���ܹ��������Ľڵ㶼�Ǵӳ�ʼ�ڵ�ɴ�ģ�DFS ������������� �ɴ��� ���⡣

     �ڳ���ʵ�� DFS ʱ��Ҫ�����������⣺
     ջ����ջ�����浱ǰ�ڵ���Ϣ���������½ڵ㷵��ʱ�ܹ�����������ǰ�ڵ㡣����ʹ�õݹ�ջ��
     ��ǣ��� BFS һ��ͬ����Ҫ���Ѿ��������Ľڵ���б�ǡ�
     * */

    /**
     3.1 ����������ͨ���
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
     Backtracking�����ݣ����� DFS��

     ��ͨ DFS ��Ҫ���� �ɴ������� ����������ֻ��Ҫִ�е��ص��λ��Ȼ�󷵻ؼ��ɡ�
     �� Backtracking ��Ҫ������� ������� ���⣬������ { 'a','b','c' } �����ַ�������������������ַ����еõ����ַ���������������ִ�е��ض���λ�÷���֮�󻹻����ִ�������̡�
     ��Ϊ Backtracking ���������ͷ��أ���Ҫ������⣬����ڳ���ʵ��ʱ����Ҫע���Ԫ�صı�����⣺

     �ڷ���һ����Ԫ�ؽ����µĵݹ����ʱ����Ҫ����Ԫ�ر��Ϊ�Ѿ����ʣ����������ڼ����ݹ����ʱ�����ظ����ʸ�Ԫ�أ�
     �����ڵݹ鷵��ʱ����Ҫ��Ԫ�ر��Ϊδ���ʣ���Ϊֻ��Ҫ��֤��һ���ݹ����в�ͬʱ����һ��Ԫ�أ����Է����Ѿ����ʹ����ǲ��ڵ�ǰ�ݹ����е�Ԫ�ء�
     * */


    /**
     4.1 ���ּ�����ϣ�Backtracking��
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
            // ���
            prefix.append(c);
            doCombination(prefix, combinations, digits);
            // ɾ��
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }



    public static void main(String[] args) {
        SearchStrategy ss=new SearchStrategy();

        //��һ��
        String input="2-1-1";
        System.out.print("���ʽ�Ĳ�ͬ��ʽ��ֵ�� ");
        ss.diffWaysToCompute(input).forEach((result)->System.out.print(result+"  "));
        System.out.println();

        //BFS ��һ��
        int[][] arrays={{1,1,0,1},{1,0,1,0},{1,1,1,1},{1,0,1,1}};
        System.out.println("���������·�����ȣ� "+ss.minPathLength(arrays,3,2));

        //DFS ��һ��
        int[][] arrays2={{1,1,0,1},{1,0,1,0},{1,1,1,1},{1,0,1,1}};
        System.out.println("������ͨ����� "+ss.maxAreaOfIsland(arrays2));


        //Backtracking ��һ��
        String digits="23";
        System.out.print("���ּ��̵�����У� ");
        ss.letterCombinations(digits).forEach((result)->System.out.print(result+"  "));
        System.out.println();
    }
}
