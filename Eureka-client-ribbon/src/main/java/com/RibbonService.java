package com;

import org.antlr.runtime.tree.RewriteRuleNodeStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class RibbonService {

	@Autowired
	RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod="hiError")
	String sayHi(String name){
		return this.restTemplate.getForObject("http://eureka-client/sayHi/name="+name, String.class);
	}
	
	String hiError(String name){
		return "Sorry,"+name+",there is something wrong here by ribbon..";
	}
}
