package it.apulia.Verifica.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService
{
    private final ClienteRepo clienteRepository;

    @Autowired
    public ClienteServiceImpl(ClienteRepo clienteRepository)
    {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> getClienti()
    {
        return this.clienteRepository.findAll();
    }

    @Override
    public Cliente getClienteByEmail(String email)
    {
        return this.clienteRepository.findClienteByEmail(email);
    }

    @Override
    public void deleteCliente(String idCliente)
    {
        this.clienteRepository.deleteById(idCliente);
    }

    @Override
    public void saveCliente(Cliente cliente)
    {
        this.clienteRepository.save(cliente);
    }

    @Override
    public void resetAll()
    {
        this.clienteRepository.deleteAll();
    }

    @Override
    public Cliente getClienteById(String idCliente)
    {
        return this.clienteRepository.findById(idCliente).get();
    }
}
