package com.TicTacToe.strategies.winnning;

import com.TicTacToe.entity.Board;
import com.TicTacToe.entity.Move;

public interface WinningStrategy {
	
	public boolean checkWinner(Move move, Board board); 
}
