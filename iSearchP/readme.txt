请先填写相关配置：在Config.properties里
client_ID ：appkey                           
client_SERCRET ：app_secret
redirect_URI : 回调地址

文档结构：
src：weibo4j接口程序
examples：weibo4j提供的示例
s4poi：有关调用API获取信息的程序
s4poitest：对s4poi中程序的测试程序

实现功能：
1. 获取用户信息，包括
	-> user id, name, gender, location (city, province, current location(latitude, longitude)), age
	-> microblogs with locations, timestamps
	-> friends' ids
2. 获取位置信息，包括
	-> 