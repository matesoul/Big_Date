package com.lsy.utils;

import java.io.File;
import java.util.ArrayList;

public class Readdir {

	public static ArrayList<File> filelist = new ArrayList<>();

	/**
	 * 读取一个目录下的文件
	 * 
	 * @param dir 文件目录
	 * @return 文件的集合
	 */
	public static ArrayList<File> read(String dir) {
		File filedir = new File(dir);
		if (filedir.isFile()) {
			filelist.add(new File(dir));
			return filelist;
		}
		if (filedir.isDirectory()) {
			String[] files = filedir.list();
			for (String s : files) {
				if (new File(filedir, s).isFile()) {
					filelist.add(new File(filedir, s));
				}
			}
		}
		return filelist;
	}

	/**
	 * 读取一个目录下的文件
	 * 
	 * @param dir 文件目录
	 * @param rec 是否递归
	 * @return 文件的集合
	 */
	public static ArrayList<File> read(String dir, boolean rec) {
		if (rec == false) {
			return read(dir);
		} else {
			File filedir = new File(dir);
			if (filedir.isFile()) {
				filelist.add(new File(dir));
				return filelist;
			}
			if (filedir.isDirectory()) {
				String[] files = filedir.list();
				for (String s : files) {
					if (new File(filedir, s).isFile()) {
						filelist.add(new File(filedir, s));
					} else if (new File(filedir, s).isDirectory()) {
						read(new File(filedir, s).toString());
					}
				}
			}
			return filelist;
		}
	}
}
