package br.edu.unipe.api.controller;

import br.edu.unipe.api.model.Usuario;
import br.edu.unipe.api.repository.UsuarioRepository;
import br.edu.unipe.api.service.UsuarioService;
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

    private final UsuarioService service;

    @GetMapping
    public List<Usuario> listar(){
        log.info("Listando Usuarios");
        return service.listarUsuarios();
    }

    @GetMapping("/{id}")
    public Usuario  consultarPorId(@PathVariable int id){
        log.info("Inicio - Consulta usuário id {} " , id);
        var usuario = service.consultar(id);
        log.info("Fim  - Consulta usuário id {} " , id);
        return usuario;
    }

    @GetMapping("/email/{email}")
    public Usuario  getUsuarioPorEmail(@PathVariable String  email){
        var usuario = service.buscarPorEmail(email);
        return usuario;
    }

    @PostMapping
    public Usuario salvar(@RequestBody Usuario usuario){
        usuario = service.salvar(usuario);
        return usuario;
    }

    @PutMapping
    public Usuario atualizar(@RequestBody Usuario usuario){
        return service.alterar(usuario);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Integer id){
        service.deletar(id);
    }

}
