package com.bashir.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import feign.Response;
import feign.codec.ErrorDecoder;

@Component
public class FeignErrorDecoder implements ErrorDecoder{

	@Override
	public Exception decode(String methodKey, Response response) {
		
		
		switch(response.status())
		{
		   case 400: {
			   
		   }
			   
		   case 404: {
			   
			   if(methodKey.contains("getSalesman"))
			   {
				   return new ResponseStatusException(
						   HttpStatus.valueOf(response.status()),
						   "Unable to retrieve salesman");
			   }
			   
			   break;
		   }
		   
		   default: 
			   return new Exception("Hello from the other side");
		}
		return null;
	}
	
	

}
