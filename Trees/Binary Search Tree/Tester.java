public class Tester {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int[] array = {3,4,5,64,55,666, 88};
        for(int num: array) System.out.println(bst.add(num));
        
        System.out.println(bst);
    }
}
