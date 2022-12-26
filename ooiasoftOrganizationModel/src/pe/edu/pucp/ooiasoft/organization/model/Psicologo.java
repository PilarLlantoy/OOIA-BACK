/* 
 * Archivo: Psicologo.java
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

public class Psicologo extends PersonalCitas {

    //Atributos propios de la clase
    private String colegiatura;

    //Constructores
    //Constructor vacio
    public Psicologo(){}

    //Getters y setters
    public String getColegiatura() {
        return colegiatura;
    }

    public void setColegiatura(String colegiatura) {
        this.colegiatura = colegiatura;
    }

}
