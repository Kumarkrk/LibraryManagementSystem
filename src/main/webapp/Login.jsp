
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - Library Management System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #eaf2f8;
            margin: 0;
        }
        .login-box {
            width: 350px;
            margin: 90px auto;
            padding: 28px;
            background: white;
            border-radius: 8px;
            box-shadow: 0 4px 12px #ccd5df;
        }
        h2 { text-align: center; color: #17365d; }
        label { display: block; margin-top: 15px; }
        input {
            width: 100%;
            padding: 10px;
            margin-top: 6px;
            box-sizing: border-box;
        }
        button {
            width: 100%;
            padding: 11px;
            margin-top: 20px;
            background: #17365d;
            color: white;
            border: 0;
            cursor: pointer;
        }
        .error { color: #c0392b; }
    </style>
</head>
<body>
    <div class="login-box">
        <h2>Library Management System</h2>

        <c:if test="${not empty requestScope.errorMessage}">
            <p class="error">
                <c:out value="${requestScope.errorMessage}"/>
            </p>
        </c:if>

        <c:if test="${not empty sessionScope.errorMessage}">
            <p class="error">
                <c:out value="${sessionScope.errorMessage}"/>
            </p>
            <c:remove var="errorMessage" scope="session"/>
        </c:if>

        <form action="<%= request.getContextPath() %>/LoginServlet"
              method="post">
            <label for="username">Username</label>
            <input type="text" id="username" name="username" required>

            <label for="password">Password</label>
            <input type="password" id="password" name="password" required>

            <button type="submit">Login</button>
        </form>
    </div>
</body>
</html>
