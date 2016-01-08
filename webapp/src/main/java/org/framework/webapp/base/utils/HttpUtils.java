package org.framework.webapp.base.utils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.RedirectLocations;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

/**
 * HTTP工具类
 * @author zhaotian
 * @date 2015-11-9 14:27:57
 * @version 1.0
 */
public class HttpUtils {
	protected static final Log LOG=LogFactory.getLog(HttpUtils.class);
	
	private static Integer socketTimeout=5000;
	private static Integer connectTimeout=5000;
	private static String charset="UTF-8";
	
	
	public static String sendGet(String url){
		CloseableHttpClient client=null;
		CloseableHttpResponse response=null;
		String result=null;
		try {
//			client=HttpClients.createDefault();
			client=createCloseableHttpClient(false, true);
			RequestConfig requestConfig=RequestConfig.custom().setSocketTimeout(socketTimeout)
					.setConnectTimeout(connectTimeout).build();
			
			
			
			HttpGet get=new HttpGet(url);
			
			get.setConfig(requestConfig);

			response=client.execute(get);

			StatusLine status=response.getStatusLine();
			LOG.info("Status:"+status.getStatusCode());
			
			if(status.getStatusCode()!=200){
				LOG.info("request url failed, http code=" + status.getStatusCode()
                        + ", url=" + url);
				return null;
			}
			
			HttpEntity entity=response.getEntity();
			
			if(entity!=null){
				result=EntityUtils.toString(entity,charset);
				LOG.info(result);
			}

			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e.getMessage());
		}finally{
			try {
				if(response!=null){
					response.close();
				}
				if(client!=null){
					client.close();
				}
			} catch (IOException e) {

				e.printStackTrace();
				LOG.error(e.getMessage());
			}
		}

		return result;
	}

	public static String sendPost(String url,String json){
		CloseableHttpClient client=null;
		CloseableHttpResponse response=null;
		String result=null;
		try {
//			client=HttpClients.createDefault();
			client=createCloseableHttpClient(false, true);
			
			RequestConfig requestConfig=RequestConfig.custom().setSocketTimeout(socketTimeout)
					.setConnectTimeout(connectTimeout).build();
			
			HttpPost post=new HttpPost(url);
			
			post.setConfig(requestConfig);
			
			

//			post.setHeader("Accept","application/json, text/javascript, */*; q=0.01");
//			post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36");
//			post.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
//			post.setHeader("X-Requested-With", "XMLHttpRequest");

			StringEntity requestEntity=new StringEntity(json,charset);
			requestEntity.setContentEncoding("UTF-8");
			requestEntity.setContentType("application/json");
			LOG.info("POST Request:"+EntityUtils.toString(requestEntity,charset));
			post.setEntity(requestEntity);


			response=client.execute(post);

			StatusLine status=response.getStatusLine();
			LOG.info("Status:"+status.getStatusCode());

			if(status.getStatusCode()!=200){
				LOG.info("request url failed, http code=" + status.getStatusCode()
                        + ", url=" + url);
				return null;
			}
			
			HttpEntity responseEntity=response.getEntity();

			if(responseEntity!=null){
				result=EntityUtils.toString(responseEntity,charset);
				LOG.info(result);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(response!=null){
					response.close();
				}
				if(client!=null){
					client.close();
				}
			} catch (IOException e) {

				e.printStackTrace();
				LOG.error(e.getMessage());
			}
		}

		return result;
	}
	
	
	public static String uploadMedia(String url, File file){
		CloseableHttpClient client=null;
		CloseableHttpResponse response=null;
		String result=null;
		try {
//			client=HttpClients.createDefault();
			client=createCloseableHttpClient(false, true);
			
			RequestConfig requestConfig=RequestConfig.custom().setSocketTimeout(socketTimeout)
					.setConnectTimeout(connectTimeout).build();
			
			HttpPost post=new HttpPost(url);
			
			post.setConfig(requestConfig);
			
			

//			post.setHeader("Accept","application/json, text/javascript, */*; q=0.01");
//			post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36");
//			post.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
//			post.setHeader("X-Requested-With", "XMLHttpRequest");

			LOG.info(">>>>FileName:"+file.getName());
			HttpEntity requestEntity=MultipartEntityBuilder.create()
					.addPart("media", new FileBody(file, ContentType.APPLICATION_OCTET_STREAM, file.getName()))
					.build();
			
			post.setEntity(requestEntity);


			response=client.execute(post);

			StatusLine status=response.getStatusLine();
			LOG.info("Status:"+status.getStatusCode());
			
			if(status.getStatusCode()!=200){
				LOG.info("request url failed, http code=" + status.getStatusCode()
                        + ", url=" + url);
				return null;
			}

			HttpEntity responseEntity=response.getEntity();

			if(responseEntity!=null){
				result=EntityUtils.toString(responseEntity,charset);
				LOG.info(result);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(response!=null){
					response.close();
				}
				if(client!=null){
					client.close();
				}
			} catch (IOException e) {

				e.printStackTrace();
				LOG.error(e.getMessage());
			}
		}

		return result;
	}
	
	public static String downloadMedia(String url, String fileDir){
		CloseableHttpClient client=null;
		CloseableHttpResponse response=null;
		String result=null;
		try {
//			client=HttpClients.createDefault();
			client=createCloseableHttpClient(false, true);
			RequestConfig requestConfig=RequestConfig.custom().setSocketTimeout(socketTimeout)
					.setConnectTimeout(connectTimeout).build();
			
			HttpGet get=new HttpGet(url);
			
			get.setConfig(requestConfig);

			HttpContext localContext=new BasicHttpContext();
			
			response=client.execute(get,localContext);
			
			for (Header hea : response.getAllHeaders()) {
				LOG.info(">>>>"+hea.getName()+":"+hea.getValue());
			}
			
			Object attribute=localContext.getAttribute(HttpClientContext.REDIRECT_LOCATIONS);
			
			RedirectLocations locations=null;
			
			if(attribute!=null){
				locations=(RedirectLocations) attribute;
			}
			
			if(locations!=null){
				List<URI> loc=locations.getAll();
				if(loc!=null&&loc.size()>0){
					URI downloadUrl=loc.get(0);
					
					LOG.info(">>>>downloadUrl:"+downloadUrl);
					
					String fileName=downloadUrl.toURL().getFile();
					File downloadFile = new File(fileDir + File.separator + fileName);
					FileUtils.writeByteArrayToFile(downloadFile, EntityUtils.toByteArray(response.getEntity()));
					result="{\"downloadFilePath\":\""+downloadFile.getAbsolutePath()
							+"\",\"httpcode\":\""+response.getStatusLine().getStatusCode()+"\"}";
				}
			}else{
				StatusLine status=response.getStatusLine();
				LOG.info("Status:"+status.getStatusCode());
				
				if(status.getStatusCode()!=200){
					LOG.info("request url failed, http code=" + status.getStatusCode()
	                        + ", url=" + url);
					return null;
				}
				
				HttpEntity entity=response.getEntity();
				
				if(entity!=null){
					result=EntityUtils.toString(entity,charset);
					LOG.info(result);
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e.getMessage());
		}finally{
			try {
				if(response!=null){
					response.close();
				}
				if(client!=null){
					client.close();
				}
			} catch (IOException e) {

				e.printStackTrace();
				LOG.error(e.getMessage());
			}
		}

		return result;
	}
	

	private static PoolingHttpClientConnectionManager createConnectionManager(){
		PoolingHttpClientConnectionManager connectionManager=null;
		try {
			SSLContext context=SSLContext.getInstance("TLS");
			X509TrustManager tm=new X509TrustManager(){

				public void checkClientTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
					// TODO Auto-generated method stub
					
				}

				public void checkServerTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
					// TODO Auto-generated method stub
					
				}

				public X509Certificate[] getAcceptedIssuers() {
					// TODO Auto-generated method stub
					return null;
				}

				};
				
				context.init(null, new TrustManager[]{tm}, null);
				
				SSLConnectionSocketFactory sf=new SSLConnectionSocketFactory(context,NoopHostnameVerifier.INSTANCE);
				
				Registry<ConnectionSocketFactory> socketFactoryRegistry=RegistryBuilder.<ConnectionSocketFactory>create()
						.register("http", PlainConnectionSocketFactory.INSTANCE).register("https", sf).build();
				connectionManager=new PoolingHttpClientConnectionManager(socketFactoryRegistry);
				
				
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		return  connectionManager;
	}
	
	
	
	public static CloseableHttpClient createCloseableHttpClient(boolean isOpenCookie,boolean enableSSL){
		if(!isOpenCookie&&!enableSSL){
			return HttpClients.createDefault();
		}
		HttpClientBuilder clientBuilder= HttpClients.custom();
		
		if(isOpenCookie){
			RequestConfig requestConfig=RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
			clientBuilder.setDefaultRequestConfig(requestConfig);
		}
		
		if(enableSSL){
			PoolingHttpClientConnectionManager manager=createConnectionManager();
			clientBuilder.setConnectionManager(manager);
		}
		
		return clientBuilder.build();
		
	}

}
