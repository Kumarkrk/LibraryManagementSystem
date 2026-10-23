<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<%
    if (session.getAttribute("loggedInUser") == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Library Management System</title>

<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f2f6fc;
        margin: 0;
        color: #333;
    }

    header {
        background-color: #174a7e;
        color: white;
        text-align: center;
        padding: 25px;
    }

    header h1 {
        margin: 0;
    }

    nav {
        background-color: #10365c;
        padding: 15px;
        text-align: center;
    }

    nav a {
        color: white;
        text-decoration: none;
        margin: 0 12px;
        font-weight: bold;
    }

    nav a:hover {
        text-decoration: underline;
    }

    .container {
        width: 85%;
        max-width: 1000px;
        margin: 35px auto;
    }

    .welcome {
        background-color: white;
        padding: 20px;
        border-radius: 8px;
        margin-bottom: 25px;
        box-shadow: 0 2px 6px #ccc;
    }

    .menu {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(220px, 1fr));
        gap: 20px;
    }

    .card {
        background-color: white;
        padding: 25px;
        text-align: center;
        border-radius: 8px;
        box-shadow: 0 2px 6px #ccc;
    }

    .card h3 {
        color: #174a7e;
    }

    .card a {
        display: inline-block;
        background-color: #174a7e;
        color: white;
        text-decoration: none;
        padding: 10px 18px;
        border-radius: 5px;
    }

    .card a:hover {
        background-color: #0d3154;
    }

    footer {
        text-align: center;
        padding: 20px;
        margin-top: 35px;
        color: #666;
    }
</style>
</head>

<body>

<header>
    <h1>LIBRARY MANAGEMENT SYSTEM</h1>
    <p>Manage books, members and borrowing records</p>
</header>

<nav>
    <a href="<%= request.getContextPath() %>/dashboard.jsp">Home</a>
    <a href="<%= request.getContextPath() %>/ViewBooksServlet">Books</a>
    <a href="<%= request.getContextPath() %>/ViewMemberServlet">Members</a>
    <a href="<%= request.getContextPath() %>/IssueBookServlet">Issue Book</a>
    <a href="<%= request.getContextPath() %>/IssueHistoryServlet">Issue History</a>
    <a href="<%= request.getContextPath() %>/LogoutServlet">Logout</a>
</nav>

<div class="container">

    <div class="welcome">
        <h2>Welcome to the Dashboard</h2>
        <p>
            Use the options below to manage the library through
            a simple web interface.
        </p>
    </div>

    <div class="menu">

        <div class="card">
            <h3>1. Book Management</h3>
            <p>Add, view, search, update and delete books.</p>
            <a href="<%= request.getContextPath() %>/ViewBooksServlet">
                Manage Books
            </a>
        </div>

        <div class="card">
            <h3>2. Member Management</h3>
            <p>Register new members and manage member details.</p>
            <a href="<%= request.getContextPath() %>/ViewMemberServlet">
                Manage Members
            </a>
        </div>

        <div class="card">
            <h3>3. Issue Book</h3>
            <p>Issue an available book to a registered member.</p>
            <a href="<%= request.getContextPath() %>/IssueBookServlet">
                Issue Book
            </a>
        </div>

      <div class="card">
    <h3>4. Return Book</h3>
    <p>Process the return of an issued book.</p>

    <a href="<%= request.getContextPath() %>/ReturnBookPageServlet">
        View Issued Books
    </a>
</div>

<div class="card">
    <h3>5. View Issue History</h3>
    <p>View book issue and return records.</p>

    <a href="<%= request.getContextPath() %>/IssueHistoryServlet">
        View History
    </a>
</div>

    </div>

</div>

<footer>
    <p>&copy; 2026 Library Management System</p>
</footer>

</body>
</html>
