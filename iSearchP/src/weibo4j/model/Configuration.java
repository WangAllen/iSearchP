/**
 * <p> Copyright (c) 2007-2009, Yusuke Yamamoto
 * <p> All rights reserved.
 * 
 * <p> Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *	<p> Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *  <p> Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *  <p> Neither the name of the Yusuke Yamamoto nor the
 *  	names of its contributors may be used to endorse or promote products
 *      derived from this software without specific prior written permission.
 *      
 *  <p> THIS SOFTWARE IS PROVIDED BY Yusuke Yamamoto ``AS IS'' AND ANY 
 *  EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL Yusuke Yamamoto BE LIABLE FOR ANY
 *  DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package weibo4j.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.AccessControlException;
import java.util.Properties;

/**
 * 封装了weibo4j的一些默认配置属性
 * 
 * @author Yusuke Yamamoto - yusuke at mac.com
 */
public class Configuration {
    private static Properties defaultProperty;

    static {
        init();
    }

    /*package*/ static void init() {
        defaultProperty = new Properties();
        defaultProperty.setProperty("weibo4j.debug", "true");
//        defaultProperty.setProperty("weibo4j.source", Weibo.CONSUMER_KEY);
        //defaultProperty.setProperty("weibo4j.clientVersion","");
        defaultProperty.setProperty("weibo4j.clientURL", "http://open.t.sina.com.cn/-{weibo4j.clientVersion}.xml");
        defaultProperty.setProperty("weibo4j.http.userAgent", "weibo4j http://open.t.sina.com.cn/ /{weibo4j.clientVersion}");
        //defaultProperty.setProperty("weibo4j.user","");
        //defaultProperty.setProperty("weibo4j.password","");
        defaultProperty.setProperty("weibo4j.http.useSSL", "false");
        //defaultProperty.setProperty("weibo4j.http.proxyHost","");
        defaultProperty.setProperty("weibo4j.http.proxyHost.fallback", "http.proxyHost");
        //defaultProperty.setProperty("weibo4j.http.proxyUser","");
        //defaultProperty.setProperty("weibo4j.http.proxyPassword","");
        //defaultProperty.setProperty("weibo4j.http.proxyPort","");
        defaultProperty.setProperty("weibo4j.http.proxyPort.fallback", "http.proxyPort");
        defaultProperty.setProperty("weibo4j.http.connectionTimeout", "20000");
        defaultProperty.setProperty("weibo4j.http.readTimeout", "120000");
        defaultProperty.setProperty("weibo4j.http.retryCount", "3");
        defaultProperty.setProperty("weibo4j.http.retryIntervalSecs", "10");
        //defaultProperty.setProperty("weibo4j.oauth.consumerKey","");
        //defaultProperty.setProperty("weibo4j.oauth.consumerSecret","");
        defaultProperty.setProperty("weibo4j.async.numThreads", "1");
        defaultProperty.setProperty("weibo4j.clientVersion", Version.getVersion());
        try {
            // Android platform should have dalvik.system.VMRuntime in the classpath.
            // @see http://developer.android.com/reference/dalvik/system/VMRuntime.html
            Class.forName("dalvik.system.VMRuntime");
            defaultProperty.setProperty("weibo4j.dalvik", "true");
        } catch (ClassNotFoundException cnfe) {
            defaultProperty.setProperty("weibo4j.dalvik", "false");
        }
        DALVIK = getBoolean("weibo4j.dalvik");
        String t4jProps = "weibo4j.properties";
        boolean loaded = loadProperties(defaultProperty, "." + File.separatorChar + t4jProps) ||
                loadProperties(defaultProperty, Configuration.class.getResourceAsStream("/WEB-INF/" + t4jProps)) ||
                loadProperties(defaultProperty, Configuration.class.getResourceAsStream("/" + t4jProps));
    }

    private static boolean loadProperties(Properties props, String path) {
        try {
            File file = new File(path);
            if(file.exists() && file.isFile()){
                props.load(new FileInputStream(file));
                return true;
            }
        } catch (Exception ignore) {
        }
        return false;
    }

    private static boolean loadProperties(Properties props, InputStream is) {
        try {
            props.load(is);
            return true;
        } catch (Exception ignore) {
        }
        return false;
    }

    private static boolean DALVIK;


    public static boolean isDalvik() {
        return DALVIK;
    }

    public static boolean useSSL() {
        return getBoolean("weibo4j.http.useSSL");
    }
    public static String getScheme(){
        return useSSL() ? "https://" : "http://";
    }

    public static String getCilentVersion() {
        return getProperty("weibo4j.clientVersion");
    }

    public static String getCilentVersion(String clientVersion) {
        return getProperty("weibo4j.clientVersion", clientVersion);
    }

    public static String getSource() {
        return getProperty("weibo4j.source");
    }

    public static String getSource(String source) {
        return getProperty("weibo4j.source", source);
    }

    /**
     * 代理服务器
     * @return
     */
    public static String getProxyHost() {
        return getProperty("weibo4j.http.proxyHost");
    }

    public static String getProxyHost(String proxyHost) {
        return getProperty("weibo4j.http.proxyHost", proxyHost);
    }

    /**
     * weibo4j.http.proxyUser
     * @return
     */
    public static String getProxyUser() {
        return getProperty("weibo4j.http.proxyUser");
    }

    public static String getProxyUser(String user) {
        return getProperty("weibo4j.http.proxyUser", user);
    }

    public static String getClientURL() {
        return getProperty("weibo4j.clientURL");
    }

    public static String getClientURL(String clientURL) {
        return getProperty("weibo4j.clientURL", clientURL);
    }

    /**
     * weibo4j.http.proxyPassword
     * @return
     */
    public static String getProxyPassword() {
        return getProperty("weibo4j.http.proxyPassword");
    }

    public static String getProxyPassword(String password) {
        return getProperty("weibo4j.http.proxyPassword", password);
    }

    /**
     * 获取端口号
     * @return
     */
    public static int getProxyPort() {
        return getIntProperty("weibo4j.http.proxyPort");
    }

    public static int getProxyPort(int port) {
        return getIntProperty("weibo4j.http.proxyPort", port);
    }

    public static int getConnectionTimeout() {
        return getIntProperty("weibo4j.http.connectionTimeout");
    }

    public static int getConnectionTimeout(int connectionTimeout) {
        return getIntProperty("weibo4j.http.connectionTimeout", connectionTimeout);
    }

    public static int getReadTimeout() {
        return getIntProperty("weibo4j.http.readTimeout");
    }

    public static int getReadTimeout(int readTimeout) {
        return getIntProperty("weibo4j.http.readTimeout", readTimeout);
    }

    public static int getRetryCount() {
        return getIntProperty("weibo4j.http.retryCount");
    }

    public static int getRetryCount(int retryCount) {
        return getIntProperty("weibo4j.http.retryCount", retryCount);
    }

    public static int getRetryIntervalSecs() {
        return getIntProperty("weibo4j.http.retryIntervalSecs");
    }

    public static int getRetryIntervalSecs(int retryIntervalSecs) {
        return getIntProperty("weibo4j.http.retryIntervalSecs", retryIntervalSecs);
    }

    public static String getUser() {
        return getProperty("weibo4j.user");
    }

    public static String getUser(String userId) {
        return getProperty("weibo4j.user", userId);
    }

    public static String getPassword() {
        return getProperty("weibo4j.password");
    }

    public static String getPassword(String password) {
        return getProperty("weibo4j.password", password);
    }

    public static String getUserAgent() {
        return getProperty("weibo4j.http.userAgent");
    }

    public static String getUserAgent(String userAgent) {
        return getProperty("weibo4j.http.userAgent", userAgent);
    }

    public static String getOAuthConsumerKey() {
        return getProperty("weibo4j.oauth.consumerKey");
    }

    public static String getOAuthConsumerKey(String consumerKey) {
        return getProperty("weibo4j.oauth.consumerKey", consumerKey);
    }

    public static String getOAuthConsumerSecret() {
        return getProperty("weibo4j.oauth.consumerSecret");
    }

    public static String getOAuthConsumerSecret(String consumerSecret) {
        return getProperty("weibo4j.oauth.consumerSecret", consumerSecret);
    }

    public static boolean getBoolean(String name) {
        String value = getProperty(name);
        return Boolean.valueOf(value);
    }

    /**
     * 属性作为int型获取
     * 
     * @param name
     * @return
     */
    public static int getIntProperty(String name) {
        String value = getProperty(name);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            return -1;
        }
    }

    public static int getIntProperty(String name, int fallbackValue) {
        String value = getProperty(name, String.valueOf(fallbackValue));
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            return -1;
        }
    }

    public static long getLongProperty(String name) {
        String value = getProperty(name);
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException nfe) {
            return -1;
        }
    }

    /**
     * 根据name获取属性
     * 
     * @param name
     * @return
     */
    public static String getProperty(String name) {
        return getProperty(name, null);
    }

    
    /**
     * 根据参数name和fallbackValue获取属性，若找不到name属性，则将fallbackValue作为默认属性返回。
     * @param name
     * @param fallbackValue
     * @return
     */
    public static String getProperty(String name, String fallbackValue) {
        String value;
        try {
            value = System.getProperty(name, fallbackValue);
            if (null == value) {
                value = defaultProperty.getProperty(name);
            }
            if (null == value) {
                String fallback = defaultProperty.getProperty(name + ".fallback");
                if (null != fallback) {
                    value = System.getProperty(fallback);
                }
            }
        } catch (AccessControlException ace) {
            // Unsigned applet cannot access System properties
            value = fallbackValue;
        }
        return replace(value);
    }

    /**
     * @param value
     * @return
     */
    private static String replace(String value) {
        if (null == value) {	// value为空，直接返回
            return value;
        }
        String newValue = value;
        int openBrace = 0;	// 标志"{"位置
        if (-1 != (openBrace = value.indexOf("{", openBrace))) {	// 从头开始的第一个"{"位置
            int closeBrace = value.indexOf("}", openBrace);	// 从第一个"{"开始的第一个"}"位置
            if (closeBrace > (openBrace + 1)) {	// 第一对“{}”中有内容
                String name = value.substring(openBrace + 1, closeBrace);	// 获取{}中的内容作为name
                if (name.length() > 0) {
                	// 将字符串中的name替换成Configuration中配置的属性值name对应的值
                    newValue = value.substring(0, openBrace) + getProperty(name)
                            + value.substring(closeBrace + 1);

                }
            }
        }
        // 一直替换到value和newValue相同为止
        if (newValue.equals(value)) {
            return value;
        } else {
            return replace(newValue);
        }
    }

    public static int getNumberOfAsyncThreads() {
        return getIntProperty("weibo4j.async.numThreads");
    }

    public static boolean getDebug() {
        return getBoolean("weibo4j.debug");

    }
}
