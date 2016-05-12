package utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import exception.BasicException;

public class HttpUtil {
	public static InputStream getInputStream(String url) throws BasicException{
		CloseableHttpClient httpClient=HttpClients.createDefault();
		HttpGet get=new HttpGet(url);
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();//设置请求和传输超时时间
		get.setConfig(requestConfig);
//		get.addHeader("Referer","https://www.baidu.com");
		try {
			HttpResponse response=httpClient.execute(get);
			int code=response.getStatusLine().getStatusCode();
			if(code!=200){
				throw new BasicException("get 返回码："+code);
			}
			return response.getEntity().getContent();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BasicException(e.getMessage());
		} 
	}
	public static byte[] getBytes(String url) throws BasicException{
		try {
			InputStream is=HttpUtil.getInputStream(url);
			ByteArrayOutputStream  bos=new ByteArrayOutputStream ();
			byte[] buffer=new byte[1024];
			int len=0;
			while((len=is.read(buffer))!=-1){
				bos.write(buffer, 0, len);
			}
			is.close();
			byte[] bytes=bos.toByteArray();
			return bytes;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BasicException(e.getMessage());
		} 
	}
	
	public static String get(String url) throws BasicException{
		CloseableHttpClient httpClient=HttpClients.createDefault();
		HttpGet get=new HttpGet(url);
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();//设置请求和传输超时时间
		get.setConfig(requestConfig);
		try {
			HttpResponse response=httpClient.execute(get);
			int code=response.getStatusLine().getStatusCode();
			if(code!=200){
				throw new BasicException("get 返回码："+code);
			}
			String res=EntityUtils.toString(response.getEntity(),"utf-8");
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BasicException(e.getMessage());
		} 
	} 
	public static String post(String url,String params) throws BasicException{
		CloseableHttpClient httpClient=HttpClients.createDefault();
		HttpPost post=new HttpPost(url);
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();//设置请求和传输超时时间
		post.setConfig(requestConfig);
		try {
//			Entity en=new StringEntity(params);?
			post.setEntity(new StringEntity(params,"utf-8"));
			HttpResponse response=httpClient.execute(post);
			int code=response.getStatusLine().getStatusCode();
			if(code!=200){
				throw new BasicException("get 返回码："+code);
			}
			String res=EntityUtils.toString(response.getEntity(),"utf-8");
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BasicException(e.getMessage());
		} 
	} 
	public static String post(String url,Map<String, String> params) throws BasicException{
		CloseableHttpClient httpClient=HttpClients.createDefault();
		HttpPost post=new HttpPost(url);
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();//设置请求和传输超时时间
		post.setConfig(requestConfig);
		List<NameValuePair> list=new ArrayList<>();
		Set<String> set=params.keySet();
		for (String key : set) {
			String value=params.get(key);
			list.add(new BasicNameValuePair(key, value));
		}
		try {
			post.setEntity(new UrlEncodedFormEntity(list,"utf-8"));
			HttpResponse response=httpClient.execute(post);
			int code=response.getStatusLine().getStatusCode();
			if(code!=200){
				throw new BasicException("get 返回码："+code);
			}
			String res=EntityUtils.toString(response.getEntity(),"utf-8");
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BasicException(e.getMessage());
		} 
	}
}
