public class IfTrueEmptyElseEmpty {
    public static void main(String[] args) {
        int a;

        a = 0;
        if (true) {
        } else {
        }
        StaticJavaLib.assertTrue(a == 0);
    }
}
