/* 
 * Archivo: EventoCicloDAO.java
 * Grupo:   Grave Error de Proyecto
 * Integrantes: 
 *  - Oscar Dueñas
 *  - Christian Carhuancho
 *  - Franccesco Jaimes
 *  - Pilar Llantoy
 *  - Cesar Rafael
 * Fecha:   07/06/2021
 */

package pe.edu.pucp.ooiasoft.event.dao;

import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.ooiasoft.event.model.EventoCiclo;

public interface EventoCicloDAO {
    //Inserta el administrador (el detalle de participantes esta inicialmente vacio)
    public int insertar(EventoCiclo evC);
    
    //Un alumno se inscribe
    public int inscribirAlumno(int idAlumno, int idEventoCiclo);
    
    //Un alumno se desinscribe
    public int desinscribirAlumno(int idAlumno, int idEventoCiclo);
    
    //Para alumno
    public ArrayList<EventoCiclo> listarPorAlumnoCicloNombreFechaEstado(int idAlumno, String ciclo, String nombre, Date fechaIni, Date fechaFin, String estado);
    public ArrayList<EventoCiclo> listarPorAlumnoCicloNoInscrito(int idAlumno, int idCiclo, String nombre, Date fechaIni, Date fechaFin);
    
    //Para administrativo
    public ArrayList<EventoCiclo> listarTodosPorCicloNombre(String ciclo, String nombre, Date fechaIni, Date fechaFin, String estado);
    public int modificar(EventoCiclo evC);
    public int eliminar(int idEventoCiclo);
}
