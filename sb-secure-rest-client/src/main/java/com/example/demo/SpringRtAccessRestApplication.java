package com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringRtAccessRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRtAccessRestApplication.class, args);
	}

	@Value("${certificate.path}")
	private  String certPath;
	
	@Bean
	public RestTemplate restTemplate() throws Exception {
	    KeyStore clientStore = KeyStore.getInstance("PKCS12");

	    //ClassPathResource classPathResource = new ClassPathResource(certPath+"/client_pavel.p12");
		InputStream inputStream = ResourceUtils.getURL(certPath+"client_pavel.p12").openStream();
	    clientStore.load(inputStream, "changeit".toCharArray());
	 
	    SSLContextBuilder sslContextBuilder = new SSLContextBuilder();
	    sslContextBuilder.useProtocol("TLS");
	    sslContextBuilder.loadKeyMaterial(clientStore, "changeit".toCharArray());
	    sslContextBuilder.loadTrustMaterial(new TrustSelfSignedStrategy());
	 
	    SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContextBuilder.build(), new NoopHostnameVerifier());
	    CloseableHttpClient httpClient = HttpClients.custom()
	            .setSSLSocketFactory(sslConnectionSocketFactory)
	            .build();
	    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
	    requestFactory.setConnectTimeout(10000); // 10 seconds
	    requestFactory.setReadTimeout(10000); // 10 seconds
	    return new RestTemplate(requestFactory);
	}

}
