package com.eidiko.portlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import com.eidiko.util.Employee;
import com.eidiko.util.PostResponse;
import com.eidiko.util.RestClient;


/**
 * A sample portlet based on GenericPortlet
 */
public class EidikoEmployeeTimelinePortlet extends GenericPortlet {

	public static final String JSP_FOLDER    = "/EidikoEmployee/";    // JSP folder name

	public static final String VIEW_JSP      = "EmployeeTimeLine";         // JSP file name to be rendered on the view mode
	public static final String RESPONSE_JSP  = "Response";  // Bean name for the portlet session
	

	 
	/**
	 * @see javax.portlet.Portlet#init()
	 */
	public void init() throws PortletException{
		super.init();
	}

	/**
	 * Serve up the <code>view</code> mode.
	 * 
	 * @see javax.portlet.GenericPortlet#doView(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
	 */
	public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		// Set the MIME type for the render response
		
		response.setContentType(request.getResponseContentType());
		
		System.out.println("change in portlet doview");
		String action = request.getParameter("Action");
		System.out.println("Action PArameter "+action);
		if(action != null && "Confirm".equals(action)){
			System.out.println("inside if confirm  "+action);
			PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(getJspFilePath(request, RESPONSE_JSP));
			rd.include(request,response);
		}else {
				// Invoke the JSP to render
		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(getJspFilePath(request,VIEW_JSP ));
		rd.include(request,response);
		}
	}

	/**
	 * Process an action request.
	 * 
	 * @see javax.portlet.Portlet#processAction(javax.portlet.ActionRequest, javax.portlet.ActionResponse)
	 */
	
	public void processAction(ActionRequest request, ActionResponse response) throws PortletException, java.io.IOException {
		System.out.println("inside processAction method");
		Employee e=new Employee();
		e.setCategoryId(Long.parseLong(request.getParameter("categoryId")));
		e.setSubCategoryId(Long.parseLong(request.getParameter("subCategoryId")));
		e.setEndDate(convertDateFormat(request.getParameter("endDate")));
		e.setStartDate(convertDateFormat(request.getParameter("startDate")));
		e.setRemarks(request.getParameter("remarks"));
		e.setId(Long.parseLong(request.getParameter("tmStatusId")));
		e.setEmployeeId(Integer.parseInt(request.getParameter("employeeId")));
		System.out.println("inside processAction method"+e.getCategoryId()+e.getEmployeeId()+e.getSkillId());
		String postEmployeeTimeline_URL=request.getPreferences().getValue("PostEmployeeTimeline_URL","");
		PostResponse res=RestClient.postEmployeeTimeline(e,postEmployeeTimeline_URL);
					response.setRenderParameter("Action", "Confirm");
					request.setAttribute("postResponse",res);
					
	}

	

	/**
	 * Process a serve Resource request.
	 * 
	 * @see javax.portlet.Portlet#serveResource(javax.portlet.ResourceRequest, javax.portlet.ResourceResponse)
	 */
	public void serveResource(ResourceRequest request, ResourceResponse response) throws PortletException, java.io.IOException {
		System.out.println("inside serveresource method");
		String action = request.getParameter("action");  
		String employeeId=request.getParameter("employeeId");
		System.out.println("inside serveresource method "+action+" "+employeeId);
		String responseData="";
		String getEmployeeTimeline_URL=request.getPreferences().getValue("GetEmployeeTimeline_URL","");
		if(action != null && "getEmployeeTimeline".equals(action)) {
			System.out.println("inside if "+action+" "+employeeId);
			
			responseData=RestClient.getEmployeeTimeline(employeeId,getEmployeeTimeline_URL);
		}/*if("postEmplyeeTimeline".equals(action)){
			Employee e=new Employee();
			
			
			e.setCategoryId(Long.parseLong(request.getParameter("categoryId")));
			e.setSubCategoryId(Long.parseLong(request.getParameter("subCategoryId")));
			e.setSkillId(Long.parseLong(request.getParameter("skillId")));
			e.setEndDate(request.getParameter("endDate"));
			e.setStartDate(request.getParameter("startDate"));
			e.setRemarks(request.getParameter("remarks"));
			e.setId(Long.parseLong(request.getParameter("id")));
			e.setEmployeeId(Integer.parseInt(request.getParameter("employeeId")));
			
			RestClient c=new RestClient();
			responseData=c.callPostRestService(e, "");
			System.out.println("Response "+responseData);
		}*/
		
		  response.setContentType("text/json");
	      PrintWriter pw = response.getWriter();;
	      pw.write(responseData);
	      pw.close();
		
	}

		/**
	 * Returns JSP file path.
	 * 
	 * @param request Render request
	 * @param jspFile JSP file name
	 * @return JSP file path
	 */
	private static String getJspFilePath(RenderRequest request, String jspFile) {
		String markup = request.getProperty("wps.markup");
		if( markup == null )
			markup = getMarkup(request.getResponseContentType());
		return JSP_FOLDER +  jspFile + ".jsp" ;
	}

	/**
	 * Convert MIME type to markup name.
	 * 
	 * @param contentType MIME type
	 * @return Markup name
	 */
	private static String getMarkup(String contentType) {
		if( "text/vnd.wap.wml".equals(contentType) )
			return "wml";
        else
            return "html";
	}

	/**
	 * Returns the file extension for the JSP file
	 * 
	 * @param markupName Markup name
	 * @return JSP extension
	 */
	private static String getJspExtension(String markupName) {
		return "jsp";
	}
	
   public Date convertDateFormat(String date){
	   Date d=null;
	   try{
		  
		   d=new SimpleDateFormat("dd-MM-yyyy").parse(date);
		   
	   }catch(Exception e){
		   e.printStackTrace();
	   }
	   
	   
	   return d;
	   
	   
   }
}
