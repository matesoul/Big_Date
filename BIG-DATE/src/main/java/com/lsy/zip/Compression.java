package com.lsy.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.junit.Test;

import com.lsy.utils.Readdir;

public class Compression {

	@Test
	public void test() throws Exception {
		Zip("C:\\Users\\work\\Desktop\\新建文件夹","C:\\Users\\work\\Desktop\\新建文件夹\\Zip\\x.zip");
		Unzip("C:\\Users\\work\\Desktop\\新建文件夹\\Zip\\x.zip", "C:\\Users\\work\\Desktop\\新建文件夹\\Zip");
	}

	/**
	 * @param dir 要压缩的目录
	 * @param pos 压缩目标文件
	 * @throws Exception
	 */
	public void Zip(String dir, String pos) throws Exception {
		// 压缩输出流
		FileOutputStream fos = new FileOutputStream(pos);
		ZipOutputStream zos = new ZipOutputStream(fos);

		ArrayList<File> files = Readdir.read(dir);
		for (File file : files) {
			FileInputStream fis = new FileInputStream(file);
			zos.putNextEntry(new ZipEntry(file.getName()));
			byte[] bytes = new byte[1024];
			while ((fis.read(bytes)) != -1) {
				zos.write(bytes);
			}
			zos.closeEntry();
			fis.close();
		}
		zos.close();
	}

	/**
	 * @param src 要解压的文件
	 * @param dir 解压的目录
	 * @throws Exception
	 */
	public void Unzip(String src, String dir) throws Exception {
		ZipInputStream zis = new ZipInputStream(new FileInputStream(src));
		ZipEntry entry;
		while ((entry = zis.getNextEntry()) != null) {
			FileOutputStream fos = new FileOutputStream(new File(dir, entry.getName()));

			byte[] bytes = new byte[1024];
			int len;
			while ((len = zis.read(bytes)) != -1) {
				fos.write(bytes, 0, len);
			}
			fos.close();
			zis.closeEntry();
		}
		zis.close();
	}

}
