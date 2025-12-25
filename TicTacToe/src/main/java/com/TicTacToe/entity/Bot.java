package com.TicTacToe.entity;

import com.TicTacToe.enums.BotDifficultyLevel;
import com.TicTacToe.enums.PlayerType;
import com.TicTacToe.strategies.botPlaying.BotPlayingStrategy;
import com.TicTacToe.strategies.botPlaying.BotPlayingStrategyFactor;

public class Bot extends Player{
	BotDifficultyLevel botDifficultyLevel;
	BotPlayingStrategy botPlayingStrategy;
	
	public BotPlayingStrategy getBotPlayingStrategy() {
		return botPlayingStrategy;
	}

	public void setBotPlayingStrategy(BotPlayingStrategy botPlayingStrategy) {
		this.botPlayingStrategy = botPlayingStrategy;
	}

	public Bot(BotDifficultyLevel bl, int id, String name, Symbol symbol){
		super(id, name, PlayerType.BOT, symbol);
		this.botDifficultyLevel = bl;
		this.botPlayingStrategy = BotPlayingStrategyFactor.getBotPlayingStrategy(bl);
	}

	public BotDifficultyLevel getBotDifficultyLevel() {
		return botDifficultyLevel;
	}

	public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
		this.botDifficultyLevel = botDifficultyLevel;
	}
	
	public Move makeMove(Board board) {
		//here, based on strategy , bot makes the move
		System.out.println("Comming here--->" + botPlayingStrategy.getClass());
		Move move = botPlayingStrategy.makeMove(board);
		move.setPlayer(this);
		return move;
	}
	
	
}
