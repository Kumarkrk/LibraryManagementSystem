<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Books</title>

    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            padding: 30px;
            font-family: Arial, sans-serif;
            background-color: #f4f7fb;
            color: #333;
        }

        .container {
            max-width: 1200px;
            margin: 20px auto;
        }

        h2 {
            color: #17365d;
            margin-bottom: 20px;
        }

        h3 {
            color: #17365d;
            margin-top: 30px;
            margin-bottom: 15px;
        }

        .search-box {
            background-color: #fff;
            padding: 25px;
            border-radius: 8px;
            box-shadow: 0 3px 12px rgba(0, 0, 0, 0.08);
        }

        .form-grid {
            display: grid;
            grid-template-columns: repeat(2, minmax(0, 1fr));
            gap: 18px 25px;
        }

        .form-group {
            display: flex;
            flex-direction: column;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
            color: #444;
        }

        input,
        select {
            width: 100%;
            padding: 11px;
            border: 1px solid #cbd5e1;
            border-radius: 5px;
            font-size: 15px;
            background-color: #fff;
        }

        input:focus,
        select:focus {
            outline: none;
            border-color: #17365d;
            box-shadow: 0 0 4px rgba(23, 54, 93, 0.2);
        }

        .actions {
            margin-top: 22px;
            display: flex;
            flex-wrap: wrap;
            gap: 10px;
        }

        button,
        .btn {
            display: inline-block;
            padding: 11px 18px;
            border: none;
            border-radius: 5px;
            background-color: #17365d;
            color: white;
            font-size: 14px;
            font-weight: bold;
            text-decoration: none;
            text-align: center;
            cursor: pointer;
        }

        button:hover,
        .btn:hover {
            background-color: #244d7c;
        }

        .btn-home {
            background-color: #5c677d;
        }

        .btn-home:hover {
            background-color: #414b60;
        }

        .error {
            margin-top: 18px;
            padding: 12px;
            color: #842029;
            background-color: #f8d7da;
            border: 1px solid #f5c2c7;
            border-radius: 5px;
        }

        .table-wrapper {
            overflow-x: auto;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 3px 12px rgba(0, 0, 0, 0.06);
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
        }

        thead {
            background-color: #17365d;
            color: white;
        }

        th,
        td {
            padding: 13px 15px;
            border: 1px solid #dee2e6;
            text-align: left;
            white-space: nowrap;
        }

        tbody tr:nth-child(even) {
            background-color: #f6f8fb;
        }

        tbody tr:hover {
            background-color: #eaf1f8;
        }

        .no-results {
            padding: 20px;
            text-align: center;
            color: #666;
        }

        @media (max-width: 650px) {
            body {
                padding: 15px;
            }

            .form-grid {
                grid-template-columns: 1fr;
            }

            .search-box {
                padding: 18px;
            }

            .actions {
                flex-direction: column;
            }

            .actions button,
            .actions .btn {
                width: 100%;
            }
        }
    </style>
</head>

<body>
    <div class="container">

        <h2>Search Books</h2>

        <div class="search-box">
            <form action="${pageContext.request.contextPath}/SearchBookServlet"
                  method="get">

                <div class="form-grid">
                    <div class="form-group">
                        <label for="isbn">ISBN</label>
                        <input type="text"
                               id="isbn"
                               name="isbn"
                               value="<c:out value='${param.isbn}'/>"
                               placeholder="Enter ISBN">
                    </div>

                    <div class="form-group">
                        <label for="title">Book Title</label>
                        <input type="text"
                               id="title"
                               name="title"
                               value="<c:out value='${param.title}'/>"
                               placeholder="Enter book title">
                    </div>

                    <div class="form-group">
                        <label for="author">Author</label>
                        <input type="text"
                               id="author"
                               name="author"
                               value="<c:out value='${param.author}'/>"
                               placeholder="Enter author name">
                    </div>

                    <div class="form-group">
                        <label for="category">Category</label>
                        <select id="category" name="category">
                            <option value="">All Categories</option>
                            <option value="DATABASE"
                                ${param.category == 'DATABASE' ? 'selected' : ''}>
                                DATABASE
                            </option>
                            <option value="PROGRAMMING"
                                ${param.category == 'PROGRAMMING' ? 'selected' : ''}>
                                PROGRAMMING
                            </option>
                            <option value="NETWORKING"
                                ${param.category == 'NETWORKING' ? 'selected' : ''}>
                                NETWORKING
                            </option>
                        </select>
                    </div>
                </div>

                <div class="actions">
                    <button type="submit">Search Books</button>

                    <a class="btn"
                       href="${pageContext.request.contextPath}/ViewBooksServlet">
                        View All Books
                    </a>

                    <a class="btn btn-home"
                       href="${pageContext.request.contextPath}/index.jsp">
                        Home
                    </a>
                </div>
            </form>
        </div>

        <c:if test="${not empty requestScope.errorMessage}">
            <p class="error">
                <c:out value="${requestScope.errorMessage}" />
            </p>
        </c:if>

        <h3>Search Results</h3>

        <div class="table-wrapper">
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
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="book" items="${books}">
                        <tr>
                            <td><c:out value="${book.id}" /></td>
                            <td><c:out value="${book.isbn}" /></td>
                            <td><c:out value="${book.title}" /></td>
                            <td><c:out value="${book.author}" /></td>
                            <td><c:out value="${book.category}" /></td>
                            <td><c:out value="${book.price}" /></td>
                            <td><c:out value="${book.quantity}" /></td>
                            <td><c:out value="${book.publishedYear}" /></td>
                        </tr>
                    </c:forEach>

                    <c:if test="${not empty param and empty books}">
                        <tr>
                            <td colspan="8" class="no-results">
                                No matching books found.
                            </td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>

    </div>
</body>
</html>
