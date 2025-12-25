package com.TicTacToe.entity;

import java.util.Scanner;

import com.TicTacToe.enums.PlayerType;

public class Player {
	private int id;
	
	private String name;
	
	private Symbol symbol;
	
	private PlayerType playerType;
	
	private Scanner scanner ;
	
	public Player(int id, String name, PlayerType playerType, Symbol symbol){
		this.id = id;
		this.name = name;
		this.playerType = playerType;
		this.symbol = symbol;
		this.scanner= new Scanner(System.in);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Symbol getSymbol() {
		return symbol;
	}

	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}

	public PlayerType getPlayerType() {
		return playerType;
	}

	public void setPlayerType(PlayerType playerType) {
		this.playerType = playerType;
	}

	public Move makeMove(Board board) {
		// TODO Auto-generated method stub
		System.out.println("Please mention the row in which yuo want to make the move. ");
		int row = scanner.nextInt();
		System.out.println("Please mention the column in which yuo want to make the move. ");
		int col = scanner.nextInt();
		
		return new Move(new Cell(row, col), this);
	}
}
