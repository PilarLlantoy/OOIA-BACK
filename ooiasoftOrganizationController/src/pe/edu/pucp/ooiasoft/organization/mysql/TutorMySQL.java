/* 
 * Archivo: TutorMySQL.java
 * Grupo:   Grave Error de Proyecto
 * Integrantes: 
 *  - Oscar Dueñas
 *  - Christian Carhuancho
 *  - Franccesco Jaimes
 *  - Pilar Llantoy
 *  - Cesar Rafael
 * Fecha:   07/06/2021
 */

package pe.edu.pucp.ooiasoft.organization.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import pe.edu.pucp.ooiasoft.information.model.Especialidad;
import pe.edu.pucp.ooiasoft.organization.config.DBManager;
import pe.edu.pucp.ooiasoft.organization.dao.TutorDAO;
import pe.edu.pucp.ooiasoft.organization.model.Tutor;


public class TutorMySQL implements TutorDAO{
    
    //Atributos necesarios para establecer la conexion en cada operacion
    Connection con;
    ResultSet rs;
    CallableStatement cs;

    @Override
    public int insertar(Tutor tutor) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{ call INSERTAR_TUTOR(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");

            //Setear parametros
            cs.registerOutParameter("_fid_persona", java.sql.Types.INTEGER);
            cs.setString("_codigo_pucp", tutor.getCodigoPUCP());
            cs.setString("_dni",tutor.getDNI());
            cs.setString("_nombre_completo",tutor.getNombreCompleto());
            cs.setDate("_fecha_nacimiento",new java.sql.Date(tutor.getFechaNacimiento().getTime()));
            cs.setInt("_edad",tutor.getEdad());
            cs.setString("_sexo",String.valueOf(tutor.getSexo()));
            cs.setString("_telefono",tutor.getTelefono());
            cs.setString("_correo",tutor.getCorreo());
            cs.setString("_usuario",tutor.getUsuario());
            cs.setString("_password",tutor.getPassword());
            cs.setBytes("_foto",tutor.getFoto());
            cs.setInt("_horas_semanales", tutor.getHorasSemanales());
            cs.setDouble("_rendimiento", tutor.getRendimiento());
            cs.setInt("_fid_especialidad", tutor.getEspecialidad().getIdEspecialidad());
            cs.setDate("_fecha_asociacion", new java.sql.Date(tutor.getFechaAsociacion().getTime()));

            //Ejecutar operacion
            cs.executeUpdate();

            //Obtener parametro de salida
            tutor.setIdPersona(cs.getInt("_fid_persona"));
            
            //Asignar exito en operacion y cerrado de recursos
            resultado = tutor.getIdPersona();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public ArrayList<Tutor> listarPorCodigoONombre(String codigoNombre) {
        ArrayList<Tutor> tutores = new ArrayList<>();
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{ call LISTAR_TUTORES_X_CODIGO_NOMBRE(?) }");

            //Setear parametros de entrada y salida			
            cs.setString("_codigo_nombre", codigoNombre);
            
            //Ejecutar llamado
            rs = cs.executeQuery();

            //Llenar arreglo	
            while(rs.next()){
                Tutor tutor = llenarTutor(rs);
             
                //Agregarlo al arreglo
                tutores.add(tutor);
            }

            //Cerrar recursos
            rs.close();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return tutores;
    }

    private Tutor llenarTutor(ResultSet rsTutor) throws Exception{
        Tutor tutor = new Tutor();
        
        //Llenar
        tutor.setIdPersona(rsTutor.getInt("id_persona"));
        tutor.setCodigoPUCP(rsTutor.getString("codigo_pucp"));
        tutor.setDNI(rsTutor.getString("dni"));
        tutor.setNombreCompleto(rsTutor.getString("nombre_completo"));
        tutor.setFechaNacimiento(rsTutor.getDate("fecha_nacimiento"));
        tutor.setEdad(rsTutor.getInt("edad"));
        tutor.setSexo(rsTutor.getString("sexo").charAt(0));
        tutor.setTelefono(rsTutor.getString("telefono"));
        tutor.setCorreo(rsTutor.getString("correo"));
        tutor.setEstado(true);
        tutor.setUsuario(rsTutor.getString("usuario"));
        tutor.setPassword(rsTutor.getString("password"));
        tutor.setFoto(rsTutor.getBytes("foto"));
        tutor.setHorasSemanales(rsTutor.getInt("horas_semanales"));
        tutor.setRendimiento(rsTutor.getDouble("rendimiento"));
        tutor.setFechaAsociacion(rsTutor.getDate("fecha_asociacion"));
        
        //Llenar la especialidad
        Especialidad esp = new Especialidad();
        esp.setIdEspecialidad(rsTutor.getInt("id_especialidad"));
        esp.setNombre(rs.getString("nombre"));
        esp.setDescripcion(rs.getString("descripcion"));
        esp.setEstado(true);
        //Asociar
        tutor.setEspecialidad(esp);
        
        //Retornar
        return tutor;
    }
    
    @Override
    public int modificar(Tutor tutor) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{ call MODIFICAR_TUTOR(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");

            //Setear parametros
            cs.setInt("_fid_persona", tutor.getIdPersona());
            cs.setString("_codigo_pucp", tutor.getCodigoPUCP());
            cs.setString("_dni",tutor.getDNI());
            cs.setString("_nombre_completo",tutor.getNombreCompleto());
            cs.setDate("_fecha_nacimiento",new java.sql.Date(tutor.getFechaNacimiento().getTime()));
            cs.setInt("_edad",tutor.getEdad());
            cs.setString("_sexo",String.valueOf(tutor.getSexo()));
            cs.setString("_telefono",tutor.getTelefono());
            cs.setString("_correo",tutor.getCorreo());
            cs.setString("_usuario",tutor.getUsuario());
            cs.setString("_password",tutor.getPassword());
            cs.setBytes("_foto",tutor.getFoto());
            cs.setInt("_horas_semanales", tutor.getHorasSemanales());
            cs.setDouble("_rendimiento", tutor.getRendimiento());
            cs.setInt("_fid_especialidad", tutor.getEspecialidad().getIdEspecialidad());
            cs.setDate("_fecha_asociacion", new java.sql.Date(tutor.getFechaAsociacion().getTime()));

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
    public int eliminar(int idTutor) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{ call ELIMINAR_TUTOR(?) }");

            //Setear parametros
            cs.setInt("_fid_persona", idTutor);

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
    public Tutor buscarTutorPorID(int idTutor) {
        Tutor tutor = null;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{ call BUSCAR_TUTOR_X_ID(?) }");

            //Setear parametros de entrada y salida			
            cs.setInt("_fid_persona", idTutor);
            
            //Ejecutar llamado
            rs = cs.executeQuery();

            //Llenar arreglo	
            while(rs.next())
                tutor = llenarTutor(rs);

            //Cerrar recursos
            rs.close();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return tutor;
    }
    
}
