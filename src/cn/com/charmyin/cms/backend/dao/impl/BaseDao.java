package cn.com.charmyin.cms.backend.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.com.charmyin.cms.backend.vo.PagerVO;
import cn.com.charmyin.cms.utils.MybatisUtil;

public class BaseDao {
	
	public void add(Object bean){
		SqlSession session = MybatisUtil.getSession();
		
		try {
			session.insert(bean.getClass().getName()+".add",bean);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally{
			session.close();
		}
	}
	
	public void del(Object bean,int id){
		SqlSession session = MybatisUtil.getSession();
		try{
			session.delete(bean.getClass().getName()+".del",id);
			session.commit();
		}catch(Exception e){
			e.printStackTrace();
			session.rollback();
		}finally{
			session.close();
		}
	}
	
	public void del(Object bean, int[] ids){
		SqlSession session = MybatisUtil.getSession();
		try {
			for(int id : ids){
				session.delete(bean.getClass().getName()+".del",id);
			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		}finally{
			session.close();
		}
	}
	
	public void del(Object bean, String[] ids){
		SqlSession session = MybatisUtil.getSession();
		try {
			for(String id : ids){
				session.delete(bean.getClass().getName()+".del",id);
			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		}finally{
			session.close();
		}
	}
	
	public Object findById(Object bean, int id){
		SqlSession session = MybatisUtil.getSession();
		try {
			Object obj = session.selectOne(bean.getClass().getName()+".findById",id);
			session.commit();
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return null;
	}
	
	public List findAll(Object bean){
		SqlSession session = MybatisUtil.getSession();
		try {
			return session.selectList(bean.getClass()+".findAll");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return null;
	}
	
	public PagerVO findPaginated(Object bean){
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
