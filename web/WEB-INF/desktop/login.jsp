<%@ page session="false" contentType="text/html;charset=UTF-8" language="java"%>
<%--desktop--%>
<%@include file="/WEB-INF/share/taglib.jsp" %>
<fmt:requestEncoding value="UTF-8" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>    
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/> 
    <meta http-equiv="Pragma" content="no-cache"/>
    <%@include file="/WEB-INF/share/desktop.css" %>
    <title>jwma WebMail</title>
</head>
<body bgcolor="#FFFFFF" link="#666666" vlink="#666666" alink="#FFFFFF">
    
    <div id="body">
   
    <fmt:setLocale value="${language}" />
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr align="left" valign="top"> 
            <td height="19"><img src="images/logo.png" width="195" height="36"  align="right"></img></td>
        </tr>
        <tr align="left" valign="top" bgcolor="#000000">
            <td height="19"> 
                &nbsp;
                <stripes:link class="rich" beanclass="pm.matthews.webmail.action.InitAction" event="mobile">
                    <fmt:message key="login.mobile"/>
                </stripes:link>          
            </td>
        </tr>
    </table><br></br>
    
    <stripes:form beanclass="pm.matthews.webmail.action.LoginAction">
        <stripes:hidden name="mobile" value="false"/>
        <table border="0" align="center" cellpadding="1" cellspacing="1">
            <tr valign="bottom">
                <td colspan="2" bgcolor="#000000" height="20">
                    <img src="images/login_small.png" width="21" height="20" align="right"></img>
                    <font face="Arial, Helvetica, sans-serif" size="+1" color="FFFFFF"><b><fmt:message key="authenticate"/></b></font>
                </td>
            </tr>
            <tr>
                <td width="22%" bgcolor="#dddddd">
                     <font face="Arial, Helvetica"><fmt:message key="login.username"/>:</font>
                </td>
                <td width="78%" bgcolor="#eeeeee">
                    <stripes:text name="username" value="${actionBean.lastLogin}" size="25"/>
                </td>
            </tr>
            <tr>
                <td width="22%" bgcolor="#dddddd">
                     <font face="Arial, Helvetica"><fmt:message key="login.password"/>:</font>
                </td>
                <td width="78%" bgcolor="#eeeeee">
                     <stripes:password name="password" size="25" />
                </td>
            </tr>
            <tr>
                <td bgcolor="#000000" align="left" height="20">
                    <stripes:checkbox name="remember"/>
                    <font face="Arial, Helvetica, sans-serif" color="#ffffff" size="-1">
                      <fmt:message key="login.remember"/>
                    </font>
                </td>
                <td bgcolor="#000000" align="right" height="20">
                    <stripes:reset name="reset"/>
                    &nbsp;&nbsp;&nbsp;
                    <stripes:submit name="authenticate"/>
                </td>
            </tr>
        </table>
</stripes:form><br></br>
<%@include file="/WEB-INF/share/footer.jsp" %>
  </div>
</body>
</html>

