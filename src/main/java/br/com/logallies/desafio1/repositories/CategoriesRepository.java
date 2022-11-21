package br.com.logallies.desafio1.repositories;

import br.com.logallies.desafio1.models.CategoriesModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriesRepository extends JpaRepository<CategoriesModel, Integer> {

    CategoriesModel findByName(String name);

}
