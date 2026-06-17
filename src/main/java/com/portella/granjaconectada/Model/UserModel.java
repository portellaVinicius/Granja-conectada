package com.portella.granjaconectada.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue
    private Long id;

    private String email;
    private String senha;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<LotesModel> lotesModel;
}
