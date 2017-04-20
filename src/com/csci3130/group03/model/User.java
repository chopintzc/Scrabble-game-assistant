package com.csci3130.group03.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Creates a user instance that will be propagated in the database
 * 
 * 
 * @author Eric Desjardins, Justin Tan, Stanford Lockhart
 * @since 2016-06-23
 * @version 2.0
 * 
 */
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private String username;

	@NotNull
	@Size(min = 8, max = 16)
	private String password;

	@OneToOne
	private Stats stats;

	@Transient
	private boolean AuthenticationStatus;

	/**
	 * Create a User DB instance
	 * 
	 */
	public User() {

	}

	/**
	 * Access the Stats table associated with a given user
	 * 
	 * @return
	 */
	public Stats getStats() {
		return stats;
	}

	/**
	 * Associate a Stats table with a user
	 * 
	 * @param stats
	 */
	public void setStats(Stats stats) {
		this.stats = stats;
	}

	/**
	 * Insert new User in the database
	 * 
	 * @param username
	 * @param password
	 */
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.AuthenticationStatus = false;
	}
	
	/**
	 * 
	 * @return true if a user is authenticated and can access the app
	 */
	public boolean getAuthenticationStatus() {
		return AuthenticationStatus;
	}

	/**
	 * Set a user to a state of being authenticated
	 * 
	 * @param authenticationStatus
	 */
	public void setAuthenticationStatus(boolean authenticationStatus) {
		AuthenticationStatus = authenticationStatus;
	}

	/**
	 * 
	 * @return the user's username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Assign a username to a given user
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 
	 * @return the user's password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Assign a password to a given user
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
