package org.dbrg.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 从存放accessToken的文件中取得accessToken信息
 * @author wail
 *
 */
public class ConfLoader {
	public ConfLoader(){}
	private static Properties cprops = new Properties();	
	/**
	 * 加载获得的accessToken等属性
	 */
	static {
//	public static void loadProps() {

		try {
			cprops.load(Thread.currentThread()
					.getContextClassLoader().
					getResourceAsStream("accessToken.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("EX111");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("EX222");
		}
		System.out.println("Done!");

	}
	
	/**
	 * 根据key取对应属性值
	 * @param key
	 * @return
	 */
	public static String getValue(String key){
		return cprops.getProperty(key);
	}
}
