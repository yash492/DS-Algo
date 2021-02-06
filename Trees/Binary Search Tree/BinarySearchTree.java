import java.util.*;

public class BinarySearchTree<E extends Comparable<? super E>> {
    private TreeNode<E> root;
    private int size;
    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    public boolean add(E data) {
        if (root == null) {
            root = new TreeNode<E>(data);
            size++;
            return true;
        }

        else if (contains(data))
            return false;

        else {
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null) {
                parent = current;
                int compare = data.compareTo(current.data);
                if (compare > 0)
                    current = current.right;
                else
                    current = current.left;
            }
            int compare = data.compareTo(parent.data);

            if (compare < 0)
                parent.left = new TreeNode<E>(data);
            else
                parent.right = new TreeNode<E>(data);
            size++;
            return true;
        }
        
    }

    public boolean contains(E data) {
        TreeNode<E> current = root;
        while (current != null) {
            int compare = data.compareTo(current.data);
            if (compare == 0) 
                return true;
            else if (compare < 0)
                current = current.left;
            else 
                current = current.right;
        } 
        return false;      
    }

    public E max() {
        TreeNode<E> current = root;
        TreeNode<E> max = null;
        while(current != null) {
            max = current;
            current = current.right;
        }
        return max.data;
    }

    public E min() {
        TreeNode<E> current = root;
        return min(current);
    }

    private E min(TreeNode<E> node) {
        TreeNode<E> current = node;
        TreeNode<E> min = null;
        while(current != null) {
            min = current;
            current = current.left;
        }
        return min.data;
    }

    public TreeNode<E> remove(E data) {
        if (!contains(data))
            throw new NoSuchElementException(data + " is not found.");
        size--;
        return removeTreeNode(root, data);
    }

    private TreeNode<E> removeTreeNode(TreeNode<E> node, E data) {
        TreeNode<E> current = node;
        TreeNode<E> parent = null;
        int compare = data.compareTo(current.data);

        while (current != null){
            parent = current;
            compare = data.compareTo(current.data);
            if (compare == 0)
                break;
            else if (compare < 0)
                current = current.left;
            else 
                current = current.right;
        }

        // Case 1: If there are no children.
        if (current.left == null && current.right == null) {
            if (current != node) {
                if (parent.left == current) 
                    parent.left = null;
                else 
                    parent.right = null;
            }
            else
                node = null;
            
        }

        //Case 2: If children exist.
        else if (current.left != null && current.right != null) {
            E min = min(current.right);
            removeTreeNode(current, min);
            current.data = min;
        }

        //Case 3: If either child exists.
        else {
            TreeNode<E> child = (current.left != null) ? current.left : current.right;
            if (current != node) {
                if (parent.left == current) {
                    parent.left = child;
                }
                else
                    parent.right = child;
            }
            else 
                node = child;
            }

        return node;

    }

    public int height() {
        TreeNode<E> current = root;
        return height(current);
    }

    private int height(TreeNode<E> node) {
        if (node == null)
            return 0;
        int left = height(node.left);
        int right = height(node.right);
        if (left > right)
            return (left + 1);
        else
            return (right + 1);
    }

    @Override
    public String toString() {
        Queue<TreeNode<E>> queue = new LinkedList<>();
        queue.add(root);

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        
        while(!queue.isEmpty()) {
            TreeNode<E> poll = queue.poll();

            sb.append(poll.data + ", ");

            if (poll.left != null) 
                queue.add(poll.left);

            if (poll.right != null)
                queue.add(poll.right);
            
        }
        sb.delete(sb.length()-2, sb.length());
        sb.append("]");
        return sb.toString();
    }

    public int size() {
        return size;
    }
}