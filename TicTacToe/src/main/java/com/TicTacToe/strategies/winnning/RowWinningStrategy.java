package com.TicTacToe.strategies.winnning;

import java.util.HashMap;
import java.util.Map;

import com.TicTacToe.entity.Board;
import com.TicTacToe.entity.Move;
import com.TicTacToe.entity.Symbol;

public class RowWinningStrategy implements WinningStrategy{
	private final Map<Integer, HashMap<Symbol, Integer>> mapToStoreCountOfSymbolInEachRow = new HashMap<>();

	@Override
	public boolean checkWinner(Move move, Board board) {
		// for each row, lets store a map having count of the different symbols present.
		// |0| -> {|X|->1, |O| -> 2}
		// |1| -> {|X|->1, |O| -> 2}
		// |2| -> {|X|->1, |O| -> 2}
		int row = move.getCell().getRow();
		Symbol symbol = move.getPlayer().getSymbol();
		if(!mapToStoreCountOfSymbolInEachRow.containsKey(row)) {
			mapToStoreCountOfSymbolInEachRow.put(row, new HashMap<>());
		}
		HashMap<Symbol, Integer> rowMap = mapToStoreCountOfSymbolInEachRow.get(row);
		if(!rowMap.containsKey(symbol)) {
			rowMap.put(symbol, 0);
		}
		rowMap.put(symbol, rowMap.get(symbol)+1);
		
		//checking the winning condition
		if(rowMap.get(symbol) == board.getSize()) {
			return true;
		}
		return false;
	}

	@Override
	public void handleUndo(Board board, Move move) {
		int row = move.getCell().getRow();
		Symbol symbol = move.getPlayer().getSymbol();
		
		Map<Symbol, Integer> rowMap = mapToStoreCountOfSymbolInEachRow.get(row);
		rowMap.put(symbol, rowMap.get(symbol)-1);
	}

}
