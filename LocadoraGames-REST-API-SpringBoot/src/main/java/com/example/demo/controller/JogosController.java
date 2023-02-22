package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Cliente;
import com.example.demo.entity.Jogos;
import com.example.demo.repository.JogosRepository;
import com.example.demo.responses.Response;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
public class JogosController {

    @Autowired
    private JogosRepository jogosRepository;
    
    @RequestMapping(value = "/jogos/mostrar", method = RequestMethod.GET)
    public List<Jogos> Get() {
        return jogosRepository.findAll();
    }
    
    @RequestMapping(value = "/jogo/{id}", method = RequestMethod.GET) 
    public ResponseEntity<Jogos> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Jogos> jogos = jogosRepository.findById(id);
        if(jogos.isPresent())
            return new ResponseEntity<Jogos>(jogos.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @RequestMapping(value = "/jogo/adicionar", method =  RequestMethod.POST)
    public ResponseEntity<Response<Jogos>> Post(@Valid @RequestBody Jogos jogos, BindingResult result) 
    {
    	Response<Jogos> response = new Response<Jogos>();    	
    	if (result.hasErrors()) {
    		result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
    		return ResponseEntity.badRequest().body(response);
    	}
    	jogosRepository.save(jogos);
    	response.setData(jogos);
        return ResponseEntity.ok(response);
    }        

    
    @RequestMapping(value = "/jogo/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Response<Jogos>> Put(@PathVariable(value = "id") long id, @Valid @RequestBody Jogos newjogo, BindingResult result)
    {
        Optional<Jogos> oldJogo = jogosRepository.findById(id);
    	Response<Jogos> response = new Response<Jogos>();    	
    	if (result.hasErrors()) {
    		result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
    		return ResponseEntity.badRequest().body(response);
    	}        	        
        if(oldJogo.isPresent()){
            Jogos jogos = oldJogo.get();
            jogos.setNome(newjogo.getNome());
            jogos.setQuantidade(newjogo.getQuantidade());
            jogos.setConsoles(newjogo.getConsoles());
            jogos.setGeneros(newjogo.getGeneros());
            jogos.setIdadem(newjogo.getIdadem());
            response.setData(jogos);
            jogosRepository.save(jogos);
            return ResponseEntity.ok(response);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }    
    
    
    @RequestMapping(value = "/jogo/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Jogos> jogos = jogosRepository.findById(id);
        if(jogos.isPresent()){
        	jogosRepository.delete(jogos.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }    
    
}
