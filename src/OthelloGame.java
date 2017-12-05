import java.awt.Color;
import java.util.Scanner;


public class OthelloGame {

    public Board game_board;
    public Player playerA;
    public AI_Player playerB;
    public Player turn;
    public int noMoveCount;
    GUI UI; 

    public void initialize(String nameA, int colorA, String nameB, int colorB){
    	this.playerA = new Player(nameA, colorA);
    	this.playerB = new AI_Player(nameB, colorB);
    	game_board = new Board();
    	game_board.board[3][3] = -1;
    	game_board.board[4][4] = -1;
    	game_board.board[3][4] = 1;
    	game_board.board[4][3] = 1;
    	noMoveCount = 0; // use to indicate end of game, if it equals 2 means both players have no valid move
    	if(playerA.color == 1)
    		turn = playerA;
    	else
    		turn = playerB;
    	UI = new GUI(this);
    }
    
    public boolean endOfGame(){
    	if(!hasValidMove(playerA) && !hasValidMove(playerB)){
    		return true;
    	}
    	return false;
    }
    public void showEndGameStatus(){
    	int scoreA = 0;
    	int scoreB = 0;
    	for(int i = 0; i < 8; i++)
    		for(int j = 0; j<8; j++){
    			if(game_board.board[i][j] == playerA.color){
    				scoreA ++;
    			}else
    				scoreB ++;
    		}
    	System.out.println(playerA.name + " get " + scoreA + " points");
    	System.out.println(playerB.name + " get " + scoreB + " points");
    	System.out.println((scoreA > scoreB? playerA.name:playerB.name) + " wins!!");
    }
    public boolean hasValidMove(Player player){
    	for(int i = 0; i < 8; i++)
    		for(int j = 0; j<8; j++){
    			if(game_board.checkValid(player.color, i, j))
    				return true;
    		}
    	return false;
    }
    
    public void Run(int i, int j){
		int row,col;
		boolean validMove;
    	// game start and take turn to put piece
    	// while not end of game
    	
    		//System.out.println("not end of game");
    		// check whether current player has possible place to put piece if yes plays else give up this turn
	    	if(hasValidMove(turn)){
	    		// show board status
	    		//game_board.showBoardStatus();
	    		// set noMove to 0, means the current player has valid move
	    		noMoveCount = 0;
	    		// get user input of x and y coordinates
	    	
	    			// show whose turn
	    			//System.out.println(turn.name + "'s turn:");
	    			Move move = new Move();
	    			if(turn == playerB){
	    				State passinSate = new State(game_board.board);
    					move = playerB.play(passinSate);
    					row = move.x;
    	    			col = move.y;
	    			}else{
	    				row = i;
	    				col = j;
	    			}

	    			validMove = game_board.checkValid(turn.color, row, col);
	    			if(!validMove)
	    				System.out.println("not a valid move, plase move again");
	    		// if not valid play put again
	    		
	    		// update board status
		    		if(validMove){
		    			if(turn == playerB)
		    			{
		    				UI.buttons[row][col].setBackground(Color.DARK_GRAY);
		    				Thread t = new Thread(){
		    	    		    public void run(){
		    	    		    	try {
		    							Thread.sleep(1000);
		    						} catch (InterruptedException e) {
		    							// TODO Auto-generated catch block
		    							e.printStackTrace();
		    						}
		    	    		    	game_board.updateBoard(turn.color, row, col);
				    				UI.printBoardGUI();
				    				if(turn == playerA)
							    		turn = playerB;
							    	else
							    		turn = playerA;
		    	    		}};
		    	    		t.start();
		    			}else{
		    				game_board.updateBoard(turn.color, row, col);
		    				UI.printBoardGUI();
		    				if(turn == playerA)
					    		turn = playerB;
					    	else
					    		turn = playerA;
		    			}
				    	
			    	}
		    		
	    	}else{
	    		System.out.println(turn.name + " has no available move!!");
	    		if(turn == playerA)
	    			turn = playerB;
	    		else
		    		turn = playerA;
	    		System.out.println(turn.name + "'s turn:");
	    		if(turn == playerB)
	    			Run(i, j);
	    	}
	    		
	    	
	    		
    	
    }
    
    public void displayResult(){
    	game_board.showBoardStatus();
    }
    
    public int[][] getBoard(){
    	return game_board.board;
    }

    public static void main(String[] args){
    	String A_name = "White";	// assume two fixed players with fixed name and color of piece
    	String B_name = "Black";
    	int A_color = -1;
    	int B_color = 1;	// assume B with black piece start first
    	OthelloGame game = new OthelloGame();
    	game.initialize(A_name, A_color, B_name, B_color);
    	if(game.turn.name == "Black"){
    		int i=0;
    		int j = 0;
    		Thread t = new Thread(){
    		    public void run(){
    		    	try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    		    	game.Run(i,j);
    		}};
    		t.start();

    	}
    		
    		
    	//game.displayResult();
    	
    }
    
    
}
