package TreeNode;

public class AVLTreeNode {
    public int data;
    public int height;
    public AVLTreeNode left;
    public AVLTreeNode right;

    public AVLTreeNode(){

    }
    
    public AVLTreeNode(int data){
        this(data, 1, null, null);
    }

    public AVLTreeNode(int data, int height, AVLTreeNode left, AVLTreeNode right){
        this.data = data;
        this.height = height;
        this.left = left;
        this.right = right;
    }
}
