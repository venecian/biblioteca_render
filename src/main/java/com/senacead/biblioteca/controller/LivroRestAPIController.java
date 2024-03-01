/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senacead.biblioteca.controller;

import com.senacead.biblioteca.model.Livro;
import com.senacead.biblioteca.service.LivroService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/livro")
public class LivroRestAPIController {
    
    @Autowired
    LivroService livroService;   
    
    @PostMapping("/adicionar")
    public ResponseEntity<Livro> addLivro(@RequestBody Livro liv) {
        var livroCriado = livroService.criar(liv);
        return new ResponseEntity<>(livroCriado, HttpStatus.CREATED);
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List> listar(){
        List<Livro> listagem = livroService.listarTodos();
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }
    
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        livroService.excluir(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<Livro> pesquisar(@PathVariable Integer id){
        Livro livroEncontrado = livroService.buscarPorId(id);
        return new ResponseEntity<>(livroEncontrado,HttpStatus.OK);
    }
    
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Livro> editLivro(@PathVariable Integer id,@RequestBody Livro liv) {
        var livroEditdo = livroService.atualizar(id, liv);
        return new ResponseEntity<>(livroEditdo,HttpStatus.OK);
    }
    
}
