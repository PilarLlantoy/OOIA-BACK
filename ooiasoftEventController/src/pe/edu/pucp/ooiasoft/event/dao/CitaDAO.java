/* 
 * Archivo: CitaDAO.java
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
import pe.edu.pucp.ooiasoft.event.model.Cita;

public interface CitaDAO {
    public int insertar(Cita cita);
    public ArrayList<Cita> listarCitasDeAlumnoPorCicloFechaPersonal(int idAlumno, String ciclo, Date fechaIni, Date fechaFin, String nombrePersonal);
    public ArrayList<Cita> listarCitasDePersonalPorCicloFechaAlumno(int idPersonal, String ciclo, Date fechaIni, Date fechaFin, String nombreAlumno);
    public int modificar(Cita cita);
    public int eliminar(int idCita);
}
