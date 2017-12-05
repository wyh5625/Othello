# Othello/Reversi

It's a game between human player and computer player(AI)

### Screenshot


### Game Rule

The game start with AI who plays black color, and human player(you) plays white color. They take turns to put the discs. The detailed game rule is published at: https://en.wikipedia.org/wiki/Reversi

## AI's methodoloyy

### Minimax with α-β pruning

<img width="409" alt="ai" src="https://user-images.githubusercontent.com/26604402/33599538-6b11c668-d9e1-11e7-9ade-e3636b346178.png">

It returns the action corresponding to the best possible move that leads to the outcome with the best utility, under the assumption that the opponent plays to minimize utility. The function MaxValue() and MinValue go through the whole game tree, all the way to the leaves or the states whose depth reach the maximfum look ahead steps.

### Deep first search

According to mechanism of minimax search tree, I choose deep first search tree. 
Data Structure: A 2D integer array, containing the status of the game board with 1 for black disc 	0 for empty and -1 for white disc.
When the AI search for the best move for the current state, it generates all possible moves, and the new states results from each move and do minimax strategy to find which move will give the best move. Since minimax tree will only back up utility values when it reaches the leaves or the maximum depth, it searches the tree in the same way as deep first search.

### Heuristic

#### Coin Parity

This component of the utility function captures the difference in coins between the max player and the min player.This reflect the fact that when player has more discs on the board, the situation is more advantageous for him.

#### Corners captivity

It is impossible to catch discs in corners because such discs are adjacent to only three fields (with no possibility to create catching position). If you put your disc in the corner it will be always stable.

#### Mobility (No. Of possible moves)

It attempts to capture the relative difference between the number of possible moves for the max and the min players, with the intent of restricting the opponent’s mobility and increasing one’s own mobility.

### Look Ahead Steps

Due to the limited computational power, AI just looks ahead 6 steps to make the best move. Larger number makes better performance, you can improve AI by increase number, although 6 gives already gives a good performance for me.

### Future improvement

Database and machine learning will be applied in the future version.
