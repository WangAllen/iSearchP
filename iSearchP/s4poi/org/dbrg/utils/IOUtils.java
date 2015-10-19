package org.dbrg.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class IOUtils {
	public static void writeFile(String filePath, String value, String encoding) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File(filePath));
			fos.write(value.getBytes(encoding));
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
