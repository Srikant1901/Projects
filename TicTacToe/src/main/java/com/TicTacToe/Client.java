package com.TicTacToe;

import java.util.ArrayList;
import java.util.List;

import com.TicTacToe.controller.GameController;
import com.TicTacToe.entity.Bot;
import com.TicTacToe.entity.Game;
import com.TicTacToe.entity.Player;
import com.TicTacToe.entity.Symbol;
import com.TicTacToe.enums.BotDifficultyLevel;
import com.TicTacToe.enums.GameState;
import com.TicTacToe.enums.PlayerType;
import com.TicTacToe.strategies.winnning.ColWinningStrategy;
import com.TicTacToe.strategies.winnning.DiagonalWinningStrategy;
import com.TicTacToe.strategies.winnning.RowWinningStrategy;
import com.TicTacToe.strategies.winnning.WinningStrategy;

public class Client {
	public static void main(String[] args) throws Exception{
		GameController gameController = new GameController();
		System.out.println(" Starting the game---> ");
		try {
			int dimension = 3;
			List<Player> players = new ArrayList<>();
			players.add(new Player(1, "Sri", PlayerType.HUMAN, new Symbol('X')));
//			players.add(new Player(2, "Gpt", PlayerType.BOT, new Symbol('O')));
			players.add(new Bot(BotDifficultyLevel.EASY, 2, "Gpt", new Symbol('O')));
			
			List<WinningStrategy> ws = List.of(
					new ColWinningStrategy(),
					new RowWinningStrategy(),
					new DiagonalWinningStrategy()
					);
			
			Game game = gameController.startGame(dimension, players, ws);
			while(gameController.checkGameState(game).equals(GameState.IN_PROGRESS)) {
				gameController.makeMove(game);
				gameController.displayBoard(game);
			}
			
//			if(gameController.checkGameState(game).equals(GameState.DRAW)) {
//				System.out.print("The game ended in DRAW ");
//			}else if(gameController.checkGameState(game).equals(GameState.SUCCESS)) {
//				Player player = gameController.getWinner(game);
//				System.out.println("Winner is :: " + player.getName());
//			}
			
		}catch(Exception e) {
			System.out.println("Game has stopped abruptly");
			throw e;
		}
//		Game game = gameController.startGame(3, new ArrayList<>(), new ArrayList<>());
//		gameController.displayBoard(game);
//		
//		while(gameController.checkGameState(game).equals(GameState.IN_PROGRESS)) {
//			gameController.makeMove(game);
//			gameController.displayBoard(game);
//		}
		
//		if(gameController.checkGameState(game).equals(GameState.DRAW)) {
//			System.out.print("The game ended in DRAW ");
//		}else if(gameController.checkGameState(game).equals(GameState.SUCCESS)) {
//			Player player = gameController.getWinner(game);
//			System.out.println("Winner is :: " + player.getName());
//		}
	}
	
}

