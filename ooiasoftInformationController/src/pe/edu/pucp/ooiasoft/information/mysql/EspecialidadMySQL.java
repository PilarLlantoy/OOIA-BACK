/* 
 * Archivo: EspecialidadMySQL.java
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
import pe.edu.pucp.ooiasoft.information.dao.EspecialidadDAO;
import pe.edu.pucp.ooiasoft.information.model.Especialidad;


public class EspecialidadMySQL implements EspecialidadDAO{
    
    //Atributos necesarios para establecer la conexion en cada operacion
    Connection con;
    CallableStatement cs;
    ResultSet rs;

    @Override
    public int insertar(Especialidad especialidad) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_ESPECIALIDAD(?,?,?)}");

            //Setear parametros
            cs.registerOutParameter("_id_especialidad", java.sql.Types.INTEGER);
            cs.setString("_nombre", especialidad.getNombre());
            cs.setString("_descripcion", especialidad.getDescripcion());

            //Ejecutar operacion
            cs.executeUpdate();

            //Obtener parametro de salida
            especialidad.setIdEspecialidad(cs.getInt("_id_especialidad"));
            
            //Asignar exito en operacion y cerrado de recursos
            resultado = especialidad.getIdEspecialidad();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public ArrayList<Especialidad> listarPorNombre(String nombre) {
        ArrayList<Especialidad> especialidades = new ArrayList<>();
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call LISTAR_ESPECIALIDADES_X_NOMBRE(?)}");

            //Setear parametros de entrada y salida			
            cs.setString("_nombre", nombre);
            
            //Ejecutar llamado
            rs = cs.executeQuery();

            //Llenar arreglo	
            while(rs.next()){
                Especialidad esp = llenarEspecialidad(rs);
             
                //Agregarlo al arreglo
                especialidades.add(esp);
            }

            //Cerrar recursos
            rs.close();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return especialidades;
    }

    @Override
    public int modificar(Especialidad especialidad) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call MODIFICAR_ESPECIALIDAD(?,?,?)}");

            //Setear parametros
            cs.setInt("_id_especialidad", especialidad.getIdEspecialidad());
            cs.setString("_nombre", especialidad.getNombre());
            cs.setString("_descripcion", especialidad.getDescripcion());

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
    public int eliminar(int idEspecialidad) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ELIMINAR_ESPECIALIDAD(?)}");

            //Setear parametros
            cs.setInt("_id_especialidad", idEspecialidad);

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
    
    //Metodo privado que llena un objeto de especialidad a partir de un ResultSet
    private Especialidad llenarEspecialidad(ResultSet rsEsp) throws Exception{
        Especialidad esp = new Especialidad();
        esp.setIdEspecialidad(rsEsp.getInt("id_especialidad"));
        esp.setNombre(rsEsp.getString("nombre"));
        esp.setDescripcion(rsEsp.getString("descripcion"));
        esp.setEstado(true);
        return esp;
    }

}
