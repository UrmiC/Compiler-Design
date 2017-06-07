
public class OneMillionObject1 {
	public static void main(String[] args) {
		int i;
		OneMillionObject1A[] mOneMillionObjectAnother;

		mOneMillionObjectAnother = new OneMillionObject1A[1000000];
		
		for(i = 0; i < 1000000; i++){
			mOneMillionObjectAnother[i] = new OneMillionObject1A();
		}
	}
}

class OneMillionObject1A {

}
