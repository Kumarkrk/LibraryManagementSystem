
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Book</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 25px;
            background: #f4f7fb;
        }
        .form-box {
            max-width: 550px;
            margin: auto;
            padding: 25px;
            background: white;
            border-radius: 7px;
        }
        h2 { color: #17365d; }
        label {
            display: block;
            margin-top: 13px;
            font-weight: bold;
        }
        input {
            width: 100%;
            padding: 9px;
            margin-top: 5px;
            box-sizing: border-box;
        }
        button, a {
            display: inline-block;
            padding: 10px 15px;
            margin-top: 18px;
            border: none;
            border-radius: 4px;
            background: #17365d;
            color: white;
            text-decoration: none;
            cursor: pointer;
        }
        .error { color: #c0392b; }
    </style>
</head>
<body>
<div class="form-box">
    <h2>Edit Book Details</h2>

    <c:if test="${not empty requestScope.errorMessage}">
        <p class="error">
            <c:out value="${requestScope.errorMessage}"/>
        </p>
    </c:if>

    <c:if test="${empty book}">
        <p>Book details are unavailable. Select the book again.</p>
        <a href="${pageContext.request.contextPath}/ViewBooksServlet">
            Back to Books
        </a>
    </c:if>

    <c:if test="${not empty book}">
        <form action="<%= request.getContextPath() %>/UpdateBookServlet"
              method="post">

            <input type="hidden" name="id"
                   value="<c:out value='${book.id}'/>">

            <label for="isbn">ISBN</label>
            <input type="text" id="isbn" name="isbn"
                   value="<c:out value='${book.isbn}'/>" required>

            <label for="title">Title</label>
            <input type="text" id="title" name="title"
                   value="<c:out value='${book.title}'/>" required>

            <label for="author">Author</label>
            <input type="text" id="author" name="author"
                   value="<c:out value='${book.author}'/>" required>

	           <label for="category">Category</label>
	        <select>
	        <option>DATABASE</option>
	        <option>PROGRAMMING</option>
	        <option>NETWORKING</option>
	        
	        </select>
            <label for="price">Price</label>
            <input type="number" id="price" name="price"
                   min="0" step="0.01"
                   value="<c:out value='${book.price}'/>" required>

            <label for="quantity">Available Quantity</label>
            <input type="number" id="quantity" name="quantity"
                   min="0"
                   value="<c:out value='${book.quantity}'/>" required>

            <label for="publishedYear">Published Year</label>
            <input type="number" id="publishedYear" name="publishedYear"
                   min="1000" max="9999"
                   value="<c:out value='${book.publishedYear}'/>" required>

            <button type="submit">Update Book</button>
            <a href="${pageContext.request.contextPath}/ViewBooksServlet">
                Cancel
            </a>
        </form>
    </c:if>
</div>
</body>
</html>
