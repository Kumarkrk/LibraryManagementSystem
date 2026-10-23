
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Member - Library Management System</title>

    <style>
        * {
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            background: #f4f7fb;
            margin: 0;
            padding: 30px 15px;
            color: #333;
        }

        .container {
            max-width: 600px;
            margin: 20px auto;
            padding: 30px;
            background: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.08);
        }

        h2 {
            text-align: center;
            color: #17365d;
            margin-top: 0;
            margin-bottom: 25px;
        }

        .form-group {
            margin-bottom: 18px;
        }

        label {
            display: block;
            font-weight: bold;
            margin-bottom: 7px;
            color: #34495e;
        }

        input, textarea {
            width: 100%;
            padding: 11px 12px;
            border: 1px solid #ccd5df;
            border-radius: 5px;
            font-family: inherit;
            font-size: 14px;
        }

        textarea {
            min-height: 90px;
            resize: vertical;
        }

        input:focus, textarea:focus {
            outline: none;
            border-color: #17365d;
            box-shadow: 0 0 0 2px rgba(23, 54, 93, 0.1);
        }

        .btn {
            display: inline-block;
            width: 100%;
            padding: 12px;
            border: none;
            border-radius: 5px;
            background: #17365d;
            color: white;
            font-size: 15px;
            font-weight: bold;
            cursor: pointer;
        }

        .btn:hover {
            background: #244b7a;
        }

        .error {
            padding: 10px;
            background: #fdecea;
            color: #c0392b;
            border-radius: 5px;
        }

        .navigation {
            text-align: center;
            margin-top: 22px;
        }

        .navigation a {
            color: #17365d;
            text-decoration: none;
            font-weight: bold;
        }

        .navigation a:hover {
            text-decoration: underline;
        }
    </style>
</head>

<body>
    <div class="container">

        <h2>Edit Library Member</h2>

        <c:if test="${not empty requestScope.errorMessage}">
            <p class="error">
                <c:out value="${requestScope.errorMessage}"/>
            </p>
        </c:if>

        <form action="${pageContext.request.contextPath}/UpdateMemberServlet"
              method="post">

            <input type="hidden" name="id"
                   value="<c:out value='${member.id}'/>">

            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" id="name" name="name"
                       value="<c:out value='${member.name}'/>"
                       maxlength="100" required>
            </div>

            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email"
                       value="<c:out value='${member.email}'/>"
                       maxlength="150" required>
            </div>

            <div class="form-group">
                <label for="phone">Phone</label>
                <input type="tel" id="phone" name="phone"
                       value="<c:out value='${member.phone}'/>"
                       pattern="[0-9]{10}" maxlength="10"
                       title="Enter a 10-digit phone number" required>
            </div>

            <div class="form-group">
                <label for="address">Address</label>
                <textarea id="address" name="address"
                          maxlength="255" required><c:out value="${member.address}"/></textarea>
            </div>

            <div class="form-group">
                <label for="membershipDate">Membership Date</label>
                <input type="date" id="membershipDate"
                       name="membershipDate"
                       value="${member.membershipDate}" required>
            </div>

            <button type="submit" class="btn">Update Member</button>
        </form>

        <div class="navigation">
            <a href="${pageContext.request.contextPath}/ViewMemberServlet">
                &larr; Back to Members
            </a>
        </div>

    </div>
</body>
</html>
