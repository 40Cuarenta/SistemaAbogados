/*
    UNIVERSIDAD DE LAS FUERZAS ARMADAS ESPE
            INGENIERIA DE SOFTWARE
NOMBRE:Enrique Ortega                            PERIODO:202451
ASIGNATURA:Programacion Orientada a Objetos      NRC:1967
FECHA:31-01-2025
Tema: Clase Abogado, hereda atributos de la super clase Usuario, ademas de 
tener mas atributos que identifica a un abogado
Podra realizar seguimientos de los casos, crear casos.
*/
package modelo;

// Hereda atributos de la clase Usuario
public class Abogado extends Usuario {
    // Atributos específicos de Abogado
    private String registro;      // Número de registro profesional
    private String especialidad;  // Rama del derecho en la que se especializa
    private int experiencia;      // Años de experiencia
    private double honorarioHora; // Tarifa por hora

    // Constructor con parámetros
    public Abogado(int id, String nombre, String apellido, String cedula, String email, String telefono, 
                   String user, String password, String registro, String especialidad, int experiencia, double honorarioHora) {
        super(id, nombre, apellido, cedula, email, telefono, user, password, "Abogado"); // Se establece automáticamente el rol
        this.registro = registro;
        this.especialidad = especialidad;
        this.experiencia = experiencia;
        this.honorarioHora = honorarioHora;
    }

    // Constructor vacío
    public Abogado() {
        super(); // Llama al constructor vacío de Usuario
        this.registro = "";
        this.especialidad = "";
        this.experiencia = 0;
        this.honorarioHora = 0.0;
    }
    
    //campos encapsulados

    /**
     * @return the registro
     */
    public String getRegistro() {
        return registro;
    }

    /**
     * @param registro the registro to set
     */
    public void setRegistro(String registro) {
        this.registro = registro;
    }

    /**
     * @return the especialidad
     */
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * @param especialidad the especialidad to set
     */
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * @return the experiencia
     */
    public int getExperiencia() {
        return experiencia;
    }

    /**
     * @param experiencia the experiencia to set
     */
    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    /**
     * @return the honorarioHora
     */
    public double getHonorarioHora() {
        return honorarioHora;
    }

    /**
     * @param honorarioHora the honorarioHora to set
     */
    public void setHonorarioHora(double honorarioHora) {
        this.honorarioHora = honorarioHora;
    }
    // Método para mostrar información completa del abogado
    public void mostrarDatosUsuario() {
        super.mostrarInfo(); // Muestra los datos generales del usuario
        System.out.println("Registro Profesional: " + registro);
        System.out.println("Especialidad: " + especialidad);
        System.out.println("Años de Experiencia: " + experiencia);
        System.out.println("Honorarios por Hora: $" + honorarioHora);
    }  
}
