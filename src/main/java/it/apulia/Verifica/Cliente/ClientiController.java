package it.apulia.Verifica.Cliente;


import it.apulia.Verifica.User.UserServiceimp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/c")
public class ClientiController
{
    private final ClienteServiceImpl clienteService;
    private final UserServiceimp userService;

    @Autowired
    public ClientiController(ClienteServiceImpl clienteService, UserServiceimp userService)
    {
        this.clienteService = clienteService;
        this.userService = userService;
    }

    @GetMapping("/clienti")
    public ResponseEntity<List<Cliente>> mostraClienti()
    {
        return ResponseEntity.ok().body(this.clienteService.getClienti());
    }

    @PostMapping("/cliente")
    public ResponseEntity<Cliente> creaCliente(@RequestBody Cliente cliente)
    {
        this.clienteService.saveCliente(cliente);
        userService.addRoleToUtente(cliente.getEmail(), "ROLE_USER");
        return ResponseEntity.ok().body(cliente);
    }

    @PostMapping("/ricarica")
    public ResponseEntity<Cliente> effettuaRicarica(@RequestBody Ricarica ricarica)
    {
        Cliente cliente = this.clienteService.getClienteByEmail(ricarica.getUsername());
        cliente.setBudget(cliente.getBudget() + ricarica.getImporto());
        return ResponseEntity.ok().body(cliente);
    }

}
