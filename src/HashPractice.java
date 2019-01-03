import java.util.Arrays;
import java.util.HashMap;

/**
Hash练习
 * */
public class HashPractice {
    /**
     哈希表使用 O(N) 空间复杂度存储数据，并且以 O(1) 时间复杂度求解问题。

     Java 中的 HashSet 用于存储一个集合，可以查找元素是否在集合中。
     如果元素有穷，并且范围不大，那么可以用一个布尔数组来存储一个元素是否存在。例如对于只有小写字符的元素，就可以用一个长度为 26 的布尔数组来存储一个字符集合，使得空间复杂度降低为 O(1)。

     Java 中的 HashMap 主要用于映射关系，从而把两个元素联系起来。HashMap 也可以用来对元素进行计数统计，此时键为元素，值为计数。
     和 HashSet 类似，如果元素有穷并且范围不大，可以用整型数组来进行统计。
     在对一个内容进行压缩或者其它转换时，利用 HashMap 可以把原始内容和转换后的内容联系起来。例如在一个简化 url 的系统中 Leetcode : 535. Encode and Decode TinyURL (Medium)，
     利用 HashMap 就可以存储精简后的 url 到原始 url 的映射，使得不仅可以显示简化的 url，也可以根据简化的 url 得到原始 url 从而定位到正确的资源。
     * */


    /**
     1.数组中两个数的和为给定值
     可以先对数组进行排序，然后使用双指针方法或者二分查找方法。这样做的时间复杂度为 O(NlogN)，空间复杂度为 O(1)。
     用 HashMap 存储数组元素和索引的映射，在访问到 nums[i] 时，判断 HashMap 中是否存在 target - nums[i]，如果存在说明 target - nums[i] 所在的索引和 i 就是要找的两个数
     该方法的时间复杂度为 O(N)，空间复杂度为 O(N)，使用空间来换取时间。
     * */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> indexForNum = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (indexForNum.containsKey(target - nums[i])) {
                return new int[]{indexForNum.get(target - nums[i]), i};
            } else {
                indexForNum.put(nums[i], i);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        HashPractice hp=new HashPractice();

        //第一题
        int[]array={3,4,2,1,5,7,9};
        System.out.print("数组中两个数的和为定值（返回索引）： ");
        Arrays.stream(hp.twoSum(array, 3)).forEach((result)->System.out.print(result+" "));
    }
}
