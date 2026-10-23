
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Members</title>

<style>
    * {
        box-sizing: border-box;
        margin: 0;
        padding: 0;
        font-family: Arial, sans-serif;
    }

    body {
        background: #f0f8ff;
        padding: 30px;
        color: #333;
    }

    .container {
        max-width: 1250px;
        margin: auto;
        background: white;
        padding: 30px;
        border-radius: 12px;
        box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
    }

    h2 {
        text-align: center;
        color: #1565c0;
        margin-bottom: 25px;
    }

    .message {
        padding: 12px;
        margin-bottom: 15px;
        border-radius: 6px;
        text-align: center;
    }

    .success {
        background: #d4edda;
        color: #155724;
    }

    .error {
        background: #f8d7da;
        color: #721c24;
    }

    .top-actions {
        display: flex;
        justify-content: space-between;
        align-items: center;
        gap: 15px;
        flex-wrap: wrap;
        margin-bottom: 25px;
    }

    .search-form {
        display: flex;
        gap: 10px;
        flex-wrap: wrap;
    }

    input[type="text"] {
        padding: 10px 12px;
        width: 260px;
        border: 1px solid #ccc;
        border-radius: 6px;
        font-size: 14px;
    }

    .btn {
        display: inline-block;
        padding: 9px 13px;
        border: none;
        border-radius: 6px;
        text-decoration: none;
        font-size: 13px;
        cursor: pointer;
        color: white;
        white-space: nowrap;
    }

    .search-btn {
        background: #1976d2;
    }

    .search-btn:hover {
        background: #0d47a1;
    }

    .add-btn {
        background: #28a745;
        font-weight: bold;
    }

    .add-btn:hover {
        background: #218838;
    }

    .edit-btn {
        background: #ff9800;
    }

    .edit-btn:hover {
        background: #e68900;
    }

    .delete-btn {
        background: #dc3545;
    }

    .delete-btn:hover {
        background: #b02a37;
    }

    .action-buttons {
        display: flex;
        align-items: center;
        gap: 8px;
    }

    .table-wrapper {
        overflow-x: auto;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 10px;
    }

    th, td {
        padding: 13px 12px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }

    th {
        background: #1976d2;
        color: white;
        white-space: nowrap;
    }

    tbody tr:hover {
        background: #f5faff;
    }

    .no-data {
        text-align: center;
        padding: 25px;
        color: #777;
    }

    @media (max-width: 600px) {
        body {
            padding: 12px;
        }

        .container {
            padding: 18px;
        }

        .top-actions {
            align-items: stretch;
        }

        .search-form {
            width: 100%;
        }

        input[type="text"] {
            width: 100%;
        }
    }
</style>
</head>

<body>

<div class="container">

    <h2>View Members</h2>

    <!-- Success message -->
    <c:if test="${not empty sessionScope.successMessage}">
        <div class="message success">
            <c:out value="${sessionScope.successMessage}" />
        </div>
        <c:remove var="successMessage" scope="session" />
    </c:if>

    <!-- Error message -->
    <c:if test="${not empty sessionScope.errorMessage}">
        <div class="message error">
            <c:out value="${sessionScope.errorMessage}" />
        </div>
        <c:remove var="errorMessage" scope="session" />
    </c:if>

    <div class="top-actions">

        <!-- Search members -->
        <form class="search-form"
              action="${pageContext.request.contextPath}/SearchMemberServlet"
              method="get">

            <input type="text"
                   name="keyword"
                   placeholder="Search by name, email or phone"
                   value="<c:out value='${param.keyword}' />">

            <button type="submit" class="btn search-btn">
                Search
            </button>
        </form>

        <!-- Add Member -->
        <a class="btn add-btn"
           href="${pageContext.request.contextPath}/members/add-member.jsp">
            + Add Member
        </a>

    </div>

    <!-- Members table -->
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
                    <th>Actions</th>
                </tr>
            </thead>

            <tbody>
                <c:choose>
                    <c:when test="${not empty members}">

                        <c:forEach var="member" items="${members}">
                            <tr>
                                <td>
                                    <c:out value="${member.id}" />
                                </td>

                                <td>
                                    <c:out value="${member.name}" />
                                </td>

                                <td>
                                    <c:out value="${member.email}" />
                                </td>

                                <td>
                                    <c:out value="${member.phone}" />
                                </td>

                                <td>
                                    <c:out value="${member.address}" />
                                </td>

                                <td>
                                    <c:out value="${member.membershipDate}" />
                                </td>

                                <!-- Edit and Delete buttons -->
                                <td>
                                    <div class="action-buttons">

                                        <a class="btn edit-btn"
                                           href="${pageContext.request.contextPath}/EditMemberServlet?id=${member.id}">
                                            Edit
                                        </a>

                                        <form action="${pageContext.request.contextPath}/DeleteMemberServlet"
                                              method="post"
                                              onsubmit="return confirm('Are you sure you want to delete this member?');">

                                            <input type="hidden"
                                                   name="id"
                                                   value="<c:out value='${member.id}' />">

                                            <button type="submit"
                                                    class="btn delete-btn">
                                                Delete
                                            </button>
                                        </form>

                                    </div>
                                </td>
                            </tr>
                        </c:forEach>

                    </c:when>

                    <c:otherwise>
                        <tr>
                            <td colspan="7" class="no-data">
                                No members found.
                            </td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>

</div>

</body>
</html>
