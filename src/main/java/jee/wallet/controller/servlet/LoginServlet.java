package jee.wallet.controller.servlet;

import jee.wallet.model.ejb.UserEjb;
import jee.wallet.model.entities.Administrator;
import jee.wallet.model.entities.Client;
import jee.wallet.model.entities.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(value = "/login",name = "LoginServlet")
@ServletSecurity(
        @HttpConstraint(transportGuarantee =
                ServletSecurity.TransportGuarantee.CONFIDENTIAL))
public class LoginServlet extends HttpServlet {

    /*private static final String REQUEST_NOTICE = "notice";
    private static final String VIEW = "/WEB-INF/jsp/login.jsp";
    private static final String INDEX_VIEW = "/WEB-INF/jsp/index.jsp";
    private static final String REDIRECT_ADMIN_URL = "/admin";
    private static final String REDIRECT_USER_URL = "/wallet";
    private static final String USER_ATTR = "user";

    @EJB
    private UserEjb userEjb;

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Form<User> form = new LoginForm();

        User user = form.validateForm(request);

        if (user != null && form.isValid()) {
            HttpSession session = request.getSession(true);
            user.setLastConnection(new Date());
            userEjb.update(user);
            request.setAttribute(REQUEST_NOTICE, "Vous êtes connecté en tant que " + user.getUsername());
            String context = this.getServletContext().getContextPath();
            session.setAttribute(USER_ATTR, user);
            if (user instanceof Administrator) {
                response.sendRedirect(context + REDIRECT_ADMIN_URL);
            } else if (user instanceof Client) {
                response.sendRedirect(context + REDIRECT_USER_URL);
            }
        } else {
            processRequest(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession() != null) {
            this.getServletContext().getRequestDispatcher(INDEX_VIEW).forward(request, response);
        }
        processRequest(request, response);
    }*/
}
