package com.taotao.test;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class httpclientTest {
	@Test
	public void doGet () throws Exception{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet get =new HttpGet("http://www.sogou.com");
		CloseableHttpResponse response = httpClient.execute(get);
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		HttpEntity entity = response.getEntity();
		String string = EntityUtils.toString(entity,"utf-8");
		System.out.println(string);
		response.close();
		httpClient.close();
	}
}
