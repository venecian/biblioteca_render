package com.senacead.biblioteca.service;

import com.senacead.biblioteca.model.Comentarios;
import com.senacead.biblioteca.repository.ComentariosRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService {

    @Autowired
    ComentariosRepository comentariosRepository;

    //CRUD
    public Comentarios criarComentario(Comentarios com) {
        com.setId(null);
        comentariosRepository.save(com);
        return com;
    }

    public Comentarios buscarComentarioPorId(Integer id) {
        return comentariosRepository.findById(id).orElseThrow(); //caso não exista retorna sem erro!
    }

    public Comentarios atualizarComentario(Integer id, Comentarios comEnviado) {
        Comentarios comentarioEncontrado = buscarComentarioPorId(id); //valida se existe o id informado
        comentarioEncontrado.setDescricao(comentarioEncontrado.getDescricao());
        comentariosRepository.save(comentarioEncontrado);
        return comentarioEncontrado;
    }
    
    public void excluirComentario(Integer id) {
        Comentarios comentarioEncontrado = buscarComentarioPorId(id); //valida se existe o id informado
        comentariosRepository.deleteById(comentarioEncontrado.getId());
    }

    public List<Comentarios> listarTodosComentariosPorIdLivro(Integer id) {
        return comentariosRepository.findByLivroId(id); //retorna todos os comentarios de um id livro especifico
    }    
    
    public void excluirTodosComentariosPorLivro(Integer id){
        for(Comentarios reg: listarTodosComentariosPorIdLivro(id)){
           excluirComentario(reg.getId());
        }
    }

}
