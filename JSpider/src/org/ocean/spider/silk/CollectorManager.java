package org.ocean.spider.silk;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.ocean.shibernate.utils.ClassUtil;
import org.ocean.spider.AException;
import org.ocean.spider.ExceptionType;
import org.ocean.spider.entity.LouPan;
import org.ocean.spider.silk.home.more.FloorHeightCollector;

public class CollectorManager {

	protected List<BasicCollector> collectors = new ArrayList<BasicCollector>();
	
	public void addCollector(BasicCollector collector){
		collectors.add(collector);
	}
	
	public void addAllDefaultCollectors(){
		List<Class<?>> clazzList = ClassUtil.getClasssFromPackage(this.getClass().getPackage().getName());
		if(clazzList == null || clazzList.size()==0){
			System.out.println("no collector found in "+ this.getClass().getPackage().getName());
			return;
		}
		for(Class<?> clazz : clazzList){
			try {
				Object collector =  clazz.newInstance();
				if(collector instanceof BasicCollector){
					collectors.add((BasicCollector)collector);
				}
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void collect(Element root ,LouPan loupan){
		for(BasicCollector collector : collectors){
			String value = "";
			try{
				if(collector instanceof FloorHeightCollector){
					System.out.print("");
				}
				value = collector.getAttrValue(root);
			}catch(AException ex){
				if(ex.getType() == ExceptionType.ValueNotFound){
					continue;
				}
				throw ex;
			}catch(Exception ex){
				throw new RuntimeException("collector "+collector.getAttrName()+" faild on root="+root);
			}
			Field field = null;
			try {
				field = loupan.getClass().getDeclaredField(collector.getAttrName());
				if(field.getType()==float.class){
					field.set(loupan, Float.valueOf(value));
					continue;
				}
				if(field.getType()==long.class){
					field.set(loupan, Long.valueOf(value));
					continue;
				}
				field.set(loupan, value);
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				System.out.println(loupan.name+"."+field.getName()+" not correct");
				throw new RuntimeException(e.getMessage());
			}
		}
	}
}
