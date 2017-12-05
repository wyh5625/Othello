import java.util.ArrayList;
import static java.lang.Math.*;
public class AI_Player extends Player {
	Evaluator e = new Evaluator();
	int maxDepth;
	int minColor = -1;
	public AI_Player(String name, int color){
    	this.name = name;
    	this.color = color;
    }
	
	public Move play(State state){
    	Move result = null;
    	state.depth = 0;
    	int v;
    	
    	
    	int alpha = -99;
		int beta = 99;
		v = MaxValue(state, alpha, beta);
		for(int i = 0; i< state.actions.size(); i++){
    		
    	}
    	for(int i = 0; i< state.actions.size(); i++){
    		//System.out.println("state's all aciotns: " + state.actions.get(i).value);
    		if(state.actions.get(i).value == v)
    			return state.actions.get(i);
    	}
    	return result;
    	
    	
    	
    }
	// with color of opponent
	public int MinValue(State state, int alpha, int beta){
		if(TerminalTest(state, minColor)){
			//state.showState();
			return (int)Utility(state);
		}
		int v = 99;	// range of utility is -64 to 64
    	state.searchMoves(minColor);
    	for(int i = 0; i<state.actions.size(); i++){
    		//System.out.println(actions.get(i).x+ "& " +actions.get(i).y);
    		State newState = new State(state);
    		newState.depth = state.depth;
    		newState.depth ++;
    		newState.state[state.actions.get(i).x][state.actions.get(i).y] = minColor;
    		state.actions.get(i).value = MaxValue(newState, alpha, beta);
    		v = min(v, state.actions.get(i).value);
    		if (v<=alpha) return v;
    		beta = min(beta, v);
    	}
    	return v;
	}
	// with color of AI
	public int MaxValue(State state, int alpha, int beta){
		if(TerminalTest(state, color)){
			//state.showState();
			return (int)Utility(state);
		}
		int v = -99;	// range of utility is -64 to 64
    	state.searchMoves(color);
    	for(int i = 0; i<state.actions.size(); i++){
    		State newState = new State(state);
    		newState.depth = state.depth;
    		newState.depth ++;
    		newState.updateState(color, state.actions.get(i).x, state.actions.get(i).y);
    		state.actions.get(i).value = MinValue(newState, alpha, beta);
    		v = max(v, state.actions.get(i).value);
    		if (v>=beta){ return v;}
    		alpha = max(alpha, v);
    	}
    	return v;
	}
	public boolean TerminalTest(State state, int color){
		if(!hasValidMove(state, color) || state.depth == 4)
			return true;
		return false;
	}
	public double Utility(State state){
		return e.evaluate(state);
	}
	public boolean hasValidMove(State state,int color){
    	for(int i = 0; i < 8; i++)
    		for(int j = 0; j<8; j++){
    			if(state.checkValid(color, i, j))
    				return true;
    		}
    	return false;
    }
	
}
