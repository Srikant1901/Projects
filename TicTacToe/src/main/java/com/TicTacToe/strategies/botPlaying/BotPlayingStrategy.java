package com.TicTacToe.strategies.botPlaying;

import com.TicTacToe.entity.Board;
import com.TicTacToe.entity.Move;

public interface BotPlayingStrategy {
	 public Move makeMove(Board board);
}
