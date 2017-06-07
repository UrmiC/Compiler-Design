
public class OneMillionObject3 {
	public static void main(String[] args) {

		int i;
		OneMillionObject3A[] a;
		OneMillionObject3B b;

		a = new OneMillionObject3A[1000000];

		for (i = 0; i < 1000000; i++) {
			a[i] = new OneMillionObject3A();
			b = new OneMillionObject3B();
			a[i].b = b;
		}
	}
}

class OneMillionObject3B {

}

class OneMillionObject3A {
	public OneMillionObject3B b;
}
