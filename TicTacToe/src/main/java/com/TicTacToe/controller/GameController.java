package com.TicTacToe.controller;

import java.util.List;

import com.TicTacToe.entity.Game;
import com.TicTacToe.entity.Player;
import com.TicTacToe.enums.GameState;
import com.TicTacToe.strategies.winnning.WinningStrategy;

public class GameController {
	//here , we are hardcoding the input and sending which calls gamecontroller---->
	
		//Client ---> GameController ----> service/models
		//1.Start the game
		//2.Print the board (display) while the game is IN_PROGRESS
		//		2.1.Make the move 
		//		2.2.Check for the winner -> check the state of game
		//3.Based on Game State ., return the result
				//3.1 DRAW -->mention the draw result
				//3.2 Winner ---> get the winner and return
		
		//4. Also, we can have undo operation anywhere in the game.
		
		public Game startGame(int dimension, List<Player> players, List<WinningStrategy> ws ) throws Exception{//generally exception not thrown to controller
			return Game.gameBuilder()
			.setDimension(dimension)
			.setPlayers(players)
			.setWinningStrategy(ws).build();
			
		}
		
		public void displayBoard(Game game) {
			game.printBoard();
		}
		
		public void makeMove(Game game) {
			game.makeMove();
		}
		
		public GameState checkGameState(Game game) {
			return game.getGameState();
		}
		
		public Player getWinner(Game game) {
			return game.getWinner();
		}
		
		public void undo(Game game) {
			
		}
}
