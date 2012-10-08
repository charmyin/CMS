package cn.com.charmyin.cms.backend.dao;

import java.io.File;
import java.util.Iterator;

import junit.framework.TestCase;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import cn.com.charmyin.cms.backend.model.Admin;
import cn.com.charmyin.cms.utils.MybatisUtil;
import cn.com.charmyin.cms.utils.PropertiesBeanFactory;

public class AdminDaoTest extends TestCase{
	Logger logger =Logger.getLogger(AdminDaoTest.class);
	public void Main(){
		Admin admin = new Admin();
		admin.setUsername("bdddbbb");
		admin.setPassword("pdddppp");
		logger.debug("hello");
		PropertiesBeanFactory factory = new PropertiesBeanFactory();
		//factory.getBean("AdminDao");
		//AdminDao adfmbi = new AdminDaoForMyBatisImpl();
		AdminDao adfmbi = (AdminDao)factory.getBean("AdminDao");
		adfmbi.addAdmin(admin);
	}
	
	public void FindByName(){
		SqlSession session = MybatisUtil.getSession();
		Admin admin = null;
		try {
			admin = (Admin)session.selectOne(Admin.class.getName()+".findAdminByUsername","bdddbbb");
			logger.debug(admin.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public void testChangeName(){
		try {
			Iterator<File> it = FileUtils.iterateFiles(new File("J:/music/zky"), new String[]{"mp3"}, false);
			for(File file;it.hasNext();){
				file = it.next();
				String name = file.getName();
			StringBuilder sb = new StringBuilder("");
//				if(sb.toString().startsWith("0")){
//					 sb = new StringBuilder(sb.substring(1));
//					 
//				}
				
				String[] strii = name.split("\\.");
				sb.append(strii[0]);
				sb.append(" 赵匡胤.mp3");
				//sb.append(strii[1]);
				file.renameTo(new File("J:/music/zky/"+sb.toString()));
				logger.debug(sb);
				 
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
