
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Issue History - Library Management System</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f7fb;
            margin: 25px;
        }

        h2 {
            color: #17365d;
        }

        nav {
            background-color: #17365d;
            padding: 14px;
            margin-bottom: 25px;
        }

        nav a {
            color: white;
            text-decoration: none;
            margin-right: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #d5dce5;
            padding: 12px;
            text-align: left;
        }

        th {
            background-color: #dce8f5;
            color: #17365d;
        }

        tr:nth-child(even) {
            background-color: #f8fafc;
        }

        .status-issued {
            color: #b45309;
            font-weight: bold;
        }

        .status-returned {
            color: #15803d;
            font-weight: bold;
        }
    </style>
</head>

<body>

    <nav>
        <a href="${pageContext.request.contextPath}/index.jsp">Home</a>
        <a href="${pageContext.request.contextPath}/ViewBooksServlet">Books</a>
        <a href="${pageContext.request.contextPath}/ViewMembersServlet">Members</a>
        <a href="${pageContext.request.contextPath}/issue-book.jsp">Issue Book</a>
        <a href="${pageContext.request.contextPath}/return-book.jsp">Return Book</a>
    </nav>

    <h2>Book Issue History</h2>

    <p style="color:green">
        <c:out value="${sessionScope.successMessage}"/>
    </p>

    <p style="color:red">
        <c:out value="${sessionScope.errorMessage}"/>
    </p>

    <table>
        <thead>
            <tr>
                <th>Issue ID</th>
                <th>Member</th>
                <th>Book</th>
                <th>Issue Date</th>
                <th>Return Date</th>
                <th>Status</th>
            </tr>
        </thead>

        <tbody>
            <c:forEach var="issue" items="${issues}">
                <tr>
                    <td><c:out value="${issue.id}"/></td>

                    <td>
                        <c:out value="${issue.member.id}"/>
                    </td>

                    <td>
                        <c:out value="${issue.book.id}"/>
                    </td>

                    <td>
                        <c:out value="${issue.issueDate}"/>
                    </td>

                    <td>
                        <c:choose>
                            <c:when test="${not empty issue.returnDate}">
                                <c:out value="${issue.returnDate}"/>
                            </c:when>
                            <c:otherwise>
                                Not Returned
                            </c:otherwise>
                        </c:choose>
                    </td>

                    <td>
                        <c:choose>
                            <c:when test="${issue.status == 'ISSUED'}">
                                <span class="status-issued">ISSUED</span>
                            </c:when>
                            <c:otherwise>
                                <span class="status-returned">RETURNED</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>

            <c:if test="${empty issues}">
                <tr>
                    <td colspan="6">No issue history found.</td>
                </tr>
            </c:if>
        </tbody>
    </table>

</body>
</html>
