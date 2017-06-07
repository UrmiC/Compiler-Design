
public class OneMillionObject4 {

	public static void main(String[] args) {
		OneMillionObject4A[] retValue;
		retValue = manipulate();
	}

	static OneMillionObject4A[] manipulate() {
		int i;
		OneMillionObject4A[] millionObjects;
		millionObjects = new OneMillionObject4A[1000000];

		for (i = 0; i < 1000000; i++) {
			millionObjects[i] = new OneMillionObject4A();
			millionObjects[i].i = 0;
		}

		changeValue(millionObjects);

		return millionObjects;
	}

	static void changeValue(OneMillionObject4A[] millionObjects) {
		int i;
		for (i = 0; i < 1000000; i++) {
			millionObjects[i].i = i;
		}
	}
}

class OneMillionObject4A {
	public int i;

}