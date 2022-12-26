/* 
 * Archivo: InformacionSubtemaCicloMySQL.java
 * Grupo:   Grave Error de Proyecto
 * Integrantes: 
 *  - Oscar Dueñas
 *  - Christian Carhuancho
 *  - Franccesco Jaimes
 *  - Pilar Llantoy
 *  - Cesar Rafael
 * Fecha:   07/06/2021
 */

package pe.edu.pucp.ooiasoft.information.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import pe.edu.pucp.ooiasoft.information.config.DBManager;
import pe.edu.pucp.ooiasoft.information.model.InformacionTemaCiclo;
import pe.edu.pucp.ooiasoft.information.model.Tema;
import pe.edu.pucp.ooiasoft.information.dao.InformacionTemaCicloDAO;
import pe.edu.pucp.ooiasoft.information.model.Ciclo;

public class InformacionTemaCicloMySQL implements InformacionTemaCicloDAO{
    
    //Atributos necesarios para establecer la conexion en cada operacion
    Connection con;
    CallableStatement cs;
    ResultSet rs;

    @Override
    public int insertar(InformacionTemaCiclo info) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{ call INSERTAR_INFORMACION_TEMA_CICLO(?,?,?,?,?,?,?,?,?) }");

            //Setear parametros
            cs.registerOutParameter("_id_informacion_tema_ciclo", java.sql.Types.INTEGER);
            cs.setInt("_fid_tema", info.getTema().getIdTema());
            cs.setInt("_fid_ciclo",info.getCiclo().getIdCiclo());
            cs.setString("_titulo", info.getTitulo());
            cs.setString("_descripcion",info.getDescripcion());
            cs.setString("_descripcionUTF",info.getDescripcionUTF());
            cs.setBytes("_foto",info.getFoto());
            cs.setDate("_fecha_registro",new java.sql.Date(info.getFechaRegistro().getTime()));
            cs.setDate("_fecha_visible",new java.sql.Date(info.getFechaVisible().getTime()));

            //Ejecutar operacion
            cs.executeUpdate();

            //Obtener parametro de salida
            info.setIdInformacionTemaCiclo(cs.getInt("_id_informacion_tema_ciclo"));
            
            //Asignar exito en operacion y cerrado de recursos
            resultado = info.getIdInformacionTemaCiclo();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public ArrayList<InformacionTemaCiclo> listarPorCicloTema(int idCiclo, int idTema) {
        ArrayList<InformacionTemaCiclo> infos = new ArrayList<>();
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call LISTAR_INFORMACION_TEMA_CICLO_X_CICLO_TEMA(?,?)}");
            
            //Setear parametros de entrada y salida
            cs.setInt("_fid_ciclo", idCiclo);
            cs.setInt("_fid_tema", idTema);
            
            //Ejecutar llamado
            rs = cs.executeQuery();

            //Llenar arreglo	
            while(rs.next()){
                InformacionTemaCiclo info = new InformacionTemaCiclo();
             
                info.setIdInformacionTemaCiclo(rs.getInt("id_informacion_tema_ciclo"));
                info.setTitulo(rs.getString("titulo"));
                info.setDescripcion(rs.getString("descripcion"));
                info.setDescripcionUTF(rs.getString("descripcionUTF"));
                info.setFoto(rs.getBytes("foto"));
                info.setFechaRegistro(rs.getDate("fecha_registro"));
                info.setFechaVisible(rs.getDate("fecha_visible"));
                info.setEstado(true);
                
                //Llenar el tema
                Tema tema = new Tema();
                tema.setIdTema(rs.getInt("id_tema"));
                tema.setNombre(rs.getString("nombre"));
                tema.setEstado(true);
                //Asociarlo a info
                info.setTema(tema);
                
                //Llenar el ciclo
                Ciclo ciclo = new Ciclo();
                ciclo.setIdCiclo(rs.getInt("id_ciclo"));
                ciclo.setAnho(rs.getInt("anho"));
                ciclo.setPeriodo(rs.getInt("periodo"));
                //Asociarlo
                info.setCiclo(ciclo);
                               
                //Agregarlo al arreglo
                infos.add(info);
            }

            //Cerrar recursos
            rs.close();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return infos;
    }

    @Override
    public int modificar(InformacionTemaCiclo info) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{ call MODIFICAR_INFORMACION_TEMA_CICLO(?,?,?,?,?,?,?,?,?) }");

            //Setear parametros
            cs.setInt("_id_informacion_tema_ciclo", info.getIdInformacionTemaCiclo());
            cs.setInt("_fid_tema",info.getTema().getIdTema());
            cs.setInt("_fid_ciclo",info.getCiclo().getIdCiclo());
            cs.setString("_titulo", info.getTitulo());
            cs.setString("_descripcion",info.getDescripcion());
            cs.setString("_descripcionUTF",info.getDescripcionUTF());
            cs.setBytes("_foto",info.getFoto());
            cs.setDate("_fecha_registro",new java.sql.Date(info.getFechaRegistro().getTime()));
            cs.setDate("_fecha_visible",new java.sql.Date(info.getFechaVisible().getTime()));

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
    public int eliminar(int idInformacionTemaCiclo) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{ call ELIMINAR_INFORMACION_TEMA_CICLO(?) }");

            //Setear parametros
            cs.setInt("_id_informacion_tema_ciclo", idInformacionTemaCiclo);
            
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