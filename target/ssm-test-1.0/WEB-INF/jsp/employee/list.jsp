<%@ page import="cn.etao.ssm.pojo.Employee" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/8/23
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Employee> listData= (List<Employee>) request.getAttribute("list");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
    </tr>
    <%
        for(int i=0;i<listData.size();i++){
            Employee mo=listData.get(i);
    %>
    <tr>
        <td><%=mo.getId()%></td>
        <td><%=mo.getName()%></td>
    </tr>
    <%
        }
    %>
</table>

</body>
</html>
