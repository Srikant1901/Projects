package com.TicTacToe.strategies.winnning;

import java.util.HashMap;
import java.util.Map;

import com.TicTacToe.entity.Board;
import com.TicTacToe.entity.Move;
import com.TicTacToe.entity.Symbol;

public class DiagonalWinningStrategy implements WinningStrategy{
	private Map<Symbol, Integer> leftDiagonalMap = new HashMap<>();
	private Map<Symbol, Integer> rightDiagonalMap = new HashMap<>();
	@Override
	public boolean checkWinner(Move move, Board board) {
		// left diagonal -> i==j
		//right diagonal -> i+j == N-1
		
		int row = move.getCell().getRow();
		int col = move.getCell().getCol();
		Symbol symbol = move.getPlayer().getSymbol();
		
		if(row == col){
			if(!leftDiagonalMap.containsKey(symbol)) {
				leftDiagonalMap.put(symbol, 0);
			}
			leftDiagonalMap.put(symbol, leftDiagonalMap.get(symbol)+1);
			//check winner
			if(leftDiagonalMap.get(symbol) == board.getSize()) {
				return true;
			}
		}else if(row + col == board.getSize()-1) {
			if(!rightDiagonalMap.containsKey(symbol)) {
				rightDiagonalMap.put(symbol, 0);
			}
			rightDiagonalMap.put(symbol, rightDiagonalMap.get(symbol)+1);
			//check winner
			if(rightDiagonalMap.get(symbol) == board.getSize()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void handleUndo(Board board, Move move) {
		int row = move.getCell().getRow();
		int col = move.getCell().getCol();
		Symbol symbol = move.getPlayer().getSymbol();
		if(row == col) {
			leftDiagonalMap.put(symbol, leftDiagonalMap.get(symbol)-1);
		}else if (row+col == board.getSize()-1) {
			rightDiagonalMap.put(symbol, rightDiagonalMap.get(symbol)-1);
		}
	}
	
}
