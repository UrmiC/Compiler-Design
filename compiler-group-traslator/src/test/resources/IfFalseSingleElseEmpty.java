public class IfFalseSingleElseEmpty {
    public static void main(String[] args) {

        int a;
        a = 0;
        if (false) {
            a = 1;
        } else {
        }
        StaticJavaLib.assertTrue(a == 0);
    }
}
