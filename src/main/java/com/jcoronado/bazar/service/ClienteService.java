package com.jcoronado.bazar.service;

import com.jcoronado.bazar.dto.ClienteDTO;
import com.jcoronado.bazar.exception.ResourceNotFoundException;
import com.jcoronado.bazar.model.Cliente;
import com.jcoronado.bazar.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private IClienteRepository clienteRepository;

    @Override
    public ClienteDTO saveCliente(ClienteDTO clienteDTO) {
        Cliente cliente = mapToEntity(clienteDTO);
        Cliente clienteCreado = clienteRepository.save(cliente);
        return mapToDTO(clienteCreado);
    }

    @Override
    public List<ClienteDTO> getClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public ClienteDTO getClienteById(Long id_cliente) {
        Cliente cliente = clienteRepository.findById(id_cliente)
                .orElseThrow(()-> new ResourceNotFoundException("Cliente no encontrado"));
        return mapToDTO(cliente);
    }

    @Override
    public void deleteCliente(Long id_cliente) {
        if (!clienteRepository.existsById(id_cliente)) {
            throw new ResourceNotFoundException("Cliente no encontrado");
        }
        clienteRepository.deleteById(id_cliente);
    }

    @Override
    public ClienteDTO editCliente(Long id_cliente, ClienteDTO clienteDTO) {

        //valido que el cliente existe
        Cliente existe = clienteRepository.findById(id_cliente)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

        //actualizo los datos
        existe.setNombre(clienteDTO.getNombre());
        existe.setApellido(clienteDTO.getApellido());

        Cliente actualizado = clienteRepository.save(existe);

        return mapToDTO(actualizado);
    }

    private ClienteDTO mapToDTO(Cliente cliente) {
        return new ClienteDTO(
                cliente.getId_cliente(),
                cliente.getNombre(),
                cliente.getApellido()
        );
    }

    private Cliente mapToEntity(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setId_cliente(clienteDTO.getIdCliente());
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setApellido(clienteDTO.getApellido());
        return cliente;
    }
}
