import java.util.*;

/**
 ������ϰ��
 * */
public class SortAlgorithm {
    /**
    1. Kth Element
     ��Ŀ�������ҵ��� k ���Ԫ�ء�
     * */

    /**
     ����ʱ�临�Ӷ� O(NlogN)���ռ临�Ӷ� O(1)
     */
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    /**
     ������ʱ�临�Ӷ� O(NlogK)���ռ临�Ӷ� O(K)��
     * */
    public int findKthLargest2(int[] nums, int k) {
        // С����
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int val : nums) {
            pq.add(val);
            // ά���ѵĴ�СΪ K
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }

    /**
     ����ѡ�� ��ʱ�临�Ӷ� O(N)���ռ临�Ӷ� O(1)
     */
    public int findKthLargest3(int[] nums, int k) {
        k = nums.length - k;
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int j = partition(nums, l, h);
            if (j == k) {
                break;
            } else if (j < k) {
                l = j + 1;
            } else {
                h = j - 1;
            }
        }
        return nums[k];
    }

    private int partition(int[] a, int l, int h) {
        int i = l, j = h + 1;
        while (true) {
            while (a[++i] < a[l] && i < h){
            } ;
            while (a[--j] > a[l] && j > l){
            };
            if (i >= j) {
                break;
            }
            swap(a, i, j);
        }
        swap(a, l, j);
        return j;
    }



   /**
    2.Ͱ��������
    * */

   /**
    ����Ƶ������ k ����
    ����˼·���������ɸ�Ͱ��ÿ��Ͱ�洢����Ƶ����ͬ����������Ͱ���±����Ͱ�������ֵ�Ƶ�ʣ����� i ��Ͱ�д洢�������ֵ�Ƶ��Ϊ i��
    �������ŵ�Ͱ֮�󣬴Ӻ���ǰ����Ͱ�����ȵõ��� k �������ǳ���Ƶ�����ĵ� k ������
    * */
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequencyForNum = new HashMap<>();
        for (int num : nums) {
            frequencyForNum.put(num, frequencyForNum.getOrDefault(num, 0) + 1);
        }
        List<Integer>[] buckets = new ArrayList[nums.length + 1];
        for (int key : frequencyForNum.keySet()) {
            int frequency = frequencyForNum.get(key);
            if (buckets[frequency] == null) {
                buckets[frequency] = new ArrayList<>();
            }
            buckets[frequency].add(key);
        }
        List<Integer> topK = new ArrayList<>();
        for (int i = buckets.length - 1; i >= 0 && topK.size() < k; i--) {
            if (buckets[i] != null) {
                topK.addAll(buckets[i]);
            }
        }
        return topK;
    }


    /**
     �����ַ����ִ������ַ�������
     * */
    public String frequencySort(String s) {
        Map<Character,Integer> frequencynum=new HashMap<>();
        for(char c:s.toCharArray()){
            frequencynum.put(c,frequencynum.getOrDefault(c,0)+1);
        }
        List<Character>[] Buckets=new ArrayList[s.length()+1];
        for(char key:frequencynum.keySet()){
            int frequency=frequencynum.get(key);
            if(Buckets[frequency]==null){
                Buckets[frequency]=new ArrayList<>();
            }
            Buckets[frequency].add(key);
        }

        StringBuilder str=new StringBuilder();
        for(int i=Buckets.length-1;i>=0;i--){
            if(Buckets[i]==null){
                continue;
            }
            for(char c:Buckets[i]){
                for (int j = i; j >0; j--) {
                    str.append(c);
                }
            }
        }
        return str.toString();
    }



    /**
     3.������������
     �����������������ɫ���졢�ס�����
     ��������ɫ�����㷨��Ŀ���ǽ�����������ɫ˳����ȷ�����С�
     ����ʵ�������зֿ��������һ�ֱ��֣��������зֿ��������У�ÿ���зֶ�������ֳ��������䣺С���з�Ԫ�ء������з�Ԫ�ء������з�Ԫ�أ������㷨�ǽ�����ֳ��������䣺���ں�ɫ�����ڰ�ɫ��������ɫ��
     * */
    public void sortColors(int[] nums) {
        int zero=-1,one=0,two=nums.length;
        while(one<two){
            if(nums[one]==0){
                swap(nums,++zero,one++);
            }else if(nums[one]==2){
                swap(nums,one,--two);
            }else{
                one++;
            }
        }
    }



    private void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }


    public static void main(String[] args) {
        SortAlgorithm sa=new SortAlgorithm();

        //��һ��
        int[] array={1,2,3,5,4,6,7};
        System.out.println("���������㷨�Ľ���� "+sa.findKthLargest(array,2)+" "+sa.findKthLargest2(array,2)+" "+sa.findKthLargest3(array,2));

        //�ڶ���
        int[] array2={1,1,1,2,2,3};
        String str="tree";
        System.out.println("����Ƶ������K������ "+sa.topKFrequent(array2,2));
        System.out.println("���ַ����ִ������ַ������� "+sa.frequencySort(str));

        //������
        int[] array3={2,0,2,1,1,0};
        sa.sortColors(array3);
        for (int i = 0; i < array3.length; i++) {
            System.out.print(array3[i]+" ");
        }

    }

}
