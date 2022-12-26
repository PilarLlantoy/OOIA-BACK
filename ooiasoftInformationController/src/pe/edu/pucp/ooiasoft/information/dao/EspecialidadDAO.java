/* 
 * Archivo: EspecialidadDAO.java
 * Grupo:   Grave Error de Proyecto
 * Integrantes: 
 *  - Oscar Dueñas
 *  - Christian Carhuancho
 *  - Franccesco Jaimes
 *  - Pilar Llantoy
 *  - Cesar Rafael
 * Fecha:   07/06/2021
 */

package pe.edu.pucp.ooiasoft.information.dao;

import java.util.ArrayList;
import pe.edu.pucp.ooiasoft.information.model.Especialidad;

public interface EspecialidadDAO {
    public int insertar(Especialidad especialidad);
    public ArrayList<Especialidad> listarPorNombre(String nombre);
    public int modificar(Especialidad especialidad);
    public int eliminar(int idEspecialidad);
}
