import java.util.Arrays;

import static java.lang.Integer.MAX_VALUE;

/**
 ��̬�滮
 * */
public class DynamicPlanning {

    /**
     1.ǿ������
     ��Ŀ����������һ��ס�������ǲ������ڽ���ס�����������������
     ���� dp ���������洢���������������� dp[i] ��ʾ������ i ��ס��ʱ�������������
     ���ڲ��������ڽ�ס������������˵� i -1 ��ס������ô�Ͳ��������ٵ� i ��ס��������
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
     2.�������С·����
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
     3.�������·����
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

        //��һ��
        int arrays[]={2,3,4,0,2};
        System.out.println("ǿ������������٣� "+dp.rob(arrays));

        //�ڶ���
        int[][]arrays2={{1,3,1},{1,5,1},{4,2,1}};
        System.out.println("�������С·���ͣ� "+dp.minPathSum(arrays2));

        //������
        System.out.println("�������·�����ǣ� "+dp.uniquePaths(5,4));
    }
}
