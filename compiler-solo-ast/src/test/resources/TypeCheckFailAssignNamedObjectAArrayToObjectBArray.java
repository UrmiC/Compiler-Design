class Object40 {

}

class Object41{
	
}

public class TypeCheckFailAssignNamedObjectAArrayToObjectBArray {
	public static void main(String[] args) {
		Object41[] OA;
		Object40[] OB;

		OB = new Object40[1];
		OB = OA;
	}
}