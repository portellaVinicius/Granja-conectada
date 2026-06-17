package com.portella.granjaconectada.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LotesModel {

    private Integer quantidadeAves;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInicio;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToMany(mappedBy = "lote", fetch = FetchType.LAZY)
    private List<DadosModel> dadosModels;

    @OneToMany(mappedBy = "lote", fetch = FetchType.LAZY)
    private List<AlertasModel> alertasModels;

    @OneToMany(mappedBy = "lote", fetch = FetchType.LAZY)
    private List<ConsumoModel> consumoRacaos;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private UserModel user;

}
