package br.com.logallies.desafio1.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProductsDTO {
    @NotBlank
    private String title;

    @NotBlank
    private Double price;

    @NotBlank
    private String description;

    @NotBlank
    private String image;
}