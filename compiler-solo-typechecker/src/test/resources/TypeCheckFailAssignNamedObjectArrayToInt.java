class Object2 {

}

public class TypeCheckFailAssignNamedObjectArrayToInt {
	public static void main(String[] args) {
		int i;
		Object2[] O;

		O = new Object2[1];
		i = O;
	}
}