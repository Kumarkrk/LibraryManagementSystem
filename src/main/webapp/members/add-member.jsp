
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Member - Library Management System</title>

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
            background: #fff;
            padding: 30px;
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
            font-weight: 600;
            margin-bottom: 7px;
            color: #34495e;
        }

        input, textarea {
            width: 100%;
            padding: 11px 12px;
            border: 1px solid #ccd5df;
            border-radius: 5px;
            font-size: 14px;
            font-family: inherit;
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

        button {
            width: 100%;
            padding: 12px;
            margin-top: 8px;
            background: #17365d;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 15px;
            font-weight: bold;
            cursor: pointer;
        }

        button:hover {
            background: #244b7a;
        }

        .error {
            color: #c0392b;
            background: #fdecea;
            padding: 10px;
            border-radius: 5px;
            margin-top: 15px;
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

        @media (max-width: 500px) {
            .container {
                padding: 20px;
            }
        }
    </style>
</head>

<body>
    <div class="container">

        <h2>Register Library Member</h2>

        <form action="${pageContext.request.contextPath}/AddMemberServlet"
              method="post">

            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" id="name" name="name"
                       maxlength="100" required>
            </div>

            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email"
                       maxlength="150" required>
            </div>

            <div class="form-group">
                <label for="phone">Phone</label>
                <input type="tel" id="phone" name="phone"
                       pattern="[0-9]{10}" maxlength="10"
                       title="Enter a 10-digit phone number"
                       required>
            </div>

            <div class="form-group">
                <label for="address">Address</label>
                <textarea id="address" name="address"
                          maxlength="255" required></textarea>
            </div>

            <div class="form-group">
                <label for="membershipDate">Membership Date</label>
                <input type="date" id="membershipDate"
                       name="membershipDate" required>
            </div>

            <button type="submit">Register Member</button>
        </form>

        <c:if test="${not empty requestScope.errorMessage}">
            <p class="error">
                <c:out value="${requestScope.errorMessage}"/>
            </p>
        </c:if>

        <div class="navigation">
            <a href="${pageContext.request.contextPath}/ViewMemberServlet">
                View Members
            </a>
           
            <a href="${pageContext.request.contextPath}/index.jsp">
                Home
            </a>
        </div>

    </div>
</body>
</html>
