
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Members - Library Management System</title>

    <style>
        * {
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 30px;
            background: #f4f7fb;
            color: #333;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 28px;
            background: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.08);
        }

        h2 {
            color: #17365d;
            margin-top: 0;
            margin-bottom: 24px;
        }

        .search-form {
            display: flex;
            gap: 10px;
            margin: 20px 0 25px;
            max-width: 650px;
        }

        input[type="text"] {
            flex: 1;
            min-width: 0;
            padding: 12px;
            border: 1px solid #ccd5df;
            border-radius: 5px;
            font-size: 14px;
        }

        input[type="text"]:focus {
            outline: none;
            border-color: #17365d;
            box-shadow: 0 0 0 2px rgba(23, 54, 93, 0.1);
        }

        button, .btn {
            display: inline-block;
            padding: 11px 17px;
            background: #17365d;
            color: #fff;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            font-size: 14px;
            cursor: pointer;
            white-space: nowrap;
        }

        button:hover, .btn:hover {
            background: #244b7a;
        }

        .table-wrapper {
            width: 100%;
            overflow-x: auto;
            margin-bottom: 25px;
        }

        table {
            width: 100%;
            min-width: 750px;
            border-collapse: collapse;
            background: #fff;
        }

        th, td {
            padding: 13px 12px;
            text-align: left;
            border-bottom: 1px solid #e0e5eb;
        }

        th {
            background: #dce8f5;
            color: #17365d;
            font-weight: 600;
            white-space: nowrap;
        }

        tbody tr:hover {
            background: #f5f9ff;
        }

        .empty-message {
            text-align: center;
            padding: 24px;
            color: #777;
        }

        .navigation {
            margin-top: 20px;
        }

        .navigation a {
            color: #17365d;
            text-decoration: none;
            font-weight: bold;
        }

        .navigation a:hover {
            text-decoration: underline;
        }

        @media (max-width: 600px) {
            body {
                padding: 12px;
            }

            .container {
                padding: 18px;
            }

            .search-form {
                width: 100%;
            }
        }
    </style>
</head>

<body>
    <div class="container">

        <h2>Search Library Members</h2>

        <form class="search-form"
              action="${pageContext.request.contextPath}/SearchMemberServlet"
              method="get">

            <input type="text"
                   name="keyword"
                   value="<c:out value='${param.keyword}'/>"
                   placeholder="Name, email, phone or ID"
                   required>

            <button type="submit">Search</button>
        </form>

        <div class="table-wrapper">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Address</th>
                        <th>Membership Date</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="member" items="${members}">
                        <tr>
                            <td><c:out value="${member.id}"/></td>
                            <td><c:out value="${member.name}"/></td>
                            <td><c:out value="${member.email}"/></td>
                            <td><c:out value="${member.phone}"/></td>
                            <td><c:out value="${member.address}"/></td>
                            <td><c:out value="${member.membershipDate}"/></td>
                        </tr>
                    </c:forEach>

                    <c:if test="${not empty param.keyword and empty members}">
                        <tr>
                            <td colspan="6" class="empty-message">
                                No matching members found.
                            </td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>

        <div class="navigation">
            <a href="${pageContext.request.contextPath}/ViewMemberServlet">
                &larr; View All Members
            </a>
            &nbsp; | &nbsp;
            <a href="${pageContext.request.contextPath}/index.jsp">
                Home
            </a>
        </div>

    </div>
</body>
</html>
