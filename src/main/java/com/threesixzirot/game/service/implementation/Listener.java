package com.threesixzirot.game.service.implementation;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Providing specific responsibilities of a listener(consumer/receiver) player 
 * including to receive a message and to send back the same message with a concatenated
 * number at the end which is a counter for received message from that initiator player 
 * 
 * 
 */

public class Listener extends AbstractPlayer{
	private static final Logger LOGGER = LoggerFactory.getLogger(Listener.class);
	
	private int receivedCounter;
	private int stopCondition;
	private Map<Integer, Integer> listOfSenders;

	public Listener(int[] senders) {
		super();
		listOfSenders = new HashMap<Integer, Integer>();
		for(int i = 0; i < senders.length; i++) {
			this.listOfSenders.put(senders[i], 0);
		}
	}
	
	public Listener(int[] senders, int stopCondition) {
		this(senders);
		this.stopCondition = stopCondition;
	}

	@Override
	public void run() {
		while (true) {
			if (stopCondition > receivedCounter) {
				Map<Integer, String> receivedMessage = this.getMessage();
				if (receivedMessage != null) {
					for (Map.Entry<Integer, String> entry: receivedMessage.entrySet()) {
						listOfSenders.put(entry.getKey(), listOfSenders.get(entry.getKey())+1);
						LOGGER.debug("listener id " + this.playerID + " received message");
						this.sendMessage(entry.getKey(), entry.getValue() + " " + listOfSenders.get(entry.getKey()));
						receivedCounter++;
					}
				}
			} else {
				break;
			}
		}
	}

	public void sendMessageTo(int id, String message, int stopCondition) {
		//Intentionally empty
		return;
	}
}
