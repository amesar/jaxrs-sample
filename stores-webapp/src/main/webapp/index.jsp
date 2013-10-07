<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>

<html>

<head>
</head>


<body bgcolor="#eeeedd" >

<h2>Stores API</h2> 

<!-- ************ -->
<h4>API Specification</h4>

<!-- * * * * * *  -->
Resource Description
<ul>
<li><a href="v1/?_wadl">WADL</a>  
 - Web Application Description Language - Resource Contract - <i>sans</i> data contract
</ul>

<!-- * * * * * *  -->
Data Description - JSON Schema
<ul>

<li>Stores objects
<ul>
<li><a href="jsonschema/stores/Store.json">Store.json</a>
</ul>

<li>Common objects
<ul>
<li><a href="jsonschema/common/Location.json">Location.json</a>
<li><a href="jsonschema/common/Address.json">Address.json</a>
</ul>

</ul>

</ul>

<!-- ************ -->
<h4>System Info</h4>
<ul>
<li>Metrics: <a href="metrics">Operational Menu</a>
 -  <a href="metrics/metrics?pretty=true">Metrics Details</a>

<li>Spring: 
    <a href="misc/spring-status.jsp">Beans</a>
 -  <a href="misc/web-context.jsp">Web Application Context</a>

<li><a href="v1/admin/stores">Admin: Store Resource - Service Provider Implementation Info</a>

<li><a href="v1/stores/foo">Sample GET store</a>

</ul>

<!-- ************ -->

<font size="-1" > <i>
<%
  String BR="<br>"; String SP="&nbsp;";
  WebApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
  out.println("Webapp startup time: "+new Date(appContext.getStartupDate())+BR);
%>
</font> </i>

</body>
</html>


