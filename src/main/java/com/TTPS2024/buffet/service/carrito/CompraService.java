package com.TTPS2024.buffet.service.carrito;

import com.TTPS2024.buffet.dao.carrito.CompraDAO;
import com.TTPS2024.buffet.helper.RequestValidatorHelper;
import com.TTPS2024.buffet.model.carrito.Carrito;
import com.TTPS2024.buffet.model.carrito.Compra;
import com.TTPS2024.buffet.model.usuario.Alumno;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
@Service
public class CompraService {
    private static final Logger LOGGER = Logger.getLogger(CompraService.class.getName());
    private CompraDAO compraDAO;
    @Autowired
    public CompraService(CompraDAO compraDAO) {
        this.compraDAO = compraDAO;
    }

    @Transactional
    public Compra save(Carrito carrito) {
        Compra compra = new Compra(carrito);
        try {
            return this.compraDAO.save(compra);
        } catch (Exception e) {
            LOGGER.info("Error al guardar la compra");
            throw new IllegalArgumentException("Error al guardar la compra");
        }
    }

    @Transactional
    public void pagarCompra(Long id) {
        RequestValidatorHelper.validateID(id);
        try {
            Compra compra = this.getById(id);
            compra.marcarPagado();
            this.compraDAO.saveAndFlush(compra);
        } catch (Exception e) {
            LOGGER.info("Error al pagar la compra");
            throw new IllegalArgumentException("Error al pagar la compra");
        }
    }

    @Transactional
    public void marcarImpagoCompra(Long id) {
        RequestValidatorHelper.validateID(id);
        try {
            Compra compra = this.getById(id);
            compra.marcarImpago();
            this.compraDAO.saveAndFlush(compra);
        } catch (Exception e) {
            LOGGER.info("Error al marcar impago la compra");
            throw new IllegalArgumentException("Error al marcar impago la compra");
        }
    }

    @Transactional
    public void delete(Long id) {
        RequestValidatorHelper.validateID(id);
        try {
            this.compraDAO.deleteById(id);
        } catch (Exception e) {
            LOGGER.info("Error al eliminar la compra");
            throw new IllegalArgumentException("Error al eliminar la compra");
        }
    }

    public Compra getById(Long id) {
        RequestValidatorHelper.validateID(id);
        try {
            return this.compraDAO.findById(id).orElseThrow(NoResultException::new);
        } catch (NoResultException e) {
            LOGGER.info("No se encontro compra con id: " + id);
            throw new IllegalArgumentException("Error al recuperar la compra por ID");
        }
    }

    public List<Compra> getComprasByAlumno(Alumno alumno) {
        if(alumno == null){
            throw new IllegalArgumentException("El alumno no puede ser nulo");
        }
        try {
            return this.compraDAO.getComprasByAlumno(alumno);
        } catch (Exception e) {
            LOGGER.info("Error al recuperar las compras por alumno");
            throw new IllegalArgumentException("Error al recuperar las compras por alumno");
        }
    }

    public List<Compra> getAll() {
        try {
            return this.compraDAO.findAll();
        } catch (Exception e) {
            LOGGER.info("Error al recuperar todas las compras");
            throw new IllegalArgumentException("Error al recuperar todas las compras");
        }
    }
    public List<Compra> getComprasPrecioMayorQue(Double precio){
        RequestValidatorHelper.validateDoubleInputParameter(precio, "El precio para el filtrado no puede ser nulo ni menor que cero");
        try{
            return this.compraDAO.getByPrecioMayorQue(precio);
        } catch(Exception e){
            LOGGER.info("Error al recuperar las compras por filtrado mayor que");
            throw new IllegalArgumentException("Error al recuperar las compras por filtrado mayor que");
        }
    }
    public List<Compra> getComprasPrecioMenorQue(Double precio){
        RequestValidatorHelper.validateDoubleInputParameter(precio, "El precio para el filtrado no puede ser nulo ni menor que cero");
        try{
            return this.compraDAO.getByPrecioMenorQue(precio);
        } catch(Exception e){
            LOGGER.info("Error al recuperar las compras por filtrado menor que");
            throw new IllegalArgumentException("Error al recuperar las compras por filtrado menor que");
        }
    }



}

