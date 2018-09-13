<%@ page session="true" contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="taglib.jsp" %>

<stripes:layout-definition>
<fmt:requestEncoding value="UTF-8" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>    
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/> 
    <meta http-equiv="Pragma" content="no-cache"/>
    <script language="JavaScript" type="text/javascript" src="jquery.js"></script>
    <%@include file="desktop.css" %>
    <title>jwma WebMail</title>
</head>
<body bgcolor="#FFFFFF" link="#666666" vlink="#666666" alink="#FFFFFF">
    
    <div id="body">
        <stripes:layout-component name="body"/>
    </div>

    <%@include file="footer.jsp" %>
</body>
</html>
</stripes:layout-definition>
