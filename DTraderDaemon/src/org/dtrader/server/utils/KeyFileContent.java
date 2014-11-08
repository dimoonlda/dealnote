package org.dtrader.server.utils;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.servlet.ServletContext;
import javax.xml.ws.WebServiceContext;

import org.apache.log4j.Logger;
import org.dtrader.server.impl.ServiceImpl;

public class KeyFileContent {
	private static final Logger logger = Logger.getLogger(KeyFileContent.class);
	
	private static KeyFileContent keyFileContent = null;
	private String algorithm = "DESede";
	/**
	 * Path to the file with encoded serial numbers
	 */
	private String pathToKeyFileSN;
	/**
	 * Path to the key-file with encoding key
	 */
	private String pathToKeyFile;
	private String keyFileAsString = null;
	
	private KeyFileContent(){
		
	}
	
	/**
	 * 
	 * @param imei - client serial number
	 * @return
	 */
	public boolean inKeyFile(String imei){
		if(imei != null && !imei.equals("") && !keyFileAsString.equals("")){
			return keyFileAsString.contains(imei);
		}
		logger.error("Error. Filed IMEI: " + imei);
		return false;
	}
	
	public static KeyFileContent getInstance(ServletContext servletContext){
		String pathToKeyFileSN =  servletContext.getInitParameter("keyFileSN");
		String pathToKeyFile = servletContext.getInitParameter("keyFile");
		return getInstance(pathToKeyFileSN, pathToKeyFile);
	}
	
	/**
	 * Initialize and returns KeyFileContent
	 * @param pathToKeyFileSN - Path to the file with encoded serial numbers
	 * @param pathToKeyFile - Path to the key-file with encoding key
	 * @return KeyFileContent
	 */
	public static KeyFileContent getInstance(String pathToKeyFileSN, String pathToKeyFile){
		if(keyFileContent == null){
			keyFileContent = new KeyFileContent();
			keyFileContent.pathToKeyFileSN = pathToKeyFileSN;
			keyFileContent.pathToKeyFile = pathToKeyFile;
			keyFileContent.readKeyFile();
		}
		return keyFileContent;
	}
	
	/**
	 * Obtaining context of file with encoded serial numbers.
	 */
	private void readKeyFile(){
        FileInputStream fis = null;
        try
        {
            fis = new FileInputStream(pathToKeyFile);
            byte[] keyspecbytes = new byte[fis.available()];
            fis.read(keyspecbytes);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(algorithm);
            DESedeKeySpec keyspec = new DESedeKeySpec(keyspecbytes);
            SecretKey key = skf.generateSecret(keyspec);
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, key);
            ObjectInputStream ois = new ObjectInputStream(new CipherInputStream(new FileInputStream(pathToKeyFileSN), cipher));
            keyFileAsString = (String) ois.readObject();
            fis.close();
            ois.close();
        }
	    catch (Exception ex){
	      logger.error("Key file read errorr. " + ex);
	      keyFileAsString = "";
	    }
	}
}
