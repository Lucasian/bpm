package com.lucasian.bpm;

public interface ProcessUserFinder {
	
	String findCurrentUser();
	
	boolean isUserValid(String userId);
	
	boolean isUserAdmin(String userId);
	
}
