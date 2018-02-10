import java.awt.*;
import javax.swing.*;

/*
 * This class is used to draw a chess board with queens.
 * Where the queens are placed on the board is controlled by
 * the instance variable: boolean board[][]. The size of board is 8x8.
 * Each element in board indicates if a queen is there. For example,
 * if board[4][5]==true, there is a queen at row 4 and column 5.
 * 
 * The value of this 2D array is passed in at the constructor. 
 * This class does not decide which cells have queens.
 */
class JChessBoardPanel extends JPanel{

	// this variable is used to indicate which cell has a queen
	private boolean board[][];

	// the user should pass a 2D boolean array to tell
	// which cells have queens
	public JChessBoardPanel(boolean board[][]){
		super();
		this.board = board;
	}


	public void drawChessBoard(Graphics g){

		// get the cell width/height
		int height = getHeight(), width=getWidth();
		int cellHeight = height/8;
		int cellWidth = width/8;

        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++ )
            {
                if ((i%2==0)&& (j%2==0))
                    g.setColor(Color.BLACK);
                else if ((i%2==1)&& (j%2==1))
                    g.setColor(Color.BLACK);
                else
                    g.setColor(Color.WHITE);

                g.fillRect(j*cellWidth, i*cellHeight, cellWidth, cellHeight);
            }

        }

	}

	public void drawQueens(Graphics g){

        int height = getHeight(), width=getWidth();
        double cellHeight = height/8;
        double cellWidth = width/8;
        g.setColor(Color.RED);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == true) {
                    g.fillOval((int)(j*cellWidth + 1.25),(int)(i*cellHeight +
                            1.25),(int)(cellWidth + .25), (int)(cellHeight +
                            .25));
                }
            }
        }


	}

	public void paint(Graphics g){
		super.paint(g);
		drawChessBoard(g);
		drawQueens(g);
	}
}

/*
 * This JFrame-based class is the container of JChessBoardPanel.
 * When the user creates a JEightQueenFrame object, he/she is supposed
 * to pass a 2D (8x8) boolean array, board, to tell where the queens should be
 * placed.  The size of board is 8x8. Each element in board indicates if a 
 * queen is there. For example, if board[4][5]==true, there is a queen at 
 * row 4 and column 5.
 */
public class JEightQueenFramePart extends JFrame {
    private boolean board[][];
    private JChessBoardPanel chessBoard;

    public JEightQueenFramePart(boolean board[][]) {

        super();

        this.board = board;

        //setting the layout
        getContentPane().setLayout( new BorderLayout() );

        //adding the ChessBoard
        getContentPane().add( new JChessBoardPanel( board ),
                BorderLayout.CENTER );

        //other settings
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setSize( 590, 610 );
        setResizable( false );
        setVisible( true );
    }

    public static boolean trial(boolean board[][], int n) {
        for (int i = 0; i < 8; i++) {
            board[n][i] = true;
            if (n == 7 && good( board, n, i ))
                return true;
            if (n < 7 && good( board, n, i ) && trial( board, n + 1 ))
                return true;
            board[n][i] = false;
        } // end for
        return false;
    } //end trial

    private static boolean good(boolean board[][], int row, int col) {
        //Initialize variables for diagonals
        int rowd, cold;
        //Check horizontal
        for (int i = 0; i < row; i++) {
            if (board[i][col] == true)
                return false;
        } // end for
        //Check vertical
        for (int j = 0; j < col; j++) {
            if (board[row][j] == true)
                return false;
        } // end for

        //Check left diagonal
        for (rowd = row, cold = col; rowd > 0 && cold > 0; rowd--, cold--) {
           if (board[rowd-1][cold-1] == true)
                return false;

        } // end for
        //Check right diagonal
        for (rowd = row, cold = col; rowd > 0 && cold < 7; rowd--, cold++) {
            if (board[rowd-1][cold+1] == true)
                return false;

        }

            return true;

    }

    public static void main(String args[]){
		
		boolean [][]board = new boolean[8][8];

		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++){
				board[i][j] = false;
			}

		trial(board,0); //see if we can place a queen at row 0
		JEightQueenFramePart queenFrame = new JEightQueenFramePart(board);

	}
}

