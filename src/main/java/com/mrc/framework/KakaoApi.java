package com.mrc.framework;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;



/**
 * 카카오 Api
 * @author jsh
 */

public class KakaoApi {
	 
	 
	/**
	 * 카카오 api 호출
	 * @param url parameter 이 한글일 경우 인코딩 해주어야 함*
	 * @return
	 */
	 public ResponseEntity<String> call(URI url)
	    {
	    	try {
	    		
	    		RestTemplate restTemplate = new RestTemplate();
		    	HttpHeaders headers = new HttpHeaders(); 

		    	headers.add("Authorization", "KakaoAK f14891693c4c1e9ed1ab2195e941c3dd"); 
		    	headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		    	headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
		    	
		    	RequestEntity<String> rq = new RequestEntity<>(headers, HttpMethod.GET, url);
		    	ResponseEntity<String> re = restTemplate.exchange(rq, String.class);
		    	return re;
	    	}catch(Exception ex)
	    	{}
	    	return null;
	    }
}
