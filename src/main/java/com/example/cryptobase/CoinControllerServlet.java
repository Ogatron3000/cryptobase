package com.example.cryptobase;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;

@WebServlet(name = "helloServlet", value = "/")
public class HelloServlet extends HttpServlet {
    private CoinDAO coinDAO;
    private DataSource dataSource;

    public void init() {
        coinDAO = new CoinDAO(dataSource);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getServletPath();
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}