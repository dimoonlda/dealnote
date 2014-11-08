package org.dtrader.server.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;

public class FtpUploader {
	
	private static final Logger logger = Logger.getLogger(FtpUploader.class);
	
	private ByteArrayOutputStream out;
	
	public FtpUploader(ByteArrayOutputStream out){
		this.out = out;
	}
	/**
	 * Upload file to FTP server in folder "kml"
	 * @param fullFileName - file name
	 * @param ftpAddress - ftp address
	 * @param ftpLogin - ftp login
	 * @param ftpPassword - ftp password
	 * @return upload result true or false
	 */
	public boolean uploadToFtp(String fullFileName, String ftpAddress, String ftpLogin, String ftpPassword){
		FTPClient kmlFtp = new FTPClient();
		boolean res = false;
		try {
			kmlFtp.connect(ftpAddress);
			kmlFtp.login(ftpLogin, ftpPassword);
			kmlFtp.enterLocalPassiveMode();
			kmlFtp.changeWorkingDirectory("kml");
			ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
			
			res = kmlFtp.storeFile(fullFileName, in);

			kmlFtp.logout();
			kmlFtp.disconnect();
		} catch (Exception e) {
			logger.error("FTP error!!! " + e);
			
		}
		return res;
	}

}
