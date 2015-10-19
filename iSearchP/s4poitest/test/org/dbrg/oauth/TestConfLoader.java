package test.org.dbrg.oauth;

import org.dbrg.oauth.ConfLoader;

public class TestConfLoader {
	public static void testGetValue() {
		/*
		 * keys:
		 * 	access_token
		 * 	expireIn
		 * 	refreshToken
		 * 	uid
		 */
		
		String access_token = ConfLoader.getValue("access_token");
		System.out.println(access_token);
		String expireIn = ConfLoader.getValue("expireIn");
		System.out.println(expireIn);
//		String refreshToken = ConfLoader.getValue("refreshToken");
//		System.out.println(refreshToken);
//		String uid = ConfLoader.getValue("uid");
//		System.out.println(uid);
	}
	
	public static void main(String[] args) {
		testGetValue();
//		ConfLoader.loadProps();
	}
}
