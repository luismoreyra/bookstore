package com.java.spring.bookstore;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * BookController.java
 * Este controlador manejara todas las peticiones realizadas por el usuario.
 * @author Luis Moreyra
 */

@Controller
public class BookController {
    private static final long serialVersionUID = 1L;

    @Autowired
    private BookDAO bookDAO;

    @RequestMapping(value = {"/", "list"}, method = {RequestMethod.GET, RequestMethod.POST})
    private String listBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Book> listBook = bookDAO.listAllBooks();
        request.setAttribute("listBook", listBook);
        return "BookList";
    }

    @RequestMapping(value = "/new", method = {RequestMethod.GET, RequestMethod.POST})
    private String showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        return "BookForm";
    }

    @RequestMapping(value = "/edit", method = {RequestMethod.GET, RequestMethod.POST})
    private String showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Book existingBook = bookDAO.getBook(id);
        request.setAttribute("book", existingBook);
        return "BookForm";
    }

    @RequestMapping(value = "/insert", method = {RequestMethod.GET, RequestMethod.POST})
    private String insertBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        float price = Float.parseFloat(request.getParameter("price"));

        Book newBook = new Book(title, author, price);
        bookDAO.insertBook(newBook);

        List<Book> listBook = bookDAO.listAllBooks();
        request.setAttribute("listBook", listBook);
        return "BookList";
    }

    @RequestMapping(value = "/update", method = {RequestMethod.GET, RequestMethod.POST})
    private String updateBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        float price = Float.parseFloat(request.getParameter("price"));

        Book book = new Book(id, title, author, price);
        bookDAO.updateBook(book);

        List<Book> listBook = bookDAO.listAllBooks();
        request.setAttribute("listBook", listBook);
        return "BookList";
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
    private String deleteBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));

        Book book = new Book(id);
        bookDAO.deleteBook(book);

        List<Book> listBook = bookDAO.listAllBooks();
        request.setAttribute("listBook", listBook);
        return "BookList";
    }

}
