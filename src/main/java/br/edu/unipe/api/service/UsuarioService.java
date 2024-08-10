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

    public Usuario alterar(Usuario usuario){

        if(Objects.nonNull(usuario.getId())){
             var user = repository.findById(usuario.getId());
             if(user.isPresent()){
                 usuario = repository.save(usuario);
             }else{
                 throw new RuntimeException("Usuário não existe");
             }
        }
        return usuario;
    }


    public void deletar(Integer id){
        log.info("Start - Excluindo user ID {} ", id);
        repository.deleteById(id);
        log.info("End - Excluindo user ID {} ", id);
    }


}
