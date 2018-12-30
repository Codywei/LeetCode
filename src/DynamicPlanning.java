import java.util.Arrays;

import static java.lang.Integer.MAX_VALUE;

/**
 动态规划
 * */
public class DynamicPlanning {

    /**
     1.强盗抢劫
     题目描述：抢劫一排住户，但是不能抢邻近的住户，求最大抢劫量。
     定义 dp 数组用来存储最大的抢劫量，其中 dp[i] 表示抢到第 i 个住户时的最大抢劫量。
     由于不能抢劫邻近住户，如果抢劫了第 i -1 个住户，那么就不能再抢劫第 i 个住户，所以
     dp[i]=max(dp[i-2]+nums[i],dp[i-1])
     * */
    public int rob(int[] nums) {
        int pre2 = 0, pre1 = 0;
        for (int i = 0; i < nums.length; i++) {
            int cur = Math.max(pre2 + nums[i], pre1);
            pre2 = pre1;
            pre1 = cur;
        }
        return pre1;
    }

    /**
     2.矩阵的最小路径和
     * */
    public int minPathSum(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n];
        Arrays.fill(dp,Integer.MAX_VALUE);
        for(int num[]:grid){
            dp[0]=num[0];
            for (int i = 1; i < num.length; i++) {
                dp[i]=Math.min(dp[i-1],dp[i])+num[i];
            }
        }
        return dp[n-1];
    }


    /**
     3.矩阵的总路径数
     * */
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        return dp[n - 1];
    }


    public static void main(String[] args) {
        DynamicPlanning dp=new DynamicPlanning();

        //第一题
        int arrays[]={2,3,4,0,2};
        System.out.println("强盗最多能抢多少： "+dp.rob(arrays));

        //第二题
        int[][]arrays2={{1,3,1},{1,5,1},{4,2,1}};
        System.out.println("矩阵的最小路径和： "+dp.minPathSum(arrays2));

        //第三题
        System.out.println("矩阵的总路径数是： "+dp.uniquePaths(5,4));
    }
}
