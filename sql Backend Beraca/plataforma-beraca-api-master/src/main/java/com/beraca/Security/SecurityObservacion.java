package com.beraca.Security;

import com.beraca.model.Observacion;
import com.beraca.model.Usuario;

public class SecurityObservacion {

    // VER OBSERVACIONES
    public static boolean puedeVerObservacion(
            Usuario usuario){

        return usuario.getRol()
                .equalsIgnoreCase("profesor")

                ||

                usuario.getRol()
                        .equalsIgnoreCase("estudiante");
    }

    // CREAR OBSERVACIONES
    public static boolean puedeCrearObservacion(
            Usuario usuario){

        return usuario.getRol()
                .equalsIgnoreCase("profesor");
    }

    // ACTUALIZAR OBSERVACIONES
    public static boolean puedeActualizarObservacion(
            Usuario usuario){

        return usuario.getRol()
                .equalsIgnoreCase("profesor");
    }

    // ELIMINAR OBSERVACIONES
    public static boolean puedeEliminarObservacion(
            Usuario usuario){

        return usuario.getRol()
                .equalsIgnoreCase("profesor");
    }

    // VALIDAR DATOS
    // En tu clase SecurityObservacion.java
    public static boolean observacionValida(Observacion observacion) {
        // Validamos que al menos uno de los tres cortes tenga información
        return (observacion.getComentarioCorte1() != null && !observacion.getComentarioCorte1().isEmpty()) ||
                (observacion.getComentarioCorte2() != null && !observacion.getComentarioCorte2().isEmpty()) ||
                (observacion.getComentarioCorte3() != null && !observacion.getComentarioCorte3().isEmpty());
    }
}