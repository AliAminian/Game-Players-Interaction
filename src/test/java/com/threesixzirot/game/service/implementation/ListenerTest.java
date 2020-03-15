package com.threesixzirot.game.service.implementation;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import java.util.HashMap;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.threesixzirot.game.repository.SharedInbox;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ LoggerFactory.class })
public class ListenerTest {

	@Mock
	private SharedInbox sharedInbox;
	
	private static Logger mockLOG;

	@InjectMocks
	private Listener listener = new Listener(new int[] { 1 }, 2);

	@BeforeClass
	public static void setUp() {
		mockStatic(LoggerFactory.class);
		mockLOG = mock(Logger.class);
		when(LoggerFactory.getLogger(any(Class.class))).thenReturn(mockLOG);
	}

	@SuppressWarnings("deprecation")
	@After
	public void shotDown() {
		listener.stop();
	}

	@Test
	public void testRun_just_receiving_message() throws InterruptedException {
		// Given
		HashMap<Integer, String> messages = new HashMap<Integer, String>();
		messages.put(1, "hi");
		messages.put(1, "how");
		
		
		// When
		when(sharedInbox.getMessage(any(int[].class))).thenReturn(messages);
		// Then
		listener.start();
		verify(mockLOG, atLeast(2)).debug(any(String.class));
	}

}
