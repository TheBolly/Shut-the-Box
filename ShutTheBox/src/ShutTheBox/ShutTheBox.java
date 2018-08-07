package ShutTheBox;
import java.util.ArrayList;
import java.util.Scanner;

public class ShutTheBox {
	
	Dice dice1 = new Dice();
	Dice dice2 = new Dice();
	
	int score;
	
	String shutBox;
	ArrayList<String> openBoxes = new ArrayList<String>();
	ArrayList<Integer> openBoxesIntegers = new ArrayList<Integer>();
	
	static Scanner scan = new Scanner(System.in);
	
	public void rollTwoDice() {

		dice1.roll();
		dice2.roll();
	}
	
	
	public int calculateScore() {
		
		return score = dice1.getFaceValue() + dice2.getFaceValue();
	}
	
	
	public void displayDice() {

		System.out.println(String.valueOf("Dice rolled: " + dice1.getFaceValue()) + " " + String.valueOf(dice2.getFaceValue())); 
	}
	
	
	public void createBoxes() {
		
		for(int i = 1; i <= 9; i++) {
			
			openBoxes.add(String.valueOf(i));			
		}
	}
	
	
	public void displayOpenBoxes() {
		
		System.out.println("**** BOXES OPEN ****");
		
		for(String num: openBoxes) {
			
			System.out.print("[" + num + "]");
		}
		System.out.println("\n***************************");
	}
	
	
	public void getShutBoxes() {
		//method to get user input
		
		System.out.print("Enter box(es) to shut ");
		shutBox = scan.nextLine();
		
		while(checkInput()) {
			
			System.out.print("Please enter valid input...");
			shutBox = scan.nextLine();	
		}
		System.out.println("");
	}
	
	
	public boolean checkInput() {
		//method to check whether the user input is actually valid
		
		boolean repeat = false;
		int target = calculateScore();
		int count = 0;
		int inputTotal = 0;
		
		try {
			
			for(String num: shutBox.split(",")) {
				//sum up user input
				inputTotal += Integer.parseInt(num);	
			}
					
			if(Integer.parseInt(shutBox.split(",")[0]) >= 10) {
				//9 is max value for single box
				repeat = true;
			}

			if (shutBox.split(",").length != 1) {
					
				for(String num: shutBox.split(",")) {
						
					if(openBoxes.get(Integer.parseInt(num) - 1).equals("X")) {
							
						count += 1;	
					}
				}
	
			} else if(openBoxes.get(Integer.parseInt(shutBox) - 1).equals("X")) {
					
				count += 1;
			} else {
					
				count = 0;	
			}

			if (count > 0){
				
				repeat = true;
				
			} else if(shutBox.split(",").length != 1) {
				
				if(shutBox.split(",")[0].equals(shutBox.split(",")[1])){
					
					repeat = true;				
				}
			}  else if(inputTotal == target) {
				
				repeat = false;
			} else {
				
				repeat = true;
			}
		}
		catch(Exception e) {
			
			repeat = true;
		}
		return repeat;
	}
	
	
	public void adjustOpenBoxes() {
		//method to amend openBoxes array
				
		for (String shut: shutBox.split(",")) {
			
			try {
				
				int i = Integer.parseInt(shut) - 1;
				
				openBoxes.remove(i);
				openBoxes.add(i, "X");
				
			} 
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<Integer> openBoxIntArray() {
		//method to attain clean int array of current openBoxes
		
		openBoxesIntegers.clear();
			
		for (String num: openBoxes) {
			
			try {
				
				int box = Integer.parseInt(num);
				openBoxesIntegers.add(box);
			} 
			catch (Exception e){
				e.printStackTrace();
			}
		}
		return openBoxesIntegers;
	}
	
	
	public void play() {
		//method to run the game

		ComboChecker comboChecker = new ComboChecker();
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		int target;
		int finalScore = 0;
		boolean valid = true;
		
		createBoxes();
		
		while(valid){
			
			comboChecker.comboArray.clear();
			
			rollTwoDice();
			displayDice();
			displayOpenBoxes();
			
			target = calculateScore();
			numbers = openBoxIntArray();			
			comboChecker.sum_up(numbers,target);
			
			if(comboChecker.comboArray.size() != 0) {

				getShutBoxes();		
				adjustOpenBoxes();
			} else {
				
				valid = false;	
			}				
		} ;
		
		for (int i: numbers) {
			
			finalScore += i;
		}
		
		System.out.println("\nGAME OVER. You total is " + finalScore + ".");
	}
	
	
	public static void main(String[] args) {
		
		ShutTheBox shutTheBox = new ShutTheBox();
		shutTheBox.play();
		scan.close();
	}

}
