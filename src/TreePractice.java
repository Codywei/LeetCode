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



    }
}
