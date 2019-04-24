<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">

	//var jsonString="<s:property value='#resultTable'/>";
	var name="<s:property value='name'/>";
	var juzhen="<s:property value='#juzhen'/>";
	var result="<s:property value='#result'/>";
	var json="<s:property value='#json'/>";
	//var jsontojs=JSON.parse(json);
	//var result1=eval("("+result+")");
	//alert(result1);
	alert(json);
	alert(result);
	alert(juzhen);
	
	alert(name);
	alert(jsonString);


</script>
</head>

<body>
<%
String sid3 = (String) request.getAttribute("key");
String sd= (String)request.getAttribute("tea");
String name=(String)session.getAttribute("name");
String age=(String)session.getAttribute("age");
out.print(sid3);
out.print(sd);
out.print(name);
out.print(age);
%>
<%=(String)session.getAttribute("age")%>
<a href="testsession">session</a>
<!-- <s:property  value="#request.key"/>
<s:property  value="#request.tea"/>

<s:property  value="#tea"/>
<s:property  value="#key"/>-->
<s:property value="#session.name"/>
<s:property value="#parameters.name"/>
<s:property value="#parameters.age"/>


<s:property value="[0].age1"/>
<s:property value="[1].age"/>
<s:iterator value="#allresult" id="id" status="st">
	 <tr>
                  	  <td><a style="cursor:pointer;"><s:property value="#id.getId()"/></a></td>
                  	  <!--<td><s:property value="#id.getSpecies()"/></td>   -->
                  	  <td><s:property value="#id.getName()"/></td>
                  	  <td><s:property value="#id.getSex()"/></td>
                  	  <td><s:property value="#id.getScore()"/></td>
                  	  <td><s:property value="#id.getUniversity()"/></td>
                  	
                  	 
                  	  </tr>
</s:iterator>
<!--<s:property value="#USER.getUsername()"/>
<s:property value="#USER.getPassword()"/>
<s:property value="#USER"/>
<s:property value="#juzhen"/>
<s:property value="#juzhen[0][0]"/>
<s:property value="#json"/>
<s:property value="#USERA"/>
<s:property value="#resultTable"/>
<s:property value="#result"/>
<s:iterator value="#AU1" id="id" status="st">
   <s:property value="#id.getUsername()"/>
   <s:property value="#id.getPassword()"/>
</s:iterator>
<s:property value="#jsonmap"/>
<s:property value="#jsonmap1"/>-->
<s:debug></s:debug>
</body>
</html>
