class Complex {
	public int[] ai;
	public Complex next;
	public int i;
}

public class ComplicatedFieldArrayAccessTest {
	public static void main(String[] args) {
		Complex[] ai;

		ai = new Complex[2];
		ai[0] = new Complex();
		ai[1] = new Complex();
		ai[0].next = ai[1];
		ai[1].next = ai[0];
		ai[1].i = 0;

		ai[ai[0].next.i].i = 4;
	}
}
