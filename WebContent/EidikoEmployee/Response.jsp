<%@page import="com.eidiko.util.PostResponse"%>
<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c_rt"%>
<%@ page import="java.util.List"%>
<%@ page import="com.eidiko.*"%>
<portlet:defineObjects />

 <!--  <style>
       
       #empTable {
  font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

#empTable td, #empTable th {
  border: 1px solid #ddd;
  padding: 8px;
}

#empTable tr:nth-child(even){background-color: #f2f2f2;}

#empTable tr:hover {background-color: #ddd;}

#empTable th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #4CAF50;
  color: white;
}
       </style> -->
<script type="text/javascript">
var y='<%=request.getAttribute("employeeResponse")%>';
console.log("value of y "+y);
</script>
<div>
<%
			//List res = (List)request.getAttribute("employeeResponse");
			PostResponse res1 = (PostResponse)request.getAttribute("postResponse");
			if(res1 != null){
			
 		%>

<div>

<table id="empTable">
<tr>
           <th>Response Code</th>
           <th>Response Message</th>
  </tr>
        <tr>
        <td> <c_rt:out value="<%=res1.getCode() %>"/></td>
        <td><c_rt:out value="<%=res1.getMessage() %>"/></td>
        </tr>
   
</table>

<form action='<portlet:renderURL/>' method="get">
<input type="submit" value="Back"></form>
</div>
 <% }%>
 </div>
