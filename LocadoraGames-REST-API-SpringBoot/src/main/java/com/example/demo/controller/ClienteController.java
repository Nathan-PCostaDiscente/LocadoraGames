package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Cliente;
import com.example.demo.entity.Jogos;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.responses.Response;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping("/cliente/mostrar")
    public List<Cliente> allClientes(){
        return clienteRepository.findAll();
    }


    @GetMapping("/cliente/{id}")
    public Optional<Cliente> getCliente(@PathVariable(value = "id") long id){
        return clienteRepository.findById(id);
        
    }

    @PostMapping("/cliente/adicionar")
    public ResponseEntity<Response<Cliente>> Post(@Valid @RequestBody Cliente cliente, BindingResult result) 
    {
    	Response<Cliente> response = new Response<Cliente>();    	
    	if (result.hasErrors()) {
    		result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
    		return ResponseEntity.badRequest().body(response);
    	}
    	clienteRepository.save(cliente);
    	response.setData(cliente);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(cliente.isPresent()){
            clienteRepository.delete(cliente.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/cliente/{id}")
    public ResponseEntity<Response<Cliente>> Put(@PathVariable(value = "id") long id, @Valid @RequestBody Cliente newCliente, BindingResult result)
    {
        Optional<Cliente> oldCliente = clienteRepository.findById(id);
    	Response<Cliente> response = new Response<Cliente>();    	
    	if (result.hasErrors()) {
    		result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
    		return ResponseEntity.badRequest().body(response);
    	}        	        
        if(oldCliente.isPresent()){
            Cliente cliente = oldCliente.get();
            cliente.setNome(newCliente.getNome());
            cliente.setEndereco(newCliente.getEndereco());
            cliente.setIdade(newCliente.getIdade());
            cliente.setSexo(newCliente.getSexo());
            response.setData(cliente);
            clienteRepository.save(cliente);
            return ResponseEntity.ok(response);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }    
    
    

}

