class Object42 {

}

public class TypeCheckFailAssignNamedObjectArrayToBooleanArray {
	public static void main(String[] args) {
		boolean[] B;
		Object42[] O;

		O = new Object42[1];
		B = O;
	}
}