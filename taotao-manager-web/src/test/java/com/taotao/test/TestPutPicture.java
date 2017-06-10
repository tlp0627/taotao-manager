package com.taotao.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;


public class TestPutPicture {
	@Test
	public void putPicture(){
		FTPClient ftpClient = new FTPClient();
		try {
			
			ftpClient.connect("192.168.6.133");
			ftpClient.login("ftpuser", "ftpuser");
			FileInputStream fileInputStream = new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\技能图\\1.jpg"));
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
			ftpClient.storeFile("123.jpg",fileInputStream);
			fileInputStream.close();
			ftpClient.logout();
		} catch (Exception e) {
			
		}
	}
	
}
