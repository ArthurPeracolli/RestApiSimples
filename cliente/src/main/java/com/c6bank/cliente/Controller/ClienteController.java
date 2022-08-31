package com.c6bank.cliente.Controller;

import com.c6bank.cliente.Dto.ClienteDto;
import com.c6bank.cliente.Entity.Cliente;
import com.c6bank.cliente.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/login")
    public ResponseEntity login(@RequestBody @Valid ClienteDto cliente){
      Optional<Cliente> result = clienteRepository.findByEmailAndSenha(cliente.getEmail(), cliente.getSenha());
      if(result.isPresent())
        return ResponseEntity.status(200).body(result);

      return ResponseEntity.status(404).build();
    }

    @GetMapping
    public ResponseEntity findAll(){
        List<Cliente> clientes = clienteRepository.findAllClientes();

        if (!clientes.isEmpty())
            return ResponseEntity.status(200).body(clientes);

        return ResponseEntity.status(204).build();

    }

    @GetMapping("/inativo")
    public ResponseEntity findAllInativo(){
        List<Cliente> result = clienteRepository.findAllClientesInativo(false);

        if (!result.isEmpty())
            return ResponseEntity.status(200).body(result);

        return ResponseEntity.status(204).build();


    }

    @PostMapping
    public ResponseEntity register(@RequestBody @Valid Cliente cliente){
        clienteRepository.save(cliente);

        return ResponseEntity.status(200).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody @Valid Cliente cliente, @PathVariable Integer id){
        if(clienteRepository.existsById(id)){
           cliente.setId(id);
           clienteRepository.save(cliente);

           return ResponseEntity.status(200).body("Alteração realizada com sucesso !");
        }
        return ResponseEntity.status(404).body("Id inválido.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        Optional<Cliente> result = clienteRepository.findById(id);

        if (result.isPresent()){
            clienteRepository.delete(result.get());

            return ResponseEntity.status(200).body("Alteração feita com sucesso");
        }

        return ResponseEntity.status(404).body("Id inválido");
    }


}
