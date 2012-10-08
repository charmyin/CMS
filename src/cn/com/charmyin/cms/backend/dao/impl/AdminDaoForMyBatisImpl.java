package cn.com.charmyin.cms.backend.dao.impl;

import org.apache.ibatis.session.SqlSession;

import cn.com.charmyin.cms.backend.dao.AdminDao;
import cn.com.charmyin.cms.backend.model.Admin;
import cn.com.charmyin.cms.utils.MybatisUtil;

public class AdminDaoForMyBatisImpl extends BaseDao implements AdminDao{

	@Override
	public void addAdmin(Admin admin) {
		super.add(admin);		
	}

	@Override
	public Admin findAdminByName(String name) {
		SqlSession session = MybatisUtil.getSession();
		Admin admin = null;
		try {
			admin = (Admin)session.selectOne(Admin.class.getName()+".findAdminByUsername",name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return admin;
	}
	
}
