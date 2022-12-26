/* 
 * Archivo: TutorDAO.java
 * Grupo:   Grave Error de Proyecto
 * Integrantes: 
 *  - Oscar Dueñas
 *  - Christian Carhuancho
 *  - Franccesco Jaimes
 *  - Pilar Llantoy
 *  - Cesar Rafael
 * Fecha:   07/06/2021
 */

package pe.edu.pucp.ooiasoft.organization.dao;

import java.util.ArrayList;
import pe.edu.pucp.ooiasoft.organization.model.Tutor;

public interface TutorDAO {
    public int insertar(Tutor tutor);
    public ArrayList<Tutor> listarPorCodigoONombre(String codigoNombre);
    public Tutor buscarTutorPorID(int idTutor);
    public int modificar(Tutor tutor);
    public int eliminar(int idTutor);
}
