package com.ecommerce.api.controller;

import com.ecommerce.api.model.dto.ProductDTO;
import com.ecommerce.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/buscar")
    public ResponseEntity buscar(@RequestParam(name = "id", required = false)Integer id) {
        if(Objects.isNull(id)) {
          return ResponseEntity.ok(service.findAll());
        }else{
            return ResponseEntity.ok(service.findById(id));
        }
    }


    @PostMapping("/crear")
    public String crear(@RequestBody ProductDTO productDTO) {
        service.save(productDTO);
        return "Producto creado correctamente";
    }

    @PutMapping("/actualizar")
    public String actualizar(@RequestBody ProductDTO productDTO) {
        service.update(productDTO);
        return "Producto actualizado correctamente";
    }

    @DeleteMapping("/eliminar")
    public String eliminar(@RequestParam(name = "id")Integer id) {
        service.delete(id);
        return "Producto eliminado correctamente";
    }

}
