/*
    UNIVERSIDAD DE LAS FUERZAS ARMADAS ESPE
            INGENIERIA DE SOFTWARE
NOMBRE:Enrique Ortega                            PERIODO:202451
ASIGNATURA:Programacion Orientada a Objetos      NRC:1967
FECHA:31-01-2025
Tema: Clase Cliente donde podra tener un portal para solicitar servicios de los
abogados del sistema judicial o firma. Ademas podra hacer seguimiento de su caso
de manera sencilla.
*/
package modelo;

// Librerías
import java.time.LocalDate;

// Hereda atributos de la superclase Usuario
public class Cliente extends Usuario {
    // Atributos específicos
    private String numeroCaso;
    private String descripcionCaso;
    private String estadoCaso; // En proceso, Abierto, Cerrado
    private LocalDate fechaRegistro;
    private Abogado abogadoAsignado; // Referencia a la clase Abogado
    private String notasAdicionales; // Particularidades del caso
    
    // Constructor con parámetros
    public Cliente(int id, String nombre, String apellido, String cedula, String email, String telefono, 
                   String user, String password, String numeroCaso, String descripcionCaso, String estadoCaso, 
                   LocalDate fechaRegistro, Abogado abogadoAsignado, String notasAdicionales) {
        super(id, nombre, apellido, cedula, email, telefono, user, password, "Cliente"); // Rol fijo
        this.numeroCaso = numeroCaso;
        this.descripcionCaso = descripcionCaso;
        this.estadoCaso = estadoCaso;
        this.fechaRegistro = fechaRegistro;
        this.abogadoAsignado = abogadoAsignado;
        this.notasAdicionales = notasAdicionales;
    }

    // Constructor vacío con valores predeterminados
    public Cliente() {
        super(); // Llama al constructor vacío de Usuario
        this.numeroCaso = "";
        this.descripcionCaso = "";
        this.estadoCaso = "En proceso";
        this.fechaRegistro = LocalDate.now(); // Fecha actual por defecto
        this.abogadoAsignado = null;
        this.notasAdicionales = "";
    }

    // Getters y Setters
    /**
     * @return the numeroCaso
     */
    public String getNumeroCaso() {
        return numeroCaso;
    }

    /**
     * @param numeroCaso the numeroCaso to set
     */
    public void setNumeroCaso(String numeroCaso) {
        this.numeroCaso = numeroCaso;
    }

    /**
     * @return the descripcionCaso
     */
    public String getDescripcionCaso() {
        return descripcionCaso;
    }

    /**
     * @param descripcionCaso the descripcionCaso to set
     */
    public void setDescripcionCaso(String descripcionCaso) {
        this.descripcionCaso = descripcionCaso;
    }

    /**
     * @return the estadoCaso
     */
    public String getEstadoCaso() {
        return estadoCaso;
    }

    /**
     * @param estadoCaso the estadoCaso to set
     */
    public void setEstadoCaso(String estadoCaso) {
        this.estadoCaso = estadoCaso;
    }

    /**
     * @return the fechaRegistro
     */
    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * @param fechaRegistro the fechaRegistro to set
     */
    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * @return the abogadoAsignado
     */
    public Abogado getAbogadoAsignado() {
        return abogadoAsignado;
    }

    /**
     * @param abogadoAsignado the abogadoAsignado to set
     */
    public void setAbogadoAsignado(Abogado abogadoAsignado) {
        this.abogadoAsignado = abogadoAsignado;
    }

    /**
     * @return the notasAdicionales
     */
    public String getNotasAdicionales() {
        return notasAdicionales;
    }

    /**
     * @param notasAdicionales the notasAdicionales to set
     */
    public void setNotasAdicionales(String notasAdicionales) {
        this.notasAdicionales = notasAdicionales;
    }
        // Add this method
    public Integer getAbogadoAsignadoId() {
        if (abogadoAsignado != null) {
            return abogadoAsignado.getId();
        }
        return null;
    }
    // Método para mostrar información detallada del cliente
    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Número de caso: " + getNumeroCaso());
        System.out.println("Descripción del caso: " + getDescripcionCaso());
        System.out.println("Estado del caso: " + getEstadoCaso());
        System.out.println("Fecha de registro: " + getFechaRegistro());
        System.out.println("Abogado asignado: " + (getAbogadoAsignado() != null ? getAbogadoAsignado().getNombre() + " " + getAbogadoAsignado().getApellido() : "Ninguno"));
        System.out.println("Notas adicionales: " + getNotasAdicionales());
    }
}

