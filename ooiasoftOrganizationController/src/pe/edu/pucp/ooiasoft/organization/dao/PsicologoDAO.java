/* 
 * Archivo: PsicologoDAO.java
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
import pe.edu.pucp.ooiasoft.organization.model.Psicologo;

public interface PsicologoDAO {
    public int insertar(Psicologo psicologo);
    public ArrayList<Psicologo> listarPorCodigoNombre(String codigoNombre);
    public Psicologo buscarPsicologoPorID(int idPsicologo);
    public int modificar(Psicologo psicologo);
    public int eliminar(int idPsicologo);
}
