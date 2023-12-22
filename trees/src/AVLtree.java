class Node<T extends Comparable<T>> {
    T value;
    int height=1;
    Node<T> left, right;

    Node(T value) {
        this.value = value;
    }
}

class AVLTree<T extends Comparable<T>> {
    Node<T> root;

    int height(Node<T> node) {
        if (node == null)
            return 0;
        return node.height;
    }

    int getBalance(Node<T> node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }
//��ƽ������Ϊ���ȥ��
    Node<T> insert(Node<T> node, T value) {
        if (node == null)
            return new Node<>(value);

        if (value.compareTo(node.value) < 0)
            node.left = insert(node.left, value);
        else if (value.compareTo(node.value) > 0)
            node.right = insert(node.right, value);
        //�������ʹ�õݹ������ֱ��node=node.right��ΪҪ��֤���е��height�õ�����
        else{
            return node; // �����������ͬ�ļ�
        }
        node.height = 1 + Math.max(height(node.left), height(node.right));

        return howToRotate(node);
    }

    Node<T> deleteNode(Node<T> root, T value) {
        if (root == null)
            return root;

        if (value.compareTo(root.value) < 0)
            root.left = deleteNode(root.left, value);
        else if (value.compareTo(root.value) > 0)
            root.right = deleteNode(root.right, value);
        else {
            if (root.left == null || root.right == null) {
                Node<T> temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;

                if (temp == null) {
                    root = null;
                } else
                    root = temp;
            } else {//���
                Node<T> temp =predecessorNode(root);
                root.value = temp.value;
                root.right = deleteNode(root.right, temp.value);
            }
        }

        if (root == null)
            return root;

        root.height = 1 + Math.max(height(root.left), height(root.right));
        return howToRotate(root);
    }

    Node<T> howToRotate(Node<T> node){
        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.left) >= 0)
            return rightRotate(node);

// ��-����ת
        if (balance < -1 && getBalance(node.right) <= 0)
            return leftRotate(node);

// ��-����ת
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

// ��-����ת
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    //��ǰ��
    Node<T> predecessorNode(Node<T> node){
        return minValueNode(node.right);
    }

    //�ҵ���С���
    Node<T> minValueNode(Node<T> node) {
        Node<T> current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    Node<T> rightRotate(Node<T> y) {
        Node<T> x = y.left;
        Node<T> T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }
    Node<T> leftRotate(Node<T> x) {
        Node<T> y = x.right;
        Node<T> T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }
    void preOrder(Node<T> node) {
        //ǰ�����
        if (node != null) {
            System.out.print(node.value +" ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }
    private void printHelper(Node<T> node, String indent, boolean isLast) {
        if (node == null) {
            return;
        }

        String marker = isLast ? "������ " : "������ ";
        System.out.println(indent + marker + node.value);

        String childIndent = isLast ? "    " : "��   ";
        printHelper(node.left, indent + childIndent, false);
        printHelper(node.right, indent + childIndent, true);
    }

    public void print() {
        printHelper(root, "", true);
    }


    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();

        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 20);
        tree.root = tree.insert(tree.root, 30);
        tree.root = tree.insert(tree.root, 40);
        tree.root = tree.insert(tree.root, 50);
        tree.root = tree.insert(tree.root, 25);

        System.out.println("AVL����ǰ����������");
        tree.preOrder(tree.root);
        System.out.println();
        tree.print();
        tree.root = tree.deleteNode(tree.root, 30);

        System.out.println("\nɾ���ڵ���AVL��ǰ����������");
        tree.preOrder(tree.root);
        System.out.println();
        tree.print();
    }
}