package com.TTPS2024.buffet.service.carta.producto;

import com.TTPS2024.buffet.controller.dto.ProductoComercializableDTO;
import com.TTPS2024.buffet.controller.request.carta.producto.ProductoComercializableRequest;
import com.TTPS2024.buffet.dao.carta.producto.ProductoComercializableDAO;
import com.TTPS2024.buffet.helper.RequestValidatorHelper;
import com.TTPS2024.buffet.helper.transformer.ProductoComercializableTransformer;
import com.TTPS2024.buffet.model.carta.producto.ProductoComercializable;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public abstract class ProductoComercializableService<T extends ProductoComercializable, S extends ProductoComercializableDAO<T> & JpaRepository<T, Long>,
        R extends ProductoComercializableRequest, Q extends ProductoComercializableDTO, X extends ProductoComercializableTransformer<Q, T>> {
    private static final Logger LOGGER = Logger.getLogger(ProductoComercializableService.class.getName());

    protected S dao;
    protected X transformer;
    public ProductoComercializableService(S dao, X transformer) {
        this.dao = dao;
        this.transformer = transformer;
    }

    @Transactional
    public Q save(R request) {
        this.sanitizeRequest(request);
        try{
            T originalProduct = this.createProductoComercializable(request);
            T result =  this.dao.saveAndFlush(originalProduct);
            this.updateComidasEnMenuRelation(originalProduct, result);
            return this.transformer.toDTO(result);
        } catch (Exception e){
            LOGGER.info("Error al guardar el producto: " + e.getMessage());
            throw new IllegalArgumentException("El producto ya existe");
        }
    }

    @Transactional
    public Q update(Long id, R request){
        RequestValidatorHelper.validateID(id);
        this.sanitizeRequest(request);
        T originalProduct;
        try{
            originalProduct = this.dao.getById(id);
        } catch (NoResultException e){
            LOGGER.info("El producto no existe con el id: " + id);
            throw new NoResultException("El producto con el id "+ id + " no existe");
        }
        originalProduct.setNombre(request.getNombre());
        originalProduct.setPrecio(request.getPrecio());
        originalProduct.setFoto(request.getImagen());
        this.setUpdateSpecificFields(originalProduct, request);
        T result = this.dao.saveAndFlush(originalProduct);
        this.updateSpecificRelations(originalProduct, result, request);
        return this.transformer.toDTO(result);
    }
    @Transactional
    public void delete(Long id) {
        RequestValidatorHelper.validateID(id);
        try{
            this.dao.deleteById(id);
        } catch (NoResultException e){
            LOGGER.info("El producto no existe con el id: " + id);
            throw new NoResultException("El producto con el id "+ id + " no existe");
        }
    }
    @Transactional
    public void delete(T product) {
        if(product == null){
            throw new IllegalArgumentException("El producto comercializable no puede ser nulo");
        }
        try{
            this.dao.delete(product);
        } catch (NoResultException e){
            LOGGER.info("El producto no existe");
            throw new NoResultException("El producto comercializable no existe");
        }
    }
    protected T getProductByIdInternal(Long id) {
        RequestValidatorHelper.validateID(id);
        try{
            return dao.findById(id).get();
        } catch (NoSuchElementException e){
            LOGGER.info("El producto no existe con el id: " + id);
            throw new NoResultException("El producto con el id "+ id + " no existe");
        }
    }
    public Q getProductById(Long id) {
        RequestValidatorHelper.validateID(id);
        try{
            T result = dao.findById(id).get();
            return this.transformer.toDTO(result);
        } catch (NoSuchElementException e){
            LOGGER.info("El producto no existe con el id: " + id);
            throw new NoResultException("El producto con el id "+ id + " no existe");
        }
    }
    public List<Q> getAll() {
        List<T> result =  dao.findAll();
        return (result.isEmpty()) ? List.of() : this.transformToDTOmodel(result);
    }
    public List<Q> getProductsByName(String name) {
        RequestValidatorHelper.validateStringInputParameter(name, "El nombre del producto no puede ser nulo o vacío");
        try{
            List<T> result = dao.findByNombreContaining(name);
            return (result.isEmpty()) ? List.of() : this.transformToDTOmodel(result);
        } catch (NoResultException e){
            LOGGER.info("El producto no existe con el nombre: " + name);
            throw new IllegalArgumentException("El producto con el nombre "+ name + " no existe");
        }
    }
    public List<Q> getProductsByPrice(Double price) {
        RequestValidatorHelper.validateDoubleInputParameter(price, "El precio del producto no puede ser nulo o negativo");
        try{
            List<T> result = dao.findByPrecio(price);
            return (result.isEmpty()) ? List.of() : this.transformToDTOmodel(result);
        } catch (NoResultException e){
            LOGGER.info("El producto no existe con el precio: " + price);
            throw new NoResultException("El producto con el precio "+ price + " no existe");
        }
    }

    private void sanitizeRequest(R request) {
        if (request.getNombre() == null || request.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede ser nulo o vacío");
        }
        if (request.getPrecio() == null || request.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio del producto no puede ser nulo o negativo");
        }
        this.sanitizeRequestSpecificFields(request);
    }

    protected abstract void sanitizeRequestSpecificFields(R requestequest);
    protected abstract void updateSpecificRelations(T originalProduc, T updatedProduct, R request) ;
    protected abstract T createProductoComercializable(R request);

    protected abstract void updateComidasEnMenuRelation(T originalProduct, T result);

    protected abstract void setUpdateSpecificFields(T product, R request) ;

    protected List<Q> transformToDTOmodel(List<T> products){
        return products.stream().map(prod -> this.transformer.toDTO(prod)).collect(Collectors.toList());
    }
    protected Q transformToDTOmodel(T product){
        return this.transformer.toDTO(product);
    }

}
