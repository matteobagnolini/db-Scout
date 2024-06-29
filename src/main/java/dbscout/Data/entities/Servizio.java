package dbscout.data.entities;

import java.util.Optional;

public record Servizio(String nome, String dataInizio, String dataFine, String periodo,
    String descrizione, String tipologia, Associato capoReferente, Optional<String> branca,
    Optional<String> Luogo, Optional<String> nomeEnteEsterno, Optional<String> cognomeEsterno,
    Optional<String> resoconto) { }

