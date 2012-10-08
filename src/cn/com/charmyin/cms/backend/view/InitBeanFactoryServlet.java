package cn.com.charmyin.cms.backend.view;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import cn.com.charmyin.cms.utils.BeanFactory;
import cn.com.charmyin.cms.utils.PropertiesBeanFactory;

public class InitBeanFactoryServlet extends HttpServlet {
	public Logger logger = Logger.getLogger(this.getClass());
	public final static String INIT_FACTORY_NAME = "_my_bean_factory";
	@Override
	public void init() throws ServletException {
		
		String beanLocation = this.getInitParameter("beansConfigLocation");
		logger.debug("beanConfigLocation:"+beanLocation+" 已经加载");
		
		BeanFactory factory;
		if(beanLocation!=null){
			factory = new PropertiesBeanFactory(beanLocation);
		}else{
			factory = new PropertiesBeanFactory();
		}
		
		getServletContext().setAttribute(INIT_FACTORY_NAME,factory);
		logger.debug("servletContext中注入factory");
		
		logger.debug("InitBeanFactoryServlet加载完成");
	}
	
	

}
