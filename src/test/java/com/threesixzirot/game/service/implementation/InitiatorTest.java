package com.threesixzirot.game.service.implementation;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ LoggerFactory.class })
public class InitiatorTest {

	private static Logger mockLOG;

    @InjectMocks
	Initiator initiator;

    @BeforeClass
    public static void setUp() {
    	mockStatic(LoggerFactory.class);
        mockLOG = mock(Logger.class);
        when(LoggerFactory.getLogger(any(Class.class))).thenReturn(mockLOG);
    }
    
    @SuppressWarnings("deprecation")
	@After
    public void shotDown() {
    	initiator.stop();
    }
    
	@Test
	public void testRun_just_sending_message() {
		// Given
		initiator.sendMessageTo(2, "hi", 5);
		// When
		
		// Then
		initiator.start();
		verify(mockLOG, atLeast(5)).info(any(String.class));
	}
	
	@Test
	public void testRun_sending_and_receiving_message() {
		// Given
		initiator.sendMessageTo(1, "hi", 5);
		// When
		
		// Then
		initiator.start();
		verify(mockLOG, atLeast(5)).info(any(String.class));
	}

}
