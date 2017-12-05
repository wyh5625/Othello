import java.util.Scanner;

public class Player {
    public String name;
    public int score;
    int color; // the color of piece that player play, 0 = white, 1 = black;
    public Player(String name, int color){
    	this.name = name;
    	this.color = color;
    }
    public Player(){
    	
    }
    public Move play(){
    	Scanner sc = new Scanner(System.in);
    	int row = sc.nextInt();
		int col = sc.nextInt();
		Move m = new Move(row, col);
		return m;
    }
}
