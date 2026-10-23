
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Book</title>
    <style>
       ```css
body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 30px 15px;
    background: #f4f7fb;
    color: #333;
}

.form-box {
    max-width: 550px;
    margin: 20px auto;
    background: #fff;
    padding: 30px;
    border-radius: 10px;
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
}

h2 {
    text-align: center;
    color: #17365d;
    margin-bottom: 25px;
}

label {
    display: block;
    margin-top: 15px;
    font-weight: bold;
}

input,
select {
    display: block;
    width: 100%;
    padding: 11px;
    box-sizing: border-box;
    margin-top: 6px;
    border: 1px solid #ccd5df;
    border-radius: 5px;
    font-size: 15px;
    background: #fff;
}

input:focus,
select:focus {
    outline: none;
    border-color: #2878c8;
    box-shadow: 0 0 4px rgba(40, 120, 200, 0.3);
}

button,
.view-btn {
    display: inline-block;
    padding: 11px 20px;
    margin-top: 22px;
    border: none;
    border-radius: 5px;
    font-size: 15px;
    font-weight: bold;
    text-decoration: none;
    text-align: center;
    cursor: pointer;
    box-sizing: border-box;
}

button {
    background: #17365d;
    color: white;
}

button:hover {
    background: #0d2745;
}

.view-btn {
    margin-left: 10px;
    background: #e3edf8;
    color: #17365d;
    border: 1px solid #b7cce3;
}

.view-btn:hover {
    background: #d0e2f5;
}

.error {
    color: #c0392b;
    background: #fdecea;
    padding: 10px;
    border-radius: 5px;
}
```
    </style>
</head>
<body>
<div class="form-box">
    <h2>Add Book</h2>

    <c:if test="${not empty requestScope.errorMessage}">
        <p class="error">
            <c:out value="${requestScope.errorMessage}"/>
        </p>
    </c:if>

    <form action="<%= request.getContextPath() %>/AddBookServlet"
          method="post">

        <label for="isbn">ISBN</label>
        <input type="text" id="isbn" name="isbn" required>

        <label for="title">Book Title</label>
        <input type="text" id="title" name="title" required>

        <label for="author">Author</label>
        <input type="text" id="author" name="author" required>

        
		<label for="category">Category</label>
		<select id="category" name="category" required>
		    <option value="">Select Category</option>
		    <option value="DATABASE">DATABASE</option>
		    <option value="PROGRAMMING">PROGRAMMING</option>
		    <option value="NETWORKING">NETWORKING</option>
		</select>

       

        <label for="price">Price</label>
        <input type="number" id="price" name="price"
               min="0" step="0.01" required>

        <label for="quantity">Available Quantity</label>
        <input type="number" id="quantity" name="quantity"
               min="0" required>

        <label for="publishedYear">Published Year</label>
        <input type="number" id="publishedYear" name="publishedYear"
               min="1000" max="9999" required>

        <button type="submit">Save Book</button>

        <a href="${pageContext.request.contextPath}/ViewBooksServlet">
            View Books
        </a>
    </form>
</div>
</body>
</html>
