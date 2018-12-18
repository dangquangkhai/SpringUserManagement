package com.usermanagement.LibTest;

import com.usermanagement.Lib.CryptoHash;

public class EncryptTest {
	public static void main(String[] args) throws Exception {
		
		String pass = CryptoHash.Encrypt("Hello");
		System.out.println("Encrypt String ('Hello') = " + pass);
		System.out.println("Decrypt String ('Hello') = " + CryptoHash.Decrypt(pass));

	}
}
