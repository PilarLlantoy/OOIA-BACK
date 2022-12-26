/* 
 * Archivo: MotivoCitaMySQL.java
 * Grupo:   Grave Error de Proyecto
 * Integrantes: 
 *  - Oscar Dueñas
 *  - Christian Carhuancho
 *  - Franccesco Jaimes
 *  - Pilar Llantoy
 *  - Cesar Rafael
 * Fecha:   07/06/2021
 */

package pe.edu.pucp.ooiasoft.event.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import pe.edu.pucp.ooiasoft.event.config.DBManager;
import pe.edu.pucp.ooiasoft.event.dao.MotivoCitaDAO;
import pe.edu.pucp.ooiasoft.event.model.MotivoCita;
import pe.edu.pucp.ooiasoft.event.model.TipoCita;


public class MotivoCitaMySQL implements MotivoCitaDAO{
    
    Connection con;
    CallableStatement cs;
    ResultSet rs;

    @Override
    public int insertar(MotivoCita motivo) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_MOTIVO_CITA(?,?,?,?)}");

            //Setear parametros
            cs.registerOutParameter("_id_motivo_cita", java.sql.Types.INTEGER);
            cs.setString("_tipo_cita", motivo.getTipoCita().toString());
            cs.setString("_abreviatura", String.valueOf(motivo.getAbreviatura()));
            cs.setString("_descripcion", motivo.getDescripcion());
            
            //Ejecutar operacion
            cs.executeUpdate();

            //Obtener parametro de salida
            motivo.setIdMotivo(cs.getInt("_id_motivo_cita"));
            
            //Asignar exito en operacion y cerrado de recursos
            resultado = motivo.getIdMotivo();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public ArrayList<MotivoCita> listar() {
        ArrayList<MotivoCita> motivos = new ArrayList<>();
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{ call LISTAR_MOTIVOS_CITA() }");
            
            //Ejecutar llamado
            rs = cs.executeQuery();

            //Llenar arreglo	
            while(rs.next()){
                //Llenar el motivo
                MotivoCita motivo = new MotivoCita();
                motivo.setIdMotivo(rs.getInt("id_motivo_cita"));
                motivo.setAbreviatura(rs.getString("abreviatura").charAt(0));
                motivo.setDescripcion(rs.getString("descripcion"));
                motivo.setTipoCita(TipoCita.valueOf(rs.getString("tipo")));
                motivo.setEstado(true);
                
                //Agregarlo al arreglo
                motivos.add(motivo);
            }

            //Cerrar recursos
            rs.close();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return motivos;
    }

    @Override
    public int modificar(MotivoCita motivo) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{ call MODIFICAR_MOTIVO_CITA(?,?,?,?) }");

            //Setear parametros
            cs.setInt("_id_motivo_cita", motivo.getIdMotivo());
            cs.setString("_tipo_cita", motivo.getTipoCita().toString());
            cs.setString("_abreviatura", String.valueOf(motivo.getAbreviatura()));
            cs.setString("_descripcion", motivo.getDescripcion());
            
            //Ejecutar operacion
            cs.executeUpdate();
            
            //Asignar exito en operacion y cerrado de recursos
            resultado = 1;
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int eliminar(int idMotivoCita) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{ call ELIMINAR_MOTIVO_CITA(?)}");

            //Setear parametros
            cs.setInt("_id_motivo_cita", idMotivoCita);
            
            //Ejecutar operacion
            cs.executeUpdate();
            
            //Asignar exito en operacion y cerrado de recursos
            resultado = 1;
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

}
