package cn.com.charmyin.cms.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class PropertiesBeanFactory implements BeanFactory {

	 Map beans = new HashMap();
	 
	 public PropertiesBeanFactory(){
		 this("beans.properties");
	 }
	 
	 public PropertiesBeanFactory(String name){
		 Properties property = new Properties();
		 try {
			property.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(name));
			Set set = property.entrySet();
			for(Iterator iterator = set.iterator();iterator.hasNext();){
				Entry entrySet = (Entry)iterator.next();
				String value = entrySet.getValue().toString();
				Object obj = Class.forName(value).newInstance();
				beans.put(entrySet.getKey().toString(), obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
	
	@Override
	public Object getBean(String name) {
		return beans.get(name);
	}

}
