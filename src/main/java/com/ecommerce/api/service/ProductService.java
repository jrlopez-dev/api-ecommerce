package com.ecommerce.api.service;

import com.ecommerce.api.common.exception.ServiceException;
import com.ecommerce.api.model.dto.ProductDTO;
import com.ecommerce.api.model.entity.Product;
import com.ecommerce.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {
    @Autowired
    ProductRepository repository;

    /**
     * Metodo que retorna toda la lista de productos creados.
     * @return retorna una lista de tipo ProductDTO
     */
    public List<ProductDTO> findAll() {
        List<Product> products = repository.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();
        if (!products.isEmpty()) {
            for (Product product : products) {
                ProductDTO productDTO = new ProductDTO();
                productDTO.setDescription(product.getDescription());
                productDTO.setUpdatedAt(product.getUpdatedAt());
                productDTO.setCreatedAt(product.getCreatedAt());
                productDTO.setProductId(product.getProductId());
                productDTO.setName(product.getName());
                productDTO.setPrice(product.getPrice());
                productDTO.setId(product.getId());
                productDTOS.add(productDTO);
            }
        }else{
            throw new ServiceException("No se encontraron registros de productos");
        }
        return productDTOS;
    }

    /**
     * Metodo que busca un producto en espesifico
     * @param id parametro que sirve para filtrar la busqueda
     * @return retorna un objeto con la data de un producto,
     */
    public ProductDTO findById(Integer id) {
        System.out.println("Codigo limpio");
        ProductDTO productDTO = new ProductDTO();
        Product product = repository.findById(id).orElse(null);
        if(Objects.nonNull(product)){
            productDTO.setProductId(product.getProductId());
            productDTO.setName(product.getName());
            productDTO.setPrice(product.getPrice());
            productDTO.setDescription(product.getDescription());
            productDTO.setStock(product.getStock());
            productDTO.setUpdatedAt(product.getUpdatedAt());
            productDTO.setCreatedAt(product.getCreatedAt());
            productDTO.setId(product.getId());
        }else{
            throw new ServiceException("No se encontro producto con el id " + id);
        }
        return productDTO;
    }

    /**
     * Metodo que realiza la funcion de poder persistir un producto
     * @param productDTO objeto que trae toda la informacion requerida para la persistencia
     * @return no retorna un mensaje de exito.
     */
    public String save(ProductDTO productDTO) {
        validarDatos(productDTO);
        long var = repository.countAllByProductId(productDTO.getProductId());
        if(var > 0){
            throw new ServiceException("El producto ya existe con id " + productDTO.getProductId());
        }
        Product product = new Product();
        product.setProductId(productDTO.getProductId());
        product.setId(getId());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setStock(productDTO.getStock());
        product.setUpdatedAt(LocalDateTime.now());
        product.setCreatedAt(LocalDateTime.now());
        repository.save(product);
        return "Registro creado con exito";
    }

    /**
     * Metodo que hace la funcion de actualizar un producto
     * @param productDTO Objeto que contiene la informacion a actualizar
     * @return retorna el mismo objeto que se actualizo
     */
    public ProductDTO update(ProductDTO productDTO) {
        Product product = repository.findById(productDTO.getId()).orElse(null);
        if(product != null){
            product.setName(productDTO.getName());
            product.setPrice(productDTO.getPrice());
            product.setDescription(productDTO.getDescription());
            product.setStock(productDTO.getStock());
            product.setUpdatedAt(productDTO.getUpdatedAt());
            repository.save(product);
            return productDTO;
        }else{
            throw new ServiceException("No se actualizo registro. No se encontro producto con id " + productDTO.getId());
        }
    }

    /**
     * Metodo que realiza la funcion de eliminar un producto
     * @param id parametro al que se desea eliminar
     */
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    /**
     * Metodo que crea de forma automatica un id auto incrementable para la insercion de registros
     * @return retorna un numero entero.
     */
    public int getId() {
        List<Product> products = repository.findAll();
        if(products.size() > 0){
            return products.stream().max(Comparator.comparing(Product::getId)).get().getId() +1;
        }else{
            return 1;
        }
    }

    public void validarDatos(ProductDTO productDTO) {
        if(productDTO.getProductId().isBlank()){
            throw new ServiceException("El id del producto no puede ser nulo");
        }else if(productDTO.getName().isBlank()){
            throw new ServiceException("El nombre del producto no puede ser nulo");
        }else if(productDTO.getDescription().isBlank()){
            throw new ServiceException("El descripcion del producto no puede ser nulo");
        }else if (Objects.isNull(productDTO.getPrice()) && productDTO.getPrice() == 0){
            throw new ServiceException("El price del producto no puede ser nulo");
        }
    }

}
