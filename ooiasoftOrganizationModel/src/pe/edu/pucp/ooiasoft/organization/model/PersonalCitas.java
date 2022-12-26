/* 
 * Archivo: PersonalCitas.java
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

public class PersonalCitas extends MiembroOOIA {

    //Atributos propios de la clase
    private int horasSemanales;
    private double rendimiento;

    //Constructores
    
    //Constructor vacio
    public PersonalCitas(){
        rendimiento = horasSemanales = 0;
    }
    
    //Getters y setters
    public int getHorasSemanales() {
        return horasSemanales;
    }

    public void setHorasSemanales(int horasSemanales) {
        this.horasSemanales = horasSemanales;
    }

    public double getRendimiento() {
        return rendimiento;
    }

    public void setRendimiento(double rendimiento) {
        this.rendimiento = rendimiento;
    }

}
