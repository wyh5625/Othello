public class Board {
    int[][] board;    //  2D array board contains 64 pieces
    public Board(){
    	board = new int[8][8];	// 0 = empty; 1 = black; -1 = white
    }
    
    public Board(Board b){
    	board = new int[8][8];
    	for(int i = 0; i< 8; i++)
    		for(int j = 0; j< 8; j++){
    			board[i][j] = b.board[i][j];
    		}
    }
    
    public void putPiece(int color, int x, int y){
        board[x][y] = color;
    }
    
    public void showBoardStatus(){
    	for(int i = -1; i < 8; i++){
    		if(i != -1)
    			System.out.print(i + " ");
    		else
    			System.out.print("* ");
    	}
    	System.out.println("");
    	for(int i = 0; i< 8; i++){
    		System.out.print(i + " ");
    		for(int j = 0; j< 8; j++){
    			if(board[i][j] == 0)
    				System.out.print("0 ");
    			else if(board[i][j] == 1)
    				System.out.print("B ");
    			else
    				System.out.print("W ");
    		}
    		System.out.println("");
    	}
    }
    
    public int checkLeft(int color, int x, int y){
    	int reversiCount = 1;
    	if(y < 2 || board[x][y - 1] == 0||board[x][y - 1] == color)
    		return -1;
    	for(int j = y - 2; j >= 0; j--){
    		if(board[x][j] == 0)
    			return -1;
    		if(board[x][j] == color)
    			return reversiCount;
    		reversiCount ++;
    	}
    	return -1;
    }
    public int checkRight(int color, int x, int y){
    	int reversiCount = 1;
    	if(y > 5 || board[x][y + 1] == 0||board[x][y + 1] == color)
    		return -1;
    	for(int j = y + 2; j < 8; j++){
    		if(board[x][j] == 0)
    			return -1;
    		if(board[x][j] == color)
    			return reversiCount;
    		reversiCount ++;
    	}
    	return -1;
    }
    public int checkUp(int color, int x, int y){
    	int reversiCount = 1;
    	if(x < 2 || board[x - 1][y] == 0 || board[x - 1][y] == color)
    		return -1;
    	for(int i = x - 2; i >= 0; i--){
    		if(board[i][y] == 0)
    			return -1;
    		if(board[i][y] == color)
    			return reversiCount;
    		reversiCount ++;
    	}
    	return -1;
    }
    public int checkDown(int color, int x, int y){
    	int reversiCount = 1;
    	if(x > 5 || board[x + 1][y] == 0 || board[x + 1][y] == color)
    		return -1;
    	for(int i = x + 2; i < 8; i++){
    		if(board[i][y] == 0)
    			return -1;
    		if(board[i][y] == color)
    			return reversiCount;
    		reversiCount ++;
    	}
    	return -1;
    }
    public int checkLeftUp(int color, int x, int y){
    	int reversiCount = 1;
    	if(x < 2 || y < 2 || board[x - 1][y - 1] == 0 || board[x - 1][y - 1] == color)
    		return -1;
    	int i = 2;
    	while(x - i >= 0 && y - i >= 0){
    		if(board[x - i][y - i] == 0)
    			return -1;
    		if(board[x - i][y - i] == color)
    			return reversiCount;
    		reversiCount ++;
    		i ++;
    	}
    	return -1;
    }
    public int checkRightDown(int color, int x, int y){
    	int reversiCount = 1;
    	if(x > 5 || y > 5 || board[x + 1][y + 1] == 0 || board[x + 1][y + 1] == color)
    		return -1;
    	int i = 2;
    	while(x + i < 8 && y + i < 8){
    		if(board[x + i][y + i] == 0)
    			return -1;
    		if(board[x + i][y + i] == color)
    			return reversiCount;
    		reversiCount ++;
    		i ++;
    	}
    	return -1;
    }
    public int checkRightUp(int color, int x, int y){
    	int reversiCount = 1;
    	if(x < 2 || y > 5 || board[x - 1][y + 1] == 0 || board[x - 1][y + 1] == color)
    		return -1;
    	int i = 2;
    	while(x - i >= 0 && y + i < 8){
    		if(board[x - i][y + i] == 0)
    			return -1;
    		if(board[x - i][y + i] == color)
    			return reversiCount;
    		reversiCount ++;
    		i ++;
    	}
    	return -1;
    }
    public int checkLeftDown(int color, int x, int y){
    	int reversiCount = 1;
    	if(x > 5 || y < 2 || board[x + 1][y - 1] == 0 || board[x + 1][y - 1] == color)
    		return -1;
    	int i = 2;
    	while(x + i < 8 && y - i >= 0){
    		if(board[x + i][y - i] == 0)
    			return -1;
    		if(board[x + i][y - i] == color)
    			return reversiCount;
    		reversiCount ++;
    		i ++;
    	}
    	return -1;
    }    
    
    public boolean checkValid(int color, int x, int y){
    	if(board[x][y] != 0){
    		return false;
    	}
    	if(checkLeft(color, x, y) == -1 && checkRight(color, x, y) == -1 && checkUp(color, x, y) == -1 && checkDown(color, x, y) == -1 && checkLeftUp(color, x, y) == -1 && checkLeftDown(color, x, y) == -1 && checkRightUp(color, x, y) == -1 && checkRightDown(color, x, y)== -1)
    		return false;
    	return true;
    }
    
    public void updateBoard(int color, int x, int y){
    	board[x][y] = color;
    	int leftRev = checkLeft(color, x, y);	// number of piece need to be reversed, -1 means no piece
    	int rightRev = checkRight(color, x, y);
    	int upRev = checkUp(color, x, y);
    	int downRev = checkDown(color, x, y);
    	int leftUpRev = checkLeftUp(color, x, y);
    	int leftDownRev = checkLeftDown(color, x, y);
    	int rightUpRev = checkRightUp(color, x, y);
    	int rightDownRev = checkRightDown(color, x, y);
    	int i = 1;
    	while(leftRev > 0){
    		board[x][y - i] = color;
    		leftRev --;
    		i ++;
    	}
    	i = 1;
    	while(rightRev > 0){
    		board[x][y + i] = color;
    		rightRev --;
    		i ++;
    	}
    	i = 1;
    	while(upRev > 0){
    		board[x - i][y] = color;
    		upRev --;
    		i ++;
    	}
    	i = 1;
    	while(downRev > 0){
    		board[x + i][y] = color;
    		downRev --;
    		i ++;
    	}
    	i = 1;
    	while(leftUpRev > 0){
    		board[x - i][y - i] = color;
    		leftUpRev --;
    		i ++;
    	}
    	i = 1;
    	while(leftDownRev > 0){
    		board[x + i][y - i] = color;
    		leftDownRev --;
    		i ++;
    	}
    	i = 1;
    	while(rightUpRev > 0){
    		board[x - i][y + i] = color;
    		rightUpRev --;
    		i ++;
    	}
    	i = 1;
    	while(rightDownRev > 0){
    		board[x + i][y + i] = color;
    		rightDownRev --;
    		i ++;
    	}		
    }

}
