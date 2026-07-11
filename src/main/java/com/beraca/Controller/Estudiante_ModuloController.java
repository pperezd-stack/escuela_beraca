package com.beraca.Controller;

import com.beraca.Service.Estudiante_ModuloService;
import com.beraca.model.Estudiante_Modulo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modulosEstudiante")
@CrossOrigin(origins = "*")
public class Estudiante_ModuloController {

    private final Estudiante_ModuloService estudianteModuloService;

    public Estudiante_ModuloController(Estudiante_ModuloService estudianteModuloService) {
        this.estudianteModuloService = estudianteModuloService;
    }

    // ==========================
    // OBTENER TODAS LAS MATRÍCULAS
    // ==========================
    @GetMapping
    public List<Estudiante_Modulo> obtenerModuloEstudiantes() {

        return estudianteModuloService.obtenerTodos();

    }

    // ==========================
    // CREAR
    // ==========================
    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody Estudiante_Modulo matricula){

        try{

            return ResponseEntity.ok(
                    estudianteModuloService.guardar(matricula));

        }catch(RuntimeException e){

            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    // ==========================
    // ACTUALIZAR
    // ==========================
    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizar(
            @RequestBody Estudiante_Modulo matricula){

        try{

            return ResponseEntity.ok(
                    estudianteModuloService.actualizar(matricula));

        }catch(RuntimeException e){

            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    // ==========================
    // ELIMINAR
    // ==========================
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(
            @PathVariable Long id){

        if(estudianteModuloService.eliminar(id)){

            return ResponseEntity.ok("Matrícula eliminada.");

        }

        return ResponseEntity.badRequest()
                .body("La matrícula no existe.");

    }

}