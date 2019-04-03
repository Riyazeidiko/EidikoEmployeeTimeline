package com.eidiko.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestClient  {

	 	 
	//public static String tokenId          					= "C8104321-6DCE-4048-863F-CABC1A0F76EE";
	
	 public static String getEmployeeTimeLineURL  	    					= "http://192.168.3.62:8080/EmployeeTimeline/rest/getTimelineByEmployeeId/";
	 public static String postEmployeeTimeLineURL  	    					= "http://192.168.3.62:8080/EmployeeTimeline/rest/addTimeline";
	 
	 public List list;
	 public static void main(String[] args) 
	 {	
		
		 
	 }
	 public static String getEmployeeTimeline(String employeeId,String getEmployeeTimeline_URL) {
		 String response = "";
		 try
		 {
			 response = callGetRestService(getEmployeeTimeline_URL+employeeId);	
			
				System.out.println("Response GET "+response);
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		return response;
     }
	 public static PostResponse postEmployeeTimeline(Employee e,String postEmployeeTimeline_URL ){
		 
		String response= callPostRestService(e,postEmployeeTimeline_URL);
		PostResponse res=null;
		 try 
		  {				  
			ObjectMapper mapperObj = new ObjectMapper();	
			res=mapperObj.readValue(response, PostResponse.class);
		  }	    	
		  catch (NullPointerException nullEx)
		  {
			  nullEx.printStackTrace(); 
		  }
		 catch (Exception e1) 
		 {
			 System.out.print(e1);
		 }
		 return res;
	 }
	 //Get Method
	 public static String callGetRestService(String getEmployeeTimeLine )
		{		 
			 String  response = null;
			 @SuppressWarnings("unused")
			 int httpStatusCode = 0;
	     System.out.println("inside get service");
			 try 
			  {				  
				ObjectMapper mapperObj = new ObjectMapper();			
				mapperObj.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);		 
			    HttpClient httpclient = new HttpClient();	
			    httpclient.getParams().setSoTimeout(600000000);
			    httpclient.getParams().setConnectionManagerTimeout(30000000); 
			   
			    GetMethod get = new GetMethod(getEmployeeTimeLine);		   
			    get.setRequestHeader("Content-Type","application/json; charset=UTF-8");
			    get.setRequestHeader("Accept","application/json; charset=UTF-8");
			    httpStatusCode = httpclient.executeMethod(get);	
			    byte[] bytes = get.getResponseBody();
				response = new String(bytes, "UTF-8");
				
				System.out.println("Response GET "+response);
			   
				
			  }	    	
			  catch (NullPointerException nullEx)
			  {
				  nullEx.printStackTrace(); 
			  }
			 catch (Exception e) 
			 {
				 System.out.print(e);
			 }
			 		 		 
			 return response;
			 		 
		}
	 
	 
	//Post Method
	 public static String callPostRestService(Employee e,String postEmployeeTimeline_URL)
	 {		 
		 String  response = null;
		 int httpStatusCode = 0;
         String request=null;
		 try 
		  {				  			
			 		
			 	HttpClient httpclient = new HttpClient();	
			    httpclient.getParams().setSoTimeout(600000000);
			    httpclient.getParams().setConnectionManagerTimeout(30000000); 
			    
			    PostMethod post = new PostMethod(postEmployeeTimeline_URL);		   
			    try 
				  {				  
					 	ObjectMapper mapperObj = new ObjectMapper();			
						mapperObj.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);						
						 request = mapperObj.writeValueAsString(e);	
						
				  }catch (Exception e1) 
					 {
						 System.out.print(e);
					 }		 	    
			   
			    post.setRequestHeader("Content-Type","application/json; charset=UTF-8");
			    post.setRequestHeader("Accept","application/json; charset=UTF-8");
			    post.setRequestBody(request);
			    
			    httpStatusCode = httpclient.executeMethod(post);			 		   		    		        
			
			    byte[] bytes = post.getResponseBody();
				response = new String(bytes, "UTF-8");
				
			    System.out.println("Response::"+response);						    	
		  }	    	
		  catch (NullPointerException nullEx)
		  {
			  nullEx.printStackTrace(); System.out.print(nullEx);
		  }
		 catch (Exception e2) 
		 {
			 System.out.print(e2);
		 }
		 		 		 
		 return response;		 		 
	 }
	 
}