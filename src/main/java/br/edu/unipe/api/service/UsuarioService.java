package br.edu.unipe.api.service;

import br.edu.unipe.api.model.Usuario;
import br.edu.unipe.api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public List<Usuario> listarUsuarios(){
        return repository.findAll();
    }

    public Usuario salvar(Usuario usuario){
        return repository.save(usuario);
    }

    public Usuario consultar(Integer id){
        validarExistenciaId(id);
        return repository.findById(id).get();
    }

    public Usuario alterar(Usuario usuario){
        validarExistenciaId(usuario.getId());
        return repository.save(usuario);
    }


    public void deletar(Integer id){
        log.info("Start - Excluindo user ID {} ", id);
        repository.deleteById(id);
        log.info("End - Excluindo user ID {} ", id);
    }

    public Usuario buscarPorEmail(String email){
        var usuario = repository.buscarPorEmail(email);
        if(Objects.isNull(usuario))
            throw new RuntimeException("Email não localizado " +email);

        return usuario;
    }

    private void validarExistenciaId(Integer id){
        if(Objects.isNull(id) || !repository.existsById(id)){
            throw new RuntimeException("Usuário não existe para o id "+id);
        }
    }

}
