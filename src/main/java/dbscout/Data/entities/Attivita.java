package dbscout.data.entities;

import java.util.Optional;

public record Attivita(
    Branca branca, 
    String dataOra, 
    String descrizione, 
    Optional<String> dataFine, 
    Optional<String> materiale, 
    Optional<Integer> quota) { }

