/*
    UNIVERSIDAD DE LAS FUERZAS ARMADAS ESPE
            INGENIERIA DE SOFTWARE
NOMBRE:Enrique Ortega                            PERIODO:202451
ASIGNATURA:Programacion Orientada a Objetos      NRC:1967
FECHA:31-01-2025
Tema: Clase PRINCIPAL donde se podran ejecutar las ventanas de vista

    paleta de colores
    Fondo Principal: Gris Claro (#F0F0F0)
    Texto Principal: Gris Oscuro (#7D7D7D)
    Textos Secundarios: Gris Medio (#B0B0B0)
    Títulos/Elementos Destacados: Gris Antracita (#4A4A4A)
    Detalles/Acentos: Gris Carbón (#2C2C2C)
    Botones Primarios (Aceptar/Confirmar): Azul Acero (#2C3E50)
    Botones Secundarios (Cancelar/Advertencias): Rojo Apagado (#D9534F)
    Hover en Botones: Azul Claro (#3A5978)
    Fondo de Inputs y Tarjetas: Blanco Suave (#FAFAFA)

Fondos Alternativos
Gris Pálido (#E8E8E8)
Un tono más claro que el gris claro original, ideal para secciones o tarjetas que requieren un poco más de contraste sin perder la sobriedad.
Gris Perla (#D7D7D7)
Este color proporciona un fondo suave y elegante, perfecto para áreas donde se desea un enfoque más sutil.
Beige Neutro (#F5F5F0)
Un color muy claro que añade calidez al diseño, manteniendo una apariencia profesional.
Azul Suave (#D0E1F9)
Un azul claro y sereno que puede utilizarse en secciones específicas para dar un toque de color sin ser demasiado brillante.
Verde Salvia Claro (#E3EDE5)
Un verde suave que aporta frescura y elegancia, ideal para áreas de contenido o tarjetas.
Ejemplo de Combinación
Fondo Principal: Gris Claro (#F0F0F0)
Fondo Alternativo: Gris Pálido (#E8E8E8) para secciones secundarias.
Tarjetas: Beige Neutro (#F5F5F0) con bordes en Gris Claro Medio (#E0E0E0).
Sección Destacada: Azul Suave (#D0E1F9) para resaltar información importante.
Área de Contenido: Verde Salvia Claro (#E3EDE5) para agregar un toque natural y relajante.
*/
package modelo;


import controlador.FlujoController;
import javafx.application.Application;
import javafx.stage.Stage;

public class AppMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FlujoController escenas = new FlujoController(primaryStage);
        escenas.indicarLogin(); // Cargar la escena de inicio
    }

    public static void main(String[] args) {
        launch(args);
    }
}

