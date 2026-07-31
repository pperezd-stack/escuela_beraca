package com.beraca.Service;

import com.beraca.Repository.ModuloRepository;
import com.beraca.model.Modulo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModuloService {

    private final ModuloRepository moduloRepository;

    public ModuloService(ModuloRepository moduloRepository) {
        this.moduloRepository = moduloRepository;
    }

    // POST
    public Modulo guardar(Modulo modulo) {
        return moduloRepository.save(modulo);
    }

    // GET - Devuelve los módulos únicos filtrados por nombre para evitar duplicados
    public List<Modulo> obtenerTodos() {
        List<Modulo> todos = moduloRepository.findAll();
        
        return todos.stream()
                .collect(Collectors.toMap(
                    Modulo::getNombre, 
                    modulo -> modulo, 
                    (existing, replacement) -> existing
                ))
                .values()
                .stream()
                .toList();
    }

    // GET ID
    public Modulo buscar(Long id) {
        return moduloRepository.findById(id).orElse(null);
    }

    // DELETE
    public boolean eliminar(Long id) {
        if (moduloRepository.existsById(id)) {
            moduloRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

