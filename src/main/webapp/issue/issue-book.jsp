
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Issue Book</title>

    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            padding: 30px;
            font-family: Arial, sans-serif;
            background-color: #f0f4f8;
            color: #333;
        }

        .container {
            max-width: 500px;
            margin: 50px auto;
            padding: 30px;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            color: #17365d;
            margin-bottom: 25px;
        }

        .error {
            color: #b02a37;
            background-color: #f8d7da;
            padding: 10px;
            border-radius: 5px;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
            color: #444;
        }

        input[type="number"] {
            width: 100%;
            padding: 12px;
            border: 1px solid #ced4da;
            border-radius: 5px;
            font-size: 15px;
        }

        input[type="number"]:focus {
            outline: none;
            border-color: #17365d;
            box-shadow: 0 0 5px rgba(23, 54, 93, 0.25);
        }

        button {
            width: 100%;
            padding: 12px;
            background-color: #17365d;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.2s;
        }

        button:hover {
            background-color: #245184;
        }

        .links {
            text-align: center;
            margin-top: 22px;
        }

        a {
            color: #17365d;
            text-decoration: none;
            font-weight: bold;
        }

        a:hover {
            text-decoration: underline;
        }

        @media (max-width: 600px) {
            body {
                padding: 15px;
            }

            .container {
                margin: 25px auto;
                padding: 22px;
            }
        }
    </style>
</head>

<body>
    <div class="container">
        <h2>Issue Book to Member</h2>

        <% if (request.getAttribute("errorMessage") != null) { %>
            <p class="error">
                <%= request.getAttribute("errorMessage") %>
            </p>
        <% } %>

        <form action="${pageContext.request.contextPath}/IssueBookServlet"
              method="post">

            <div class="form-group">
                <label for="memberId">Member ID:</label>
                <input type="number"
                       id="memberId"
                       name="memberId"
                       min="1"
                       required>
            </div>

            <div class="form-group">
                <label for="bookId">Book ID:</label>
                <input type="number"
                       id="bookId"
                       name="bookId"
                       min="1"
                       required>
            </div>

            <button type="submit">Issue Book</button>
        </form>

        <div class="links">
            <a href="${pageContext.request.contextPath}/ViewBooksServlet">
                View Books
            </a>
        </div>
    </div>
</body>
</html>
