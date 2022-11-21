package br.com.logallies.desafio1.controllers;

import br.com.logallies.desafio1.dtos.CategoriesDTO;
import br.com.logallies.desafio1.models.CategoriesModel;
import br.com.logallies.desafio1.repositories.CategoriesRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoriesController {
    @Autowired
    private CategoriesRepository categoriesRepository;

    @GetMapping
    public List<CategoriesModel> findAll() {
        List<CategoriesModel> result = categoriesRepository.findAll();
        return result;
    }

    @GetMapping("/{id}")
    public CategoriesModel findById(@PathVariable int id) {
        CategoriesModel result = categoriesRepository.findById(id).get();
        return result;
    }

    @PostMapping
    public ResponseEntity<CategoriesModel> insertCategory(@RequestBody CategoriesDTO category) {
        CategoriesModel categ = new CategoriesModel();
        BeanUtils.copyProperties(categ, category);
        try {
            CategoriesModel result = categoriesRepository.save(categ);
            return new ResponseEntity<>(categ, HttpStatus.CREATED);
        } catch (Throwable e) {
            return new ResponseEntity<>(categ,HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoriesModel> updateCategory(@PathVariable int id, @RequestBody CategoriesDTO category) {
        return categoriesRepository.findById(id)
                .map(catg -> {
                    BeanUtils.copyProperties(catg, category);
                    CategoriesModel categorySaved = categoriesRepository.save(catg);
                    return ResponseEntity.ok().body(categorySaved);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable int id) {
        return categoriesRepository.findById(id)
                .map(catg -> {
                    categoriesRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
