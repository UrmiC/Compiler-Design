class Item {
   public int val;
   public Item next;
}

public class onemillionobjecttest{
	public static void main(String[] args) {
	   Item curr;
	   Item head;
	   int i;

	   head = null;

	   for(i = 0; i < 1000000;i++) {
		  curr = new Item();
		  curr.val = i;
		  curr.next = head;
		  head = curr;
	   }

	   curr = head;
	}
}