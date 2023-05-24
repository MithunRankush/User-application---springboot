package com.gigin.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
@Data
@Entity
public class User {
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private int id;
		private String username;
		private String password;
		private String email;
		private long mbl;
		public User(int id, String username, String password, String email, long mbl) {
			super();
			this.id = id;
			this.username = username;
			this.password = password;
			this.email = email;
			this.mbl = mbl;
		}
		
		
}
