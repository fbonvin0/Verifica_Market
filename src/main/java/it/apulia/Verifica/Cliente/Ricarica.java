package it.apulia.Verifica.Cliente;

import lombok.Data;

@Data
public class Ricarica
{
    String username;
    Double importo;

    public Ricarica(String username, Double importo)
    {
        this.username = username;
        this.importo = importo;
    }
}
