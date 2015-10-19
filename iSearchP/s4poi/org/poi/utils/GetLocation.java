/**
 * 
 */
package org.poi.utils;

import org.dbrg.utils.ConfLoader;

import weibo4j.Location;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONObject;

/**
 * @author wail
 *
 */
public class GetLocation {
	public Location GetUserCurrentLocation() {
		Location loc = new Location(null);
		return loc;
	}
	
	public GetLocation(String j) {
		String access_token = ConfLoader.getValue("access_token");

		Location l = new Location(access_token);
		try {
			JSONObject json = l.getLocation(j);
			Log.logInfo(json.toString());
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}
}
