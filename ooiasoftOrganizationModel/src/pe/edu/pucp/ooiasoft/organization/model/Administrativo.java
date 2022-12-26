/* 
 * Archivo: Administrativo.java
 * Grupo:   Grave Error de Proyecto
 * Integrantes: 
 *  - Oscar Dueñas
 *  - Christian Carhuancho
 *  - Franccesco Jaimes
 *  - Pilar Llantoy
 *  - Cesar Rafael
 * Fecha:   07/06/2021
 */

package pe.edu.pucp.ooiasoft.organization.model;

import java.util.Date;

public class Administrativo extends MiembroOOIA {

    //Atributos de relaciones
    private TipoAdministrativo tipo;

    //Constructor vacio
    public Administrativo(){}
    
    //Getters y setters
    public TipoAdministrativo getTipo() {
        return tipo;
    }

    public void setTipo(TipoAdministrativo tipo) {
        this.tipo = tipo;
    }

}
