package com.beraca.Security;

import com.beraca.model.Calificacion;
import com.beraca.model.Usuario;

public class SecurityCalificacion {

    // SOLO PROFESOR PUEDE CREAR NOTAS
    public static boolean puedeCrearCalificacion(
            Usuario usuario){

        return usuario.getRol()
                .equalsIgnoreCase("profesor");
    }

    // SOLO PROFESOR PUEDE MODIFICAR NOTAS
    public static boolean puedeModificarCalificacion(
            Usuario usuario){

        return usuario.getRol()
                .equalsIgnoreCase("profesor");
    }

    // SOLO PROFESOR PUEDE ELIMINAR NOTAS
    public static boolean puedeEliminarCalificacion(
            Usuario usuario){

        return usuario.getRol()
                .equalsIgnoreCase("profesor");
    }

    // ESTUDIANTE Y PROFESOR PUEDEN VER NOTAS
    public static boolean puedeVerCalificacion(
            Usuario usuario){

        return usuario.getRol()
                .equalsIgnoreCase("profesor")

                ||

                usuario.getRol()
                        .equalsIgnoreCase("estudiante");
    }

    // VALIDAR QUE LAS NOTAS NO ESTEN VACIAS
    public static boolean calificacionValida(
            Calificacion calificacion){

        return calificacion.getCorte1() != null
                && calificacion.getCorte2() != null
                && calificacion.getCorte3() != null;
    }
}