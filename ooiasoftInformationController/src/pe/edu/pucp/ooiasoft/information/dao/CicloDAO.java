/* 
 * Archivo: CicloDAO.java
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
import pe.edu.pucp.ooiasoft.information.model.Ciclo;

public interface CicloDAO {
    public int insertar(Ciclo ciclo);
    public ArrayList<Ciclo> listar();
    public int modificar(Ciclo ciclo);
    public int eliminar(int idCiclo);
}
