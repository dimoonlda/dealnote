package org.dtrader.server.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Utils {
	
	public static String nullAsString(String str){
		if(str == null) {
			return "";
		} else return str;
	}
	
	public static String calculateKey(String str, String secret) throws Exception{
		byte[] linebreak = {}; // Remove Base64 encoder default linebreak
		SecretKey key;
		Cipher cipher;
		Base64 coder;
		key = new SecretKeySpec(secret.getBytes(), "AES");
		cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "SunJCE");
		coder = new Base64(32, linebreak, true);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] cipherText = cipher.doFinal(str.getBytes());
		
		return new String(coder.encode(cipherText));
	}
}
