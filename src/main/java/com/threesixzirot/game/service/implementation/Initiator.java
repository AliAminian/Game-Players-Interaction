package com.threesixzirot.game.service.implementation;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Providing specific responsibilities of an initiator(sender/producer) player 
 * including to send a message and to receive the same message with a concatenated 
 * number at the end which consumer (listener/receiver) player has attached 
 * 
 */
public class Initiator extends AbstractPlayer{
	private static final Logger LOGGER = LoggerFactory.getLogger(Initiator.class); 
	
	private int stopCondition;
	private int sendingMessageCount;
	private String message;
	private int receiver;

	public Initiator() {
		super();
	}
	
	public void sendMessageTo(int id, String message, int stopCondition) {
		this.stopCondition = stopCondition;
		this.sendingMessageCount = stopCondition;
		this.message = message;
		this.receiver = id;
	}

	@Override
	public void run() {
		while (true) {
			if (this.sendingMessageCount > 0) {
				this.sendMessage(this.receiver, this.message);
				LOGGER.info("Initiator id " + this.playerID + " sent message");
				this.sendingMessageCount--;
			}

			if (stopCondition > 0) {
				HashMap<Integer, String> receivedMessage = this.getMessage();
				if (receivedMessage != null) {
					for (Map.Entry<Integer, String> entry: receivedMessage.entrySet()) {
						LOGGER.info("Initiator id " + this.playerID + " received back message: [" + entry.getValue() + "]");
						stopCondition--;
					}
					
				}
			} else {
				break;
			}
		}
	}
}
