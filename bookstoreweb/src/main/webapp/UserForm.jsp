<%-- 
    Document   : UserForm
    Created on : 2 de mai de 2022, 11:02:00
    Author     : devsys-a
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
         <jsp:include page="./contents/headerTags.jsp"/>
    </head>
    <body>
        <h1 style="text-align: center">Cadastrar</h1>
        <div class="container" style="align-items: center">
            <form action="<%=request.getContextPath()%>/bsuser/register" method="post">
                <div class="table-responsive">
                    <table class="table">
                        <tr>
                            <td>
                                <label for="userNome"> <strong>Nome</strong> </label>
                                <input type="text" class="form-control" name="userNome" size="45" value="<c:out value='${user.fullName}' />">
                            </td>
                        </tr>    
                        <tr>
                            <td>
                                <label for="userEmail"> <strong> Email</strong> </label>
                                <input type="text" class="form-control" name="userEmail" size="40" value="<c:out value='${user.email}'/>">
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label for="userPassword"> <strong> Senha</strong> </label>
                                <input type="password" class="form-control" name="userPassword" value="<c:out value='${user.password}'/>">
                            </td>
                        </tr>
                        <td>
                            <small  class="form-text text-muted">Já é Cadastrado?</small>
                            <small  class="form-text text-muted"><a href="<%=request.getContextPath()%>/loginPage.jsp">Faça o Login</a></small>
                        </td>
                        <tr>
                            
                            <td colspan="2" align="center">
                               <button type="submit" class="btn btn-primary">Enviar</button>
                            </td>
                        </tr>
                    </table>
                </div>
            </form>
        </div>
    </body>
</html>
