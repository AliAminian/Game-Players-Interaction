package com.threesixzirot.game.service;


/**
 * Main interface for the business with auto generating unique id for each Player
 * 
 */
public interface Player {
	public void sendMessageTo(int id, String message, int stopCondition);
	public int getPlayerID();
	public void start(); //for multithreading
}
