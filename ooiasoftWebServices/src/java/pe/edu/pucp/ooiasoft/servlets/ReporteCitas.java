/* 
 * Archivo: ReporteCitas.java
 * Grupo:   Grave Error de Proyecto
 * Integrantes: 
 *  - Oscar Dueñas
 *  - Christian Carhuancho
 *  - Franccesco Jaimes
 *  - Pilar Llantoy
 *  - Cesar Rafael
 * Fecha:   04/07/2021
 */

package pe.edu.pucp.ooiasoft.servlets;

import java.awt.Image;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import pe.edu.pucp.ooiasoft.config.DBManager;

public class ReporteCitas extends HttpServlet {

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
        try{
            //Referencia al reporte
            JasperReport reporte = 
                    (JasperReport) JRLoader.loadObject(
                    ReporteEventos.class.getResource(
                    "/pe/edu/pucp/ooiasoft/reportes/ReporteCitas.jasper"));
            String rutaSubreporte1 = ReporteEventos.class.getResource("/pe/edu/pucp/ooiasoft/reportes/SubReporteCitas.jasper").getPath();
            //Referencia a la imagen
            String rutaImagen = ReporteEventos.class.getResource("/pe/edu/pucp/ooiasoft/img/logoOOIA.PNG").getPath();
            Image logo = (new ImageIcon(rutaImagen)).getImage();
            
            //Referencia para el grafico
            String rutaGraf1 = ReporteEventos.class.getResource("/pe/edu/pucp/ooiasoft/reportes/SubRepCitasGrafico.jasper").getPath();
            String rutaGraf2 = ReporteEventos.class.getResource("/pe/edu/pucp/ooiasoft/reportes/SubRepCitasGrafico2.jasper").getPath();
            
            //Referencia para Rankin
            String rutaRankin = ReporteEventos.class.getResource("/pe/edu/pucp/ooiasoft/reportes/SubRepCitasRankin.jasper").getPath();
            
            //Arreglo de parametros que ingresan a la generación del reporte
            HashMap hm = new HashMap();
            hm.put("imagen", logo);
            hm.put("RutaSubreporte",rutaSubreporte1); 
            hm.put("rutaGraf",rutaGraf1); 
            hm.put("rutaGraf2",rutaGraf2); 
            hm.put("rutaRankin",rutaRankin); 
            
            //Objeto de conexion
            Connection con = DBManager.getInstance().getConnection();
            
            //Poblar el reporte
            JasperPrint jp = JasperFillManager.fillReport(reporte, hm, con);
            
            //Cerramos la conexion
            con.close();
            
            //Mostrarlo via web
            JasperExportManager.exportReportToPdfStream(jp, response.getOutputStream());
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
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
