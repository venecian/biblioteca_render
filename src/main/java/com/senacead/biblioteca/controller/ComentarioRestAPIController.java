
package com.senacead.biblioteca.controller;

import com.senacead.biblioteca.model.Comentarios;
import com.senacead.biblioteca.service.ComentarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comentario")
public class ComentarioRestAPIController {
    
    @Autowired
    ComentarioService comentarioService;
    
    @PostMapping("/adicionar")
    public ResponseEntity<Comentarios> addLivro(@RequestBody Comentarios com) {
        var comentarioCriado = comentarioService.criarComentario(com);
        return new ResponseEntity<>(comentarioCriado, HttpStatus.CREATED);
    }

    @GetMapping("/pesquisar/{idLivro}")
    public ResponseEntity<List> pesquisar(@PathVariable Integer idLivro){
       List<Comentarios> lista = comentarioService.listarTodosComentariosPorIdLivro(idLivro);
       return new ResponseEntity<>(lista,HttpStatus.OK);
    }
    
    @DeleteMapping("/excluir/{idComentario}")
    public ResponseEntity<?> delete(@PathVariable Integer idComentario){
        comentarioService.excluirComentario(idComentario);
        return new ResponseEntity<>(HttpStatus.OK);
    }    
    
    @DeleteMapping("/excluir-todos-comentarios/{idLivro}")
    public ResponseEntity<?> deletePorLivro(@PathVariable Integer idLivro){
        comentarioService.excluirTodosComentariosPorLivro(idLivro);
        return new ResponseEntity<>(HttpStatus.OK);
    }    
    
}
