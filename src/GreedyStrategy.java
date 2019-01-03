import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 贪心策略
 * */

public class GreedyStrategy {
    /**
     1.分配糖果
     题目描述：每个孩子都有一个满足度，每个糖果都有一个大小，只有糖果的大小大于等于一个孩子的满足度，该孩子才会获得满足。求解最多可以获得满足的孩子数量。
     给一个孩子的糖果应当尽量小又能满足该孩子，这样大糖果就能拿来给满足度比较大的孩子。因为最小的孩子最容易得到满足，所以先满足最小的孩子。

     证明：假设在某次选择中，贪心策略选择给当前满足度最小的孩子分配第 m 个糖果，第 m 个糖果为可以满足该孩子的最小糖果。
     假设存在一种最优策略，给该孩子分配第 n 个糖果，并且 m < n。
     我们可以发现，经过这一轮分配，贪心策略分配后剩下的糖果一定有一个比最优策略来得大。因此在后续的分配中，贪心策略一定能满足更多的孩子。
     也就是说不存在比贪心策略更优的策略，即贪心策略就是最优策略。
     * */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int gi=0;
        int si=0;
        while(gi<g.length&&si<s.length){
            if(g[gi]<=s[si]){
                gi++;
            }
            si++;
        }
        return gi;
    }

   /**
    2.不重叠的区间个数（计算需要移除的区间个数）

    题目描述：计算让一组区间不重叠所需要移除的区间个数。
    先计算最多能组成的不重叠区间个数，然后用区间总个数减去不重叠区间的个数。
    在每次选择中，区间的结尾最为重要，选择的区间结尾越小，留给后面的区间的空间越大，那么后面能够选择的区间个数也就越大。
    按区间的结尾进行排序，每次选择结尾最小，并且和前一个区间不重叠的区间。
    * */
    public int eraseOverlapIntervals(Interval[] intervals) {

        if(intervals.length==0){
            return 0;
        }
        Arrays.sort(intervals,Comparator.comparingInt(o->o.end));
        int cnt=1;
        int end=intervals[0].end;
        for(int i=1;i<intervals.length;i++){
            if(intervals[i].start<end){
                continue;
            }
            end=intervals[i].end;
            cnt++;
        }
        return intervals.length-cnt;
    }

    //Interval内部类
    private class Interval {
        int start;
        int end;
        private Interval(int start,int end){
            this.start=start;
            this.end=end;
        }
    }


    /**
     3.投飞镖刺破气球

     题目描述：气球在一个水平数轴上摆放，可以重叠，飞镖垂直投向坐标轴，使得路径上的气球都会刺破。求解最小的投飞镖次数使所有气球都被刺破。
     也是计算不重叠的区间个数，不过和上一题的区别在于，[1, 2] 和 [2, 3] 在本题中算是重叠区间。
     * */
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        Arrays.sort(points, Comparator.comparingInt(o -> o[1]));
        int cnt = 1, end = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] <= end) {
                continue;
            }
            cnt++;
            end = points[i][1];
        }
        return cnt;
    }

    /**
     4.根据身高和序号重组队列

     题目描述：一个学生用两个分量 (h, k) 描述，h 表示身高，k 表示排在前面的有 k 个学生的身高比他高或者和他一样高。
     为了使插入操作不影响后续的操作，身高较高的学生应该先做插入操作，否则身高较小的学生原先正确插入的第 k 个位置可能会变成第 k+1 个位置。
     身高降序、k 值升序，然后按排好序的顺序插入队列的第 k 个位置中。
     * */
    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0 || people[0].length == 0) {
            return new int[0][0];
        }

        Arrays.sort(people, (a, b) -> (a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]));
        List<int[]> queue = new ArrayList<>();
        for (int[] p : people) {
            queue.add(p[1], p);
        }
        return queue.toArray(new int[queue.size()][]);
    }

    /**
     5.种植花朵
     题目描述：花朵之间至少需要一个单位的间隔，求解是否能种下 n 朵花。
     * */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int len = flowerbed.length;
        int cnt = 0;
        for (int i = 0; i < len && cnt < n; i++) {
            if (flowerbed[i] == 1) {
                continue;
            }
            int pre = i == 0 ? 0 : flowerbed[i - 1];
            int next = i == len - 1 ? 0 : flowerbed[i + 1];
            if (pre == 0 && next == 0) {
                cnt++;
                flowerbed[i] = 1;
            }
        }
        return cnt >= n;
    }

    /**
     6.判断是否为子序列
     * */
    public boolean isSubsequence(String s, String t) {
        int index = -1;
        for (char c : s.toCharArray()) {
            index = t.indexOf(c, index + 1);
            if (index == -1) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
         GreedyStrategy gs=new GreedyStrategy();

         //第一题
         int[] children={2,1};
         int[] candy={2,1,3};
         System.out.println("分配糖果最多满足小孩的个数： "+gs.findContentChildren(children,candy));

         //第二题
        Interval[] intervals=new Interval[3];
        intervals[0]=gs. new Interval(1,2);
        intervals[1]=gs. new Interval(1,2);
        intervals[2]=gs. new Interval(2,3);
        System.out.println("构造不重叠区间需要移除的重叠区间个数： "+gs.eraseOverlapIntervals(intervals));

        //第三题
        int[][] pointer={{10,16},{2,8}, {1,6}, {7,12}};
        System.out.println("最小投镖数（不重叠区间个数）："+gs.findMinArrowShots(pointer));

        //第四题
        int [][]people={{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
        int [][]array=gs.reconstructQueue(people);
        System.out.println("身高和序号重组序列： ");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }

        //第五题
        int[] flowered={1,0,0,0,1};
        System.out.println("能种植这么多花朵吗： "+gs.canPlaceFlowers(flowered,1));

        //第六题
        String s="abc";
        String t="ahbgdc";
        System.out.println("是否为子序列： "+gs.isSubsequence(s,t));
    }


}
