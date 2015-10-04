import java.util.ArrayList;
/*
 * @author Isaac Mahon 20063321
 * @brief Numerical Tic Toe Game
 */

public class NumericTicTacToe {
	/*
	 * Sets up the necessary components for the game to function
	 */
	public static void main(String[] args) 
	{
		Draw draw = new Draw();
		draw.drawBoard();
		int[][] board = new int[3][3];
		
		ArrayList<Integer> choices = new ArrayList<Integer>();
		choices.add(2);
		choices.add(4);
		choices.add(6);
		choices.add(8);
		draw.drawNumbers(choices);
		Engine engine = new Engine(draw, board, choices);
		

	}

}
