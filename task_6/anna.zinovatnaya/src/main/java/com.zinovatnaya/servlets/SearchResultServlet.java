package com.zinovatnaya.servlets;

import com.zinovatnaya.Book;
import com.zinovatnaya.BooksDB;
import org.owasp.esapi.ESAPI;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/searchresult")
public class SearchResultServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    PrintWriter out = resp.getWriter();

    out.println("<html>");
    out.println("<body>");
    List<Book> books = BooksDB.getInstance().getBooksByAuthor(req.getParameter("author"));
    if (!books.isEmpty()) {
      for (Book b : books) {
        out.println("<p>");
        out.println(b.getName() + " " + b.getAuthor() + " " + b.getDescription());
        out.println("</p>");
      }
    } else {
      out.println("<p>");
      out.println("No books of this author");
      out.println("</p>");
    }

    out.println("<p>");
    out.println("<a href=\"/bookshop\">Return home</a>");
    out.println("</p>");

    out.println("</body>");
    out.println("</html>");
  }
}