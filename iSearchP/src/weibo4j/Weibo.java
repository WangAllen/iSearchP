package weibo4j;

import weibo4j.http.HttpClient;

/**
 * 基类，实现java.io.Serializable接口， 并有两个protected成员HttpClient和access_token。
 * <PRE>
 * HttpClient： weibo4j.http.HttpClient
 * access_token： String
 * </PRE>
 * 
 * @author wail
 *
 */
public class Weibo implements java.io.Serializable {

	private static final long serialVersionUID = 4282616848978535016L;

	protected static HttpClient client = new HttpClient();

//	如果希望自己设置HttpClient的各种参数，可以使用下面的构造方法
//	protected static HttpClient client = new HttpClient(int maxConPerHost, int conTimeOutMs, int soTimeOutMs,
//			int maxSize);
	
	protected String access_token;

}