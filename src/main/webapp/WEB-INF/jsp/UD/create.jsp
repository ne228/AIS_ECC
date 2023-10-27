<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<html lang="ru">

<head>
    <jsp:include page="../../template/_metaStyle.html" />
    <title></title>
</head>
<body>
<jsp:include page="../../template/header.html" />

<div class="container" >
    <form th:action="@{/yourMapping}" th:object="${CreateUD}" method="post">
        <input type="text" th:field="*{name}" />
        <span th:if="${#fields.hasErrors('name')}" th:errors="*{name}">Ошибка</span>
        <button type="submit">Отправить</button>
    </form>
</div>

<jsp:include page="../../template/footer.html"  />

</body>
</html>