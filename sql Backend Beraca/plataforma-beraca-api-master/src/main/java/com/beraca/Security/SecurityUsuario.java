package com.beraca.Security;

import com.beraca.model.Usuario;

public class SecurityUsuario {

    // VERIFICAR SI ES PROFESOR
    public static boolean esProfesor(Usuario usuario){

        return usuario.getRol().equalsIgnoreCase("PROFESOR");
    }

    // VERIFICAR SI ES ESTUDIANTE
    public static boolean esEstudiante(Usuario usuario){

        return usuario.getRol().equalsIgnoreCase("ESTUDIANTE");
    }

    // PERMISO PARA MODIFICAR NOTAS
    public static boolean puedeModificarNotas(Usuario usuario){

        return esProfesor(usuario);
    }

    // PERMISO PARA VER NOTAS
    public static boolean puedeVerNotas(Usuario usuario){

        return esProfesor(usuario) || esEstudiante(usuario);
    }

    // VALIDAR LOGIN
    public static boolean loginValido(Usuario usuario){

        return usuario.getNombre() != null
                && !usuario.getNombre().isBlank()
                && usuario.getPassword() != null
                && !usuario.getPassword().isBlank();
    }

}
