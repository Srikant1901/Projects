package com.TicTacToe.strategies.winnning;

import com.TicTacToe.entity.Board;
import com.TicTacToe.entity.Player;

public interface WinningStrategy {
	
	public boolean checkWinner(Player player, Board board); 
}
