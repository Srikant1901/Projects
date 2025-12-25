package com.TicTacToe.strategies.winnning;

import java.util.HashMap;
import java.util.Map;

import com.TicTacToe.entity.Board;
import com.TicTacToe.entity.Move;
import com.TicTacToe.entity.Symbol;

public class ColWinningStrategy implements WinningStrategy{
	private final Map<Integer, HashMap<Symbol, Integer>> mapToStoreCountOfSymbolInEachCol = new HashMap<>();

	@Override
	public boolean checkWinner(Move move, Board board) {
		// for each column, lets store a map having count of the different symbols present.
		// |0| -> {|X|->1, |O| -> 2}
		// |1| -> {|X|->1, |O| -> 2}
		// |2| -> {|X|->1, |O| -> 2}
		int col = move.getCell().getCol();
		Symbol symbol = move.getPlayer().getSymbol();
		if(!mapToStoreCountOfSymbolInEachCol.containsKey(col)) {
			mapToStoreCountOfSymbolInEachCol.put(col, new HashMap<>());
		}
		HashMap<Symbol, Integer> colMap = mapToStoreCountOfSymbolInEachCol.get(col);
		if(!colMap.containsKey(symbol)) {
			colMap.put(symbol, 0);
		}
		colMap.put(symbol, colMap.get(symbol)+1);
		
		//checking the winning condition
		if(colMap.get(symbol) == board.getSize()) {
			return true;
		}
		return false;
	}

}
