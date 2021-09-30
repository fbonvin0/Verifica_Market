package it.apulia.Verifica.market.carrello;

import it.apulia.Verifica.Cliente.Cliente;

import java.util.List;

public interface CarrelloService {

    public Carrello addCarrello(NotaSpesa notaSpesa);
    public Carrello getCarrellobyId(Integer id);
    public List<Carrello> getAllCarrelli();
    public RicercaCarrello findCarrelliByAnno(Integer anno);
    public RicercaCarrello findCarrelliByDataAndTotaleRange(Integer anno, Double min, Double max);
    boolean controllo(Carrello carrello, Cliente cliente);
    void eliminaCarrello(Carrello carrello);



}
