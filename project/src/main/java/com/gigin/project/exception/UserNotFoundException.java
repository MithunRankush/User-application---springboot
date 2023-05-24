package com.gigin.project.exception;

public class UserNotFoundException extends Exception {
       public UserNotFoundException() {
    	   super("user not found");
       }
}
