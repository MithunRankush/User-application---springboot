package com.gigin.project.exception;

public class UserAlreadyExistsException extends Exception {
	public UserAlreadyExistsException()
	{
		super("User Already Exists");
	}
}
