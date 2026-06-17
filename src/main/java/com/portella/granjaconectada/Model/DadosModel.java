package com.portella.granjaconectada.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dados")
public class DadosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double aguaAtual;
    private Double racaoAtual;
    private Float temperaturaAtual;
    private Integer mortalidadeAtual;
    private Double pesoAtual;
    private Integer idade;

    @ManyToOne
    @JoinColumn(name = "lote_id")
    private LotesModel lote;

}
