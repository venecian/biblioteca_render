package com.senacead.biblioteca.controller;

import com.senacead.biblioteca.model.Comentarios;
import com.senacead.biblioteca.model.Livro;
import com.senacead.biblioteca.service.ComentarioService;
import com.senacead.biblioteca.service.LivroService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LivroController {
    
    @Autowired 
    LivroService livroService;
    
    @Autowired 
    ComentarioService comentarioService;     
        
    @GetMapping("/inicio") //eh definido a URL que sera chamada no navegador
    public String inicio(){
        return "index"; //sera apontado o arquivo HTML que sera chamado (template)
    }
    
    @GetMapping("/inserir") //eh definido a URL que sera chamada no navegador
    public String cadastro(Model model){
        model.addAttribute("livro", new Livro());
        return "cadastro"; //sera apontado o arquivo HTML que sera chamado (template)
    } 
    
    @GetMapping("/alterar")
    public String alterarLivro(Model model, @RequestParam String id){
        Integer idLivro = Integer.parseInt(id);
        Livro livroEncontrado = livroService.buscarPorId(idLivro);
        model.addAttribute("livro", livroEncontrado);
        return "cadastro";
    }
    
    @PostMapping("/gravar") 
    public String processarFormulario(@ModelAttribute Livro livro,Model model){

        if (livro.getId()!=null) {
            livroService.atualizar(livro.getId(), livro);
        } else {
            livroService.criar(livro);
        }
        return "redirect:/listar";
    }

    
    @GetMapping("/excluir")
    public String excluirLivro(Model model, @RequestParam String id){
        Integer idLivro = Integer.parseInt(id);
        comentarioService.excluirTodosComentariosPorLivro(idLivro);
        livroService.excluir(idLivro);
        return "redirect:/listar";
    }    
    
    @GetMapping("/listar") //eh definido a URL que sera chamada no navegador
    public String listagem(Model model){
        model.addAttribute("lista",livroService.listarTodos());
        return "listagem"; //sera apontado o arquivo HTML que sera chamado (template)
    } 

    @GetMapping("/excluirComentario")
    public String exibirComentario(@RequestParam String id, Model model){
        Integer idComentario = Integer.parseInt(id);
        
        Comentarios objComentario = comentarioService.buscarComentarioPorId(idComentario);
        
        comentarioService.excluirComentario(idComentario);
        
        Integer idLivro = objComentario.getLivro().getId();
        return "redirect:/exibir?id=" + idLivro;
    }    
     
    @GetMapping("/exibir")
    public String exibirDados(@RequestParam String id, Model model){
        Integer idLivro = Integer.parseInt(id);
        
        Livro livroEncontrado = new Livro();
        livroEncontrado = livroService.buscarPorId(idLivro);
        
        List<Comentarios> comentariosEncontrado = new ArrayList<>();
        comentariosEncontrado = comentarioService.listarTodosComentariosPorIdLivro(idLivro);
        
        model.addAttribute("livro", livroEncontrado);
        model.addAttribute("comentario", new Comentarios());
        model.addAttribute("comentarios", comentariosEncontrado);
        return "exibir";
    }
    
    @PostMapping("/gravar-comentario") 
    public String processarComentario(@ModelAttribute Livro livro,@ModelAttribute Comentarios comentario,Model model){
        comentario.setLivro(livro);
        comentarioService.criarComentario(comentario);
        return "redirect:/listar";
    } 
    
    
}
