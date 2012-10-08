package cn.com.charmyin.cms.backend.view;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.charmyin.cms.utils.BeanFactory;

public class BaseServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			BeanFactory factory = (BeanFactory)getServletContext().getAttribute(InitBeanFactoryServlet.INIT_FACTORY_NAME);
			for(Method method : this.getClass().getMethods()){
				if(method.getName().startsWith("set")){
					String beanName = method.getName().substring(3);
					Object obj = factory.getBean(beanName);
					method.invoke(this, obj);
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		super.service(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		process(req, resp);
	}
	
	protected void process(HttpServletRequest req, HttpServletResponse resp){
		try {
			//获得method
			String method = req.getParameter("method");
			if(method==null || method.trim().isEmpty()){
				//donothing 执行默认的doget dopost方法
			}else{
				Method m = this.getClass().getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
				m.invoke(this, req,resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

 
	
	
}
