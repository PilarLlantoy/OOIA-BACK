/* 
 * Archivo: TemaDAO.java
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
import pe.edu.pucp.ooiasoft.information.model.Tema;

public interface TemaDAO {
    public int insertar(Tema tema);
    public ArrayList<Tema> listar();
    public int modificar(Tema tema);
    public int eliminar(int idTema);
}
