
public class OneMillionObject2 {
	public static void main(String[] args) {
		int i;
		int[] bigArray;

		bigArray = new int[1000000];

		for (i = 0; i < 1000000; i++) {
			bigArray[i] = i;
		}
	}
}
