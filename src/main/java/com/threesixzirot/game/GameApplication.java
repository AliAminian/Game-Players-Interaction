package com.threesixzirot.game;

import com.threesixzirot.game.service.Player;
import com.threesixzirot.game.service.implementation.Initiator;
import com.threesixzirot.game.service.implementation.Listener;

/**
 * Running the project for two following scenarios:
 * 1. 2 initiator
 * 2. 1 listener 
 *
 */
public class GameApplication {
	public static void main(String[] args) {
		int totalMessages = 10; //This parameter is just for stop condition
		
		Player initiator = new Initiator();
		
		int[] senderList = new int[]{initiator.getPlayerID()};
		
		Player listener = new Listener(senderList, totalMessages);
		
		initiator.sendMessageTo(listener.getPlayerID(), "Hello Game from Initiator", totalMessages);
		
		initiator.start();
		listener.start();
		
		/* 

		//for two player sending message and one receiver

		int totalMessages = 10; //This parameter is just for stop condition
		
		Player initiator1 = new Initiator();
		Player initiator2 = new Initiator();
		Player listener = new Listener(new int[]{initiator1.getPlayerID(), initiator2.getPlayerID()}, totalMessages);
		initiator1.sendMessageTo(listener.getPlayerID(), "Hello Game from first Initiator", 5);
		initiator2.sendMessageTo(listener.getPlayerID(), "Hello Game from first Initiator", 5);
		
		initiator1.start();
		initiator2.start();
		listener.start();
		
		*/
		
	}
}
