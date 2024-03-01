
package com.senacead.biblioteca.service;

import com.senacead.biblioteca.model.Livro;
import com.senacead.biblioteca.repository.LivroRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivroService {

    @Autowired
    LivroRepository livroRepository;
    
    //CRUD
    public Livro criar(Livro liv) {
        liv.setId(null);
        livroRepository.save(liv);
        return liv;
    }
    
    public List<Livro> listarTodos(){
        return livroRepository.findAll();
    }
    
    public Livro buscarPorId(Integer id) {
        return livroRepository.findById(id).orElseThrow(); //caso não exista retorna sem erro!
    }
    
    public void excluir(Integer id) {
        Livro livroEncontrado = buscarPorId(id); //valida se existe o id informado
        livroRepository.deleteById(livroEncontrado.getId());
    }
    
    public Livro atualizar(Integer id, Livro livroEnviado) {
        Livro livroEncontrado = buscarPorId(id); //valida se existe o id informado
        livroEncontrado.setNome(livroEnviado.getNome());
        livroEncontrado.setAutor(livroEnviado.getAutor());
        livroEncontrado.setLido(livroEnviado.isLido());
        livroRepository.save(livroEncontrado);
        return livroEncontrado;
    }
    
    
}
