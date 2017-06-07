

public class ConditionalNullArraytypeTest {
    public static void main(String[] args) {
        int[] a;
        
        a = new int[1];
        a = true ? null : a;
    }
}
