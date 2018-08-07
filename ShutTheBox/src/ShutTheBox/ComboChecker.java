package ShutTheBox;
import java.util.ArrayList;
import java.util.Arrays;

public class ComboChecker {
	
		ArrayList<String> comboArray = new ArrayList<String>();

	    public void sum_up_recursive(ArrayList<Integer> numbers, int target, ArrayList<Integer> partial) {
	      int s = 0;
	      for (int x: partial) s += x;
	      
	      if (s == target) {
	      	  comboArray.add(Arrays.toString(partial.toArray()));	      	  
	      }
	      
	      if (s >= target)
	    	  
	           return;
	      
	      for(int i=0;i<numbers.size();i++) {
	    	  
	           ArrayList<Integer> remaining = new ArrayList<Integer>();
	           int n = numbers.get(i);
	           for (int j=i+1; j<numbers.size();j++) remaining.add(numbers.get(j));
	           ArrayList<Integer> partial_rec = new ArrayList<Integer>(partial);
	           partial_rec.add(n);
	           sum_up_recursive(remaining,target,partial_rec);   
	       }
	    }
	    
	    
	    public void sum_up(ArrayList<Integer> numbers, int target) {
	    	
	        sum_up_recursive(numbers,target,new ArrayList<Integer>()); 
	    }
}
