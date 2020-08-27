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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="styles/book_page.css">
    <!-- Подключаем Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
</head>
<body>
    <header id="pageHeader"></header>
    <article id="mainArticle">
        <h2>Edit book info</h2>
        <form>
            <div class="form-group">
                <label for="cover">Cover</label>
                <input type="file" class="form-control-file" id="cover">
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <div class="form-group">
                        <label for="title">Title*</label>
                        <input type="text" class="form-control" id="title" placeholder="Title of the book...">
                    </div>
                </div>
                <div class="form-group col-md-6">
                    <div class="form-group">
                        <label for="author">Author(-s)*</label>
                        <input type="text" class="form-control" id="author" placeholder="Author's name...">
                    </div>
                </div>
                <div class="form-group col-md-6">
                    <div class="form-group">
                        <label for="genre">Genre(-s)*</label>
                        <input type="text" class="form-control" id="genre" placeholder="Genre...">
                    </div>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <div class="form-group">
                        <label for="publisher">Publisher</label>
                        <input type="text" class="form-control" id="publisher" placeholder="Publisher's name...">
                    </div>
                </div>
                <div class="form-group col-md-6">
                    <div class="form-group">
                        <label for="publish_date">Publish Date*</label>
                        <input type="text" class="form-control" id="publish_date" placeholder="Publish date...">
                    </div>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <div class="form-group">
                        <label for="count">Page count*</label>
                        <input type="text" class="form-control" id="count" placeholder="Page count...">
                    </div>
                </div>
                <div class="form-group col-md-6">
                    <div class="form-group">
                        <label for="isbn">ISBN*</label>
                        <input type="text" class="form-control" id="isbn" placeholder="ISBN...">
                    </div>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <div class="form-group">
                        <label for="description">Description</label>
                        <textarea class="form-control" id="description" rows="3"></textarea>
                    </div>
                </div>
                <div class="form-group col-md-2">
                    <div class="form-group">
                        <label for="total_amount">Total Amount*</label>
                        <input type="text" class="form-control" id="total_amount">
                    </div>
                </div>
            </div>
        </form>
        <div id="saveDiscard" style="text-align: center">
            <button type="button" class="btn btn-secondary">Save</button>
            <button type="button" class="btn btn-secondary">Discard</button>
        </div>
    </article>
    <nav id="mainNav">
        <h3 id="navTitle">Navigation</h3>
        <div id="inner-grid-nav">
            <button type="button" class="btn btn-dark" onclick="location.href='/'">Main Page</button>
            <button type="button" class="btn btn-dark">Search</button>
            <button type="button" class="btn btn-dark">Readers Page</button>
        </div>
    </nav>

    <footer id="pageFooter"></footer>
    <!-- Подключаем jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <!-- Подключаем плагин Popper -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>

    <!-- Подключаем Bootstrap JS -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous" ></script>
</body>
</html>
