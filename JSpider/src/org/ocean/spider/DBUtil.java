package org.ocean.spider;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.cfg.AvailableSettings;
import org.ocean.shibernate.SessionFactoryBuilder;

public class DBUtil {

	public static void init(){
		Map<String,String> settings = new HashMap<String , String>();
		settings.put(AvailableSettings.URL, "jdbc:mysql://localhost:3306/ihouse?characterEncoding=utf-8");
		settings.put(AvailableSettings.USER, "root");
		settings.put(AvailableSettings.PASS, "");
		settings.put(AvailableSettings.SHOW_SQL, "true");
		settings.put(AvailableSettings.DRIVER, "com.mysql.jdbc.Driver");
		settings.put(AvailableSettings.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
		settings.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");
		settings.put(AvailableSettings.HBM2DDL_AUTO, "update");
		settings.put("annotated.packages", "org.ocean.spider.entity");
		SessionFactoryBuilder.applySettings(settings);
	}
}
