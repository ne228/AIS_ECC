<jsp:useBean id="authRequest" scope="request" type="com.example.ais_ecc.models.AuthRequest"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<head>
    <title></title>
<%--    <%@ include file="template/_metaStyle.jsp" %>--%>
</head>
<body>
<!--<div th:insert="template/header :: header"></div>-->
<%--<jsp:include page="template/header.jsp" />--%>


<div class="container" >

    <h2>Login</h2>
    <form action="${pageContext.request.contextPath}/auth/loginAction" method="POST">
        <div class="form-group">
            <label for="email">Email</label>
            <input type="text" class="form-control" placeholder="Введите email" id="email" name="email" value="${authRequest.email}" />
            <c:if test="${not empty param.email}">
                <p class="text-danger">${param.email}</p>
            </c:if>
        </div>
        <div class="form-group">
            <label for="password">Пароль</label>
            <input type="password" class="form-control" placeholder="Введите пароль" id="password" name="password" value="${authRequest.password}" />
            <c:if test="${not empty param.password}">
                <p class="text-danger">${param.password}</p>
            </c:if>
        </div>

        <button type="submit" class="btn btn-primary">Войти</button>
    </form>
</div>

<%--<jsp:include page="../template/footer.jsp" />--%>

</body>
</html>
