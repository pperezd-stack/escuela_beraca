package com.beraca.Security;

import com.beraca.model.Modulo;
import com.beraca.model.Usuario;

public class SecurityModulo {

    // VER MODULOS
    public static boolean puedeVerModulo(
            Usuario usuario){

        return usuario.getRol()
                .equalsIgnoreCase("profesor")

                ||

                usuario.getRol()
                        .equalsIgnoreCase("estudiante");
    }

    // CREAR MODULOS
    public static boolean puedeCrearModulo(
            Usuario usuario){

        return usuario.getRol()
                .equalsIgnoreCase("profesor");
    }

    // ACTUALIZAR MODULOS
    public static boolean puedeActualizarModulo(
            Usuario usuario){

        return usuario.getRol()
                .equalsIgnoreCase("profesor");
    }

    // ELIMINAR MODULOS
    public static boolean puedeEliminarModulo(
            Usuario usuario){

        return usuario.getRol()
                .equalsIgnoreCase("profesor");
    }

    // VALIDAR DATOS
    public static boolean moduloValido(
            Modulo modulo){

        return modulo.getNombre() != null
                && modulo.getOrden() != null;
    }
}