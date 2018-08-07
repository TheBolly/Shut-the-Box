package ShutTheBox;
import java.util.Random;

public class Dice {
	
	int faceValue;
	
	public void roll() {
		//method to randomly generate a number from 1 to 6
		
		Random rand = new Random();
		
		faceValue = rand.nextInt(6) + 1;
		
	}
	
	public int getFaceValue() {
		
		return faceValue;
	}
	
	

}
