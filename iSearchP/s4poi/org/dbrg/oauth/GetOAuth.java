package org.dbrg.oauth;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.dbrg.consts.CONSTS;
import org.dbrg.utils.IOUtils;

import weibo4j.Oauth;
import weibo4j.examples.oauth2.Log;
import weibo4j.http.AccessToken;
import weibo4j.model.WeiboException;
import weibo4j.util.BareBonesBrowserLaunch;

/**
 * 获取授权码
 * 
 * @author wail
 *
 */
public class GetOAuth {

	/**
	 * 获取AccessToken
	 * @return
	 */
	public static AccessToken getAccessToken() {
		AccessToken accessToken = null;	// 授权码
		Oauth oauth = new Oauth();	// Weibo授权方法对象
		try {
			// Bare Bones Browser Launch 
			BareBonesBrowserLaunch.openURL(oauth.authorize("code"));
			// 打印获得的url
			System.out.println(oauth.authorize("code"));
			// （提示）将获得的code输入到命令行
			System.out.print("Hit enter when it's done.[Enter]:");
			// 流方式读取输入的code
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String code = null;
			try {
				code = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 输出日志信息
			Log.logInfo("code: " + code);
			
			// 根据code获得accessToken
			accessToken = oauth.getAccessTokenByCode(code);
		} catch (WeiboException e) {
			e.printStackTrace();
			if(401 == e.getStatusCode()){
				Log.logInfo("Unable to get the access token.");
			}else{
				e.printStackTrace();
			}
		}
		// 将accessToken相关信息写入到文件
		writeToFile(accessToken);
		return accessToken;
	}
	
	/**
	 * 将AccessToken相关信息写入到文件保存
	 * 
	 * @param accessToken
	 * @return
	 */
	private static boolean writeToFile(AccessToken accessToken) {		
		// 判断accessToken文件是否存在，若文件存在，则不再进行授权
		File file = new File(CONSTS.filePath);
		if (file.exists() ) {
			// 或者采用覆盖的方式写入
			return false;
		} else {
			// 将授权转变为String，写入到文件中
			String str = "access_token = " + accessToken.getAccessToken() + "\n"
							+ "expireIn = " + accessToken.getExpireIn() + "\n"
							+ "refreshToken = " + accessToken.getRefreshToken() + "\n" 
							+ "uid = " + accessToken.getUid() + "\n";
			// 利用IOUtils写入到文件，文件编码为"UTF-8"
			IOUtils.writeFile(CONSTS.filePath, str, "UTF-8");
		}
		return true;
	}
	
	
}
