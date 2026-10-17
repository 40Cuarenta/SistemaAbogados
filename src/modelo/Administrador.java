/*
    UNIVERSIDAD DE LAS FUERZAS ARMADAS ESPE
            INGENIERIA DE SOFTWARE
NOMBRE:Enrique Ortega                            PERIODO:202451
ASIGNATURA:Programacion Orientada a Objetos      NRC:1967
FECHA:31-01-2025
Tema: Clase Administrador donde podra tener un portal para observar los clientes
abogados de su firma, ganancias de los casos, los casos pendientes.
*/
package modelo;

// Librerías
import java.time.LocalDate;

// Hereda atributos de la superclase Usuario
public class Administrador extends Usuario {
    // Atributos específicos
    private String nivelAcceso; // Ejemplo: "superadmin", "gestor"
    private LocalDate fechaIngreso; // Fecha de ingreso al sistema
    private boolean estadoCuenta; // true = Activo, false = Desactivado
    
    // Constructor con parámetros
    public Administrador(int id, String nombre, String apellido, String cedula, String email, String telefono, 
                         String user, String password, String nivelAcceso, LocalDate fechaIngreso, boolean estadoCuenta) {
        super(id, nombre, apellido, cedula, email, telefono, user, password, "Administrador"); // Rol fijo
        this.nivelAcceso = nivelAcceso;
        this.fechaIngreso = fechaIngreso;
        this.estadoCuenta = estadoCuenta;
    }

    // Constructor vacío con valores predeterminados
    public Administrador() {
        super(); // Llama al constructor vacío de Usuario
        this.nivelAcceso = "gestor"; // Nivel de acceso por defecto
        this.fechaIngreso = LocalDate.now(); // Fecha actual
        this.estadoCuenta = true; // Activo por defecto
    }
    
    //metodos de encapsulamiento

    /**
     * @return the nivelAcceso
     */
    public String getNivelAcceso() {
        return nivelAcceso;
    }

    /**
     * @param nivelAcceso the nivelAcceso to set
     */
    public void setNivelAcceso(String nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }

    /**
     * @return the fechaIngreso
     */
    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    /**
     * @param fechaIngreso the fechaIngreso to set
     */
    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    /**
     * @return the estadoCuenta
     */
    public boolean isEstadoCuenta() {
        return estadoCuenta;
    }

    /**
     * @param estadoCuenta the estadoCuenta to set
     */
    public void setEstadoCuenta(boolean estadoCuenta) {
        this.estadoCuenta = estadoCuenta;
    }
    
    //Metodos
    // Método para mostrar información detallada del administrador
    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Nivel de Acceso: " + nivelAcceso);
        System.out.println("Fecha de ingreso: " + fechaIngreso);
        System.out.println("Estado de Cuenta: " + (estadoCuenta ? "Activo" : "Desactivado"));
    }
}
