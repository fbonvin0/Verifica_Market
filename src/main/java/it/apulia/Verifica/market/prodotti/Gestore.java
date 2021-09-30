package it.apulia.Verifica.market.prodotti;


import it.apulia.Verifica.Cliente.Cliente;
import it.apulia.Verifica.Cliente.ClienteServiceImpl;
import it.apulia.Verifica.market.carrello.Carrello;
import it.apulia.Verifica.market.carrello.CarrelloServiceImpl;
import it.apulia.Verifica.market.carrello.NotaSpesa;
import it.apulia.Verifica.market.carrello.RicercaCarrello;
import it.apulia.Verifica.market.utils.OggettoRegex;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/market")
public class Gestore {
    private final ProductServiceImpl prodottoService;
    private final CarrelloServiceImpl carrelloService;
    private final ClienteServiceImpl clienteService;

    @Autowired
    public Gestore(ProductServiceImpl prodottoService, CarrelloServiceImpl carrelloService, ClienteServiceImpl clienteService) {
        this.prodottoService = prodottoService;
        this.carrelloService = carrelloService;
        this.clienteService = clienteService;
    }

    @PostMapping("/prodotti")
    ResponseEntity<?> aggiungiProdotto(@RequestBody Prodotto prodotto) {
        Prodotto p = this.prodottoService.addProdotto(prodotto);
        return ResponseEntity.ok().body(prodotto);
    }

    @GetMapping("/prodotti")
    ResponseEntity<List<Prodotto>> mostraProdotti() {
        return ResponseEntity.ok().body(this.prodottoService.getProdotti());
    }

    @GetMapping("/prodotti/{productId}")
    ResponseEntity<Prodotto> getProductById(@PathVariable Integer idProdotto) {
        Prodotto prodotto = this.prodottoService.getProdottoById(idProdotto);
        return ResponseEntity.ok().body(prodotto);
    }

    @PutMapping("/prodotti/{productId}")
    ResponseEntity<Prodotto> aggiornaProdotto(@PathVariable Integer idProdotto, @RequestBody Prodotto prodotto)
    {
        this.prodottoService.updateProdotto(prodotto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/prodotti/{productId}")
    ResponseEntity<?> eliminaProdotto(@PathVariable Integer idProdotto)
    {
        this.prodottoService.deleteProdottoById(idProdotto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/prodotti/ricerca")
    ResponseEntity<?> SearchByString(@RequestBody OggettoRegex regex)
    {
        return ResponseEntity.ok().body(this.prodottoService.getProdottiNomeSimile(regex.getRegex()));
    }

    @GetMapping("/carrelli")
    ResponseEntity<List<Carrello>> mostraCarrelli()
    {
        return ResponseEntity.ok().body(this.carrelloService.getAllCarrelli());
    }

    @PostMapping("/carrelli")
    ResponseEntity<Carrello> creaCarrello(@RequestBody NotaSpesa notaSpesa, @RequestParam String idCliente){
        Carrello carrello = this.carrelloService.addCarrello(notaSpesa);
        Cliente cliente = this.clienteService.getClienteById(idCliente);
        if (this.carrelloService.controllo(carrello, cliente))
        {
            return ResponseEntity.ok().body(carrello);
        }
        else
        {
            this.carrelloService.eliminaCarrello(carrello);
            return null;
        }
    }

    @GetMapping("/carrelli/{idcarrello}")
    ResponseEntity<Carrello> getCarrelloById(@PathVariable Integer idCarrello)
    {
        return ResponseEntity.ok().body(this.carrelloService.getCarrellobyId(idCarrello));
    }

    @GetMapping("/carrelli/bilancio")
    ResponseEntity<RicercaCarrello> getRisultatoPerAnno(@RequestParam Integer anno)
    {
        return ResponseEntity.ok().body(this.carrelloService.findCarrelliByAnno(anno));
    }

    @GetMapping("/carrelli/bilanciofiltrato")
    ResponseEntity<RicercaCarrello> getRisultatoFiltrato(@RequestParam Integer anno, @RequestParam(defaultValue = "0.00") Double min, @RequestParam(defaultValue = "1000000.00") Double max)
    {
        return ResponseEntity.ok().body(this.carrelloService.findCarrelliByDataAndTotaleRange(anno,min,max));
    }
}
