public class TypeCheckFailTooManyMethodArguments {
    public static void main(String[] args) {
    	takeTwoArgs (true, 2, null);
    }
    
    static void takeTwoArgs (boolean b, int i){
    }
}