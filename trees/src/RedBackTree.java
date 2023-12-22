import javax.lang.model.type.NullType;
import java.awt.*;

class RedBlackTree<T extends Comparable<T>> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private class Node {
        T value;
        Node parent;
        Node left, right;
        boolean color=RED;
        public Node(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
            //默认是红色
        }
        public void inverse(){
           this.color=!this.color;
        }
    }
    private Node root;

    private Node rotateLeft(Node node) {
        Node x = node.right;
        node.right = x.left;
        if (x.left != null) {
            x.left.parent = node;
        }
        x.parent = node.parent;
        if (node.parent == null) {
            root = x;
        } else if (node == node.parent.left) {
            node.parent.left = x;
        } else {
            node.parent.right = x;
        }
        x.left = node;
        node.parent = x;
        return x;
    }

    private Node rotateRight(Node node) {
        Node x = node.left;
        node.left = x.right;
        if (x.right != null) {
            x.right.parent = node;
        }
        x.parent = node.parent;
        if (node.parent == null) {
            root = x;
        } else if (node == node.parent.left) {
            node.parent.left = x;
        } else {
            node.parent.right = x;
        }
        x.right = node;
        node.parent = x;
        return x;
    }
    public void insert(T value) {
        Node newNode = new Node(value);
        if (root == null) {
            root = newNode;
            newNode.color = BLACK; // 根节点为黑色
            return;
        }
        Node current = root;
        Node parent = null;
        while (current != null) {
            parent = current;
            if (value.compareTo(current.value) < 0) {
                current = current.left;
            } else if (value.compareTo(current.value) > 0) {
                current = current.right;
            } else {
                return;
            }
        }
        newNode.parent = parent;
        if (value.compareTo(parent.value) < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        fixInsertViolation(newNode);
    }

    private void fixInsertViolation(Node node) {
        while (isRed(node.parent)) {
            if (node.parent == node.parent.parent.left) {
                Node uncle = node.parent.parent.right;
                if (isRed(uncle)) {
                    // Case 1: Uncle is red
                    setBlack(node.parent);
                    setBlack(uncle);
                    setRed(node.parent.parent);
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.right) {
                        // Case 2: Uncle is black and node is a right child
                        node = node.parent;
                        rotateLeft(node);
                    }
                    // Case 3: Uncle is black and node is a left child
                    setBlack(node.parent);
                    setRed(node.parent.parent);
                    rotateRight(node.parent.parent);
                }
            } else {
                Node uncle = node.parent.parent.left;
                if (isRed(uncle)) {
                    // Case 1: Uncle is red
                    setBlack(node.parent);
                    setBlack(uncle);
                    setRed(node.parent.parent);
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.left) {
                        // Case 2: Uncle is black and node is a left child
                        node = node.parent;
                        rotateRight(node);
                    }
                    // Case 3: Uncle is black and node is a right child
                    setBlack(node.parent);
                    setRed(node.parent.parent);
                    rotateLeft(node.parent.parent);
                }
            }
        }
        root.color = BLACK; // 根节点始终为黑色
    }

    private void setRed(Node node) {
        if (node != null) {
            node.color = RED;
        }
    }

    private void setBlack(Node node) {
        if (node != null) {
            node.color = BLACK;
        }
    }

    private boolean isRed(Node node) {
        return node != null && node.color == RED;
    }
    public void delete(T value){
        Node node = searchNode(value);
        if (node == null) {
            return; // 节点不存在，无需删除
        }

        if (node.left != null && node.right != null) {
            // 被删除节点有两个子节点
            Node successor = predecessorNode(node);
            node.value = successor.value;
            node = successor;
        }//所有的node都在最后
        Node child;
        // 被删除节点最多只有一个子节点
        if (node.left != null) {
            child = node.left;
        } else {
            child = node.right;
        }

        if (!isRed(node)) {
            inverseColor(node);
        }

        if(node==node.parent.left){
            node.parent.left=child;
        }else{
            node.parent.right=child;
        }
    }
    Node predecessorNode(Node node){
        return minValueNode(node.right);
    }

    //找到最小结点
    Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }
    public Node searchNode(T value) {
        Node current = root;
        while (current != null) {
            int cmp = value.compareTo(current.value);
            if (cmp == 0) {
                return current; // 找到匹配的节点
            } else if (cmp < 0) {
                current = current.left; // 在当前节点的左子树中继续搜索
            } else {
                current = current.right; // 在当前节点的右子树中继续搜索
            }
        }
        return null; // 未找到匹配的节点
    }
    public void inverseColor(Node node) {
        boolean isLeftChild = (node == node.parent.left);
        // Case 1: Sibling is red and parent is black
        if (isRed(getSibling(node))) {
            if (isLeftChild) {
                Node tempRoot = rotateLeft(node.parent);
                tempRoot.inverse(); // Black
                tempRoot.left.color = RED;
            } else {
                Node tempRoot = rotateRight(node.parent);
                tempRoot.inverse(); // Black
                tempRoot.right.color = RED;
            }
            inverseColor(node);
        } else if (isRed(node.parent)) { // Sibling is black
            if (isLeftChild) {
                if (getSibling(node).left.color == RED) {
                    Node tempRoot = rotateLeft(rotateRight(getSibling(node)).parent);
                    setSpecial(tempRoot);
                    tempRoot.left.left.inverse();
                } else if (getSibling(node).right.color == RED) {
                    Node tempRoot = rotateLeft(node.parent);
                    setSpecial(tempRoot);
                    tempRoot.left.left.inverse();
                } else {
                    node.color = RED;
                    getSibling(node).color = RED;
                    node.parent.color = BLACK;
                }
            } else {
                if (isRed(getSibling(node).right)) {
                    Node tempRoot = rotateRight(rotateLeft(getSibling(node)).parent);
                    setSpecial(tempRoot);
                    tempRoot.right.right.inverse();
                } else if (isRed(getSibling(node))){
                    Node tempRoot = rotateRight(node.parent);
                    setSpecial(tempRoot);
                    tempRoot.right.right.inverse();
                } else {
                    node.color = RED;
                    getSibling(node).color = RED;
                    node.parent.color = BLACK;
                }
            }
        } else { // All black (parent cannot be root)
            if (node.parent.parent == root) {
                root.color = RED;
            }
            inverseColor(node.parent); // Swap with a good parent
            inverseColor(node); // Repeat with new parent
        }
    }
    private void setSpecial(Node node){
        node.color=RED;
        node.left.color=BLACK;
        node.right.color=BLACK;
    }
    private Node getSibling(Node node){
        if(node.parent==null){
            System.out.println("error");
            return null;
        }
        if(node==node.parent.left){
            return node.parent.right;
        }else{
            return node.parent.left;
        }
    }
    public static void main(String[] args) {
        RedBlackTree<Integer> tree = new RedBlackTree<>();

        // 插入测试
        System.out.println("Insertion Test:");
        int[] values = {5, 10, 3, 8, 1, 2, 7, 9};
        for (int value : values) {
            tree.insert(value);
            System.out.println("Inserted: " + value);
            System.out.println("Tree structure:");
            printTreeStructure(tree.root);
            System.out.println();
        }

        // 删除测试
        System.out.println("Deletion Test:");
        int[] deleteValues = {3, 5, 10};
        for (int value : deleteValues) {
            tree.delete(value);
            System.out.println("Deleted: " + value);
            System.out.println("Tree structure:");
            printTreeStructure(tree.root);
            System.out.println();
        }
    }

    private static void printTreeStructure(RedBlackTree.Node node) {
        if (node == null) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(node.value).append("(").append(node.color ? "R" : "B").append(")");
        if (node.left != null || node.right != null) {
            sb.append(" -> [");
            if (node.left != null) {
                sb.append(node.left.value).append("(").append(node.left.color ? "R" : "B").append(")");
            }
            sb.append(", ");
            if (node.right != null) {
                sb.append(node.right.value).append("(").append(node.right.color ? "R" : "B").append(")");
            }
            sb.append("]");
        }
        System.out.println(sb.toString());

        printTreeStructure(node.left);
        printTreeStructure(node.right);
    }
}