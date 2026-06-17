package com.portella.granjaconectada.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "alertas")
public class AlertasModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String messagem;
    private String nivel;
    private String tipo;
    private Double valorAtual;
    private Double valorEsperado;

    private LocalDateTime data;

    @ManyToOne
    private LotesModel lote;
}
