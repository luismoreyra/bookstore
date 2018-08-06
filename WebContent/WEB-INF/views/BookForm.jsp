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
        <h2>
        <c:if test="${book != null}">
            Editar libro
        </c:if>
        <c:if test="${book == null}">
            Agregar nuevo libro
        </c:if>
        </h2>
        <c:if test="${book != null}">
            <form action="update" method="post" class="form">
        </c:if>
        <c:if test="${book == null}">
            <form action="insert" method="post" class="form">
        </c:if>
        <table class="table table-striped table-bordered">
            <c:if test="${book != null}">
                <input type="hidden" name="id" value="<c:out value='${book.id}' />" />
            </c:if>
            <tr>
                <th scope="row">Title: </th>
                <td>
                    <input type="text" name="title" class="input-form" size="80"
                            value="<c:out value='${book.title}' />"
                        />
                </td>
            </tr>
            <tr>
                <th scope="row">Author: </th>
                <td>
                    <input type="text" name="author" class="input-form" size="45"
                            value="<c:out value='${book.author}' />"
                    />
                </td>
            </tr>
            <tr>
                <th scope="row">Price: </th>
                <td>
                    <input type="text" name="price" class="input-form" size="5"
                            value="<c:out value='${book.price}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Guardar" class="btn btn-primary" />
                </td>
            </tr>
        </table>
        </form>
    </div>
</body>
</html>
