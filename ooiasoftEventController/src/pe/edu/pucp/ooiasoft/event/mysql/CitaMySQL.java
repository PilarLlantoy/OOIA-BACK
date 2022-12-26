/* 
 * Archivo: CitaMySQL.java
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
import java.util.Date;
import pe.edu.pucp.ooiasoft.event.config.DBManager;
import pe.edu.pucp.ooiasoft.event.dao.CitaDAO;
import pe.edu.pucp.ooiasoft.event.model.Cita;
import pe.edu.pucp.ooiasoft.event.model.MotivoCita;
import pe.edu.pucp.ooiasoft.event.model.TipoCita;
import pe.edu.pucp.ooiasoft.information.model.Ciclo;
import pe.edu.pucp.ooiasoft.organization.model.PersonalCitas;
import pe.edu.pucp.ooiasoft.organization.model.Psicologo;
import pe.edu.pucp.ooiasoft.organization.model.Tutor;
import pe.edu.pucp.ooiasoft.student.model.Alumno;

public class CitaMySQL implements CitaDAO{
    
    Connection con;
    CallableStatement cs;
    ResultSet rs;

    @Override
    public int insertar(Cita cita) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_CITA(?,?,?,?,?,?,?,?,?)}");

            //Setear parametros
            cs.registerOutParameter("_id_cita", java.sql.Types.INTEGER);
            cs.setInt("_fid_motivo_cita", cita.getMotivoCita().getIdMotivo());
            cs.setInt("_fid_personal_citas", cita.getPersonalCitas().getIdPersona());
            cs.setInt("_fid_ciclo", cita.getCiclo().getIdCiclo());
            cs.setInt("_fid_alumno", cita.getAlumno().getIdPersona());
            cs.setDate("_fecha_registro", new java.sql.Date(cita.getFechaRegistro().getTime()));
            cs.setDate("_fecha_atencion", new java.sql.Date(cita.getFechaAtencion().getTime()));
            cs.setInt("_hora", cita.getHora());
            cs.setInt("_asistio", 0);
            
            //Ejecutar operacion
            cs.executeUpdate();

            //Obtener parametro de salida
            cita.setIdCita(cs.getInt("_id_cita"));
            
            //Asignar exito en operacion y cerrado de recursos
            resultado = cita.getIdCita();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public ArrayList<Cita> listarCitasDeAlumnoPorCicloFechaPersonal(int idAlumno, String ciclo, Date fechaIni, Date fechaFin, String nombrePersonal) {
        ArrayList<Cita> citas = new ArrayList<>();
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{ call LISTAR_CITAS_X_ALUMNO_CICLO_FECHA_PERSONAL(?,?,?,?,?) }");
            cs.setInt("_id_alumno", idAlumno);
            cs.setString("_ciclo", ciclo);
            cs.setDate("_fecha_ini", new java.sql.Date(fechaIni.getTime()));
            cs.setDate("_fecha_fin", new java.sql.Date(fechaFin.getTime()));
            cs.setString("_nombre_personal", nombrePersonal);
            
            //Ejecutar llamado
            rs = cs.executeQuery();

            //Llenar arreglo	
            while(rs.next()){
                //Llenar la cita
                Cita cita = llenarDatosCita(rs);
                
                //Agregar la cita al arreglo
                citas.add(cita);
            }

            //Cerrar recursos
            rs.close();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return citas;
    }
    
    private Cita llenarDatosCita(ResultSet rsCita) throws Exception{
        Cita cita = new Cita();
        cita.setIdCita(rsCita.getInt("id_cita"));
        cita.setFechaRegistro(rsCita.getDate("fecha_registro"));
        cita.setFechaAtencion(rsCita.getDate("fecha_atencion"));
        cita.setHora(rsCita.getInt("hora"));
        if(rsCita.getInt("asistio") == 1) cita.setAsistio(true);
        else cita.setAsistio(false);
        cita.setEstado(true);

        //Llenar el motivo cita
        MotivoCita motivo = new MotivoCita();
        motivo.setIdMotivo(rsCita.getInt("fid_motivo_cita"));
        motivo.setTipoCita(TipoCita.valueOf(rsCita.getString("tipo_cita")));
        motivo.setAbreviatura(rsCita.getString("abreviatura").charAt(0));
        motivo.setDescripcion(rsCita.getString("descripcion"));
        motivo.setEstado(true);
        //Asociar
        cita.setMotivoCita(motivo);

        //Llenar el ciclo
        Ciclo cicloCita = new Ciclo();
        cicloCita.setIdCiclo(rsCita.getInt("fid_ciclo"));
        cicloCita.setAnho(rsCita.getInt("anho"));
        cicloCita.setPeriodo(rsCita.getInt("periodo"));
        cicloCita.setEstado(true);
        //Asociarlo
        cita.setCiclo(cicloCita);

        //Llenar el personal dependiendo del tipo
        PersonalCitas personal;
        if(rsCita.getString("tipo_personal").charAt(0) == 'T') personal = new Tutor();
        else personal = new Psicologo();
        personal.setIdPersona(rsCita.getInt("id_personal"));
        personal.setCodigoPUCP(rsCita.getString("codigo_pucp_personal"));
        personal.setNombreCompleto(rsCita.getString("nombre_personal"));
        personal.setDNI(rsCita.getString("dni_personal"));
        personal.setTelefono(rsCita.getString("telefono_personal"));
        personal.setCorreo(rsCita.getString("correo_personal"));
        personal.setFoto(rsCita.getBytes("foto_personal"));
        personal.setEstado(true);
        //Asociarlo
        cita.setPersonalCitas(personal);

        //Llenar el alumno
        Alumno alum = new Alumno();
        alum.setIdPersona(rsCita.getInt("id_alumno"));
        alum.setCodigoPUCP(rsCita.getString("codigo_pucp_alumno"));
        alum.setDNI(rsCita.getString("dni_alumno"));
        alum.setNombreCompleto(rsCita.getString("nombre_alumno"));
        alum.setTelefono(rsCita.getString("telefono_alumno"));
        alum.setCorreo(rsCita.getString("correo_alumno"));
        alum.setFoto(rsCita.getBytes("foto_alumno"));
        alum.setEstado(true);
        //Asociarlo
        cita.setAlumno(alum);
        
        //Retornar
        return cita;
    }

    @Override
    public ArrayList<Cita> listarCitasDePersonalPorCicloFechaAlumno(int idPersonal, String ciclo, Date fechaIni, Date fechaFin, String nombreAlumno) {
        ArrayList<Cita> citas = new ArrayList<>();
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{ call LISTAR_CITAS_X_PERSONAL_CICLO_FECHA_ALUMNO(?,?,?,?,?) }");
            cs.setInt("_id_personal", idPersonal);
            cs.setString("_ciclo", ciclo);
            cs.setDate("_fecha_ini", new java.sql.Date(fechaIni.getTime()));
            cs.setDate("_fecha_fin", new java.sql.Date(fechaFin.getTime()));
            cs.setString("_nombre_alumno", nombreAlumno);
            
            //Ejecutar llamado
            rs = cs.executeQuery();

            //Llenar arreglo	
            while(rs.next()){
                //Llenar la cita
                Cita cita = llenarDatosCita(rs);
                
                //Agregar la cita al arreglo
                citas.add(cita);
            }

            //Cerrar recursos
            rs.close();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return citas;
    }

    @Override
    public int modificar(Cita cita) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call MODIFICAR_CITA(?,?,?,?,?,?,?,?,?)}");

            //Setear parametros
            cs.setInt("_id_cita", cita.getIdCita());
            cs.setInt("_fid_motivo_cita", cita.getMotivoCita().getIdMotivo());
            cs.setInt("_fid_personal_citas", cita.getPersonalCitas().getIdPersona());
            cs.setInt("_fid_ciclo", cita.getCiclo().getIdCiclo());
            cs.setInt("_fid_alumno", cita.getAlumno().getIdPersona());
            cs.setDate("_fecha_registro", new java.sql.Date(cita.getFechaRegistro().getTime()));
            cs.setDate("_fecha_atencion", new java.sql.Date(cita.getFechaAtencion().getTime()));
            cs.setInt("_hora", cita.getHora());
            if(cita.getAsistio() == true) cs.setInt("_asistio", 1);
            else cs.setInt("_asistio", 0);
            
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
    public int eliminar(int idCita) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{ call ELIMINAR_CITA(?) }");

            //Setear parametros
            cs.setInt("_id_cita", idCita);
            
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
