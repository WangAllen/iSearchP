package test.org.dbrg.utils;

import org.dbrg.utils.IOUtils;

public class TestIOUtils {
	public static void main(String[] args) {
		String filePath = "test.txt";
		String value = "中国人民万岁,hello world,123";
		String encoding = "utf-8";

		IOUtils.writeFile(filePath, value, encoding);

		System.out.println("done!");
	}
}
