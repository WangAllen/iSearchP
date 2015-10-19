package org.dbrg.oauth;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.dbrg.consts.CONSTS;

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
	
	public static String getValue(String key){
		return cprops.getProperty(key);
	}
}
