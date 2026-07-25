package com.beraca.Controller;

import com.beraca.model.Modulo;
import com.beraca.model.Usuario;
import com.beraca.Repository.ModuloRepository;
import com.beraca.Security.SecurityModulo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modulos")
@CrossOrigin(origins = "*")
public class ModuloController {

    private final ModuloRepository moduloRepository;

    public ModuloController(ModuloRepository moduloRepository) {
        this.moduloRepository = moduloRepository;
    }

    // GET - ahora sí trae los módulos reales de la base de datos
    @GetMapping
    public List<Modulo> obtenerModulos() {
        return moduloRepository.findAll();
    }

    // POST - ahora sí guarda el módulo en la base de datos
    @PostMapping("/crear")
    public Object crearModulo(@RequestBody Modulo modulo) {
        if (!SecurityModulo.moduloValido(modulo)) {
            return "Datos inválidos";
        }
        return moduloRepository.save(modulo);
    }
}
