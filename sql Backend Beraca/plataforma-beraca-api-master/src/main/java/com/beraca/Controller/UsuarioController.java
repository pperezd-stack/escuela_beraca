package com.beraca.Controller;

import com.beraca.model.Usuario;
import org.springframework.web.bind.annotation.*;
import com.beraca.Service.UsuarioService;
import java.util.List;
import com.beraca.dto.UsuarioResponse;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // POST
    @PostMapping("/crear")
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.guardar(usuario);
    }

   @GetMapping
public List<UsuarioResponse> obtenerUsuarios() {

    return usuarioService.obtenerTodos()
            .stream()
            .map(usuario -> new UsuarioResponse(

                    usuario.getId(),
                    usuario.getNombre(),
                    usuario.getRol()

            ))
            .collect(Collectors.toList());

}
    // GET ID
  @GetMapping("/{id}")
public ResponseEntity<UsuarioResponse> buscarUsuario(@PathVariable Long id) {

    Usuario usuario = usuarioService.buscar(id);

    if (usuario == null) {

        return ResponseEntity.notFound().build();

    }

    UsuarioResponse respuesta = new UsuarioResponse(

            usuario.getId(),
            usuario.getNombre(),
            usuario.getRol()

    );

    return ResponseEntity.ok(respuesta);

}
    // DELETE
    @DeleteMapping("/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        boolean eliminado = usuarioService.eliminar(id);

        if(eliminado) {
            return "Usuario eliminado";
        }

        return "Usuario no encontrado";
    }
}
