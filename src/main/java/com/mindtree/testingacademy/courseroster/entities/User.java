/**
 * 
 */
package com.mindtree.testingacademy.courseroster.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * @author azureUser
 *
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Entity
@Table(name = "UserDetails")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "User_Id", nullable = false)
	private int userId;

	@Column(name = "Name", nullable = false)
	private String name;

	@Column(name = "UserName", unique = true, nullable = false)
	private String userName;

	@Column(name = "Password", nullable = false)
	private String password;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "Email_Id", nullable = false)
	private String emailId;

	/**
	 * 
	 */
	public User() {
	}

	/**
	 * @param userId
	 * @param userName
	 * @param password
	 * @param emailId
	 */
	public User(String userName, String password, String emailId) {
		super();
		this.userName = userName;
		this.password = password;
		this.emailId = emailId;
	}

	/**
	 * @param name
	 * @param userName
	 * @param password
	 * @param emailId
	 */
	public User(String name, String userName, String password, String emailId) {
		super();
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.emailId = emailId;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 * @param userName
	 * @param password
	 * @param emailId
	 */
	public User(int userId, String userName, String name, String password, String emailId) {
		super();
		this.userId = userId;
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.emailId = emailId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId
	 *            the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

}
