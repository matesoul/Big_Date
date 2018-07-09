package com.lsy.archiver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Archiver {

	/**
	 * 文件归档
	 * 
	 * @param xar  归档文件
	 * @param file 要归档的文件
	 */
	public static void appendFile(String xar, String file) {
		try {
			FileOutputStream fos = new FileOutputStream(xar, true);

			// 1.文件名长度
			File f = new File(file);
			String filename = f.getName();
			byte[] fileNameBytes = filename.getBytes();
			byte[] fileNameLenBytes = DataUtils.int2bytes(fileNameBytes.length);
			fos.write(fileNameLenBytes);

			// 2.文件名内容
			fos.write(fileNameBytes);

			// 3.文件长度
			int len = (int) f.length();
			byte[] lenBytes = DataUtils.int2bytes(len);
			fos.write(lenBytes);

			// 4.文件内容
			FileInputStream fis = new FileInputStream(f);
			byte[] buf = new byte[1024];
			int len0 = 0;
			while ((len0 = fis.read(buf)) != -1) {
				fos.write(buf, 0, len0);
			}
			fis.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 归档目录下所有文件
	 * 
	 * @param xar 归档文件
	 * @param dir 要归档的目录
	 */
	public static void appendFiledir(String xar, String dir) {
		File filedir = new File(dir);
		if (filedir.isFile()) {
			appendFile(xar, dir);
		} else {
			String[] filelist = filedir.list();
			for (String file : filelist) {
				File afile = new File(filedir, file);
				if (afile.isDirectory()) {
					continue;
				}
				appendFile(xar, afile.toString());
			}
		}
	}

	/**
	 * 解档
	 * 
	 * @param xar 归档文件
	 * @param dir 解压目录
	 */
	private static void unarchive(String xar, String dir) {
		try {
			FileInputStream fis = new FileInputStream(xar);
			while (true) {

				// 1.读取文件名长度
				byte[] byte4 = new byte[4];
				int len = fis.read(byte4);
				// 文件结束
				if (len == -1) {
					break;
				}

				// 2.读取文件名
				byte[] filenamebytes = new byte[DataUtils.bytes2int(byte4)];
				fis.read(filenamebytes);
				String filename = new String(filenamebytes);
				File file = new File(dir, filename);
				FileOutputStream fos = new FileOutputStream(file);

				// 3.读取文件长度
				fis.read(byte4);
				byte[] fileContBytes = new byte[DataUtils.bytes2int(byte4)];

				// 4.读取文件内容
				fis.read(fileContBytes);
				fos.write(fileContBytes);

				fos.close();
			}
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
//		appendFile("C:/Users/work/Desktop/新建文件夹/面试问题.xar", "C:/Users/work/Desktop/新建文件夹/面试问题1.txt");
//		appendFile("C:/Users/work/Desktop/新建文件夹/面试问题.xar", "C:/Users/work/Desktop/新建文件夹/面试问题2.txt");
//		appendFile("C:/Users/work/Desktop/新建文件夹/面试问题.xar", "C:/Users/work/Desktop/新建文件夹/面试问题3.txt");

		appendFiledir("C:/Users/work/Desktop/新建文件夹/归档文件.xar", "C:/Users/work/Desktop/新建文件夹");
		unarchive("C:/Users/work/Desktop/新建文件夹/归档文件.xar", "C:/Users/work/Desktop/新建文件夹/解档");
	}
}
