package com.TTPS2024.buffet.service.carta.producto;

import com.TTPS2024.buffet.controller.request.carta.producto.ProductoComercializableRequest;
import com.TTPS2024.buffet.dao.carta.producto.ProductoComercializableDAO;
import com.TTPS2024.buffet.helper.RequestValidatorHelper;
import com.TTPS2024.buffet.model.carta.producto.ProductoComercializable;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.logging.Logger;

public abstract class ProductoComercializableService<T extends ProductoComercializable, S extends ProductoComercializableDAO<T> & JpaRepository<T, Long>,  R extends ProductoComercializableRequest> {
    private static final Logger LOGGER = Logger.getLogger(ProductoComercializableService.class.getName());

    protected S dao;
    public ProductoComercializableService(S dao) {
        this.dao = dao;
    }

    @Transactional
    public T save(R request) {
        this.sanitizeRequest(request);
        try{
            T originalProduct = this.createProductoComercializable(request);
            T result =  this.dao.saveAndFlush(originalProduct);
            this.updateComidasEnMenuRelation(originalProduct, result);
            return result;
        } catch (Exception e){
            LOGGER.info("Error al guardar el producto: " + e.getMessage());
            throw new IllegalArgumentException("El producto ya existe");
        }
    }

    @Transactional
    public T update(Long id, R request){
        RequestValidatorHelper.validateID(id);
        this.sanitizeRequest(request);
        T originalProduct;
        try{
            originalProduct = this.dao.getById(id);
        } catch (Exception e){
            LOGGER.info("El producto no existe con el id: " + id);
            throw new IllegalArgumentException("El producto no existe");
        }
        originalProduct.setNombre(request.getNombre());
        originalProduct.setPrecio(request.getPrecio());
        originalProduct.setFoto(request.getImagen());
        this.setUpdateSpecificFields(originalProduct, request);
        T result = this.dao.saveAndFlush(originalProduct);
        this.updateSpecificRelations(originalProduct, result, request);
        return result;
    }
    @Transactional
    public void delete(Long id) {
        RequestValidatorHelper.validateID(id);
        try{
            this.dao.deleteById(id);
        } catch (NoResultException e){
            LOGGER.info("El producto no existe con el id: " + id);
            throw new IllegalArgumentException("El producto no existe");
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
            throw new IllegalArgumentException("El producto comercializable no existe");
        }
    }

    public T getProductById(Long id) {
        RequestValidatorHelper.validateID(id);
        try{
            return dao.findById(id).orElse(null);
        } catch (NoResultException e){
            LOGGER.info("El producto no existe con el id: " + id);
            throw new IllegalArgumentException("El producto no existe");
        }
    }
    public List<T> getAll() {
        return dao.findAll();
    }
    public List<T> getProductsByName(String name) {
        RequestValidatorHelper.validateStringInputParameter(name, "El nombre del producto no puede ser nulo o vacío");
        try{
            return dao.findByNombre(name);
        } catch (NoResultException e){
            LOGGER.info("El producto no existe con el nombre: " + name);
            throw new IllegalArgumentException("El producto no existe");
        }
    }
    public List<T> getProductsByPrice(Double price) {
        RequestValidatorHelper.validateDoubleInputParameter(price, "El precio del producto no puede ser nulo o negativo");
        try{
            return dao.findByPrecio(price);
        } catch (NoResultException e){
            LOGGER.info("El producto no existe con el precio: " + price);
            throw new IllegalArgumentException("El producto no existe");
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

}
