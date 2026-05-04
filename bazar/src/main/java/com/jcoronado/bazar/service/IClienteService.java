package com.jcoronado.bazar.service;

import com.jcoronado.bazar.dto.ClienteDTO;
import com.jcoronado.bazar.model.Cliente;

import java.util.List;

public interface IClienteService {

    //crear
    public ClienteDTO saveCliente(ClienteDTO clienteDTO);

    //traer
    public List<ClienteDTO> getClientes();

    //traer por id
    public ClienteDTO getClienteById(Long id_cliente);

    //eliminar
    public void deleteCliente(Long id_cliente);

    //editar
    public ClienteDTO editCliente(Long id_cliente, ClienteDTO clienteDTO);

}
