package com.portella.granjaconectada.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ConsumoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer idadeDias;
    private Double consumoGramas;
    private Double pesoEsperado;

    private Boolean padrao;

    @ManyToOne
    @JoinColumn(name = "lote_id")
    private LotesModel lote;

}
