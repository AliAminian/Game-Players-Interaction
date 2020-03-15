package com.threesixzirot.game.repository;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Providing a thread safe data structure for sending/receiving message to/from players
 * including following services:
 *  1. getting message
 *  2. putting message
 *  
 */
public class SharedInbox {
	private static SharedInbox shared_memory = null; 
	private ConcurrentHashMap<int[], String> messages;
	
	public SharedInbox() {
		this.messages = new ConcurrentHashMap<>();
	}
	
	public static SharedInbox getInstance() 
    { 
		if (shared_memory == null) {
			synchronized (SharedInbox.class) {
				if ((shared_memory == null)) {
					 shared_memory = new SharedInbox();
				}
			}
		}
        return shared_memory; 
    } 

	public synchronized void sendMessage(int[] addresses, String message) throws InterruptedException {
		messages.put(addresses, message);
		notify();
	}

	public synchronized HashMap<Integer, String> getMessage(int[] addresses) throws InterruptedException {
		notify();
		
		while (messages.size() == 0) wait(); //No message to get
		
		HashMap<Integer, String> receivedMessages = new HashMap<Integer, String>();
		for (int[] key: this.messages.keySet()) {
			if (addresses[1] == key[1]) {
				String message = (String) messages.get(key);
				receivedMessages.put(key[0], message);
				messages.remove(key);
				break;
			}
		}
		return receivedMessages;
	}
}
