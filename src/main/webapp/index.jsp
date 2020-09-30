<%@ page import="static org.apache.coyote.http11.Constants.a" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%--
  Created by IntelliJ IDEA.
  User: Даша
  Date: 25.09.2020
  Time: 0:22
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Book Library</title>
    <link rel="shortcut icon" href="images/shelf.png" type="image/x-icon">
    <link rel="stylesheet" href="styles/main_page.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
</head>
<body>
<header id="pageHeader">
    <h1>Welcome to the Book Library</h1>
</header>
<article id="mainArticle">
    <h2>Book List</h2>
    <table class="table table-hover">
        <thead>
        <tr id="tr1">
            <th scope="col">#</th>
            <th scope="col">Title</th>
            <th scope="col">Author</th>
            <th scope="col">Publish Date</th>
            <th scope="col">Amount of available book copies</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody id="tableBody">

        <c:forEach var="book" items="${listBook}">
            <tr>
                <th scope="row"><c:out value='${book.id}'/></th>
                <td><a style="color: #1d2124"
                       href="${pageContext.request.contextPath}/edit?id=<c:out value='${book.id}'/>">${book.title}
                </a></td>
                <td><c:out value='${book.author}'/>
                </td>
                <td><c:out value='${book.date}'/>
                </td>
                <td><c:out value='${book.amount}'/>
                </td>
                <td>
                    <a style="color: #1d2124"
                       href="${pageContext.request.contextPath}/remove?id=<c:out value='${book.id}'/>">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <nav aria-label="pagination">
        <ul class="pagination justify-content-end">
            <ul class="pagination">
                <li class="page-item disabled">
                    <a class="page-link" href="#" tabindex="-1">1</a>
                </li>
                <li class="page-item"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
            </ul>
        </ul>
    </nav>
    <!-- Search Modal Window -->
    <div class="modal fade" id="search" style="background: #d8d8c4" tabindex="-1" role="dialog"
         aria-labelledby="modalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addModalLongTitle">Search filter</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group col-md-6" style="max-width: 100%">
                            <div class="form-group">
                                <label for="title">Title</label>
                                <input type="search" class="form-control" id="title" placeholder="Pride and Prejudice"
                                       required>
                            </div>
                        </div>
                        <div class="form-group col-md-6" style="max-width: 100%">
                            <div class="form-group">
                                <label for="author">Author(-s)</label>
                                <input type="search" class="form-control" id="author" placeholder="Jane Austen">
                            </div>
                        </div>
                        <div class="form-group col-md-6" style="max-width: 100%">
                            <div class="form-group">
                                <label for="genre">Genre(-s)</label>
                                <input type="search" class="form-control" id="genre" placeholder="Romance novel">
                            </div>
                        </div>
                        <div class="form-group col-md-6" style="max-width: 100%">
                            <div class="form-group">
                                <label for="description">Description</label>
                                <textarea class="form-control" id="description" rows="3"></textarea>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Discard</button>
                    <button type="button" class="btn btn-primary" id="searchModalButton">Search</button>
                </div>
            </div>
        </div>
    </div>
</article>
<nav id="mainNav">
    <h3>Navigation</h3>
    <div id="inner-grid-nav">
        <button type="button" class="btn btn-dark" data-toggle="modal" data-target="#search">Search</button>
        <button type="button" class="btn btn-dark">Readers Page</button>
    </div>
</nav>
<div id="siteAds">
    <div id="inner-grid-ads">
        <h4>Edit</h4>
        <button type="button" class="btn btn-light" onclick="location.href='/add'">Add</button>
    </div>
</div>
<footer id="pageFooter"></footer>

<script type="text/javascript" src="${pageContext.request.contextPath}/index.js"></script>
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
