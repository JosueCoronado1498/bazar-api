package com.jcoronado.bazar.controller;

import com.jcoronado.bazar.dto.ClienteDTO;
import com.jcoronado.bazar.service.IClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @PostMapping("/crear")
    public ResponseEntity<ClienteDTO> saveCliente(@Valid @RequestBody ClienteDTO clienteDTO){
        ClienteDTO clienteCreado = clienteService.saveCliente(clienteDTO);

        return ResponseEntity.status(201).body(clienteCreado);
    }

    @GetMapping()
    public ResponseEntity<List<ClienteDTO>> getClientes(){

        return ResponseEntity.ok(clienteService.getClientes());

    }

    @GetMapping("/{id_cliente}")
    public ResponseEntity<ClienteDTO> getClienteById(@PathVariable Long id_cliente){
        return ResponseEntity.ok(clienteService.getClienteById(id_cliente));
    }

    @DeleteMapping("/eliminar/{id_cliente}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id_cliente){
        clienteService.deleteCliente(id_cliente);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/editar/{id_cliente}")
    public ResponseEntity<ClienteDTO> editCliente(@PathVariable Long id_cliente,
                                               @Valid @RequestBody ClienteDTO clienteDTO){
        return ResponseEntity.ok(clienteService.editCliente(id_cliente, clienteDTO));
    }

}
