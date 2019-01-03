import java.util.Arrays;
import java.util.Stack;

/**
 栈和队列练习
 * */
public class StackandQueue {

    /**
     1.用栈实现括号匹配
     * */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char cStack = stack.pop();
                boolean b1 = c == ')' && cStack != '(';
                boolean b2 = c == ']' && cStack != '[';
                boolean b3 = c == '}' && cStack != '{';
                if (b1 || b2 || b3) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }


    /**
     2.数组中元素与下一个比它大的元素之间的距离
     * */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] dist = new int[n];
        Stack<Integer> indexs = new Stack<>();
        for (int curIndex = 0; curIndex < n; curIndex++) {
            while (!indexs.isEmpty() && temperatures[curIndex] > temperatures[indexs.peek()]) {
                int preIndex = indexs.pop();
                dist[preIndex] = curIndex - preIndex;
            }
            indexs.add(curIndex);
        }
        return dist;
    }

    public static void main(String[] args) {
        StackandQueue sq=new StackandQueue();

        //第一题
        String s="()[]{}";
        System.out.println("用栈实现括号匹配："+sq.isValid(s));

        //第二题
        int[] array={73, 74, 75, 71, 69, 72, 76, 73};
        System.out.print("数组中元素与下一个比它大的元素之间的距离：");
        Arrays.stream(sq.dailyTemperatures(array)).forEach((result)->System.out.print(result+" "));
    }
}
