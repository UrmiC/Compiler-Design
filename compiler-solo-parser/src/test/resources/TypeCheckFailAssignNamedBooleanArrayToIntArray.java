public class TypeCheckFailAssignNamedBooleanArrayToIntArray {
    public static void main(String[] args) {
    	int[] I;
    	boolean[] B;
    	
    	B = new boolean[1];
    	I = B;
    }
}