import java.awt.Font;
import java.util.ArrayList;
/*
 * @author Isaac Mahon	20063321
 * @notes Handles all methods that draw to the screen
 */

public class Draw {
	public Draw()
	{
		
	}
	/*
	 * Draws Window and grids
	 */
	public void drawBoard()
	{
		StdDraw.setCanvasSize(1024, 512);
		StdDraw.setPenRadius(0.04);							// draw thicker lines
		StdDraw.line(0, 0.33, 0.5, 0.33);
		StdDraw.line(0, 0.66, 0.5, 0.66);
		StdDraw.line(0.126, 0, 0.126, 1);
		StdDraw.line(0.33, 0, 0.33, 1);
		/*
		 * Converting relative line values to exact pixel values and back again to maintain same grid in different
		 * window size
		 * 
		 * 0*512, 0.33*512, 1*512, 0.33*512
		 * 0*512, 0.66*512, 1*512, 0.66*512
		 * 0.33*512, 0*512, 0.33*512, 1*512
		 * 0.66*512, 0*512, 0.66*512, 1*512
		 * 
		 * 0, 168.96, 512, 168.96
		 * 0, 337.92, 512, 337.92
		 * 168.92, 0, 168.92, 0
		 * 337.92, 0, 337.92, 0
		 *
		 *
		 */
		StdDraw.line(0.5, 0.25, 1, 0.25);
		StdDraw.line(0.5, 0.5, 1, 0.5);
		StdDraw.line(0.5, 0.75, 1, 0.75);
		StdDraw.line(0.5, 0, 0.5, 1);
		StdDraw.line(1, 0, 1, 1);

		StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 64));
	}
	
	/*
	 * Draws the available numbers to the side of the grid
	 */
	public void drawNumbers(ArrayList<Integer> available)
	{
		for(int i = 1; i <= available.size(); i++)
		{
			
			StdDraw.text(0.75, (i*0.25)-0.13, (available.get(i-1).toString()));
		}
	}
	
	/*
	 * Draws the inputted move into the declared row and column
	 */
	public void drawMove(int col, int row, int choice)
	{
		double x = col*(0.5*0.33) +0.05;
		double y = row*(0.33)+0.10;
		String num = String.valueOf(choice);
		StdDraw.text(x, y, num );
	}
	
	/*
	 * Draws the win declaration
	 */
	public void endGame(int turn)
	{
		StdDraw.setPenColor(StdDraw.RED);
		if (turn == 9) {									// A draw
			StdDraw.text(0.5, 0.0, "A Draw");
		} else {											// last player won
			StdDraw.text(0.5, 0.0, (turn % 2 == 0 ? "I" : "You") + " Win");
		}
	}

}
