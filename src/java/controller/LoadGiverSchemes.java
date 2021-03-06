/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EmpDao;
import dao.ScholarshipDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Clock;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.SchSchemeModel;
import model.UserModel;

/**
 *
 * @author SarthakJ
 */
public class LoadGiverSchemes extends HttpServlet {

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
        
        PrintWriter out=response.getWriter();
        
        ServletContext context=getServletContext();
        HttpSession session=request.getSession();
        
        UserModel um=(UserModel)session.getAttribute("UserModel");
        EmpDao ed =new EmpDao();
        
        String eusername=um.getEusername();
        if(ed.isEmp(eusername,context))
        {
            System.out.println("in controller");
            //ArrayList<SchSchemeModel> alsch=new ArrayList<>();
            ScholarshipDao sd=new ScholarshipDao();
            
            ArrayList<SchSchemeModel> alsch;
            alsch = sd.fetchScholarshipSch(eusername,context);
            
            session.setAttribute("FetchedSch", alsch);
            System.out.println("in controller");
            if(alsch!=null)
            System.out.println("in controller");
            
            response.sendRedirect("giverScholarship.jsp");
        }
        
        else
        {
            out.println("<h1><strong>You're Not an Employee</strong></h1>");
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
