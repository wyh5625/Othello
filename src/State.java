import java.util.ArrayList;

public class State {
	public int [][] state;
	int depth;
	int value;
	ArrayList<Move> actions;
	public void searchMoves(int color){
		actions = validMoves(color);
	}
	public State(){
		state = new int[8][8];
		state[3][4] = 1;
		state[4][3] = 1;
		state[3][3] = 2;
		state[4][4] = 2;
	}
	public State(int[][] ori){
		state = new int[8][8];
		for(int i=0; i<8; i++)
			for(int j=0; j<8; j++){
				state[i][j] = ori[i][j];
			}
	}
	public void showState(){
		for(int i=0; i<8; i++)
			for(int j=0; j<8; j++){
				System.out.print(state[i][j]);
			}
	}
	public State(State ori){
		state = new int[8][8];
		for(int i=0; i<8; i++)
			for(int j=0; j<8; j++){
				state[i][j] = ori.state[i][j];
			}
	}
	public ArrayList<Move> validMoves(int color){
		ArrayList<Move> result = new ArrayList<Move>();
		for(int i = 0; i< 8; i++)
			for(int j =0; j< 8; j++){
				if(checkValid(color, i, j)){
					Move m = new Move(i,j);
					result.add(m);
				}
			}
		return result;
	}
	public int checkLeft(int color, int x, int y){
    	int reversiCount = 1;
    	if(y < 2 || state[x][y - 1] == 0||state[x][y - 1] == color)
    		return -1;
    	for(int j = y - 2; j >= 0; j--){
    		if(state[x][j] == 0)
    			return -1;
    		if(state[x][j] == color)
    			return reversiCount;
    		reversiCount ++;
    	}
    	return -1;
    }
    public int checkRight(int color, int x, int y){
    	int reversiCount = 1;
    	if(y > 5 || state[x][y + 1] == 0||state[x][y + 1] == color)
    		return -1;
    	for(int j = y + 2; j < 8; j++){
    		if(state[x][j] == 0)
    			return -1;
    		if(state[x][j] == color)
    			return reversiCount;
    		reversiCount ++;
    	}
    	return -1;
    }
    public int checkUp(int color, int x, int y){
    	int reversiCount = 1;
    	if(x < 2 || state[x - 1][y] == 0 || state[x - 1][y] == color)
    		return -1;
    	for(int i = x - 2; i >= 0; i--){
    		if(state[i][y] == 0)
    			return -1;
    		if(state[i][y] == color)
    			return reversiCount;
    		reversiCount ++;
    	}
    	return -1;
    }
    public int checkDown(int color, int x, int y){
    	int reversiCount = 1;
    	if(x > 5 || state[x + 1][y] == 0 || state[x + 1][y] == color)
    		return -1;
    	for(int i = x + 2; i < 8; i++){
    		if(state[i][y] == 0)
    			return -1;
    		if(state[i][y] == color)
    			return reversiCount;
    		reversiCount ++;
    	}
    	return -1;
    }
    public int checkLeftUp(int color, int x, int y){
    	int reversiCount = 1;
    	if(x < 2 || y < 2 || state[x - 1][y - 1] == 0 || state[x - 1][y - 1] == color)
    		return -1;
    	int i = 2;
    	while(x - i >= 0 && y - i >= 0){
    		if(state[x - i][y - i] == 0)
    			return -1;
    		if(state[x - i][y - i] == color)
    			return reversiCount;
    		reversiCount ++;
    		i ++;
    	}
    	return -1;
    }
    public int checkRightDown(int color, int x, int y){
    	int reversiCount = 1;
    	if(x > 5 || y > 5 || state[x + 1][y + 1] == 0 || state[x + 1][y + 1] == color)
    		return -1;
    	int i = 2;
    	while(x + i < 8 && y + i < 8){
    		if(state[x + i][y + i] == 0)
    			return -1;
    		if(state[x + i][y + i] == color)
    			return reversiCount;
    		reversiCount ++;
    		i ++;
    	}
    	return -1;
    }
    public int checkRightUp(int color, int x, int y){
    	int reversiCount = 1;
    	if(x < 2 || y > 5 || state[x - 1][y + 1] == 0 || state[x - 1][y + 1] == color)
    		return -1;
    	int i = 2;
    	while(x - i >= 0 && y + i < 8){
    		if(state[x - i][y + i] == 0)
    			return -1;
    		if(state[x - i][y + i] == color)
    			return reversiCount;
    		reversiCount ++;
    		i ++;
    	}
    	return -1;
    }
    public int checkLeftDown(int color, int x, int y){
    	int reversiCount = 1;
    	if(x > 5 || y < 2 || state[x + 1][y - 1] == 0 || state[x + 1][y - 1] == color)
    		return -1;
    	int i = 2;
    	while(x + i < 8 && y - i >= 0){
    		if(state[x + i][y - i] == 0)
    			return -1;
    		if(state[x + i][y - i] == color)
    			return reversiCount;
    		reversiCount ++;
    		i ++;
    	}
    	return -1;
    }    
    
    public boolean checkValid(int color, int x, int y){
    	if(state[x][y] != 0){
    		return false;
    	}
    	if(checkLeft(color, x, y) == -1 && checkRight(color, x, y) == -1 && checkUp(color, x, y) == -1 && checkDown(color, x, y) == -1 && checkLeftUp(color, x, y) == -1 && checkLeftDown(color, x, y) == -1 && checkRightUp(color, x, y) == -1 && checkRightDown(color, x, y)== -1)
    		return false;
    	return true;
    }
    public void updateState(int color, int x, int y){
    	state[x][y] = color;
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
    		state[x][y - i] = color;
    		leftRev --;
    		i ++;
    	}
    	i = 1;
    	while(rightRev > 0){
    		state[x][y + i] = color;
    		rightRev --;
    		i ++;
    	}
    	i = 1;
    	while(upRev > 0){
    		state[x - i][y] = color;
    		upRev --;
    		i ++;
    	}
    	i = 1;
    	while(downRev > 0){
    		state[x + i][y] = color;
    		downRev --;
    		i ++;
    	}
    	i = 1;
    	while(leftUpRev > 0){
    		state[x - i][y - i] = color;
    		leftUpRev --;
    		i ++;
    	}
    	i = 1;
    	while(leftDownRev > 0){
    		state[x + i][y - i] = color;
    		leftDownRev --;
    		i ++;
    	}
    	i = 1;
    	while(rightUpRev > 0){
    		state[x - i][y + i] = color;
    		rightUpRev --;
    		i ++;
    	}
    	i = 1;
    	while(rightDownRev > 0){
    		state[x + i][y + i] = color;
    		rightDownRev --;
    		i ++;
    	}		
    }

}
