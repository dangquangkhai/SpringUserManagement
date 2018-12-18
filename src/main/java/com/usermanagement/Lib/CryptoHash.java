package com.usermanagement.Lib;

import com.usermanagement.Lib.Encryption;
import com.usermanagement.Lib.Decryption;

public class CryptoHash {
	private final static String defaultPassword = "Iloveopsr1998";
	
	public static String Encrypt(String text) {
		try {
			return new Encryption().encrypt(defaultPassword, text);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "false";
		}
	}
	
	public static String Decrypt(String text) {
		try {
			return new Decryption().decrypt(defaultPassword, text);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "false";
		}
	}
	
}
