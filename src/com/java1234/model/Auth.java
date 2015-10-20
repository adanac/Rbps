package com.java1234.model;

/**
 * 菜单Model
 * @author 
 *
 */
public class Auth {

	private int authId; // 菜单ID
	private String authName; // 菜单名称
	private String authPath; // 菜单路径
	private int parentId; // 父节点
	private String authDescription; // 菜单描述
	private String state; // 菜单状态
	private String iconCls; // 菜单样式
	
	
	
	public Auth() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Auth(String authName, String authPath, String authDescription,
			String iconCls) {
		super();
		this.authName = authName;
		this.authPath = authPath;
		this.authDescription = authDescription;
		this.iconCls = iconCls;
	}


	public int getAuthId() {
		return authId;
	}
	public void setAuthId(int authId) {
		this.authId = authId;
	}
	public String getAuthName() {
		return authName;
	}
	public void setAuthName(String authName) {
		this.authName = authName;
	}
	public String getAuthPath() {
		return authPath;
	}
	public void setAuthPath(String authPath) {
		this.authPath = authPath;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getAuthDescription() {
		return authDescription;
	}
	public void setAuthDescription(String authDescription) {
		this.authDescription = authDescription;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	
	
}
