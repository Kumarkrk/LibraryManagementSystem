
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Books</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 25px;
            background: #f4f7fb;
        }
        h2 { color: #17365d; }
        .actions { margin: 18px 0; }
        a, button {
            display: inline-block;
            padding: 8px 12px;
            margin: 3px;
            border: none;
            border-radius: 4px;
            background: #17365d;
            color: white;
            text-decoration: none;
            cursor: pointer;
        }
        .delete { background: #c0392b; }
        table {
            width: 100%;
            border-collapse: collapse;
            background: white;
        }
        th, td {
            border: 1px solid #d5dce5;
            padding: 10px;
            text-align: left;
        }
        th { background: #dce8f5; }
        .message { color: #16803c; }
        .error { color: #c0392b; }
        form.inline { display: inline; }
    </style>
</head>
<body>
    <h2>Library Management System - Books</h2>

    <c:if test="${not empty sessionScope.successMessage}">
        <p class="message">
            <c:out value="${sessionScope.successMessage}"/>
        </p>
        <c:remove var="successMessage" scope="session"/>
    </c:if>

    <c:if test="${not empty sessionScope.errorMessage}">
        <p class="error">
            <c:out value="${sessionScope.errorMessage}"/>
        </p>
        <c:remove var="errorMessage" scope="session"/>
    </c:if>

    <div class="actions">
        <a href="<%= request.getContextPath() %>/books/add-books.jsp">
            Add Book
        </a>
        <a href="<%= request.getContextPath() %>/SearchBookServlet">
            Search Books
        </a>
        <a href="<%= request.getContextPath() %>/index.jsp">
            Home
        </a>
    </div>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>ISBN</th>
                <th>Title</th>
                <th>Author</th>
                <th>Category</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Published Year</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="book" items="${books}">
                <tr>
                    <td><c:out value="${book.id}"/></td>
                    <td><c:out value="${book.isbn}"/></td>
                    <td><c:out value="${book.title}"/></td>
                    <td><c:out value="${book.author}"/></td>
                    <td><c:out value="${book.category}"/></td>
                    <td><c:out value="${book.price}"/></td>
                    <td><c:out value="${book.quantity}"/></td>
                    <td><c:out value="${book.publishedYear}"/></td>
                    <td>
                        <a href="<%= request.getContextPath() %>/UpdateBookServlet?id=${book.id}">
                            Edit
                        </a>

                        <form class="inline"
                              action="${pageContext.request.contextPath}/DeleteBookServlet"
                              method="post"
                              onsubmit="return confirm('Delete this book?');">
                            <input type="hidden" name="id"
                                   value="<c:out value='${book.id}'/>">
                            <button class="delete" type="submit">
                                Delete
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>

            <c:if test="${empty books}">
                <tr>
                    <td colspan="9">No books available.</td>
                </tr>
            </c:if>
        </tbody>
    </table>
</body>
</html>
