package cn.com.charmyin.cms.backend.dao;

import java.util.List;

import cn.com.charmyin.cms.backend.model.Admin;

public interface AdminDao {
	public void addAdmin(Admin admin);
	public Admin findAdminByName(String name);
}
