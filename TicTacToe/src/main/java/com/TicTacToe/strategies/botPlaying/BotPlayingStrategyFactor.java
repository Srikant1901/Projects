package com.TicTacToe.strategies.botPlaying;

import com.TicTacToe.enums.BotDifficultyLevel;

public class BotPlayingStrategyFactor {
	//Based on the difficulty level of the bot, we need to fetch the strategy level and give, so we use here factory pattern, where there
	//wud be a method in which based on the difficulty level of the bot , we give the strategy
	
	public static BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel bl) {
		if(BotDifficultyLevel.EASY.equals(bl)) {
			return new EasyBotPlayingStrategy();
		}else if(BotDifficultyLevel.MEDIUM.equals(bl)) {
			return new MediumBotPlayingStrategy();
		}else if(BotDifficultyLevel.HARD.equals(bl)) {
			return new HardBotPlayingStrategy();
		}
		
		return null;
	}
}
