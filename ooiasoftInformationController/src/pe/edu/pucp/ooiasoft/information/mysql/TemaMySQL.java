/* 
 * Archivo: TemaMySQL.java
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
import pe.edu.pucp.ooiasoft.information.dao.TemaDAO;
import pe.edu.pucp.ooiasoft.information.model.Tema;


public class TemaMySQL implements TemaDAO{
    
    //Atributos necesarios para establecer la conexion en cada operacion
    Connection con;
    CallableStatement cs;
    ResultSet rs;

    @Override
    public int insertar(Tema tema) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_TEMA(?,?)}");

            //Setear parametros
            cs.registerOutParameter("_id_tema", java.sql.Types.INTEGER);
            cs.setString("_nombre", tema.getNombre());

            //Ejecutar operacion
            cs.executeUpdate();

            //Obtener parametro de salida
            tema.setIdTema(cs.getInt("_id_tema"));
            
            //Asignar exito en operacion y cerrado de recursos
            resultado = tema.getIdTema();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public ArrayList<Tema> listar() {
        ArrayList<Tema> temas = new ArrayList<>();
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call LISTAR_TEMAS()}");
            
            //Ejecutar llamado
            rs = cs.executeQuery();

            //Llenar arreglo	
            while(rs.next()){
                Tema tema = new Tema();
             
                tema.setIdTema(rs.getInt("id_tema"));
                tema.setNombre(rs.getString("nombre"));
                tema.setEstado(true);
                
                //Agregarlo al arreglo
                temas.add(tema);
            }

            //Cerrar recursos
            rs.close();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return temas;
    }

    @Override
    public int modificar(Tema tema) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call MODIFICAR_TEMA(?,?)}");

            //Setear parametros
            cs.setInt("_id_tema", tema.getIdTema());
            cs.setString("_nombre", tema.getNombre());

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
    public int eliminar(int idTema) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ELIMINAR_TEMA(?)}");

            //Setear parametros
            cs.setInt("_id_tema", idTema);

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
