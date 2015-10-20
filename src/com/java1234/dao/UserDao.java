package com.java1234.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.java1234.model.PageBean;
import com.java1234.model.User;
import com.java1234.util.StringUtil;

/**
 * 用户DAO
 * @author 
 *
 */
public class UserDao {

	/**
	 * 用户登录验证
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User login(Connection con,User user)throws Exception{
		User resultUser=null;
		String sql="select * from t_user where userName=? and password=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			resultUser=new User();
			resultUser.setUserId(rs.getInt("userId"));
			resultUser.setUserName(rs.getString("userName"));
			resultUser.setPassword(rs.getString("password"));
			resultUser.setRoleId(rs.getInt("roleId"));
		}
		return resultUser;
	}
	
	/**
	 * 修改密码
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int modifyPassword(Connection con,User user)throws Exception{
		String sql="update t_user set password=? where userId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, user.getPassword());
		pstmt.setInt(2, user.getUserId());
		return pstmt.executeUpdate();
	}
	
	/**
	 * 用户查询
	 * @param con
	 * @param pageBean
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public ResultSet userList(Connection con,PageBean pageBean,User user)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_user u ,t_role r where u.roleId=r.roleId and u.userType!=1 ");
		if(StringUtil.isNotEmpty(user.getUserName())){
			sb.append(" and u.userName like '%"+user.getUserName()+"%'");
		}
		if(user.getRoleId()!=-1){
			sb.append(" and u.roleId="+user.getRoleId());
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	
	/**
	 * 用户个数查询
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int userCount(Connection con,User user)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from t_user u ,t_role r where u.roleId=r.roleId and u.userType!=1 ");
		if(StringUtil.isNotEmpty(user.getUserName())){
			sb.append(" and u.userName like '%"+user.getUserName()+"%'");
		}
		if(user.getRoleId()!=-1){
			sb.append(" and u.roleId="+user.getRoleId());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	
	/**
	 * 用户添加
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int userAdd(Connection con,User user)throws Exception{
		String sql="insert into t_user values(null,?,?,2,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());
		pstmt.setInt(3, user.getRoleId());
		pstmt.setString(4, user.getUserDescription());
		return pstmt.executeUpdate();
	}
	
	/**
	 * 用户更新
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int userUpdate(Connection con,User user)throws Exception{
		String sql="update t_user set userName=?,password=?,roleId=?,userDescription=? where userId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());
		pstmt.setInt(3, user.getRoleId());
		pstmt.setString(4, user.getUserDescription());
		pstmt.setInt(5, user.getUserId());
		return pstmt.executeUpdate();
	}
	
	/**
	 * 判断用户名是否存在
	 * @param con
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public boolean existUserWithUserName(Connection con,String userName)throws Exception{
		String sql="select * from t_user where userName=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, userName);
		return pstmt.executeQuery().next();
	}
	
	/**
	 * 用户删除
	 * @param con
	 * @param delIds
	 * @return
	 * @throws Exception
	 */
	public int userDelete(Connection con,String delIds)throws Exception{
		String sql="delete from t_user where userId in ("+delIds+")";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
	
	/**
	 * 判断指定角色ID的用户是否存在
	 * @param con
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public boolean existUserWithRoleId(Connection con,String roleId)throws Exception{
		String sql="select * from t_user where roleId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, roleId);
		return pstmt.executeQuery().next();
	}
}
