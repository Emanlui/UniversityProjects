/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.mongodb.DBCursor;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.ConexionMongo;
import modelo.ConexionOracle;

/**
 *
 * @author emanlui
 */
public class Controlador extends HttpServlet {

    
                ConexionMongo bdm = new ConexionMongo();
                ConexionOracle bdo = new ConexionOracle();
                
                
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
              
                
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
               
                
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
                bdm.conectar();
                    try {
                        bdo.conectar();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
            
                String accion = request.getParameter("accion");
                if(accion.equals("BuscarE")){
                    this.BuscarE(request,response);
                }else if(accion.equals("Insertar")){
                    try {
                        this.Insertar(request, response);
                    } catch (SQLException ex) {
                        Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else if(accion.equals("BuscarA")){
                  
                    try {
                        this.BuscarA(request,response);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }else if(accion.equals("Actualizar")){
                    this.Actualizar(request,response);
                }else if(accion.equals("Eliminar")){
                    this.Eliminar(request,response);
                }
             
    }
    
    private void BuscarE(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,IOException{
        
                response.setContentType("text/html;charset=UTF-8");
                
                String busqueda;
        
                busqueda = request.getParameter("busqueda");

               
                DBCursor cursor = bdm.obtenerDatos(busqueda);
             
                request.setAttribute("datoDeLaBusqueda", cursor);
                
                //try (PrintWriter out = response.getWriter()) {
                    RequestDispatcher rd = request.getRequestDispatcher("buscarVista.jsp");
                    //response.sendRedirect("buscarVista.jsp");
                    rd.forward(request, response);
                    //this.getServletContext().getRequestDispatcher("buscarVista.jsp").forward(request, response);
                //}
    }
   
    
    private void BuscarA(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,IOException, ClassNotFoundException, SQLException{
        
       
        String busqueda = request.getParameter("busqueda");
        int datoBuscar = Integer.parseInt(request.getParameter("busqueda"));
        
        String resultado = bdo.getEspecie(datoBuscar);
        String estado = bdo.getEstado(datoBuscar);
        String descrip = bdo.getSpecies_page(datoBuscar);
        
        request.setAttribute("datoArbol", resultado);
        request.setAttribute("estadoRes", estado);
        request.setAttribute("descripcionRes", descrip);
                
        RequestDispatcher rd = request.getRequestDispatcher("buscarVista.jsp");
        rd.forward(request, response);
        
        
    }
    
    
    private void Insertar(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,IOException, SQLException, ClassNotFoundException{
                
               
                
                String stringPath = request.getParameter("imgInfo");  
                
                String excelPath = request.getParameter("excelFile");  
                
                if(!excelPath.equals("")){
                    excelPath = excelPath.substring(7, excelPath.length());   // Corta el path para que lo pueda insertar    
                    bdo.insertarExcel(excelPath);
                    
                }
                if(!stringPath.equals("")){
                    stringPath = stringPath.substring(7, stringPath.length());   // Corta el path para que lo pueda insertar
                }
                
                
                     
                    //bdo.insertarSpeciesPage(Integer.parseInt(request.getParameter("id")),request.getParameter("Descripcion"),request.getParameter("cientifico"));
                    //bdo.insertarStatus(Integer.parseInt(request.getParameter("id")),request.getParameter("estado"));
                
               
                int id = Integer.parseInt(request.getParameter("id"));
                
              
                String dueno = request.getParameter("dueno");
                String autor = request.getParameter("autor");
                
                bdm.insertar(id,dueno ,autor ,stringPath);
              
                RequestDispatcher rd = request.getRequestDispatcher("InsertarVista.jsp");
                
                rd.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void Actualizar(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException,IOException{
        
            bdm.actualizar(Integer.parseInt(request.getParameter("id")),request.getParameter("autor"),request.getParameter("dueno"),request.getParameter("imgInfo"));
            RequestDispatcher rd = request.getRequestDispatcher("ActualizarVista.jsp");
                    //response.sendRedirect("buscarVista.jsp");
                    rd.forward(request, response);
    }

    private void Eliminar(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException,IOException{
           
            bdm.eliminar(Integer.parseInt(request.getParameter("id")));
            
            RequestDispatcher rd = request.getRequestDispatcher("EliminarVista.jsp");
            
            rd.forward(request, response);
        
    }

}
