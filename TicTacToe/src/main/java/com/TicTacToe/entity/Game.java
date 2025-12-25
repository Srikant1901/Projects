package com.TicTacToe.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.TicTacToe.enums.CellState;
import com.TicTacToe.enums.GameState;
import com.TicTacToe.enums.PlayerType;
import com.TicTacToe.exceptions.DuplicateSymbolOptedException;
import com.TicTacToe.exceptions.InvalidBotCountException;
import com.TicTacToe.exceptions.InvalidPlayerCountException;
import com.TicTacToe.strategies.winnning.WinningStrategy;

public class Game {
	private Board board;
	
	private Player winner;
	
	private List<Move> moves;
	
	private List<Player> players;
	
	private GameState gameState;
	
	private int nextPlayerMoveIndex;
	
	private List<WinningStrategy> winningStrategy;
	
	private Game (int dimension, List<Player> players, List<WinningStrategy> winningStrategy) {
		this.board = new Board(dimension);
		this.players = players;
		this.winningStrategy = winningStrategy;
		this.gameState = GameState.IN_PROGRESS;
		this.nextPlayerMoveIndex = 0;
		this.winner = null;
		this.moves = new ArrayList<>();
	}
	
	public static Builder gameBuilder() {
		return new Builder();
	}
	
	public static class Builder{
		private int dimension;
		private List<Player> players;
		private List<WinningStrategy> winningStrategy;
		public int getDimension() {
			return dimension;
		}
		public Builder setDimension(int dimension) {
			this.dimension = dimension;
			return this;
		}
		public List<Player> getPlayers() {
			return players;
		}
		public Builder setPlayers(List<Player> players) {
			this.players = players;
			return this;
		}
		public List<WinningStrategy> getWinningStrategy() {
			return winningStrategy;
		}
		public Builder setWinningStrategy(List<WinningStrategy> winningStrategy) {
			this.winningStrategy = winningStrategy;
			return this;
		}
		
		public void validate()throws Exception{
			//validate bot count
			//validate player count
			
			int botCount  = 0;
			for(Player p : players) {
				if(p.getPlayerType().equals(PlayerType.BOT)) {
					botCount++;
				}
			}
			if(botCount>1) {
				throw new InvalidBotCountException(); 
			}
			
			if(players.size() != dimension - 1) {
				throw new InvalidPlayerCountException();
			}
			
			//validate unique symbol for each player
//			List<Symbol> s = players.stream().map( p -> p.getSymbol()).collect(Collectors.toList());
//			
//			Set<Symbol> s1 = new HashSet<>();
//			s1.addAll(s);
//			
//			if(s1.size() < s.size()) {
//				throw new DuplicateSymbolOptedException();
//			}
			
			Map<Character, Integer> symbolCounts = new HashMap<>();
			for(Player p : players) {
				if(!symbolCounts.containsKey(p.getSymbol().getSymbol())) {
					symbolCounts.put(p.getSymbol().getSymbol(), 0);
				}else {
					throw new DuplicateSymbolOptedException();
				}
			}

		}
		
		public Game build()throws Exception{
			 validate();
			 return new Game(this.dimension, this.players, this.winningStrategy);
		}
	}


	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

	public List<Move> getMoves() {
		return moves;
	}

	public void setMoves(List<Move> moves) {
		this.moves = moves;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public int getNextPlayerMoveIndex() {
		return nextPlayerMoveIndex;
	}

	public void setNextPlayerMoveIndex(int nextPlayerMoveIndex) {
		this.nextPlayerMoveIndex = nextPlayerMoveIndex;
	}

	public List<WinningStrategy> getWinningStrategy() {
		return winningStrategy;
	}

	public void setWinningStrategy(List<WinningStrategy> winningStrategy) {
		this.winningStrategy = winningStrategy;
	}

	public void printBoard() {
		// TODO Auto-generated method stub
		board.printBoard();
	}
	public boolean validateMove(Move move) {
		int row = move.getCell().getRow();
		int col = move.getCell().getCol();
		
		if(row >= board.getSize()) {
			return false;
		}
		if(col >= board.getSize()) {
			return false;
		}
		
		return board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY) ;
	}
	public void makeMove() {
		Player currentMovePlayer = players.get(nextPlayerMoveIndex);
		System.out.println("index of player---> " + nextPlayerMoveIndex + " player----> " + currentMovePlayer.getClass().getName() +"_" + currentMovePlayer.getPlayerType());
		System.out.println("It is " + currentMovePlayer.getName() + "'s turn. Please make your move!! ");
		Move move = currentMovePlayer.makeMove(board);
		//the made move can be valid or not , so we have to validate that also!
		if(!validateMove(move)) {
			System.out.println("Invalid Move! Please try again");
			return;
		}
		
		int row = move.getCell().getRow();
		int col = move.getCell().getCol();
		
		Cell cellToBeUpdated = board.getBoard().get(row).get(col);
		cellToBeUpdated.setCellState(CellState.FILLED);
		cellToBeUpdated.setPlayer(currentMovePlayer);
		
		Move finalMove = new Move(cellToBeUpdated, currentMovePlayer);
		moves.add(finalMove);
		
		nextPlayerMoveIndex += 1;
		nextPlayerMoveIndex %= players.size();
		
		if(checkWinner(board, finalMove)) {
			gameState = GameState.SUCCESS;
			winner = currentMovePlayer;
		}else if(moves.size() == board.getSize()* board.getSize()){
			gameState = GameState.DRAW;
		}
	}
	
	public boolean checkWinner(Board board, Move move) {
		// go to each winning strategy and see if it has won with any of the followed strategy.
		for(WinningStrategy ws: winningStrategy) {
			System.out.println("Came here in chk outside if!!" + ws.getClass().getName() );
			if(ws.checkWinner(move, board)) {
				System.out.println("Came here in chk!!");
				return true;
			}
		}
		return false;
	}

	public void undo() {
		if(moves.size()==0) {
			System.out.println("No moves has been done , cant undo!! ");
			return;
		}
		
		Move move = moves.get(moves.size() -1);
		moves.remove(move);
		Cell cell = move.getCell();
		
		cell.setPlayer(null);
		cell.setCellState(CellState.EMPTY);
		
		nextPlayerMoveIndex -=1;
		nextPlayerMoveIndex = (nextPlayerMoveIndex + players.size()) % players.size();
		for(WinningStrategy w : winningStrategy) {
			w.handleUndo(board, move);
		}
	}

}
