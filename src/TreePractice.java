import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
树练习
 * */
public class TreePractice {

    /**
     1.统计左叶子节点的和
     * */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null){
            return 0;
        }
        if (isLeaf(root.left)) {
            return root.left.val + sumOfLeftLeaves(root.right);
        }
        return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }

    private boolean isLeaf(TreeNode node){
        if (node == null) {
            return false;
        }
        return node.left == null && node.right == null;
    }

    /**
     2.找出二叉树中第二小的节点

     Input:
       2
      / \
     2   5
        / \
       5  7

     Output: 5

     题意：一个节点要么具有 0 个或 2 个子节点，如果有子节点，那么根节点是最小的节点。
     * */
    public int findSecondMinimumValue(TreeNode root) {

        if(root==null){
            return -1;
        }
        if(root.left==null&&root.right==null){
            return -1;
        }
        int leftval=root.left.val;
        int rightval=root.right.val;
        if(root.val==leftval){
            leftval=findSecondMinimumValue(root.left);
        }
        if(root.val==root.right.val){
            rightval=findSecondMinimumValue(root.right);
        }
        if(leftval!=-1&&rightval!=-1){
            return Math.min(leftval,rightval);
        }
        if(leftval!=-1){
            return leftval;
        }
        return rightval;
    }


    /**
     3.1 非递归实现二叉树的前序遍历
     * */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node == null){
                continue;
            }
            ret.add(node.val);
            // 先右后左，保证左子树先遍历
            stack.push(node.right);
            stack.push(node.left);
        }
        return ret;
    }

    /**
     3.2 非递归实现二叉树的后序遍历

     前序遍历为 root -> left -> right，后序遍历为 left -> right -> root。
     可以修改前序遍历成为 root -> right -> left，那么这个顺序就和后序遍历正好相反。
     * */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node == null) {
                continue;
            }
            ret.add(node.val);
            stack.push(node.left);
            stack.push(node.right);
        }
        Collections.reverse(ret);
        return ret;
    }

    /**
     3.3 非递归实现二叉树的中序遍历

     先把左子节点循环压入栈中，跳出循环时，开始将栈顶元素弹出，并将其右子节点压入栈中
     * */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {

            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            TreeNode node = stack.pop();
            ret.add(node.val);
            cur = node.right;
        }
        return ret;
    }


    /**
     4.Trie
     Trie，又称前缀树或字典树，用于判断字符串是否存在或者是否具有某种字符串前缀。

     实现一个 Trie，用来求前缀和
     * */
    class Trie {

        private class Node {
            Node[] childs = new Node[26];
            boolean isLeaf;
        }

        private Node root = new Node();

        public Trie() {
        }

        public void insert(String word) {
            insert(word, root);
        }

        private void insert(String word, Node node) {
            if (node == null){
                return;
            }
            if (word.length() == 0) {
                node.isLeaf = true;
                return;
            }
            int index = indexForChar(word.charAt(0));
            if (node.childs[index] == null) {
                node.childs[index] = new Node();
            }
            insert(word.substring(1), node.childs[index]);
        }

        public boolean search(String word) {
            return search(word, root);
        }

        private boolean search(String word, Node node) {
            if (node == null){
                return false;
            }
            if (word.length() == 0){
                return node.isLeaf;
            }
            int index = indexForChar(word.charAt(0));
            return search(word.substring(1), node.childs[index]);
        }

        public boolean startsWith(String prefix) {
            return startWith(prefix, root);
        }

        private boolean startWith(String prefix, Node node) {
            if (node == null){
                return false;
            }
            if (prefix.length() == 0){
                return true;
            }
            int index = indexForChar(prefix.charAt(0));
            return startWith(prefix.substring(1), node.childs[index]);
        }


        private int indexForChar(char c) {
            return c - 'a';
        }

    }



    /**
     5.实现一个 Trie，用来求前缀和
     * */
    class MapSum {

        private class Node {
            Node[] child = new Node[26];
            int value;
        }

        private Node root = new Node();

        public MapSum() {

        }

        public void insert(String key, int val) {
            insert(key, root, val);
        }

        private void insert(String key, Node node, int val) {
            if (node == null){
                return;
            }
            if (key.length() == 0) {
                node.value = val;
                return;
            }
            int index = indexForChar(key.charAt(0));
            if (node.child[index] == null) {
                node.child[index] = new Node();
            }
            insert(key.substring(1), node.child[index], val);
        }

        public int sum(String prefix) {
            return sum(prefix, root);
        }

        private int sum(String prefix, Node node) {
            if (node == null){
                return 0;
            }
            if (prefix.length() != 0) {
                int index = indexForChar(prefix.charAt(0));
                return sum(prefix.substring(1), node.child[index]);
            }
            int sum = node.value;
            for (Node child : node.child) {
                sum += sum(prefix, child);
            }
            return sum;
        }

        private int indexForChar(char c) {
            return c - 'a';
        }
    }







    public static void main(String[] args) {
        TreePractice tp=new TreePractice();

        //第一题
        TreeNode node1=new TreeNode(3);
        TreeNode node2=new TreeNode(9);
        TreeNode node3=new TreeNode(20);
        TreeNode node4=new TreeNode(15);
        TreeNode node5=new TreeNode(7);
        node1.left=node2;
        node1.right=node3;
        node3.left=node4;
        node3.right=node5;
        System.out.println("统计左叶子节点的和： "+tp.sumOfLeftLeaves(node1));


        //第二题
        TreeNode node21=new TreeNode(2);
        TreeNode node22=new TreeNode(2);
        TreeNode node23=new TreeNode(5);
        TreeNode node24=new TreeNode(5);
        TreeNode node25=new TreeNode(7);
        node21.left=node22;
        node21.right=node23;
        node23.left=node24;
        node23.right=node25;
        System.out.println("找出二叉树中第二小的节点： "+tp.findSecondMinimumValue(node21));



        //第三题
        TreeNode node31=new TreeNode(1);
        TreeNode node32=new TreeNode(2);
        TreeNode node33=new TreeNode(3);
        TreeNode node34=new TreeNode(4);
        TreeNode node35=new TreeNode(5);
        node31.left=node32;
        node31.right=node33;
        node33.left=node34;
        node33.right=node35;
        System.out.print("非递归实现二叉树的前序遍历: ");
        tp.preorderTraversal(node31).forEach((result)->System.out.print(result+" "));
        System.out.println();

        System.out.print("非递归实现二叉树的中序遍历: ");
        tp.inorderTraversal(node31).forEach((result)->System.out.print(result+" "));
        System.out.println();

        System.out.print("非递归实现二叉树的后序遍历: ");
        tp.postorderTraversal(node31).forEach((result)->System.out.print(result+" "));
        System.out.println();


        //第四题
        Trie trie=tp.new Trie();
        trie.insert("apple");
        System.out.println("是否含有该单词： "+trie.search("apple"));
        System.out.println("是否含有该前缀： "+trie.startsWith("ap"));


        //第五题
        MapSum ms=tp.new MapSum();
        ms.insert("apple",3);
        ms.insert("ap",2);
        System.out.println("该单词数量： "+ms.sum("apple"));
        System.out.println("该前缀数量： "+ms.sum("a"));

    }
}
