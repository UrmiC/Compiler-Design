public class TypeCheckFailBooleanArrayWithBooleanAccess {
    public static void main(String[] args) {
    	boolean[] B;
    	boolean b;
    	
    	B = new boolean[4];
    	b = B[b];
    }
}