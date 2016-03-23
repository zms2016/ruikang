import java.util.Random;


public class Tesss {

	public static void main(String[] args) {
		 
       for (int i=0; i<1000;i++)
       {
    	   System.out.println(getRandomString(5));
       }
		
	}
	
	
	  public static String getRandomString(int length){  
	       String str="amo";  
		  
	        java.util.Random   random = new  Random();
	        StringBuffer sb = new StringBuffer();  
	          
	        for(int i = 0 ; i < length; ++i){  
	            int number = random.nextInt(3);//[0,62)  
	              
	            sb.append(str.charAt(number));  
	        }  
	        return sb.toString();
	  } 

	  

}
