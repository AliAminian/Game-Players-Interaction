package com.threesixzirot.game.service.implementation;

import java.util.HashMap;

import org.slf4j.LoggerFactory;

import com.threesixzirot.game.repository.SharedInbox;
import com.threesixzirot.game.service.Player;
import com.threesixzirot.game.util.PlayerUtil;

/**
 * Providing "main and common services" for all type of Player(s) including:
 *  1. accessing shared memory
 *  2. reading message from shared memory
 *  3. sending message to shared memory
 *  4. assigning unique ID for each player
 * see types of Players: 
 * {@link com.threesixzirot.game.service.implementation.Initiator} 
 * and
 * {@link com.threesixzirot.game.service.implementation.Listener}
 * 
 */
public abstract class AbstractPlayer extends Thread implements Player {
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AbstractPlayer.class);

	protected int playerID;
	protected SharedInbox sharedMessage;

	protected AbstractPlayer() {
		this.playerID = PlayerUtil.getId();
		this.sharedMessage = SharedInbox.getInstance();
	}

	protected void sendMessage(int receiver, String message) {
		try {
			int[] addresses = new int[] {this.playerID, receiver};
			sharedMessage.sendMessage(addresses, message);
		} catch (InterruptedException e) {
			LOGGER.error(e.getMessage());
		}
	}

	protected HashMap<Integer, String> getMessage() {
		HashMap<Integer, String> receivedMessages = null;
		try {
			receivedMessages = sharedMessage.getMessage(new int[] {PlayerUtil.INVALID_PLAYER_ID, this.playerID});
		} catch (InterruptedException e) {
			LOGGER.error(e.getMessage());
		}
		return receivedMessages;
	}
	
	@Override
	public int getPlayerID() {
		return this.playerID;
	}
}
