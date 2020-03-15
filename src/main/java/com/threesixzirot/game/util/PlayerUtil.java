package com.threesixzirot.game.util;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * Generating unique id for each Player {@link com.threesixzirot.game.service.Player}
 * 
 */
public class PlayerUtil {
	public static final int INVALID_PLAYER_ID = 0;
	private static AtomicInteger id = new AtomicInteger(1);
	
	public static int getId() {
		return id.getAndIncrement();
	}
}
