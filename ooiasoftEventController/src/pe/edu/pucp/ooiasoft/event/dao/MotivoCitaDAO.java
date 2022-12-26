/* 
 * Archivo: MotivoCitaDAO.java
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
import pe.edu.pucp.ooiasoft.event.model.MotivoCita;

public interface MotivoCitaDAO {
    public int insertar(MotivoCita motivo);
    public ArrayList<MotivoCita> listar();
    public int modificar(MotivoCita motivo);
    public int eliminar(int idMotivoCita);
}
