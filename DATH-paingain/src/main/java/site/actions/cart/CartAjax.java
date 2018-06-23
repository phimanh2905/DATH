/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package site.actions.cart;

import DAO.ProductDAO;
import database.Hibernate;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Cart;
import model.PgOrderDetails;
import model.PgProducts;

/**
 *
 * @author dangt
 */
@WebServlet(name = "cartajax", urlPatterns = {"/cart-action"})
public class CartAjax extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet cart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet cart at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pId = request.getParameter("pId") != null ? request.getParameter("pId") : "";
        String action = request.getParameter("action") != null ? request.getParameter("action") : "";
        PgProducts product = new ProductDAO().getPgProductsByID(Integer.parseInt(pId));
        PgOrderDetails order = new PgOrderDetails();
        order.setPgProducts(product);
        order.setUnitPrice(product.getUnitPrice());
        Hibernate.getSessionFactory().getCurrentSession().close();
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("mycart");
        if (cart == null) {
            cart = new Cart();
        }
        if (!action.equals("delete")) {
            if (action.equals("add")) {
                order.setAmount(1);
            } else if (action.equals("update")) {
                String quantity = request.getParameter("quantity");
                order.setAmount(Integer.parseInt(quantity));
            }
            cart.addToCart(order);
            session.setAttribute("mycart", cart);
            response.getWriter().print("success");
        } else {
            if (cart.removeFromCart(order)) {
                session.setAttribute("mycart", cart);
                response.getWriter().print("success");
            }else{
                response.getWriter().print("faild");
            }
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}