package HashSet;

public class Tester {
    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>();
        set.add(3);
        set.add(4);
        set.remove(3);
        
        System.out.print(set);
    }
    
}
