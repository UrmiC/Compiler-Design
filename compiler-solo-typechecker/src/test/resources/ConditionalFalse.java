public class ConditionalFalse {
    public static void main(String[] args) {
        int i;

        i = -1;
        i = i > 0 ? 2 : 3;
        StaticJavaLib.assertTrue(i == 3);
    }
}
    
