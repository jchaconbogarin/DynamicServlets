/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Boga
 */
@WebServlet(urlPatterns = {"/DynamicImplementation"})
public abstract class DynamicServlet extends HttpServlet
{
    protected String final_redirect;
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String HTTPAction)
            throws ServletException, IOException
    {
        assignRequestAndResponse(request, response);
        String action = request.getParameter("action");
        if (action == null) action = "index";
        
        try {
            executeAction(action, HTTPAction);
        }
        catch (InvocationTargetException ex) {
            try {
                throw ex.getCause();
            }
            catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        catch (NoSuchMethodException | IllegalAccessException ex) {
            ex.printStackTrace();
        }
    }

    protected void executeAction(String accion, String accionHTTP)
            throws ServletException, IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    protected void redirect()
            throws ServletException, IOException
    {
        RequestDispatcher view = request.getRequestDispatcher(final_redirect);
        view.forward(request, response);
    }
    
    protected void assignRequestAndResponse(HttpServletRequest request, HttpServletResponse response)
    {
        this.request = request;
        this.response = response;
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
            throws ServletException, IOException
    {
        processRequest(request, response, "get");
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
            throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        processRequest(request, response, "post");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Dynamic Servlet Desription";
    }// </editor-fold>

}
