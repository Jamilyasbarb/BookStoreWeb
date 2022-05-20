<%-- 
    Document   : UserList
    Created on : 2 de mai de 2022, 11:02:15
    Author     : devsys-a
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="./contents/headerTags.jsp"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <div class="table-responsive">
                <table class="table table-hover">
                    <tr>
                        <th>Id</th>
                        <th>Nome</th>
                        <th>Email</th>
                    </tr>
                    <c:forEach var="user" items="${listaUser}">
                        <tr>
                            <td><c:out value="${user.id}"/></td>
                            <td><c:out value="${user.fullName}"/></td>
                            <td><c:out value="${user.email}"/></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>   
    </body>
</html>
