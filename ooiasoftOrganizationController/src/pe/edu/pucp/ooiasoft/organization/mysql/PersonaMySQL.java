/* 
 * Archivo: PersonaMySQL.java
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
import pe.edu.pucp.ooiasoft.organization.config.DBManager;
import pe.edu.pucp.ooiasoft.organization.dao.PersonaDAO;
import pe.edu.pucp.ooiasoft.organization.model.Persona;

public class PersonaMySQL implements PersonaDAO{
    
    //Atributos necesarios para establecer la conexion en cada operacion
    Connection con;
    ResultSet rs;
    CallableStatement cs;

    @Override
    public char buscarTipoPersonaPorIDPersona(int idPersona) {
        char tipo = 'N'; //N de no encontrado
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{ call BUSCAR_TIPO_PERSONA(?,?) }");

            //Setear parametros
            cs.registerOutParameter("_tipo", java.sql.Types.CHAR);
            cs.setInt("_id_persona", idPersona);
                      
            //Ejecutar operacion
            cs.executeQuery();
            
            //Recuperar tipo y cerrar recursos
            tipo = cs.getString("_tipo").charAt(0);
            
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return tipo;
    }

    @Override
    public int buscarIDPersonaPorUsuarioPassword(String usuario, String password) {
        int idPersona = -1; //No encontrado
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{ call BUSCAR_PERSONA_X_USUARIO_PASSWORD(?,?) }");

            //Setear parametros
            cs.setString("_usuario", usuario);
            cs.setString("_password", password);
                      
            //Ejecutar operacion
            rs = cs.executeQuery();
            
            //Solo si encuentra, se asigna el id, sino se retorna -1
            if(rs.next())
                idPersona = rs.getInt("id_persona");

            //Cerrar recursos
            rs.close();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return idPersona;
    }

    @Override
    public int modificarPersona(Persona persona) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call MODIFICAR_PERSONA(?,?,?,?,?,?,?,?,?,?,?,?)}");

            //Setear parametros
            cs.setInt("_id_persona", persona.getIdPersona());
            cs.setString("_codigo_pucp", persona.getCodigoPUCP());
            cs.setString("_dni",persona.getDNI());
            cs.setString("_nombre_completo",persona.getNombreCompleto());
            cs.setDate("_fecha_nacimiento",new java.sql.Date(persona.getFechaNacimiento().getTime()));
            cs.setInt("_edad",persona.getEdad());
            cs.setString("_sexo",String.valueOf(persona.getSexo()));
            cs.setString("_telefono",persona.getTelefono());
            cs.setString("_correo",persona.getCorreo());
            cs.setString("_usuario",persona.getUsuario());
            cs.setString("_password",persona.getPassword());
            cs.setBytes("_foto",persona.getFoto());

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
    public int buscarIDPersonaPorCodigoCorreo(String codigoPUCP, String correo) {
        int idPersona = -1; //No encontrado
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{ call BUSCAR_PERSONA_X_CODIGO_CORREO(?,?) }");

            //Setear parametros
            cs.setString("_codigo_pucp", codigoPUCP);
            cs.setString("_correo", correo);
                      
            //Ejecutar operacion
            rs = cs.executeQuery();
            
            //Solo si encuentra, se asigna el id, sino se retorna -1
            if(rs.next())
                idPersona = rs.getInt("id_persona");

            //Cerrar recursos
            rs.close();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return idPersona;
    }
    
}
