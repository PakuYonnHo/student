package com.yjb.business.app.utils;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientUtils {
	private static HttpClient httpclient = HttpClients.createDefault();
	private static String UTF_8 = "utf-8";

	public static String getApiResult(String url, String api, String name, String pwd, String params)
			throws ClientProtocolException, IOException {

		StringBuffer sb = new StringBuffer();
		if (StringUtils.isNotEmpty(url)) {
			sb.append(url);
		}
		if (StringUtils.isNotEmpty(api)) {
			sb.append(api);
		}
		if (StringUtils.isNotEmpty(name)) {
			sb.append(name);
		}
		if (StringUtils.isNotEmpty(pwd)) {
			sb.append(pwd);
		}
		if (StringUtils.isNotEmpty(params)) {
			sb.append(params);
		}

		HttpGet httpget = new HttpGet(sb.toString());
		HttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();
		String html = EntityUtils.toString(entity, UTF_8);
		httpget.releaseConnection();

		System.out.println("httpget>>>>>>:" + html);
		return html;
	}

	public static String postApiResult(String url, String api, String name, String pwd, String jsonData)
			throws ClientProtocolException, IOException {

		StringBuffer sb = new StringBuffer();
		if (StringUtils.isNotEmpty(url)) {
			sb.append(url);
		}
		if (StringUtils.isNotEmpty(api)) {
			sb.append(api);
		}
		if (StringUtils.isNotEmpty(name)) {
			sb.append(name);
		}
		if (StringUtils.isNotEmpty(pwd)) {
			sb.append(pwd);
		}
//		 CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(sb.toString());
		StringEntity data = new StringEntity(jsonData, UTF_8);
		httpPost.setEntity(data);
		HttpResponse response = httpclient.execute(httpPost);
		HttpEntity entity = response.getEntity();
		String html = EntityUtils.toString(entity, UTF_8);
		httpPost.releaseConnection();

		System.out.println("httpget>>>>>>:" + html);
		return html;
	}
}
