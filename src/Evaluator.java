
public class Evaluator {
	public double evaluate(State n){
		double result;
		result = 64*(3*CoinParity(n) + 1*Mobility(n) + 2*CornerCaptured(n))/600;
		return result;
	}
	public double CoinParity(State n){
		int tutalCoins = 0;
		int netValue = 0;
		for (int i = 0; i < 8; i++) 
            for (int j = 0; j < 8; j++) {
            	if(n.state[i][j] != 0)
            		tutalCoins ++;
            	netValue += n.state[i][j];
            }
		return 100.0 * netValue / tutalCoins;
	}
	public double Mobility(State n){
		int maxMoveCount = 0;
		int minMoveCount = 0;
		for(int i = 0; i< 8; i++)
			for(int j =0; j< 8; j++){
				if(n.checkValid(1, i, j)){
					maxMoveCount ++;
				}
				if(n.checkValid(-1, i, j)){
					minMoveCount ++;
				}	
			}
		if(maxMoveCount + minMoveCount !=0)
			return 100.0*(maxMoveCount - minMoveCount)/(maxMoveCount + minMoveCount);
		else
			return 0;
	}
	public double CornerCaptured(State n){
		int maxCorners = 0;
		int minCorners = 0;
		if(n.state[0][0] == 1)
			maxCorners ++;
		else if(n.state[0][0] == -1)
			minCorners ++;
		if(n.state[0][7] == 1)
			maxCorners ++;
		else if(n.state[0][7] == -1)
			minCorners ++;
		if(n.state[7][0] == 1)
			maxCorners ++;
		else if(n.state[7][0] == -1)
			minCorners ++;
		if(n.state[7][7] == 1)
			maxCorners ++;
		else if(n.state[7][7] == -1)
			minCorners ++;
		if ( maxCorners + minCorners != 0)
				return 100.0 * (maxCorners - minCorners) / (maxCorners + minCorners);
		else
			return 0;
		
	}
	
	
}
