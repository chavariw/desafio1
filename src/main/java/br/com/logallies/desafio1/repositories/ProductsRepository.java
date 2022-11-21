package br.com.logallies.desafio1.repositories;

import br.com.logallies.desafio1.models.ProductsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsRepository extends JpaRepository<ProductsModel, Integer> {

    List<ProductsModel> findByCategoryId(int id);

}