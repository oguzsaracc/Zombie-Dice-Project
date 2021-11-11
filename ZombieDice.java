
/*

    Computing Science BSCH
    Computer Programming

    Zombie Dice Assignment
           Project 1

             TEAM
   Girls Just Wanna Have Fun


  [Jozsef Kallai  -   2993559]
  [Andrew Roche   -   2967864]
  [Oguz Sarac     -   2988989]



        V2 - 06 MARCH 2019

*/

import java.util.*;

class ZombieDice{
	
	//Declaring variables that will be needed throughout the program
    static int playerCount;
    static boolean gameStarted = false;
    static int currentTurn = 0;
	static boolean gameOver = false;
	static int score;
	static Scanner kb = new Scanner(System.in);
	
	//This first method sets up the conditions for the game
	public static void main(String[] args) { 
	
		//Title Screen
		System.out.println("	__________________________________________________");
		System.out.println("	| ____           _                ___   	 |");
		System.out.println("	||_  /___ _ __ | |__[*]___  ___  |   \\[*]__ ___  |");
		System.out.println("	|   / *  \\ \\  \\| *- \\ | ,_| |__| | |) | / _/ ,_) |");
		System.out.println("	|/___\\___/_|_|_|_.__/_\\___|      |___/|_\\__\\___| |");
		System.out.println("	|________________________________________________|");
		System.out.println(" Welcome to the Zombie Dice Game !! ");

		
        while (gameStarted == false) {
			
			if (gameOver == true){
				
				//This section exists to ensure that the game does not loop eternally 
				System.out.println("Quitting game. Goodbye.");
				break;
				}
			
			//Retrieving number of players, limited to between 2 - 5
            System.out.println("\n\nEnter amount of players (2-5)");
            playerCount = kb.nextInt();
			
            if ((playerCount <= 5) && (playerCount >= 2)) {
				
				//Once players count is determined, main_game method is called to begin playing
                System.out.println("\nCreating players...\n");
                gameStarted = true;
                main_game();
				} 
				
			else {
                System.out.println("\n#Invalid input!");
                System.out.println("(A minimum of 2, and maximum of 5 players allowed)\n");
				}
        }
    } 
	
	
	
	//This method tracks player score, rotates between players after their turn, and checks for win conditions
	public static void main_game() {

		//Players and score tracked via arrays
        String [] playerName = new String[playerCount];
		for (int i = 0; i < playerCount; i++) {
			System.out.print("> Please enter the player " + (i + 1) + " name: ");
			playerName[i] = kb.next();
			}
		
		
		int[] playerScore = new int[playerCount];
		
		

        System.out.println("\nGame starting... Players: "+ playerCount + "\n");



        while (gameStarted == true) {
			
			//Prints player scores before each turn to help players keep track
			System.out.println("\nPlayer:\t\tscore:");

			//Rotates between players for each turn
			if (currentTurn == playerCount) {
				currentTurn = 1;
				}
			
			else {
				currentTurn++;
				}
			

			
			for (int i = 0; i < playerCount; i++) {
				System.out.print(playerName[i] + "\t\t" + playerScore[i] + "\n");
				} 
		
			System.out.println("\nIt's your turn, " + playerName[currentTurn-1]);

			//Calls method turn, which returns score attained during that player's turn
			playerScore[currentTurn-1] = (playerScore[currentTurn-1] + turn(score, currentTurn));
			
			System.out.println("\nYour score is now " + playerScore[currentTurn-1] + "\n");
			
			//Win Condition
			if(playerScore[currentTurn-1] >= 13){
				
				System.out.println("\nCongratulations, " + playerName[currentTurn-1] + " you won!");
				gameStarted = false;
				gameOver = true;
				}
        } 
    } 
	
	public static int turn(int score, int currentTurn){
		
		boolean turn = true;
		int diceTotal = 0;
		int redDice = 0;
		int greenDice = 0;
		int yellowDice = 0;
		int brains = 0;
		int shotguns = 0;
		int greenFoot = 0;
		int redFoot = 0;
		int yellowFoot = 0;
		
		while (turn == true){
			


			//int choice is used to allow players to pick an option
			System.out.println("\nPick an option: \n 1) Play turn \n 2) Show score \n 3) End Turn\n 4) Quit game\n");
			
			int choice = kb.nextInt();
			
			if (choice == 1){
				
				System.out.println("\nPlaying game!\n");
				

				
				System.out.println("Picking new dice...");
				
				//Generating dice
				while (diceTotal<3){
			
					double i = Math.random() * 13;
					i = Math.round(i);
					
					if ( i < 6 ){
						greenDice++;
						diceTotal++;
						System.out.println("green");
					}
					
					else if ( i > 9){
						redDice++;
						diceTotal++;
						System.out.println("Red");
						
					}
					
					else {
						yellowDice++;
						diceTotal++;
						System.out.println("Yellow");
					}
			
			
				}
		
				System.out.println("\nYou have " + greenDice + " green " + yellowDice + " yellow and " + redDice + " red dice.\n" );
				
				//Rolling dice and determining outcome
				while (diceTotal > 0){
			
					//Rolling red dice
					if (redDice > 0){
						System.out.println("Rolling red dice...");
						
						double i = (Math.random() * 5) + 1;
						i = Math.round(i);
						
						if (i < 4){
							System.out.println("Shotgun\n");
							shotguns++;
							redDice--;
							diceTotal--;
						}
						
						else if (i == 6){
							System.out.println("Brain\n");
							brains++;
							redDice--;
							diceTotal--;
						}
						
						else{
							System.out.println("Footsteps\n");
							redFoot++;
							redDice--;
							diceTotal--;
						}
						
					}
					
					//Rolling yellow dice
					else if (yellowDice > 0){
						System.out.println("Rolling yellow dice...");
						
						double i = (Math.random() * 5) + 1;
						i = Math.round(i);
						
						if (i < 3){
							System.out.println("Shotgun\n");
							shotguns++;
							yellowDice--;
							diceTotal--;
						}
						
						else if (i > 4){
							System.out.println("Brain\n");
							brains++;
							yellowDice--;
							diceTotal--;
						}
						
						else{
							System.out.println("Footsteps\n");
							yellowFoot++;
							diceTotal--;
							yellowDice--;
						}
						
					}
					
					//Rolling green dice
					else if (greenDice > 0){
						System.out.println("Rolling green dice...");
						
						double i = (Math.random() * 5) + 1;
						i = Math.round(i);
						
						if (i < 2){
							System.out.println("Shotgun\n");
							shotguns++;
							greenDice--;
							diceTotal--;
						}
						
						else if (i > 3  ){
							System.out.println("Brain\n");
							brains++;
							greenDice--;
							diceTotal--;
						}
						
						else{
							System.out.println("Footsteps\n");
							greenFoot++;
							diceTotal--;
							greenDice--;
							
						}
						
						
					}
				}
				
				//Declaring outcome of the turn
				System.out.println("You have " + brains + " brains and " + shotguns + " shotguns.\n");
				
				
				//Ensuring footprints are tracked, and colour of that dice kept for the next turn
				if (greenFoot > 0 || redFoot > 0 || yellowFoot > 0){
					
					diceTotal = greenFoot + redFoot + yellowFoot;
					System.out.println("You still have " + diceTotal + " footsteps - " + greenFoot + " green, " + yellowFoot + " yellow, and " + redFoot + " red.\n" );
					
					greenDice = greenFoot;
					yellowDice = yellowFoot;
					redDice = redFoot;
					
					greenFoot = 0;
					yellowFoot = 0;
					redFoot = 0;
				}
				
				//Turn over condition
				if (shotguns > 2){
					
					System.out.println("You got three shotguns, turn over!");
					brains = 0;
					turn = false;
				}
				
			}
			
			//Prints current number of brains and shotguns the player has during their turn
			else if (choice == 2){
				
				System.out.println("\nYou currently have " + brains + " brains and " + shotguns + " shotguns.");
			}
			
			//End turn option
			else if (choice == 3){
				
				System.out.println("\nTurn over.\n");
				turn = false;
			}
			
			//End game option
			else if (choice == 4){
				
				turn = false;
				gameStarted = false;
				gameOver = true;
			}
			
			//Invalid input alert
			else {
				System.out.println("\nnot an option!\n");
			}
		}
		
		//Gives final tally of brains gathere during the turn and returns to main_game so that variable score can be updated
		System.out.println("You got " + brains + " brains this turn.");
		return (brains);
		
	}
}
