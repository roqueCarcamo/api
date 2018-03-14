package com.services.servicios.listener;

/**
 * @descripcion: Clase que permite iniciar el contexto de la aplicación.
* @autor: (Rodolfo Cárcamo)
 * @fechaCreacion: 12-04-2017
*/
public class ContextoAplicacion {

    private static ContextoAplicacion INSTANCE;
    private String rutaContextoAplicacion;

    private ContextoAplicacion() {
    }

    /**
     * Método para crear la instancia.
     *
     */
    private static void crearInstancia() {
        if (INSTANCE == null) {
            // Sólo se accede a la zona sincronizada
            // cuando la instancia no está creada
            synchronized (ContextoAplicacion.class) {
                // En la zona sincronizada sería necesario volver
                // a comprobar que no se ha creado la instancia
                if (INSTANCE == null) {
                    INSTANCE = new ContextoAplicacion();
                }
            }
        }
    }

    /**
     * Método para obtener la instancia de la aplicación.
     *
     * @return ContextoAplicacion INSTANCE
     */
    public static ContextoAplicacion getInstance() {
        crearInstancia();
        return INSTANCE;
    }

    /**
     * Método para iniciar sesión.
     *
     * @return Object object
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

   /**
     * Método para obtener la ruta de la aplicación.
     *
     * @return String rutaContextoAplicacion
     */
    public String getRutaContextoAplicacion() {
        return rutaContextoAplicacion;
    }

    /**
     * Método para cambiar la ruta de la aplicación.
     *
     * @param String rutaContextoAplicacion
     */
    public void setRutaContextoAplicacion(String rutaContextoAplicacion) {
        this.rutaContextoAplicacion = rutaContextoAplicacion;
    }
}
