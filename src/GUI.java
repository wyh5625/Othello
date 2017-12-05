import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GUI extends JFrame implements ActionListener {
	private JPanel panel;
	public JButton[][] buttons;
	private int[][] board;
	private OthelloGame game;
	AI_Player p;

	public GUI(OthelloGame newGame) {
    	this.game = newGame;
		this.setBoard(game.getBoard());
		initUI();
		this.setVisible(true);
		//System.out.println(game.turn.name + "'s turn(" + game.turn.color + ")");
		//ComputerPlay();
	}

	public GUI(int[][] board, AI_Player player) {
		this.setBoard(board);
		initUI();
		this.setVisible(true);
		p = player;
	}

	public void initUI() {
		panel = new JPanel();

		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(8, 8, 5, 5));

		this.buttons = new JButton[8][8];

		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[i].length; j++) {
				if (this.board[i][j] != 0) {
					this.buttons[i][j] = new JButton(
							this.board[i][j]+"");
					panel.add(this.buttons[i][j]);
				} else {
					this.buttons[i][j] = new JButton(".");
					panel.add(this.buttons[i][j]);
				}
				buttons[i][j].addActionListener(this);
				if (buttons[i][j].getText().equals("1"))
					buttons[i][j].setBackground(Color.BLACK);
				else if (buttons[i][j].getText().equals("-1"))
					buttons[i][j].setBackground(Color.WHITE);
				else if (buttons[i][j].getText().equals("."))
					buttons[i][j].setBackground(Color.LIGHT_GRAY);
			}
			panel.setVisible(true);
		}

		add(panel);

		setTitle("Othello");
		setSize(600, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void setBoard(int[][] board) {
		this.board = board;
	}
	
	public void setButtonUnclickable(){
		for (int i = 0; i < this.board.length; i++) 
			for (int j = 0; j < this.board[i].length; j++) {
				buttons[i][j].setEnabled(false);
			}
	}

	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
				if (buttons[i][j] == e.getSource()) {
					game.Run(i, j);
					if(game.endOfGame()){
						System.out.println("end of game£¡£¡");
						game.showEndGameStatus();
						setButtonUnclickable();
						return; 
					}
					final int x = i;
					final int y = j;
					if(game.turn.name == "Black"){
						// black is computer player
						Thread t = new Thread(){
			    		    public void run(){
			    		    	try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
			    		    	game.Run(x,y);
			    		}};
			    		t.start();
					}
					if(game.endOfGame()){
						System.out.println("end of game£¡£¡");
						game.showEndGameStatus();
						setButtonUnclickable();
						return; 
					}
						
				}
			}
		}
	}


	public void printBoardGUI() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] != 0)
					buttons[i][j].setText(board[i][j] + "");
				if (buttons[i][j].getText().equals("1"))
					buttons[i][j].setBackground(Color.BLACK);
				else if (buttons[i][j].getText().equals("-1"))
					buttons[i][j].setBackground(Color.WHITE);
			}
		}
	}


}
