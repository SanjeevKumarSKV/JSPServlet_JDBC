<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>User Management Application</title>
</head>
<body>
	<center>
		<h1>User Management</h1>
        <h2>
        	<a href="employee/new">Add New Employee</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="employee/list">List All Employee</a>
        	
        </h2>
	</center>
    <div align="center">
		<c:if test="${employee != null}">
			<form action="update" method="put">
        </c:if>
        <c:if test="${employee == null}">
			<form action="insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
            	<h2>
            		<c:if test="${employee != null}">
            			Edit User
            		</c:if>
            		<c:if test="${employee == null}">
            			Add New User
            		</c:if>
            	</h2>
            </caption>
        		<c:if test="${employee != null}">
        			<input type="hidden" name="id" value="<c:out value='${employee.id}' />" />
        		</c:if>            
            <tr>
                <th>User Name: </th>
                <td>
                	<input type="text" name="name" size="45"
                			value="<c:out value='${employee.name}' />"
                		/>
                </td>
            </tr>

            <tr>
                <th>Country: </th>
                <td>
                	<input type="text" name="password" size="15"
                			value="<c:out value='${employee.password}' />"
                	/>
                </td>
            </tr>
            <tr>
            	<td colspan="2" align="center">
            		<input type="submit" value="Save" />
            	</td>
            </tr>
        </table>
        </form>
    </div>	
</body>
</html>
