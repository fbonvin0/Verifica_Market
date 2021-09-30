package it.apulia.Verifica.User;

import it.apulia.Verifica.Cliente.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

@Document(collection = "utenti")
@Data //annotazione di lombok per la creazione automatica di getter e setter
@NoArgsConstructor //indica la creazione del costruttore senza parametri
@AllArgsConstructor //indica la creazione del costruttore con un parametro per ogni campo
public class Utente {
    @Id
    private String id;
    private String nome;
    private   double Budget;
    @Indexed
    private String username; //noi metteremo l'email
    private String password;
  //  @DBRef
    private Collection<Role> roles;




    }

