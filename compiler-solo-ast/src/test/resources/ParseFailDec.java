public class ParseFailDec {
    public static void main(String[] args) {
        int i;

        i = 2;
        --i;
        StaticJavaLib.assertTrue(i == 1);
    }
}
