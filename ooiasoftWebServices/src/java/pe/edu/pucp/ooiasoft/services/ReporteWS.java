/* 
 * Archivo: ReporteWS.java
 * Grupo:   Grave Error de Proyecto
 * Integrantes: 
 *  - Oscar Dueñas
 *  - Christian Carhuancho
 *  - Franccesco Jaimes
 *  - Pilar Llantoy
 *  - Cesar Rafael
 * Fecha:   08/06/2021
 */
 
package pe.edu.pucp.ooiasoft.services;

import javax.jws.WebService;
import javax.jws.WebMethod;
import java.awt.Image;
import java.sql.Connection;
import java.util.HashMap;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import pe.edu.pucp.ooiasoft.config.DBManager;
import pe.edu.pucp.ooiasoft.servlets.ReporteCitas;
import pe.edu.pucp.ooiasoft.servlets.ReporteEventos;

@WebService(serviceName = "ReporteWS")
public class ReporteWS {

    @WebMethod(operationName = "generarReporteCitasxPersonal")
    public byte[] generarReporteCitasxPersonal(int id) {
        byte[] arreglo = null;
        try{
            //Referencia al reporte
            JasperReport reporte = 
                    (JasperReport) JRLoader.loadObject(
                    ReporteCitas.class.getResource(
                    "/pe/edu/pucp/ooiasoft/reportes/ReporteCitas.jasper"));
            
            //referencia al subreporte
            String rutaSubreporte1 = ReporteCitas.class.getResource("/pe/edu/pucp/ooiasoft/reportes/SubReporteCitas.jasper").getPath();
            //Referencia a la imagen
            String rutaImagen = ReporteCitas.class.getResource("/pe/edu/pucp/ooiasoft/img/logoOOIA.PNG").getPath();
            Image logo = (new ImageIcon(rutaImagen)).getImage();
            //Referencia a los graficos
            String rutaGrafico = ReporteCitas.class.getResource("/pe/edu/pucp/ooiasoft/reportes/SubRepCitasGrafico.jasper").getPath();
            String rutaGrafico2 = ReporteCitas.class.getResource("/pe/edu/pucp/ooiasoft/reportes/SubRepCitasGrafico2.jasper").getPath();
            
            //Referencia para Rankin
            String rutaRankin = ReporteEventos.class.getResource("/pe/edu/pucp/ooiasoft/reportes/SubRepCitasRankin.jasper").getPath();
            
            //Arreglo de parametros que ingresan a la generación del reporte
            HashMap hm = new HashMap();
            hm.put("imagen", logo);
            hm.put("pidPersona", id);
            hm.put("RutaSubreporte",rutaSubreporte1);  
            hm.put("rutaGraf",rutaGrafico); 
            hm.put("rutaGraf2",rutaGrafico2); 
            hm.put("rutaRankin",rutaRankin);
            
            //Objeto de conexion
            Connection con = DBManager.getInstance().getConnection();
            
            //Poblar el reporte
            JasperPrint jp = JasperFillManager.fillReport(reporte, hm, con);
            
            //Cerramos la conexion
            con.close();
            
            //Mostrarlo via web            
            arreglo = JasperExportManager.exportReportToPdf(jp);
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return arreglo;
    }

    @WebMethod(operationName = "generarReporteEventosxCiclo")
    public byte[] generarReporteEventosxCiclo(int id) {
        byte[] arreglo = null;
        try{
            
            //Referencia al reporte
            JasperReport reporte = 
                    (JasperReport) JRLoader.loadObject(
                    ReporteEventos.class.getResource(
                    "/pe/edu/pucp/ooiasoft/reportes/ReporteEventos.jasper"));
            
            //Referencia a la imagen
            String rutaImagen = ReporteEventos.class.getResource("/pe/edu/pucp/ooiasoft/img/logoOOIA.PNG").getPath();
            Image logo = (new ImageIcon(rutaImagen)).getImage(); 
            //referencia al subreporte
            String rutaSubreporte1 = ReporteCitas.class.getResource("/pe/edu/pucp/ooiasoft/reportes/SubReporteEventos.jasper").getPath();
            //Referencia a los graficos
            String rutaGrafico = ReporteCitas.class.getResource("/pe/edu/pucp/ooiasoft/reportes/SubRepEventosGrafico.jasper").getPath();
            
            //Arreglo de parametros que ingresan a la generación del reporte
            HashMap hm = new HashMap(); 
            hm.put("pidCiclo", id);
            hm.put("imagen", logo);
            hm.put("RutaSubReporte",rutaSubreporte1);  
            hm.put("rutaGrafico",rutaGrafico);
            
            //Objeto de conexion
            Connection con = DBManager.getInstance().getConnection();
            //Poblar el reporte
            JasperPrint jp = JasperFillManager.fillReport(reporte, hm, con);
            //Cerramos la conexion
            con.close();
            //Mostrarlo via web
            arreglo = JasperExportManager.exportReportToPdf(jp);

        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return arreglo;
    }
}
