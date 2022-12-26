/* 
 * Archivo: EventoCicloMySQL.java
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
import pe.edu.pucp.ooiasoft.event.dao.EventoCicloDAO;
import pe.edu.pucp.ooiasoft.event.model.EstadoEvento;
import pe.edu.pucp.ooiasoft.event.model.EventoCiclo;
import pe.edu.pucp.ooiasoft.information.model.Ciclo;
import pe.edu.pucp.ooiasoft.information.model.Especialidad;
import pe.edu.pucp.ooiasoft.student.model.Alumno;
import pe.edu.pucp.ooiasoft.student.model.TipoAlumno;


public class EventoCicloMySQL implements EventoCicloDAO{
    
    Connection con;
    CallableStatement cs;
    ResultSet rs;

    @Override
    public int insertar(EventoCiclo evC) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{ call INSERTAR_EVENTO_CICLO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");

            //Setear parametros
            cs.registerOutParameter("_id_evento_ciclo", java.sql.Types.INTEGER);
            cs.setInt("_fid_ciclo", evC.getCiclo().getIdCiclo());
            cs.setDate("_fecha_registro", new java.sql.Date(evC.getFechaRegistro().getTime()));
            cs.setDate("_fecha_evento", new java.sql.Date(evC.getFechaEvento().getTime()));
            cs.setString("_titulo",evC.getTitulo());
            cs.setString("_tituloUTF",evC.getTituloUTF());
            cs.setString("_descripcion",evC.getDescripcion());
            cs.setString("_descripcionUTF",evC.getDescripcionUTF());
            cs.setString("_organizadores",evC.getOrganizadores());
            cs.setString("_link_encuesta_satisfaccion",evC.getLinkEncuestaSatisfaccion());
            cs.setString("_link_video",evC.getLinkVideo());
            cs.setBytes("_foto",evC.getFoto());
            cs.setInt("_capacidad_maxima",evC.getCapacidadMax());
            cs.setInt("_hora_inicio", evC.getHoraIni());
            cs.setInt("_duracion_horas",evC.getDuracionHoras());
            cs.setString("_aula",evC.getAula());

            //Ejecutar operacion
            cs.executeUpdate();

            //Obtener parametro de salida
            evC.setIdEventoCiclo(cs.getInt("_id_evento_ciclo"));
            
            //Asignar exito en operacion y cerrado de recursos
            resultado = evC.getIdEventoCiclo();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }
    
    @Override
    public ArrayList<EventoCiclo> listarPorAlumnoCicloNombreFechaEstado(int idAlumno, String ciclo, String nombre, Date fechaIni, Date fechaFin, String estado) {
        ArrayList<EventoCiclo> eventos = new ArrayList<>();
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call LISTAR_EVENTO_CICLO_X_ALUMNO_CICLO_NOMBRE_FECHA_ESTADO(?,?,?,?,?,?)}");

            //Setear parametros de entrada y salida			
            cs.setInt("_id_alumno", idAlumno);
            cs.setString("_ciclo", ciclo);
            cs.setString("_nombre", nombre);
            cs.setDate("_fecha_ini", new java.sql.Date(fechaIni.getTime()));
            cs.setDate("_fecha_fin", new java.sql.Date(fechaFin.getTime()));
            cs.setString("_estado", estado);
            
            //Ejecutar llamado
            rs = cs.executeQuery();

            //Llenar arreglo	
            while(rs.next()){
                EventoCiclo evento = llenarEventoCiclo(rs);
                
                //Agregarlo al arreglo
                eventos.add(evento);
            }

            //Cerrar recursos
            rs.close();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return eventos;
    }

    private EventoCiclo llenarEventoCiclo(ResultSet rsEvento) throws Exception{
        //Llenar el evento ciclo
        EventoCiclo evento = new EventoCiclo();
        evento.setIdEventoCiclo(rsEvento.getInt("id_evento_ciclo"));
        evento.setFechaRegistro(rsEvento.getDate("fecha_registro"));
        evento.setFechaEvento(rsEvento.getDate("fecha_evento"));
        evento.setTitulo(rsEvento.getString("titulo"));
        evento.setTituloUTF(rsEvento.getString("tituloUTF"));
        evento.setDescripcion(rsEvento.getString("descripcion_evento_ciclo"));
        evento.setDescripcionUTF(rsEvento.getString("descripcionUTF"));
        evento.setOrganizadores(rsEvento.getString("organizadores"));
        evento.setLinkEncuestaSatisfaccion(rsEvento.getString("link_encuesta_satisfaccion"));
        evento.setLinkVideo(rsEvento.getString("link_video"));
        evento.setFoto(rsEvento.getBytes("foto"));
        evento.setCapacidadMax(rsEvento.getInt("capacidad_max"));
        evento.setCantAsistentes(rsEvento.getInt("cant_asistentes"));
        evento.setHoraIni(rsEvento.getInt("hora_inicio"));
        evento.setDuracionHoras(rsEvento.getInt("duracion_horas"));
        evento.setAula(rsEvento.getString("aula"));
        evento.setEstadoEvento(EstadoEvento.valueOf(rsEvento.getString("estado_evento")));
        evento.setEstado(true);

        //Llenar el ciclo
        Ciclo ciclo = new Ciclo();
        ciclo.setIdCiclo(rsEvento.getInt("id_ciclo"));
        ciclo.setAnho(rsEvento.getInt("anho"));
        ciclo.setPeriodo(rsEvento.getInt("periodo"));
        ciclo.setEstado(true);
        //Asociar
        evento.setCiclo(ciclo);
        
        //Retornar
        return evento;
    }
      
    @Override
    public ArrayList<EventoCiclo> listarPorAlumnoCicloNoInscrito(int idAlumno, int idCiclo, String nombre, Date fechaIni, Date fechaFin) {
        ArrayList<EventoCiclo> eventos = new ArrayList<>();
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call LISTAR_EVENTO_CICLO_X_ALUMNO_CICLO_NO_INSCRITO(?,?,?,?,?)}");

            //Setear parametros de entrada y salida			
            cs.setInt("_id_alumno", idAlumno);
            cs.setInt("_id_ciclo", idCiclo);
            cs.setString("_nombre", nombre);
            cs.setDate("_fecha_ini", new java.sql.Date(fechaIni.getTime()));
            cs.setDate("_fecha_fin", new java.sql.Date(fechaFin.getTime()));
            
            //Ejecutar llamado
            rs = cs.executeQuery();

            //Llenar arreglo	
            while(rs.next()){
                EventoCiclo evento = llenarEventoCiclo(rs);
                
                //Agregarlo al arreglo
                eventos.add(evento);
            }

            //Cerrar recursos
            rs.close();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return eventos;
    }

    @Override
    public ArrayList<EventoCiclo> listarTodosPorCicloNombre(String ciclo, String nombre, Date fechaIni, Date fechaFin, String estado) {
        ArrayList<EventoCiclo> eventos = new ArrayList<>();
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{ call LISTAR_EVENTO_CICLO_X_CICLO_NOMBRE_FECHA_ESTADO(?,?,?,?,?) }");

            //Setear parametros de entrada y salida			
            cs.setString("_ciclo", ciclo);
            cs.setString("_nombre", nombre);
            cs.setDate("_fecha_ini", new java.sql.Date(fechaIni.getTime()));
            cs.setDate("_fecha_fin", new java.sql.Date(fechaFin.getTime()));
            cs.setString("_estado", estado);
            
            //Ejecutar llamado
            rs = cs.executeQuery();

            //Llenar arreglo	
            while(rs.next()){
                EventoCiclo evento = llenarEventoCiclo(rs);

                //Llenar inscritos
                llenarInscritosEventoCiclo(evento);
                
                //Agregarlo al arreglo
                eventos.add(evento);
            }

            //Cerrar recursos
            rs.close();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return eventos;
    }
    
    private void llenarInscritosEventoCiclo(EventoCiclo evento) throws Exception{
        cs = con.prepareCall("{call LISTAR_INSCRITOS_EVENTO_CICLO_X_CODIGO_NOMBRE(?,?)}");

        //Setear parametros de entrada y salida
        cs.setInt("_id_evento_ciclo", evento.getIdEventoCiclo());
        cs.setString("_codigo_nombre", "");

        //Ejecutar llamado
        ResultSet rsAlum = cs.executeQuery();

        //Llenar arreglo, este listado solo muestra la informacion basica del alumno
        //Sera necesario llamar a otro metodo para llenar sus detalles de notas
        while(rsAlum.next()){
            Alumno alum = new Alumno();

            //No se llenan su detalle de ciclos, cursos ni notas, es un listado basico
            alum.setIdPersona(rsAlum.getInt("id_persona"));
            alum.setCodigoPUCP(rsAlum.getString("codigo_pucp"));
            alum.setDNI(rsAlum.getString("dni"));
            alum.setNombreCompleto(rsAlum.getString("nombre_completo"));
            alum.setFechaNacimiento(rsAlum.getDate("fecha_nacimiento"));
            alum.setEdad(rsAlum.getInt("edad"));
            alum.setSexo(rsAlum.getString("sexo").charAt(0));
            alum.setTelefono(rsAlum.getString("telefono"));
            alum.setCorreo(rsAlum.getString("correo"));
            alum.setEstado(true);
            alum.setUsuario(rsAlum.getString("usuario"));
            alum.setPassword(rsAlum.getString("password"));
            alum.setFoto(rsAlum.getBytes("foto"));
            alum.setEscalaPago(rsAlum.getInt("escala_pago"));
            alum.setCRAEST(rsAlum.getDouble("craest"));
            alum.setPromedioPonderado(rsAlum.getDouble("promedio_ponderado"));
            alum.setCantCiclos(rsAlum.getInt("cant_ciclos"));
            alum.setCantCursosAprobados(rsAlum.getInt("cantidad_cursos_aprobados"));
            alum.setCantidadCreditosAprobados(rsAlum.getDouble("cantidad_creditos_aprobados"));
            alum.setTotalCreditos(rsAlum.getDouble("total_creditos"));
            alum.setTipo(TipoAlumno.valueOf(rsAlum.getString("tipo")));

            //Llenar la especialidad
            Especialidad esp = new Especialidad();
            esp.setIdEspecialidad(rsAlum.getInt("fid_especialidad"));
            esp.setNombre(rsAlum.getString("nombre"));
            //Asociar
            alum.setEspecialidad(esp);

            //Agregarlo al arreglo
            evento.agregarAlumno(alum);
        }

        //Cerrar recursos
        rsAlum.close();
    }

    @Override
    public int modificar(EventoCiclo evC) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{ call MODIFICAR_EVENTO_CICLO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");

            //Setear parametros
            cs.setInt("_id_evento_ciclo", evC.getIdEventoCiclo());
            cs.setInt("_fid_ciclo", evC.getCiclo().getIdCiclo());
            cs.setDate("_fecha_registro", new java.sql.Date(evC.getFechaRegistro().getTime()));
            cs.setDate("_fecha_evento", new java.sql.Date(evC.getFechaEvento().getTime()));
            cs.setString("_titulo",evC.getTitulo());
            cs.setString("_tituloUTF",evC.getTituloUTF());
            cs.setString("_descripcion",evC.getDescripcion());
            cs.setString("_descripcionUTF",evC.getDescripcionUTF());
            cs.setString("_organizadores",evC.getOrganizadores());
            cs.setString("_link_encuesta_satisfaccion",evC.getLinkEncuestaSatisfaccion());
            cs.setString("_link_video",evC.getLinkVideo());
            cs.setBytes("_foto",evC.getFoto());
            cs.setInt("_capacidad_maxima",evC.getCapacidadMax());
            cs.setInt("_hora_inicio", evC.getHoraIni());
            cs.setInt("_duracion_horas",evC.getDuracionHoras());
            cs.setString("_aula",evC.getAula());

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

    private void insertarInscritos(EventoCiclo evC) throws Exception{
        //Para cada inscrito
        for(Alumno alum : evC.getAlumnosInscritos()){
            //Preparar llamado
            cs = con.prepareCall("{ call INSERTAR_EVENTO_CICLO_ALUMNO(?,?,?) }");
            cs.registerOutParameter("_id_evento_ciclo_alumno", java.sql.Types.INTEGER);
            cs.setInt("_fid_evento_ciclo", evC.getIdEventoCiclo());
            cs.setInt("_fid_alumno", alum.getIdPersona());
            
            //Ejecutar llamado
            cs.executeUpdate();
            
            int aux = cs.getInt("_id_evento_ciclo_alumno");
        }
    }
    
    @Override
    public int eliminar(int idEventoCiclo) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ELIMINAR_EVENTO_CICLO(?)}");

            //Setear parametros
            cs.setInt("_fid_evento_ciclo", idEventoCiclo);
            
            //Ejecutar llamado
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
    public int inscribirAlumno(int idAlumno, int idEventoCiclo) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_EVENTO_CICLO_ALUMNO(?,?,?)}");

            //Setear parametros
            cs.registerOutParameter("_id_evento_ciclo_alumno", java.sql.Types.INTEGER);
            cs.setInt("_fid_evento_ciclo", idEventoCiclo);
            cs.setInt("_fid_alumno", idAlumno);
            
            //Ejecutar llamado
            cs.executeUpdate();
            
            //Asignar exito en operacion y cerrado de recursos
            resultado = cs.getInt("_id_evento_ciclo_alumno");
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int desinscribirAlumno(int idAlumno, int idEventoCiclo) {
        int resultado = -1;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ELIMINAR_EVENTO_CICLO_ALUMNO(?,?,?)}");

            //Setear parametros
            cs.registerOutParameter("_resultado", java.sql.Types.INTEGER);
            cs.setInt("_fid_evento_ciclo", idEventoCiclo);
            cs.setInt("_fid_alumno", idAlumno);
            
            //Ejecutar llamado
            cs.executeUpdate();
            
            //Asignar exito en operacion y cerrado de recursos
            resultado = cs.getInt("_resultado");
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }
    
}