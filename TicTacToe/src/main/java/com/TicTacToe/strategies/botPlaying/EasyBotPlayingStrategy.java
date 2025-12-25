package com.TicTacToe.strategies.botPlaying;

import java.util.List;

import com.TicTacToe.entity.Board;
import com.TicTacToe.entity.Cell;
import com.TicTacToe.entity.Move;
import com.TicTacToe.enums.CellState;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{

	@Override
	public Move makeMove(Board board) {
		for(List<Cell> row : board.getBoard()) {
			for(Cell cell : row) {
				if(cell.getCellState().equals(CellState.EMPTY)) {
					return new Move(cell , null);
				}
			}
		}
		return null;
	}

}
