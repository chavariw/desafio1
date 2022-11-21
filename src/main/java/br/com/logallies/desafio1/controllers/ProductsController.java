package br.com.logallies.desafio1.controllers;

import br.com.logallies.desafio1.dtos.ProductsDTO;
import br.com.logallies.desafio1.models.CategoriesModel;
import br.com.logallies.desafio1.models.ProductsModel;
import br.com.logallies.desafio1.repositories.CategoriesRepository;
import br.com.logallies.desafio1.repositories.ProductsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.RecursiveTask;

@RestController
@RequestMapping(value = "/products")
public class ProductsController {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    @GetMapping
    public List<ProductsModel> findAll() {
        List<ProductsModel> result = productsRepository.findAll();
        return result;
    }

    @GetMapping(value = "/{id}")
    public ProductsModel findById(@PathVariable int id) {
        ProductsModel result = productsRepository.findById(id).get();
        return result;
    }

    @PostMapping
    public ResponseEntity<ProductsModel> insertProduct(@RequestBody ProductsDTO product) {
        ProductsModel productsModel = new ProductsModel();
        BeanUtils.copyProperties(product, productsModel);
        try {
            ProductsModel result = productsRepository.save(productsModel);
            return new ResponseEntity<>(productsModel,HttpStatus.CREATED);
        } catch (Throwable e) {
            return new ResponseEntity<>(productsModel,HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductsModel> updateProduct(@PathVariable int id, @RequestBody ProductsDTO product) {
        return productsRepository.findById(id)
                .map(prd -> {
                    BeanUtils.copyProperties(product, prd);
                    ProductsModel prodSaved = productsRepository.save(prd);
                    return ResponseEntity.ok().body(prodSaved);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) {
        return productsRepository.findById(id)
                .map(prd -> {
                    productsRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/categories")
    public List<CategoriesModel> getProductsCategories() {
        List<CategoriesModel> result = categoriesRepository.findAll();
        return result;
    }

    @GetMapping(value = "/category/{name}")
    public ResponseEntity<List<ProductsModel>> getProductsByCategory(@PathVariable String name) {
        CategoriesModel category = new CategoriesModel();
        try {
            category = categoriesRepository.findByName(name);
            List<ProductsModel> result = productsRepository.findByCategoryId(category.getId());
            return new ResponseEntity<>(result,HttpStatus.CREATED);
        } catch (Throwable e) {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }
}
