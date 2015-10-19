package test.org.dbrg.oauth;

import org.dbrg.oauth.GetOAuth;

import weibo4j.http.AccessToken;

public class TestGetOAuth {
	
	public static void testGetAccessToken() {
		AccessToken accessToken = GetOAuth.getAccessToken();
		System.out.println(accessToken.toString());		
	}
	
	public static void main(String[] args) {
		testGetAccessToken();
	}
}

/*
 * 测试时间：2015-10-17 21:14:25
 * 测试方法：运行为JavaApplication->出现授权页面->将授权页面url后面的code复制粘贴到Console中，[Enter]
 * 测试结果：
 * 授权页面url：https://api.weibo.com/oauth2/default.html?code=0cab9a134becc0089df58b1fd45415fa
 * 
 * AccessToken [
 * accessToken=2.00u49n3BXYoRvCc2a3de434cYiPpZD, 
 * expireIn=157679999, 
 * refreshToken=,
 * uid=1252999410]
 * 
 */