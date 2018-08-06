<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Administrador de libros</title>
    <link rel="stylesheet" href="<c:out value='resources/css/bootstrap.min.css' />">
    <script src="<c:out value='resources/js/jquery-3.2.1.slim.min.js' />" ></script>
    <script src="<c:out value='resources/js/bootstrap.min.js' />" ></script>
</head>
<body>
    <div class="container" align="center">
        <h1 class="display-4">Administrador de libros</h1>
        <h2>
            <a href="new" class="btn btn-success">Agregar nuevo libro</a>
            &nbsp;&nbsp;&nbsp;
            <a href="list" class="btn btn-primary">Listar todos los libros</a>
        </h2>
        <br>
        <h2>Lista de libros</h2>
        <table class="table table-striped table-bordered">
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Título</th>
                <th scope="col">Autor</th>
                <th scope="col">Precio</th>
                <th scope="col">Acciones</th>
            </tr>
            <c:forEach var="book" items="${listBook}">
                <tr>
                    <td><c:out value="${book.id}" /></td>
                    <td><c:out value="${book.title}" /></td>
                    <td><c:out value="${book.author}" /></td>
                    <td><c:out value="${book.price}" /></td>
                    <td>
                        <a href="edit?id=<c:out value='${book.id}' />" class="btn btn-warning">Editar</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="delete?id=<c:out value='${book.id}' />" class="btn btn-danger">Borrar</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
