<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Даша
  Date: 23.08.2020
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book Page</title>
    <link rel="shortcut icon" href="images/shelf.png" type="image/x-icon">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="styles/book_page.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
</head>
<body>
<header id="pageHeader"></header>
<article id="mainArticle">
    <h2>Add book info</h2>
    <jsp:useBean id="book" scope="request" type="app.dbService.entity.Book"/>

    <form action="/add" method="post" enctype="multipart/form-data" class="needs-validation" novalidate="">
        <c:if test="${book != null}">
            <input type="hidden" name="id" value="<c:out value='${book.id}' />"/>
        </c:if>
        <div class="custom-file">
            <label for="cover">Cover</label>
            <input type="file" class="custom-file-input" id="cover" name="cover" accept="image/jpeg, image/png">
            <label class="custom-file-label" for="cover">Choose file for cover</label>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <div class="form-group">
                    <label for="title">Title*</label>
                    <input type="text" class="form-control" id="title" placeholder="Pride and Prejudice" name="title"
                           required>
                    <div class="valid-feedback">
                        Looks good!
                    </div>
                    <div class="invalid-feedback">
                        Please provide a valid title.
                    </div>
                </div>
            </div>
            <div class="form-group col-md-6">
                <div class="form-group">
                    <label for="author">Author(-s)*</label>
                    <input type="text" class="form-control" id="author" placeholder="Jane Austen" name="author"
                           required pattern="^([a-zA-Z]+\,?\s?)+">
                    <div class="valid-feedback">
                        Looks good!
                    </div>
                    <div class="invalid-feedback">
                        Please provide a valid author's name.
                    </div>
                </div>
            </div>
            <div class="form-group col-md-6">
                <div class="form-group">
                    <label for="genre">Genre(-s)*</label>
                    <input type="text" class="form-control" id="genre" placeholder="Romance novel" name="genre"
                           required pattern="^([a-zA-Z]+\,?\s?)+">
                    <div class="valid-feedback">
                        Looks good!
                    </div>
                    <div class="invalid-feedback">
                        Please provide a valid genre.
                    </div>
                </div>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <div class="form-group">
                    <label for="publisher">Publisher*</label>
                    <input type="text" class="form-control" id="publisher" placeholder="J. Thomas"
                           value="${book.publisher}" name="publisher" required>
                    <div class="valid-feedback">
                        Looks good!
                    </div>
                    <div class="invalid-feedback">
                        Please provide a valid publisher's name.
                    </div>
                </div>
            </div>
            <div class="form-group col-md-6">
                <div class="form-group">
                    <label for="publish_date">Publish Date*</label>
                    <input type="date" class="form-control" id="publish_date" placeholder="23.01.1999" name="date"
                           required>
                    <div class="valid-feedback">
                        Looks good!
                    </div>
                    <div class="invalid-feedback">
                        Please provide a valid publish date.
                    </div>
                </div>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <div class="form-group">
                    <label for="count">Page count*</label>
                    <input type="number" class="form-control" id="count" min="0" placeholder="350" name="pages"
                           required>
                    <div class="valid-feedback">
                        Looks good!
                    </div>
                    <div class="invalid-feedback">
                        Please provide a valid page count.
                    </div>
                </div>
            </div>
            <div class="form-group col-md-6">
                <div class="form-group">
                    <label for="isbn">ISBN*</label>
                    <input type="number" class="form-control" id="isbn" placeholder="98752345689" name="isbn" required
                           pattern="[0-9]{13}$">
                    <div class="valid-feedback">
                        Looks good!
                    </div>
                    <div class="invalid-feedback">
                        Please provide a valid ISBN.
                    </div>
                </div>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <div class="form-group">
                    <label for="description">Description</label>
                    <textarea class="form-control" id="description" rows="3" name="description"
                              placeholder="The novel follows the character development of Elizabeth Bennet, the dynamic protagonist of the book who learns about the repercussions of hasty judgments and comes to appreciate the difference between superficial goodness and actual goodness."></textarea>
                </div>
            </div>
            <div class="form-group col-md-2">
                <div class="form-group">
                    <label for="total_amount">Total Amount*</label>
                    <input type="number" class="form-control" id="total_amount" min="0" name="total_amount"
                           placeholder="2"
                           required>
                    <div class="valid-feedback">
                        Looks good!
                    </div>
                    <div class="invalid-feedback">
                        Please provide a valid total amount.
                    </div>
                </div>
            </div>
        </div>
        <div id="saveDiscard" style="text-align: center">
            <button type="submit" class="btn btn-secondary" name="submit">Save</button>
            <button type="button" class="btn btn-secondary" onclick="location.href='/list'">Discard</button>
        </div>
    </form>
    <script>
        (function () {
            'use strict';
            window.addEventListener('load', function () {
                var forms = document.getElementsByClassName('needs-validation');
                var validation = Array.prototype.filter.call(forms, function (form) {
                    form.addEventListener('submit', function (event) {
                        if (form.checkValidity() === false) {
                            event.preventDefault();
                            event.stopPropagation();
                        }
                        form.classList.add('was-validated');
                    }, false);
                });
            }, false);
        })();
    </script>
</article>
<nav id="mainNav">
    <h3 id="navTitle">Navigation</h3>
    <div id="inner-grid-nav">
        <button type="button" class="btn btn-dark" onclick="location.href='/list'">Main Page</button>
        <button type="button" class="btn btn-dark">Readers Page</button>
    </div>
</nav>

<footer id="pageFooter"></footer>
<script type="text/javascript" src="${pageContext.request.contextPath}/control/book_page.js"></script>
<!-- Подключаем jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Подключаем плагин Popper -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>

<!-- Подключаем Bootstrap JS -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>
</body>
</html>
