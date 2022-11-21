package br.com.logallies.desafio1.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_categories")
public class CategoriesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private String name;
}
