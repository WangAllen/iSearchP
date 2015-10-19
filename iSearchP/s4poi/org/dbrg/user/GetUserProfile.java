package org.dbrg.user;

import org.dbrg.utils.ConfLoader;

import weibo4j.Account;
import weibo4j.Users;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.User;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONException;
import weibo4j.org.json.JSONObject;

public class GetUserProfile {
	private Users user;
	
	
	
	public static void main(String[] args) {
		// 
		String access_token = ConfLoader.getValue("access_token");
		//String uid = args[1];
		Users um = new Users(access_token);
		try {
			Account am = new Account(access_token);
			JSONObject uuid = am.getUid();
			String uid = null;
			try {
				uid = uuid.getString("uid");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			User user = um.showUserById(uid);
			Log.logInfo(user.toString());
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}
}
