package com.java1234.model;

/**
 * �û�Model
 * @author 
 *
 */
public class User {

	private int userId;  // �û�ID
	private String userName; // �û���
	private String password; // �û�����
	private int userType; // �û�����
	private int roleId=-1; // ��ɫid
	private String roleName; // ��ɫ����
	private String userDescription; // �û�����
	
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	
	
	
	public User(String userName, String password, int roleId,
			String userDescription) {
		super();
		this.userName = userName;
		this.password = password;
		this.roleId = roleId;
		this.userDescription = userDescription;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getUserDescription() {
		return userDescription;
	}
	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
}
