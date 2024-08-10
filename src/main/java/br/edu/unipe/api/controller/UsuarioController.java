package br.edu.unipe.api.controller;

import br.edu.unipe.api.model.Usuario;
import br.edu.unipe.api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioRepository repository;

    @GetMapping
    public List<Usuario> listar(){
        log.info("Listando Usuarios");
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario  getUsuario(@PathVariable int id){
        log.info("Inicio - Consulta usuário id {} " , id);
        var usuario = repository.findById(id).get();
        log.info("Fim  - Consulta usuário id {} " , id);
        return usuario;
    }

    @GetMapping("/email/{email}")
    public Usuario  getUsuarioPorEmail(@PathVariable String  email){
        var usuario = repository.buscarPorEmail(email);
        return usuario;
    }

    @PostMapping
    public Usuario postUsuario(@RequestBody Usuario usuario){
        usuario = repository.save(usuario);
        return usuario;
    }

    @PutMapping
    public Usuario putUsuario(@RequestBody Usuario usuario){
        if(Objects.isNull(usuario.getId())){
            throw new RuntimeException("ID não é válido");
        }else if(!repository.existsById(usuario.getId())){
            throw new RuntimeException("ID não encontrado");
        }
        return repository.save(usuario);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Integer id){
        repository.deleteById(id);
    }

}
