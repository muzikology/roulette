package za.co.playsafe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class RouletteMain {
		

	   public static void main(String[] args)
	   {

	       Scanner keyboard = new Scanner(System.in);
	       Random generator = new Random();
	       double total = 1000;
	       double amount;
	       int choice, win = 0, lose = 0, spin = 0;
	       int number;
	       int rouletteNum;
	       int result;
	       char response = 'y';
	       int resultArr[] = new int[36];
	       ArrayList<String> playerNames = new ArrayList<String>();
	       
	       
	       try {
	    	   /* Load list of player names from file**/
	    	   File file = new File("C:\\dev\\playsafe_app\\roulette\\playerNames.txt"); 
	    	   
	    	   BufferedReader br = new BufferedReader(new FileReader(file)); 
	    	   
	    	   String st; 

				while ((st = br.readLine()) != null) { 
					playerNames.add(st);
				     System.out.println(st);
				   }
			} catch (IOException e) {
				e.printStackTrace();
			} 

	       while (response == 'y' || response == 'Y' && total <= 0)
	       {
	    	   String name = null;
	    	   System.out.print("Enter Player Name between Muzi and Danielle: ");
	    	   String p1 = keyboard.nextLine();
	    	   for(String str: playerNames) {
	    		   if(str.equals(p1)) {
	    			   name = str;
	    		   }
	    	   }
	    	   System.out.println("PlayerName is :" + name);
	           System.out.print("Enter your bet amount " + name + ": ");
	           amount = keyboard.nextDouble();
	           System.out.print("0 - Even\n1 - Odd\n2 - Number\n");
	           choice = -1;
	           while (choice < 0 || choice > 2)
	           {
	               System.out.print("Place bet on: ");
	               choice = keyboard.nextInt();
	           }
	           number = 0;
	           if (choice == 2)  
	           {
	               while (number < 1 || number > 36)
	               {
	                   System.out.print("Place bet on number(1-36): ");
	                   number = keyboard.nextInt();
	               }
	           }
	           rouletteNum = generator.nextInt(37);
	           spin++;
	           System.out.println("Roulette number: " + rouletteNum);
	           
	           if (choice == 2)
	           {
	               if (rouletteNum == number)
	                   result = 35;
	               else
	                   result = 0;
	           }
	           else
	           {
	               if (rouletteNum == 0 || rouletteNum % 2 != choice)
	                   result = 0;
	               else
	                   result = 1;
	           }

	           //Print out game result, win/lose amount
	           if (result > 0)
	           {
	               System.out.println("Hi " + name + "Congratulatons You win!");
	               System.out.printf("You have won R%.2f \n", result * amount);
	               System.out.printf("Here's your money back: R%.2f \n",
	                       (result + 1) * amount);
	               total = (result + 1) * amount + total;
	               win ++;
	               resultArr[rouletteNum]++;
	               
	           }
	           else
	           {
	               System.out.println("Hi " + name + "You lose");
	               System.out.printf("You have lost R%.2f \n",
	                       (result + 1) * amount);
	               total = total - (result + 1) * (amount);
	               lose ++;
	               resultArr[rouletteNum]++;
	               
	               if (total <= 0) {
	            	   break;
	               }

	           }

	           //Ask for another game
	           for (int totals=1; totals<36; totals++) {
	        		if( resultArr[totals] > 0 ) {
	        			System.out.println("The number " + totals + " won " + resultArr[totals] + " times.");
	        		}
	        	}
	           
	           
	           System.out.println(name + "You have R" + total + " remaining." );
	           System.out.println("You have won " + win + " games.");
	           System.out.println("You have lost " + lose + " games.");
	           System.out.println("The wheel has been spun " + spin + " times.");          
	           System.out.print("\nWould you like to play another game? (y/n) " + name);
	           response = keyboard.next().charAt(0);
	           
	           
	     }
 }
}

	
