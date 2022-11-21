package br.com.logallies.desafio1.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_products")
public class ProductsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private Double price;
    private String description;
    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoriesModel categoriesModel;
}
