package com.example.cryptobase;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;

/**
 * This servlet acts as a page controller for the app,
 * handling all requests from the user.
 */
@WebServlet(name = "CoinControllerServlet", value = "/")
public class CoinControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CoinDAO coinDAO;

    @Override
    public void init() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource dataSource = (DataSource) envContext.lookup("jdbc/CoinbaseDB");
            coinDAO = new CoinDAO(dataSource);
        } catch (NamingException ex) {
            System.err.println(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/insert":
                    insertCoin(request, response);
                    break;
                case "/update":
                    updateCoin(request, response);
                    break;
                default:
                    listCoins(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/delete":
                    deleteCoin(request, response);
                    break;
                default:
                    listCoins(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listCoins(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Coin> coins = coinDAO.listAllCoins();
        request.setAttribute("coins", coins);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("coinList.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("coinForm.jsp");
        requestDispatcher.forward(request, response);
    }

    private void insertCoin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String abbreviation = request.getParameter("abbreviation");
        float price = Float.parseFloat(request.getParameter("price"));
        long marketCap = Long.parseLong(request.getParameter("marketCap"));

        Coin coin = new Coin(name, abbreviation, price, marketCap);
        coinDAO.addCoin(coin);
        response.sendRedirect("/");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        Coin coin = coinDAO.getCoin(id);
        request.setAttribute("coin", coin);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("coinForm.jsp");
        requestDispatcher.forward(request, response);
    }

    private void updateCoin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String abbreviation = request.getParameter("abbreviation");
        float price = Float.parseFloat(request.getParameter("price"));
        long marketCap = Long.parseLong(request.getParameter("marketCap"));

        Coin coin = new Coin(id, name, abbreviation, price, marketCap);
        coinDAO.updateCoin(coin);
        response.sendRedirect("/");
    }

    private void deleteCoin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        coinDAO.deleteCoin(id);
        response.sendRedirect("/");
    }

    @Override
    public void destroy() {
    }
}