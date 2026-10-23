
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Return Book | Library Management System</title>

<style>
* {
    box-sizing: border-box;
    margin: 0;
    padding: 0;
}

body {
    font-family: Arial, sans-serif;
    background: #f1f5f9;
    color: #1e293b;
}

.header {
    background: #0f172a;
    color: white;
    padding: 20px 5%;
    display: flex;
    justify-content: space-between;
    align-items: center;
    flex-wrap: wrap;
    gap: 15px;
}

.header h2 {
    font-size: 23px;
}

.header a {
    color: white;
    text-decoration: none;
    background: #334155;
    padding: 10px 16px;
    border-radius: 6px;
}

.container {
    max-width: 1100px;
    margin: 35px auto;
    padding: 0 20px;
}

.card {
    background: white;
    padding: 25px;
    border-radius: 10px;
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.07);
    margin-bottom: 25px;
}

.card h3 {
    margin-bottom: 20px;
    color: #0f172a;
}

.message {
    padding: 13px;
    margin-bottom: 18px;
    border-radius: 6px;
}

.success {
    background: #dcfce7;
    color: #166534;
}

.error {
    background: #fee2e2;
    color: #991b1b;
}

.return-form {
    display: flex;
    gap: 12px;
    flex-wrap: wrap;
}

input[type="number"] {
    flex: 1;
    min-width: 200px;
    padding: 12px;
    border: 1px solid #cbd5e1;
    border-radius: 6px;
    font-size: 15px;
}

button {
    border: none;
    padding: 11px 17px;
    border-radius: 6px;
    cursor: pointer;
    color: white;
    font-size: 14px;
}

.return-button {
    background: #2563eb;
}

.return-button:hover {
    background: #1d4ed8;
}

.table-wrapper {
    overflow-x: auto;
}

table {
    width: 100%;
    border-collapse: collapse;
    background: white;
}

th, td {
    text-align: left;
    padding: 14px;
    border-bottom: 1px solid #e2e8f0;
    white-space: nowrap;
}

th {
    background: #e2e8f0;
    color: #0f172a;
}

tr:hover td {
    background: #f8fafc;
}

.empty {
    text-align: center;
    padding: 25px;
    color: #64748b;
}

@media (max-width: 600px) {
    .container {
        margin: 20px auto;
        padding: 0 12px;
    }

    .card {
        padding: 16px;
    }

    .return-form {
        flex-direction: column;
    }

    input[type="number"] {
        width: 100%;
    }
}
</style>
</head>

<body>

<header class="header">
    <h2>Library Management System</h2>
    <a href="${pageContext.request.contextPath}/IssueHistoryServlet">
        Issue History
    </a>
</header>

<main class="container">

    <c:if test="${not empty sessionScope.successMessage}">
        <div class="message success">
            <c:out value="${sessionScope.successMessage}" />
        </div>
        <c:remove var="successMessage" scope="session" />
    </c:if>

    <c:if test="${not empty sessionScope.errorMessage}">
        <div class="message error">
            <c:out value="${sessionScope.errorMessage}" />
        </div>
        <c:remove var="errorMessage" scope="session" />
    </c:if>

    <section class="card">
        <h3>Return a Book by Issue ID</h3>

        <form class="return-form"
              action="${pageContext.request.contextPath}/ReturnBookServlet"
              method="post">

            <input type="number"
                   name="issueId"
                   placeholder="Enter issue ID"
                   min="1"
                   required>

            <button type="submit" class="return-button">
                Return Book
            </button>
        </form>
    </section>

    <section class="card">
        <h3>Currently Issued Books</h3>

        <div class="table-wrapper">
            <table>
                <thead>
                    <tr>
                        <th>Issue ID</th>
                        <th>Member Name</th>
                        <th>Book Title</th>
                        <th>Issue Date</th>
                        <th>Action</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="issue" items="${activeIssues}">
                        <tr>
                            <td>
                                <c:out value="${issue.id}" />
                            </td>

                            <td>
                                <c:out value="${issue.member.name}" />
                            </td>

                            <td>
                                <c:out value="${issue.book.title}" />
                            </td>

                            <td>
                                <c:out value="${issue.issueDate}" />
                            </td>

                            <td>
                                <form
                                    action="${pageContext.request.contextPath}/ReturnBookServlet"
                                    method="post">

                                    <input type="hidden"
                                           name="issueId"
                                           value="${issue.id}">

                                    <button type="submit"
                                            class="return-button">
                                        Return
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>

                    <c:if test="${empty activeIssues}">
                        <tr>
                            <td colspan="5" class="empty">
                                No books are currently issued.
                            </td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </section>

</main>

</body>
</html>
