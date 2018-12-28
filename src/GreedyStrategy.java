import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 ̰�Ĳ���
 * */
public class GreedyStrategy {
    /**
     1.�����ǹ�
     ��Ŀ������ÿ�����Ӷ���һ������ȣ�ÿ���ǹ�����һ����С��ֻ���ǹ��Ĵ�С���ڵ���һ�����ӵ�����ȣ��ú��ӲŻ������㡣��������Ի������ĺ���������
     ��һ�����ӵ��ǹ�Ӧ������С��������ú��ӣ��������ǹ���������������ȱȽϴ�ĺ��ӡ���Ϊ��С�ĺ��������׵õ����㣬������������С�ĺ��ӡ�

     ֤����������ĳ��ѡ���У�̰�Ĳ���ѡ�����ǰ�������С�ĺ��ӷ���� m ���ǹ����� m ���ǹ�Ϊ��������ú��ӵ���С�ǹ���
     �������һ�����Ų��ԣ����ú��ӷ���� n ���ǹ������� m < n��
     ���ǿ��Է��֣�������һ�ַ��䣬̰�Ĳ��Է����ʣ�µ��ǹ�һ����һ�������Ų������ô�����ں����ķ����У�̰�Ĳ���һ�����������ĺ��ӡ�
     Ҳ����˵�����ڱ�̰�Ĳ��Ը��ŵĲ��ԣ���̰�Ĳ��Ծ������Ų��ԡ�
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
    2.���ص������������������Ҫ�Ƴ������������

    ��Ŀ������������һ�����䲻�ص�����Ҫ�Ƴ������������
    �ȼ����������ɵĲ��ص����������Ȼ���������ܸ�����ȥ���ص�����ĸ�����
    ��ÿ��ѡ���У�����Ľ�β��Ϊ��Ҫ��ѡ��������βԽС���������������Ŀռ�Խ����ô�����ܹ�ѡ����������Ҳ��Խ��
    ������Ľ�β��������ÿ��ѡ���β��С�����Һ�ǰһ�����䲻�ص������䡣
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

    //Interval�ڲ���
    private class Interval {
        int start;
        int end;
        private Interval(int start,int end){
            this.start=start;
            this.end=end;
        }
    }


    /**
     3.Ͷ���ڴ�������

     ��Ŀ������������һ��ˮƽ�����ϰڷţ������ص������ڴ�ֱͶ�������ᣬʹ��·���ϵ����򶼻���ơ������С��Ͷ���ڴ���ʹ�������򶼱����ơ�
     Ҳ�Ǽ��㲻�ص��������������������һ����������ڣ�[1, 2] �� [2, 3] �ڱ����������ص����䡣
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
     4.������ߺ�����������

     ��Ŀ������һ��ѧ������������ (h, k) ������h ��ʾ��ߣ�k ��ʾ����ǰ����� k ��ѧ������߱����߻��ߺ���һ���ߡ�
     Ϊ��ʹ���������Ӱ������Ĳ�������߽ϸߵ�ѧ��Ӧ���������������������߽�С��ѧ��ԭ����ȷ����ĵ� k ��λ�ÿ��ܻ��ɵ� k+1 ��λ�á�
     ��߽���k ֵ����Ȼ���ź����˳�������еĵ� k ��λ���С�
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
     5.��ֲ����
     ��Ŀ����������֮��������Ҫһ����λ�ļ��������Ƿ������� n �仨��
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
     6.�ж��Ƿ�Ϊ������
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

         //��һ��
         int[] children={2,1};
         int[] candy={2,1,3};
         System.out.println("�����ǹ��������С���ĸ����� "+gs.findContentChildren(children,candy));

         //�ڶ���
        Interval[] intervals=new Interval[3];
        intervals[0]=gs. new Interval(1,2);
        intervals[1]=gs. new Interval(1,2);
        intervals[2]=gs. new Interval(2,3);
        System.out.println("���첻�ص�������Ҫ�Ƴ����ص���������� "+gs.eraseOverlapIntervals(intervals));

        //������
        int[][] pointer={{10,16},{2,8}, {1,6}, {7,12}};
        System.out.println("��СͶ���������ص������������"+gs.findMinArrowShots(pointer));

        //������
        int [][]people={{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
        int [][]array=gs.reconstructQueue(people);
        System.out.println("��ߺ�����������У� ");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }

        //������
        int[] flowered={1,0,0,0,1};
        System.out.println("����ֲ��ô�໨���� "+gs.canPlaceFlowers(flowered,1));

        //������
        String s="abc";
        String t="ahbgdc";
        System.out.println("�Ƿ�Ϊ�����У� "+gs.isSubsequence(s,t));
    }


}
