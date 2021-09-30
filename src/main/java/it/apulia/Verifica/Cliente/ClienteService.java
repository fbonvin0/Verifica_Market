package it.apulia.Verifica.Cliente;

import java.util.List;

public interface ClienteService
{
    List<Cliente> getClienti();
    Cliente getClienteByEmail(String email);
    void deleteCliente(String idCliente);
    void saveCliente(Cliente cliente);
    void resetAll();
    Cliente getClienteById(String idCliente);
}
