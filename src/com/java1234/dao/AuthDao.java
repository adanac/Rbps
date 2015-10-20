package com.java1234.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.java1234.model.Auth;
import com.java1234.util.StringUtil;


/**
 * �˵�DAO��
 * @author 
 *
 */
public class AuthDao {

	/**
	 * ��ȡָ�����ڵ��Ȩ�޵Ľڵ�
	 * @param con ����
	 * @param parentId ���ڵ�
	 * @param authIds Ȩ��
	 * @return
	 * @throws Exception
	 */
	public JSONArray getAuthByParentId(Connection con,String parentId,String authIds)throws Exception{
		JSONArray jsonArray=new JSONArray();
		String sql="select * from t_auth where parentId=? and authId in ("+authIds+")";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, parentId);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("id", rs.getInt("authId"));
			jsonObject.put("text", rs.getString("authName"));
			if(!hasChildren(con, rs.getString("authId"), authIds)){
				jsonObject.put("state", "open");
			}else{
				jsonObject.put("state", rs.getString("state"));				
			}
			jsonObject.put("iconCls", rs.getString("iconCls"));
			JSONObject attributeObject=new JSONObject();
			attributeObject.put("authPath", rs.getString("authPath"));
			jsonObject.put("attributes", attributeObject);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	/**
	 * �ж��Ƿ����ӽڵ�
	 * @param con ����
	 * @param parentId ���ڵ�ID
	 * @param authIds Ȩ��ID����
	 * @return
	 * @throws Exception
	 */
	private boolean hasChildren(Connection con,String parentId,String authIds)throws Exception{
		String sql="select * from t_auth where parentId=? and authId in ("+authIds+")";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, parentId);
		ResultSet rs=pstmt.executeQuery();
		return rs.next();
	}
	
	/**
	 * �ݹ��ȡָ��Ȩ�޺͸��ڵ��µ����нڵ�
	 * @param con ����
	 * @param parentId ���ڵ�
	 * @param authIds Ȩ��ID����
	 * @return
	 * @throws Exception
	 */
	public JSONArray getAuthsByParentId(Connection con,String parentId,String authIds)throws Exception{
		JSONArray jsonArray=this.getAuthByParentId(con, parentId,authIds);
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jsonObject=jsonArray.getJSONObject(i);
			if("open".equals(jsonObject.getString("state"))){
				continue;
			}else{
				jsonObject.put("children", getAuthsByParentId(con,jsonObject.getString("id"),authIds));
			}
		}
		return jsonArray;
	}
	
	/**
	 * ���ݸ��ڵ�ID��ȡ��ѡ��˵��ڵ�
	 * @param con ����
	 * @param parentId ���ڵ�
	 * @param authIds Ȩ��ID����
	 * @return
	 * @throws Exception
	 */
	public JSONArray getCheckedAuthByParentId(Connection con,String parentId,String authIds)throws Exception{
		JSONArray jsonArray=new JSONArray();
		String sql="select * from t_auth where parentId=? ";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, parentId);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			JSONObject jsonObject=new JSONObject();
			int authId=rs.getInt("authId");
			jsonObject.put("id", authId);
			jsonObject.put("text", rs.getString("authName"));
			jsonObject.put("state", rs.getString("state"));
			jsonObject.put("iconCls", rs.getString("iconCls"));
			if(StringUtil.existStrArr(authId+"", authIds.split(","))){
				jsonObject.put("checked", true);
			}
			JSONObject attributeObject=new JSONObject();
			attributeObject.put("authPath", rs.getString("authPath"));
			jsonObject.put("attributes", attributeObject);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	/**
	 * ���ݸ��ڵ��Ȩ��ID���ϻ�ȡ���в˵��ڵ�
	 * @param con ����
	 * @param parentId ���ڵ�
	 * @param authIds Ȩ��ID����
	 * @return
	 * @throws Exception
	 */
	public JSONArray getCheckedAuthsByParentId(Connection con,String parentId,String authIds)throws Exception{
		JSONArray jsonArray=this.getCheckedAuthByParentId(con, parentId,authIds);
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jsonObject=jsonArray.getJSONObject(i);
			if("open".equals(jsonObject.getString("state"))){
				continue;
			}else{
				jsonObject.put("children", getCheckedAuthsByParentId(con,jsonObject.getString("id"),authIds));
			}
		}
		return jsonArray;
	}
	
	/**
	 * ���ݸ��ڵ�ID��ȡ���α��˵��ڵ�
	 * @param con ����
	 * @param parentId ���ڵ�
	 * @return
	 * @throws Exception
	 */
	public JSONArray getTreeGridAuthByParentId(Connection con,String parentId)throws Exception{
		JSONArray jsonArray=new JSONArray();
		String sql="select * from t_auth where parentId=? ";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, parentId);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("id", rs.getInt("authId"));
			jsonObject.put("text", rs.getString("authName"));
			jsonObject.put("state", rs.getString("state"));
			jsonObject.put("iconCls", rs.getString("iconCls"));
			jsonObject.put("authPath", rs.getString("authPath"));
			jsonObject.put("authDescription", rs.getString("authDescription"));
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	/**
	 * ���ݸ��ڵ��ȡ���в˵��ڵ�
	 * @param con ����
	 * @param parentId ���ڵ�
	 * @return
	 * @throws Exception
	 */
	public JSONArray getListByParentId(Connection con,String parentId)throws Exception{
		JSONArray jsonArray=this.getTreeGridAuthByParentId(con, parentId);
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jsonObject=jsonArray.getJSONObject(i);
			if("open".equals(jsonObject.getString("state"))){
				continue;
			}else{
				jsonObject.put("children", getListByParentId(con,jsonObject.getString("id")));
			}
		}
		return jsonArray;
	}
	
	/**
	 * ��Ӳ˵�
	 * @param con ����
	 * @param auth Ȩ��
	 * @return
	 * @throws Exception
	 */
	public int authAdd(Connection con,Auth auth)throws Exception{
		String sql="insert into t_auth values(null,?,?,?,?,'open',?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, auth.getAuthName());
		pstmt.setString(2, auth.getAuthPath());
		pstmt.setInt(3, auth.getParentId());
		pstmt.setString(4, auth.getAuthDescription());
		pstmt.setString(5, auth.getIconCls());
		return pstmt.executeUpdate();
	}
	
	/**
	 * ���²˵�
	 * @param con ����
	 * @param auth Ȩ��
	 * @return
	 * @throws Exception
	 */
	public int authUpdate(Connection con,Auth auth)throws Exception{
		String sql="update t_auth set authName=?,authPath=?,authDescription=?,iconCls=? where authId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, auth.getAuthName());
		pstmt.setString(2, auth.getAuthPath());
		pstmt.setString(3, auth.getAuthDescription());
		pstmt.setString(4, auth.getIconCls());
		pstmt.setInt(5, auth.getAuthId());
		return pstmt.executeUpdate();
	}
	
	/**
	 * �ж��Ƿ���Ҷ�ӽڵ�
	 * @param con ����
	 * @param authId Ȩ��ID����
	 * @return
	 * @throws Exception
	 */
	public boolean isLeaf(Connection con,String authId)throws Exception{
		String sql="select * from t_auth where parentId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, authId);
		ResultSet rs=pstmt.executeQuery();
		return !rs.next();
	}
	
	/**
	 * ͨ��ID�޸�״̬
	 * @param con ����
	 * @param state ״̬
	 * @param authId Ȩ��ID
	 * @return
	 * @throws Exception
	 */
	public int updateStateByAuthId(Connection con,String state,String authId)throws Exception{
		String sql="update t_auth set state=? where authId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, state);
		pstmt.setString(2, authId);
		return pstmt.executeUpdate();
	}
	
	/**
	 * ɾ��Ȩ�޲˵�
	 * @param con ����
	 * @param authId Ȩ��ID
	 * @return
	 * @throws Exception
	 */
	public int authDelete(Connection con,String authId)throws Exception{
		String sql="delete from t_auth where authId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, authId);
		return pstmt.executeUpdate();
	}
	
	/**
	 * ��ȡָ�����ڵ��¶��ӽڵ�ĸ���
	 * @param con ����
	 * @param parentId ���ڵ�
	 * @return
	 * @throws Exception
	 */
	public int getAuthCountByParentId(Connection con,String parentId)throws Exception{
		String sql="select count(*) as total from t_auth where parentId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, parentId);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
}
