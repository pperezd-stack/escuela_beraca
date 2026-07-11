package com.beraca.Service;

import com.beraca.Repository.ModuloRepository;
import com.beraca.Repository.UsuarioRepository;
import com.beraca.model.Modulo;
import com.beraca.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuloService {

    private final ModuloRepository moduloRepository;
    private final UsuarioRepository usuarioRepository;

    public ModuloService(
            ModuloRepository moduloRepository,
            UsuarioRepository usuarioRepository) {

        this.moduloRepository = moduloRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // ==========================
    // CREAR
    // ==========================
    public Modulo guardar(Modulo modulo) {

        validar(modulo);

        return moduloRepository.save(modulo);
    }

    // ==========================
    // ACTUALIZAR
    // ==========================
    public Modulo actualizar(Modulo modulo){

        if(modulo.getId()==null){
            throw new RuntimeException("Debe indicar el ID del módulo.");
        }

        if(!moduloRepository.existsById(modulo.getId())){
            throw new RuntimeException("El módulo no existe.");
        }

        validar(modulo);

        return moduloRepository.save(modulo);
    }

    // ==========================
    // TODOS
    // ==========================
    public List<Modulo> obtenerTodos(){

        return moduloRepository.findAll();

    }

    // ==========================
    // BUSCAR
    // ==========================
    public Modulo buscar(Long id){

        return moduloRepository.findById(id)
                .orElse(null);

    }

    // ==========================
    // ELIMINAR
    // ==========================
    public boolean eliminar(Long id){

        if(!moduloRepository.existsById(id)){
            return false;
        }

        moduloRepository.deleteById(id);

        return true;

    }

    // ==========================
    // MÓDULO DEL PROFESOR
    // ==========================
    public Modulo obtenerModuloProfesor(Long profesorId){

        return moduloRepository
                .findByProfesor_Id(profesorId)
                .orElse(null);

    }

    // ==========================
    // VALIDACIONES
    // ==========================
    private void validar(Modulo modulo){

        if(modulo.getNombre()==null || modulo.getNombre().isBlank()){
            throw new RuntimeException("Debe indicar el nombre del módulo.");
        }

        if(modulo.getOrden()==null || modulo.getOrden().isBlank()){
            throw new RuntimeException("Debe indicar el orden del módulo.");
        }

        Usuario profesor = modulo.getProfesor();

        if(profesor==null){
            throw new RuntimeException("Debe asignar un profesor.");
        }

    }

}

