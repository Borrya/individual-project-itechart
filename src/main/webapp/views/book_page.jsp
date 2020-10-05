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
    <c:if test="${book == null}">
        <h2>Add book info</h2>
    </c:if>
    <c:if test="${book != null}">
        <h2>Edit book info</h2>
    </c:if>
    <c:if test="${book == null}">
    <form action="/insert" method="post" enctype="multipart/form-data" class="needs-validation" novalidate="">
        </c:if>
        <c:if test="${book != null}">
        <form action="/update" method="post" class="needs-validation" novalidate="">
            </c:if>
            <c:if test="${book != null}">
                <input type="hidden" name="id" value="${book.id}">
            </c:if>
            <c:if test="${book == null}">
                <div class="custom-file">
                    <label for="cover">Cover</label>
                    <input type="file" class="custom-file-input" id="cover" name="cover" accept="image/jpeg, image/png">
                    <label class="custom-file-label" for="cover">Choose file for cover</label>
                </div>
            </c:if>
            <c:if test="${book != null}">
                <div class="custom-file">
                    <div class="text-right">
                        <img src="data:image/jpg;base64,<c:out value='${book.cover64}' />"
                             onerror="this.src='images/empty.png'"
                             alt="Cover for book"
                             style="width: 350px; height: 350px; position: relative; z-index: 1; margin-right: 40px; margin-top: 68px; box-shadow: 0px 0px 0px 6px #b38484, 0px 0px 0px 25px #fff">
                    </div>
                </div>
            </c:if>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <div class="form-group">
                        <label for="title">Title*</label>
                        <input type="text" class="form-control" id="title" placeholder="Pride and Prejudice"
                               name="title"
                               value="<c:out value='${book.title}' />" required>
                        <div class="valid-feedback">
                            Looks good!
                        </div>
                        <div class="invalid-feedback">
                            Please provide a valid title.
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <div class="form-group">
                        <label for="author">Author(-s)*</label>
                        <input type="text" class="form-control" id="author" placeholder="Jane Austen" name="author"
                               value="<c:out value='${book.author}' />" required pattern="^([a-zA-Z]+\,?\s?)+">
                        <div class="valid-feedback">
                            Looks good!
                        </div>
                        <div class="invalid-feedback">
                            Please provide a valid author's name.
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <div class="form-group">
                        <label for="genre">Genre(-s)*</label>
                        <input type="text" class="form-control" id="genre" placeholder="Romance novel" name="genre"
                               value="<c:out value='${book.genre}' />" required pattern="^([a-zA-Z]+\,?\s?)+">
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
                               value="<c:out value='${book.publisher}' />" name="publisher" required>
                        <div class="valid-feedback">
                            Looks good!
                        </div>
                        <div class="invalid-feedback">
                            Please provide a valid publisher's name.
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <div class="form-group">
                        <label for="publish_date">Publish Date*</label>
                        <input type="date" class="form-control" id="publish_date" placeholder="23.01.1999" name="date"
                               value="<c:out value='${book.date}' />" required>
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
                               value="<c:out value='${book.pages}' />" required>
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
                        <input type="number" class="form-control" id="isbn" placeholder="9875234568945" name="isbn"
                               required
                               value="<c:out value='${book.isbn}' />" pattern="[0-9]{13}$">
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
                                  value="<c:out value='${book.description}' />"
                                  placeholder="The novel follows the character development of Elizabeth Bennet, the dynamic protagonist of the book who learns about the repercussions of hasty judgments and comes to appreciate the difference between superficial goodness and actual goodness."></textarea>
                    </div>
                </div>
                <div class="form-group col-md-2">
                    <div class="form-group">
                        <label for="total_amount">Total Amount*</label>
                        <input type="number" class="form-control" id="total_amount" min="0" name="total_amount"
                               placeholder="2"
                               value="<c:out value='${book.amount}' />" required>
                        <div class="valid-feedback">
                            Looks good!
                        </div>
                        <div class="invalid-feedback">
                            Please provide a valid total amount.
                        </div>
                    </div>
                </div>
                <c:if test="${book != null}">
                    <div class="form-group col-md-4">
                        <div class="form-group">
                            <label for="status">Status</label>
                            <input class="form-control" type="text" value="<c:out value='${book.status}' />"
                                   name="status">
                        </div>
                    </div>
                </c:if>
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
        <c:if test="${book != null}">

        <h3 id="recordsList">Borrow Records List</h3>
        <button type="button" class="btn btn-light" data-toggle="modal" data-target="#addBorrowRecordModal"
                style="margin-bottom: 11px">Add
        </button>
        <table class="table table-hover"
               style="background-color: antiquewhite; text-align: center; border-color: #82694d; font-family: Calibri">
            <thead>
            <tr id="tr1">
                <th scope="col">#</th>
                <th scope="col">Reader Name</th>
                <th scope="col">Reader Email Address</th>
                <th scope="col">Borrow Date</th>
                <th scope="col">Due Date</th>
                <th scope="col">Return Date</th>
            </tr>
            </thead>
            <tbody id="tableBody">
            <tr>
                <th scope="row">1</th>
                <td>
                    <button type="button" class="btn btn-link" style="color: #212529" data-toggle="modal"
                            data-target="#editBorrowRecordModal">Daria
                    </button>
                <td>borrya@org.com</td>
                <td>08.08.2020</td>
                <td>1 month</td>
                <td>08.09.2020</td>
            </tr>
            </tbody>
        </table>
        <!-- Modal Window for Adding New Record -->
        <div class="modal fade" id="addBorrowRecordModal" tabindex="-1" role="dialog" aria-labelledby="modalCenterTitle"
             aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addModalLongTitle">Add borrow record</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div class="form-group col-md-6" style="max-width: 100%">
                                <div class="form-group">
                                    <label for="addEmail">Reader email address*</label>
                                    <input type="email" class="form-control" id="addEmail"
                                           placeholder="name@example.com"
                                           required>
                                </div>
                            </div>
                            <div class="form-group col-md-6" style="max-width: 100%">
                                <div class="form-group">
                                    <label for="addName">Reader name*</label>
                                    <input type="text" class="form-control" id="addName" placeholder="John" required>
                                </div>
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <label class="input-group-text" for="inputGroupSelect01">Time period</label>
                                </div>
                                <select class="custom-select" id="inputGroupSelect01">
                                    <option selected>Choose...</option>
                                    <option value="1">1 month</option>
                                    <option value="2">2 months</option>
                                    <option value="3">3 months</option>
                                    <option value="6">6 months</option>
                                    <option value="12">12 months</option>
                                </select>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Discard</button>
                        <button type="button" class="btn btn-primary">Save</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- Modal Window for Editing New Record -->
        <div class="modal fade" id="editBorrowRecordModal" tabindex="-1" role="dialog"
             aria-labelledby="modalCenterTitle"
             aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editModalLongTitle">Edit borrow record</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div class="form-group col-md-6" style="max-width: 100%">
                                <div class="form-group">
                                    <label for="editEmail">Reader email address</label>
                                    <input type="email" class="form-control" id="editEmail"
                                           placeholder="name@example.com"
                                           readonly>
                                </div>
                            </div>
                            <div class="form-group col-md-6" style="max-width: 100%">
                                <div class="form-group">
                                    <label for="editName">Reader name</label>
                                    <input type="text" class="form-control" id="editName" placeholder="John" readonly>
                                </div>
                            </div>
                            <div class="form-group col-md-4" style="max-width: 100%">
                                <div class="form-group">
                                    <label for="borrowDate">Borrow Date</label>
                                    <input type="number" class="form-control" id="borrowDate" placeholder="01.01.1999"
                                           required>
                                </div>
                            </div>
                            <div class="form-group col-md-4" style="max-width: 100%">
                                <div class="form-group">
                                    <label for="timePeriod">Time Period</label>
                                    <input type="number" class="form-control" id="timePeriod" placeholder="1 month"
                                           readonly>
                                </div>
                            </div>
                            <div class="form-group col-md-4" style="max-width: 100%">
                                <label for="status">Status</label>
                                <select id="status" class="form-control" required>
                                    <option selected>Choose...</option>
                                    <option>Returned</option>
                                    <option>Returned and Damaged</option>
                                    <option>Lost</option>
                                </select>
                            </div>
                            <div class="form-group col-md-6" style="max-width: 100%">
                                <div class="form-group">
                                    <label for="comment">Comment</label>
                                    <textarea class="form-control" id="comment" rows="2"></textarea>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Discard</button>
                        <button type="button" class="btn btn-primary">Save</button>
                    </div>
                </div>
            </div>
        </div>
        <div id="saveDiscard" style="text-align: center">
            <button type="button" class="btn btn-secondary" onclick="location.href='/list'">Save</button>
            <button type="button" class="btn btn-secondary" onclick="location.href='/list'">Discard</button>
        </div>
        </c:if>
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
