/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package site.actions.checkout;

import database.Hibernate;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Cart;
import model.PgLog;
import model.PgOrderDetails;
import model.PgOrderStatus;
import model.PgOrders;
import model.PgUsers;
import org.hibernate.Session;

import DAO.LogDAO;
import DAO.OrderStatusDAO;
import DAO.ProductDAO;
import model.PgProductSales;
import model.PgProducts;
import service.UserAuthentication;

/**
 *
 * @author dangt
 */
@WebServlet(name = "CheckoutAction", urlPatterns = {"/checkout-action"})
public class CheckoutAction extends HttpServlet {

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
        response.sendRedirect(request.getContextPath() + "/site/layouts/accessdenied.jsp");
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
        processRequest(request, response);
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
        HttpSession httpSession = request.getSession();
        PgOrders cartc = (PgOrders) httpSession.getAttribute("mycart");
        UserAuthentication auth = (UserAuthentication) httpSession.getAttribute("authentic");

        if (auth == null) {
            response.sendRedirect(request.getContextPath() + "/site-login");
            return;
        }
        if (cartc == null) {
            cartc = new Cart();
        }
        PgOrders cart = new PgOrders();
        PgUsers user = auth.getUsers();
        if (!cartc.getPgOrderDetailses().isEmpty()) {
            cart.setPgUsers(user);
            cart.setOrderDate(new Date());
            PgOrderStatus status = new PgOrderStatus();
            status.setOrderStatusKey(1);
            cart.setPgOrderStatus(status);
            cart.setShipName(user.getFirstName() + " " + user.getLastName());
            cart.setShipAddress(user.getAddress());
            cart.setShipPhone(user.getPhoneNumber());
            cart.setPgOrderDetailses(cartc.getPgOrderDetailses());

            Session session = Hibernate.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(cart);
            session.getTransaction().commit();
            session.close();
            session = Hibernate.getSessionFactory().openSession();
            session.beginTransaction();
            for (PgOrderDetails ord : cart.getPgOrderDetailses()) {

                ord.setPgOrders(cart);
                ord.setUnitPrice(ord.getPgProducts().getUnitPrice());
                for (PgProductSales sale : ord.getPgProducts().getPgProductSaleses()) {
                    if (sale.getSalesStatus() == 1 && (sale.getEndDate().compareTo(new Date()) == 0 || sale.getEndDate().compareTo(new Date()) == 1)) {
                        if (sale.getIsPercent()) {
                            int saleval = ord.getPgProducts().getUnitPrice() * sale.getSaleValue() / 100;
                            ord.setUnitSale(saleval);
                        } else {
                            ord.setUnitSale(sale.getSaleValue());
                        }
                        break;
                    }
                }
                session.save(ord);
            }
            session.getTransaction().commit();
            session.close();;
            httpSession.setAttribute("mycart", new Cart());
            response.getWriter().print("success");
            Date Ngay = new Date();
        	SimpleDateFormat datefrmats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	String datestr = datefrmats.format(Ngay);
        	try {
        		Date now = datefrmats.parse(datestr);
        		String ms = "Khách hàng "+auth.getUsers().getUserId()+" đã yêu cầu đơn hàng có mã "+cart.getOrderId();
				 PgLog log = new PgLog(auth.getUsers(),now,ms,"");
				 new LogDAO().insertPgLog(log);
			} catch (Exception e) {
				// TODO: handle exception
			}
        } else {
            response.getWriter().print("cart empty");
        }
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
