package com.beraca.Security;

import com.beraca.model.Estudiante_Modulo;
import com.beraca.model.Usuario;

public class SecurityEstudiante_Modulo {

    // VER ASIGNACIONES
    public static boolean puedeVerAsignacion(
            Usuario usuario){

        return usuario.getRol()
                .equalsIgnoreCase("profesor")

                ||

                usuario.getRol()
                        .equalsIgnoreCase("estudiante");
    }

    // CREAR ASIGNACIONES
    public static boolean puedeCrearAsignacion(
            Usuario usuario){

        return usuario.getRol()
                .equalsIgnoreCase("profesor");
    }

    // ACTUALIZAR ASIGNACIONES
    public static boolean puedeActualizarAsignacion(
            Usuario usuario){

        return usuario.getRol()
                .equalsIgnoreCase("profesor");
    }

    // ELIMINAR ASIGNACIONES
    public static boolean puedeEliminarAsignacion(
            Usuario usuario){

        return usuario.getRol()
                .equalsIgnoreCase("profesor");
    }

    // VALIDAR DATOS
    public static boolean asignacionValida(
            Estudiante_Modulo estudianteModulo){

        return estudianteModulo.getEstudiante_id() != null
                && estudianteModulo.getModulo_id() != null;
    }
}
