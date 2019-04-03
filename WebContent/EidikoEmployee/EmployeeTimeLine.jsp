<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt_rt"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c_rt"%>
<%@page import="java.util.Map"%>
<portlet:defineObjects />
<portlet:resourceURL id="resource_URL" var="resource_URL"> </portlet:resourceURL>
        
         
          
         <link rel="stylesheet" type="text/css" href=<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/css/style.css")%> />
        <script src=<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/js/modernizr.custom.63321.js")%>></script>
      	<style>
			body {
				background: #e1c192 url("/images/wood_pattern.jpg");
			}
			
		</style>
		
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
			
			<header>
			
				
				<h2  style="text-align: center;padding-bottom: 15px" > Eidiko Employee Data </h2>
				
				

				
				
			</header>
			
			
			
			
			<section class="main">
			<div>
			
			<p style="text-align:center">
						<label for="login">Timeline Id</label>
						<input type="text" name="tmStatusId1" id="empId">
			</p>
			<p style="text-align:center" >   
						<input type="submit" id="getTimeline" name="submit" value="Get Employee Data ">
			</p>
			</div>
				<form class="form-2" action="<portlet:actionURL/>" style="display:none" id="employeetimeLineForm">
					<h1><span class="log-in">Employee Data</span></h1>
					
					<label for="login">Timeline Id</label>
						<input type="text" name="tmStatusId" >
					
					<label for="password">Employee Id</label>
						<input type="text" name="employeeId" >
					
						<label for="password">Category Id</label>
						<input type="text" name="categoryId">
					
					
					
					<label for="password">SubCategory Id</label>
						<input type="text" name="subCategoryId" >
					
					<label for="password">SkillId</label>
						<input type="text" name="skillId">
					
					<label for="password">Start Date</label>
						<input type="date" name="startDate">
					
					<label for="password">End Date</label>
						<input type="date"  name="endDate">
					
						<label for="password">Remarks</label>
						<input type="text" name="remarks" >
					
					
					
					<p class="clearfix"> 
						    
						<input type="submit" name="submit" value="Click Here">
					</p>
				</form>​​
			</section>
			
        
        <div>
       
        <table id="empTable" style="display:none" border="2">
        
        <thead>
        <tr>
           <th>Id</th>
           <th>Employee Id</th>
           <th>Category </th>
           <th>Sub Category </th>
           <th>Skill ID</th>
           <th>Start Date </th>
           <th>End Date </th>
           <th>Remarks  </th>
        </tr>
        </thead>
        <tbody>
        
        </tbody>
        </table>
        </div>
        <div>
        <p class="form-2 clearfix"> 
						    
						<input type="button" id="showForm" name="showForm" value="New Employee Data Add">
		</p>
        </div>
		
		
		 <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
		<script type="text/javascript">
			$("#showForm").click(function(){
			$("#employeetimeLineForm").show();
			});
			$('#getTimeline').click(function(){
				$("#empTable tbody tr").remove();
					var data=getEmployeeTimelineData();
					console.log(data);
					if(data!="null"){
					for(i=0;i<data.length;i++){
					$("#empTable tbody").append('<tr><td>'+data[i].id+'</td><td>'+data[i].employeeId+'</td><td>'+data[i].categoryId+'</td><td>'+data[i].subCategoryId+'</td><td>'+data[i].skillId+'</td><td>'+data[i].startDate+'</td><td>'+data[i].endDate+'</td><td>'+data[i].remarks+'</td></tr>')
					}
					$("#empTable").show();
					}
					
			});
	function getEmployeeTimelineData(){
	console.log("verifyAddresInfo val");
    var resultdata=[];
	var url="<portlet:resourceURL/>";
	 		console.log("url "+url);
		employeeId =$("#empId").val();
			
		
		
    $.ajax({
        type: "POST",
        url: url,
		 async: false,
        data: { 
        'action': 'getEmployeeTimeline', 
        'employeeId': employeeId,
		
		},
        success: function(response) {
			console.log("response "+response);
			resultdata=response;
          }
    });
	
    return resultdata;
}

/* $("#postEmplyeeSubmit").click(function(){
//alert("inside  getEmplyeeSubmit")
postEmplyeeSubmitTimeline();
});

 function postEmplyeeSubmitTimeline(){
 
 	var url='<portlet:resourceURL/>'
 employeeId=$("#employeeId").val();
 id=$("#id").val();
 categoryId=$("#categoryId").val();
 subCategoryId=$("#subCategoryId").val();
 skillId=$("#skillId").val();
 startDate=$("#startDate").val();
 endDate=$("#endDate").val();
 remarks=$("#remarks").val();
 
 $.ajax({
 url:url,
 data:{
"action":"postEmplyeeTimeline",
"employeeId":employeeId,
"categoryId":categoryId,
"subCategoryId":subCategoryId,
"skillId":skillId,
"startDate":startDate,
"endDate":endDate,
"remarks":remarks
},
suceess:function(response){

console.log(response);
}

 });

}

 */

		</script>
   