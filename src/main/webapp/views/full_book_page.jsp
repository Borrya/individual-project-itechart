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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
</head>
<body>
<header id="pageHeader"></header>
<article id="mainArticle">
    <h2>Book Info</h2>
    <button type="button" class="btn btn-light" style="margin-bottom: 11px">Edit</button>
    <form id="bookForm">
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
            <div class="form-group col-md-4">
                <div class="form-group">
                    <label for="total_amount">Status</label>
                    <input class="form-control" type="text" placeholder="Available" readonly>
                </div>
            </div>
        </div>
    </form>
    <h3 id="recordsList">Borrow Records List</h3>
    <button type="button" class="btn btn-light" data-toggle="modal" data-target="#addBorrowRecordModal" style="margin-bottom: 11px">Add</button>
    <table class="table table-hover" style="background-color: antiquewhite; text-align: center; border-color: #82694d; font-family: Calibri">
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
            <td><a style="color: #1d2124" onclick="editBorrowRecord()">Daria</a></td>
            <td>borrya@org.com</td>
            <td>08.08.2020</td>
            <td>1 month</td>
            <td>08.09.2020</td>
        </tr>
        </tbody>
    </table>
    <!-- Modal Window for Adding New Record -->
    <div class="modal fade" id="addBorrowRecordModal" tabindex="-1" role="dialog" aria-labelledby="modalCenterTitle" aria-hidden="true">
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
                        <div class="form-group col-md-6">
                            <div class="form-group">
                                <label for="addEmail">Reader email address*</label>
                                <input type="email" class="form-control" id="addEmail" placeholder="name@example.com" required>
                            </div>
                        </div>
                        <div class="form-group col-md-6">
                            <div class="form-group">
                                <label for="addName">Reader name*</label>
                                <input type="text" class="form-control" id="addName" placeholder="Reader name.." required>
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
    <div class="modal fade" id="editBorrowRecordModal" tabindex="-1" role="dialog" aria-labelledby="modalCenterTitle" aria-hidden="true">
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
                        <div class="form-group col-md-4">
                            <div class="form-group">
                                <label for="editName">Reader name*</label>
                                <input type="text" class="form-control" id="editName" placeholder="Reader name...">
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
        <button type="button" class="btn btn-secondary">Save</button>
        <button type="button" class="btn btn-secondary" onclick="location.href='/'">Discard</button>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/control/full_book_page.js"></script>
<!-- Подключаем jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- Подключаем плагин Popper -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<!-- Подключаем Bootstrap JS -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous" ></script>
</body>
</html>
