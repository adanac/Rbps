package com.java1234.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.java1234.model.PageBean;
import com.java1234.model.Role;
import com.java1234.util.StringUtil;

/**
 * ��ɫDAO
 * @author 
 *
 */
public class RoleDao {

	/**
	 * ͨ��ID��ȡ��ɫ����
	 * @param con ����
	 * @param id ��ɫID
	 * @return
	 * @throws Exception
	 */
	public String getRoleNameById(Connection con,int id)throws Exception{
		String roleName=null;
		String sql="select roleName from t_role where roleId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1,id);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			roleName=rs.getString("roleName");
		}
		return roleName;
	}
	
	/**
	 * ͨ��ID��ȡȨ��ID����
	 * @param con
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String getAuthIdsById(Connection con,int id)throws Exception{
		String authIds=null;
		String sql="select authIds from t_role where roleId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1,id);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			authIds=rs.getString("authIds");
		}
		return authIds;
	}
	
	/**
	 * ��ѯ
	 * @param con
	 * @param pageBean
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public ResultSet roleList(Connection con,PageBean pageBean,Role role)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_role");
		if(StringUtil.isNotEmpty(role.getRoleName())){
			sb.append(" and roleName like '%"+role.getRoleName()+"%'");
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	
	/**
	 * ��ȡ��ѯ����
	 * @param con
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public int roleCount(Connection con,Role role)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from t_role ");
		if(StringUtil.isNotEmpty(role.getRoleName())){
			sb.append(" and roleName like '%"+role.getRoleName()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	
	/**
	 * ��ɫɾ��
	 * @param con
	 * @param delIds
	 * @return
	 * @throws Exception
	 */
	public int roleDelete(Connection con,String delIds)throws Exception{
		String sql="delete from t_role where roleId in ("+delIds+")";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
	
	/**
	 * ��ɫ���
	 * @param con
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public int roleAdd(Connection con,Role role)throws Exception{
		String sql="insert into t_role values(null,?,'',?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, role.getRoleName());
		pstmt.setString(2, role.getRoleDescription());
		return pstmt.executeUpdate();
	}
	
	/**
	 * ��ɫɾ��
	 * @param con
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public int roleUpdate(Connection con,Role role)throws Exception{
		String sql="update t_role set roleName=?,roleDescription=? where roleId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, role.getRoleName());
		pstmt.setString(2, role.getRoleDescription());
		pstmt.setInt(3, role.getRoleId());
		return pstmt.executeUpdate();
	}
	
	/**
	 * ����Ȩ��ID����
	 * @param con
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public int roleAuthIdsUpdate(Connection con,Role role)throws Exception{
		String sql="update t_role set authIds=? where roleId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, role.getAuthIds());
		pstmt.setInt(2, role.getRoleId());
		return pstmt.executeUpdate();
	}
}
