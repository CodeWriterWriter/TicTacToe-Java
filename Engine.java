import java.util.ArrayList;
/*
 * @author Isaac Mahon 20063321
 * @notes Handles all the game logic for the game
 */

public class Engine {
	Draw draw;
	int[][] board;
	ArrayList<Integer> ai = new ArrayList<Integer>();
	ArrayList<Integer> human;
	/*
	 * Runs the game
	 */
	public Engine(Draw draw, int[][] board, ArrayList<Integer> human)
	{
		this.human = human;
		this.board = board;
		this.draw = draw;
		ai.add(1);
		ai.add(3);
		ai.add(5);
		ai.add(7);
		ai.add(9);
		/*
		 * Repeating turns
		 */
		int turn = 0;
		while (turn < 9)
		{
			System.out.println(turn);
			if (turn%2 == 0)
			{
				aiMove(turn);
			}
			else
			{
				playerMove();
			}
			
			/*
			 * Checks for a win
			 */
			if ((Math.abs(board[0][0] + board[0][1] + board[0][2]) == 15)
					|| (Math.abs(board[1][0] + board[1][1] + board[1][2]) == 15)
					|| (Math.abs(board[2][0] + board[2][1] + board[2][2]) == 15)
					|| (Math.abs(board[0][0] + board[1][0] + board[2][0]) == 15)
					|| (Math.abs(board[0][1] + board[1][1] + board[2][1]) == 15)
					|| (Math.abs(board[0][2] + board[1][2] + board[2][2]) == 15)
					|| (Math.abs(board[0][0] + board[1][1] + board[2][2]) == 15)
					|| (Math.abs(board[0][2] + board[1][1] + board[2][0]) == 15))
				break;
			turn++;
		}
		draw.endGame(turn);
	}
	
	/*
	 * Runs the AI's move.
	 */
	public void aiMove(int turn)
	{
		int row = 0;
		int col = 0;
		int choice = 0;
		/*
		 * Picks a random move on the first turn
		 */
		if (turn == 0 )
		{
			while (true)
			{
				row = (int) (Math.random() * 3);
				col = (int) (Math.random() * 3);
				choice = (int) (Math.random() * (ai.size()-1));
				if (board[row][col] == 0) 
				{		// valid move (empty slot)
					board[row][col] = ai.get(choice);
					draw.drawMove(col, row,ai.get(choice));
					ai.remove(choice);
					break;
				}
					
			}
		}
		/*
		 * Otherwise checks every number, and every square for a winning move
		 */
		else
		{
			boolean madeMove = false;
			while (true) 									
			{
				if (madeMove == true)
				{
					break;
				}
				for (int a = 0; a <ai.size(); a++)
				{
			  		for (int x = 0; x < 3; x++)					//loops through all squares and checks if free
			  		{
			 			for ( int y = 0; y < 3; y++)
			 			{
			 				if (madeMove == false)
			 				{
				  				if( board[x][y] == 0)
				  				{
				  					if (((x == 0 && y == 0)					//checks for first diagonal and then if it is the winning move
				  						|| (x == 1 && y == 1) 
				  						|| (x == 2 && y == 2)))
				  					{
				  						if (Math.abs(board[0][0] + board[1][1] + board[2][2]) + ai.get(a) == 15)
				  						{
				  							row = x;
				  							col = y;
				  							board[x][y] = ai.get(a);
				  							madeMove  = true;
				  							draw.drawMove(col, row, ai.get(a));
				  							ai.remove(a);
				  							break;
				  						}
				  					}
				  					if (((x == 0 && y == 2) 			//checks second diagonal for winning move
					  						|| (x == 1 && y == 1) 
					  						|| (x == 2 && y == 0)))
					  				{
				  						if (Math.abs(board[0][2] + board[1][1] + board[2][0]) + ai.get(a) == 15)
				  						{
				  							row = x;
				  							col = y;
				  							board[x][y] = ai.get(a);
				  							madeMove  = true;
				  							draw.drawMove(col, row, ai.get(a));
				  							ai.remove(a);
				  							break;
				  						}
					  				}
				  					else if ((Math.abs(board[x][0] + board[x][1] + board[x][2]) + ai.get(a) == 15))	//checks for winning row
				  					{
				  						row = x;
			  							col = y;
			  							board[x][y] = ai.get(a);
			  							madeMove  = true;
			  							draw.drawMove(col, row, ai.get(a));
			  							ai.remove(a);
			  							break;
				  					}
				  					else if ((Math.abs(board[0][y] + board[1][y] + board[2][y]) + ai.get(a) == 15)) //checks for winning column
				  					{
				  						row = x;
			  							col = y;
			  							board[x][y] = ai.get(a);
			  							madeMove  = true;
			  							draw.drawMove(col, row, ai.get(a));
			  							ai.remove(a);
			  							break;
				  					}
				  				}
			 				}
				 		}
				  	}
			  		if (madeMove == false)						//if a winning move can't be found, a random one is made
	  				{
						choice = (int) (Math.random() * (ai.size()-1));
	  					row = (int) (Math.random() * 3);
	  					col = (int) (Math.random() * 3);
	  					if (board[row][col] == 0) 
	  					{			// valid move (empty slot)
	  						board[row][col] = ai.get(choice);
	  						draw.drawMove(col, row, ai.get(choice));
	  						ai.remove(choice);
	  						madeMove = true;
	  						break;
	  					}
					}
			  		else
			  		{
			  			break;
			  		}
				}
			}
		}
	}
	
	/*
	 * Runs Players Moves
	 */
	public void playerMove()
	{
		int col = 0;
		int row = 0;
		int choice = 0;
		while (true)
		{
			if (StdDraw.mousePressed())
			{
				if (StdDraw.mouseX() > 0.5)		//checks if player is clicking on a number option
				{
					if (StdDraw.mouseY() < 0.25) //logic for checking which number is selected
					{
						for(int a = 0; a < human.size(); a++)
						{
							if (choice == 0)
							{
								if (human.get(a).equals(2))
								{
									choice = 2;
									human.remove(a);
									break;
								}
							}
						}
					}
					else if (StdDraw.mouseY() < 0.5)
					{
						for(int a = 0; a < human.size(); a++)
						{
							if (choice == 0)
							{
								if (human.get(a).equals(4))
								{
									choice = 4;
									human.remove(a);
									break;
								}
							}
						}
					}
					else if (StdDraw.mouseY() < 0.75)
					{
						for(int a = 0; a < human.size(); a++)
						{
							if (choice == 0)
							{
								if (human.get(a).equals(6))
								{
									choice = 6;
									human.remove(a);
									break;
								}
							}
						}
					}
					else
					{
						for(int a = 0; a < human.size(); a++)
						{
							if (choice == 0)
							{
								if (human.get(a).equals(8))
								{
									choice = 8;
									human.remove(a);
									break;
								}
							}
						}
					}	
				}
			}
			if (StdDraw.mousePressed()) //fills in players move
			{
				if (choice != 0)
				{
					if (StdDraw.mouseX() < 0.5)
					{	
						col = (int) ((StdDraw.mouseX() * 6));						
						row = (int) ((StdDraw.mouseY() * 3));
						if (board[row][col] == 0) 
						{		// valid move (empty slot)
							board[row][col] = choice;
							draw.drawMove(col, row, choice);
							break;
						}
					}
				}
			}
		}
	}
}
