public class SearchTest
{
    public static void main(String[] args)
    {
    	int[] myList;
    	int foundIndex; 
    	int toSearch;
    	int i;
    	myList = new int[] {1,2,3,5,4};
    	toSearch = 2;
    	foundIndex = 0;
    	 
    		  for (i = 0; i < 5; i++) {  
    		  
    		   if (myList[i] == toSearch) {  
    		    foundIndex = i;  
    		   }  
    		  }  
    		  StaticJavaLib.assertTrue(foundIndex == 1);
    		 }  
    		
    }

