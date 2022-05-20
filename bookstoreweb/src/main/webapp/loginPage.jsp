<%-- 
    Document   : Login
    Created on : 7 de mai de 2022, 10:06:45
    Author     : Jamily
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="./contents/headerTags.jsp"/>
    </head>
    <body>
        <div class="form-group">
            <div class="container" style="align-items: center">
                <h1 style="text-align: center" class="titulo">Login</h1>
                <form action="login" method="post">
                    <label for="email">Email:</label>
                    <input name="email" class="form-control" size="30" />
                    <br><br>
                    <label for="password">Password:</label>
                    <input type="password" class="form-control" name="password" size="30" />
                    <br>
                    <small  class="form-text text-muted">Não é Cadastrado?</small>
                    <small  class="form-text text-muted"><a href="<%=request.getContextPath()%>/bsuser/new">Cadastre-se</a></small>
                    <!--
                    Esse atributo MESSAGE será utilizado como retorno de
                   mensagem ao usuário caso
                    login inválido.
                    -->
                    <br>${messageJamily}<br><br>
                    <button type="submit" class="btn btn-primary">Enviar</button>        
                </form>
            </div>
        </div>
    </body>
</html>

