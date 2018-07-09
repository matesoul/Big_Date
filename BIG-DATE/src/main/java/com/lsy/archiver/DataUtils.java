package com.lsy.archiver;

public class DataUtils {
	
	public static byte[] int2bytes(int i){
		byte[] bytes = new byte[4];
		bytes[0] = (byte)(i >> 0);
		bytes[1] = (byte)(i >> 8);
		bytes[2] = (byte)(i >> 16);
		bytes[3] = (byte)(i >> 24);
		return bytes;
	}
	
	public static int bytes2int(byte[] bytes){
		return (bytes[0] & 0xff) << 0
				|(bytes[1] & 0xff) << 8
				|(bytes[2] & 0xff) << 16
				|(bytes[3] & 0xff) << 24;
	}
}
