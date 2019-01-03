import java.util.*;

/**
 排序练习题
 * */

public class SortAlgorithm {
    /**
    1. 数组中第K大元素
     题目描述：找到第 k 大的元素。
     * */

    /**
     排序：时间复杂度 O(NlogN)，空间复杂度 O(1)
     */
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    /**
     堆排序：时间复杂度 O(NlogK)，空间复杂度 O(K)。
     * */
    public int findKthLargest2(int[] nums, int k) {
        // 小顶堆
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int val : nums) {
            pq.add(val);
            // 维护堆的大小为 K
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }

    /**
     快速选择 ：时间复杂度 O(N)，空间复杂度 O(1)
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
    2.桶排序两题
    * */

   /**
    出现频率最多的 k 个数
    解题思路：设置若干个桶，每个桶存储出现频率相同的数，并且桶的下标代表桶中数出现的频率，即第 i 个桶中存储的数出现的频率为 i。
    把数都放到桶之后，从后向前遍历桶，最先得到的 k 个数就是出现频率最多的的 k 个数。
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
     按照字符出现次数对字符串排序
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
     3.荷兰国旗问题
     荷兰国旗包含三种颜色：红、白、蓝。
     有三种颜色的球，算法的目标是将这三种球按颜色顺序正确地排列。
     它其实是三向切分快速排序的一种变种，在三向切分快速排序中，每次切分都将数组分成三个区间：小于切分元素、等于切分元素、大于切分元素，而该算法是将数组分成三个区间：等于红色、等于白色、等于蓝色。
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

        //第一题
        int[] array={1,2,3,5,4,6,7};
        System.out.println("三种排序算法的结果： "+sa.findKthLargest(array,2)+" "+sa.findKthLargest2(array,2)+" "+sa.findKthLargest3(array,2));

        //第二题
        int[] array2={1,1,1,2,2,3};
        String str="tree";
        System.out.println("出现频率最多的K个数： "+sa.topKFrequent(array2,2));
        System.out.println("按字符出现次数的字符串排序： "+sa.frequencySort(str));

        //第三题
        int[] array3={2,0,2,1,1,0};
        sa.sortColors(array3);
        for (int i = 0; i < array3.length; i++) {
            System.out.print(array3[i]+" ");
        }

    }

}
