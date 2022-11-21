package br.com.logallies.desafio1.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoriesDTO {
    @NotBlank
    private String name;
}
