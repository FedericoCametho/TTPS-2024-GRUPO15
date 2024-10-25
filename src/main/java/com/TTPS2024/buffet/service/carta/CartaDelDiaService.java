package com.TTPS2024.buffet.service.carta;

import com.TTPS2024.buffet.controller.request.carta.CartaDelDiaRequest;
import com.TTPS2024.buffet.dao.carta.CartaDelDiaDAO;
import com.TTPS2024.buffet.helper.RequestValidatorHelper;
import com.TTPS2024.buffet.model.carta.CartaDelDia;
import com.TTPS2024.buffet.model.carta.CartaSemanal;
import com.TTPS2024.buffet.model.carta.DiaSemana;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
@Service
public class CartaDelDiaService {
    private static final Logger LOGGER = Logger.getLogger(CartaDelDiaService.class.getName());
    private CartaDelDiaDAO cartaDelDiaDAO;
    @Autowired
    public CartaDelDiaService(CartaDelDiaDAO cartaDelDiaDAO) {
        this.cartaDelDiaDAO = cartaDelDiaDAO;
    }

    @Transactional
    public CartaDelDia save(CartaDelDiaRequest cartaDelDiaRequest) {
        this.sanitize(cartaDelDiaRequest);
        CartaDelDia cartaDelDia = this.createCartaDelDia(cartaDelDiaRequest);
        try {
            return this.cartaDelDiaDAO.saveAndFlush(cartaDelDia);
        } catch (Exception e) {
            LOGGER.info("Error al guardar la carta del dia");
            throw new IllegalArgumentException("Error al guardar la carta del dia");
        }

    }

    public CartaDelDia getById(Long id) {
        RequestValidatorHelper.validateID(id);
        try {
            return this.cartaDelDiaDAO.findById(id).orElse(null);
        } catch (Exception e) {
            LOGGER.info("No se encontro carta del dia con id: " + id);
            throw new IllegalArgumentException("No se encontro carta del dia con id: " + id);
        }
    }

    private void sanitize(CartaDelDiaRequest cartaDelDiaRequest) {
        if (cartaDelDiaRequest.getMenuComun() == null) {
            throw new IllegalArgumentException("Menu comun es requerido");
        }
        if (cartaDelDiaRequest.getMenuVegetariano() == null) {
            throw new IllegalArgumentException("Menu vegetariano es requerido");
        }
        if (cartaDelDiaRequest.getDiaSemana() == null) {
            throw new IllegalArgumentException("Dia de la semana es requerido");
        }
    }

    public CartaDelDia createCartaDelDia(CartaDelDiaRequest cartaDelDiaRequest) {
        CartaDelDia cartaDelDia = new CartaDelDia();
        cartaDelDia.setDiaSemana(cartaDelDiaRequest.getDiaSemana());
        cartaDelDia.agregarMenuComun(cartaDelDiaRequest.getMenuComun());
        cartaDelDia.agregarMenuVeggie(cartaDelDiaRequest.getMenuVegetariano());
        return cartaDelDia;
    }

    @Transactional
    public void delete(Long id) {
        RequestValidatorHelper.validateID(id);
        try{
            this.cartaDelDiaDAO.deleteById(id);
        } catch (NoResultException e) {
            LOGGER.info("La carta del dia no existe");
            throw new IllegalArgumentException("La carta del dia no existe");
        }
    }
    @Transactional
    public void delete(CartaDelDia cartaDelDia) {
        if (cartaDelDia == null) {
            throw new IllegalArgumentException("La carta del dia a eliminar no puede ser nula");
        }
        try{
            this.cartaDelDiaDAO.delete(cartaDelDia);
        } catch (NoResultException e) {
            LOGGER.info("La carta del dia no existe");
            throw new IllegalArgumentException("La carta del dia no existe");
        }
    }

    @Transactional
    public void update(Long id, CartaDelDiaRequest cartaDelDiaRequest) {
        RequestValidatorHelper.validateID(id);
        this.sanitize(cartaDelDiaRequest);
        CartaDelDia cartaDelDia;
        try{
            cartaDelDia = this.getById(id);
        } catch (Exception e){
            LOGGER.info("La carta del dia no existe");
            throw new IllegalArgumentException("La carta del dia no existe");
        }
        cartaDelDia.setDiaSemana(cartaDelDiaRequest.getDiaSemana());
        cartaDelDia.agregarMenuComun(cartaDelDiaRequest.getMenuComun());
        cartaDelDia.agregarMenuVeggie(cartaDelDiaRequest.getMenuVegetariano());
        cartaDelDia.setActiva(cartaDelDiaRequest.isActiva());
        try{
            this.cartaDelDiaDAO.saveAndFlush(cartaDelDia);
        } catch (Exception e){
            LOGGER.info("Error al actualizar la carta del dia");
            throw new IllegalArgumentException("Error al actualizar la carta del dia");
        }
    }

    public CartaDelDia update(CartaDelDia cartaDelDia) {
        return cartaDelDiaDAO.saveAndFlush(cartaDelDia);
    }

    public List<CartaDelDia> getAll() {
        return cartaDelDiaDAO.findAll();
    }

    public List<CartaDelDia> getCartaDelDiaByDiaSemana(DiaSemana diaSemana) {
        return cartaDelDiaDAO.getCartaDelDiaByDiaSemana(diaSemana);
    }

    @Transactional
    public CartaDelDia updateCartaSemanalRelation(CartaSemanal cartaSemanal, Long cartaDelDiaId){
        CartaDelDia cartaDelDia;
        try{
            cartaDelDia = this.getById(cartaDelDiaId);
        } catch (Exception e){
            LOGGER.info("La carta del dia no existe");
            throw new IllegalArgumentException("La carta del dia no existe");
        }
        try {
            cartaDelDia.setCartaSemanal(cartaSemanal);
            return this.cartaDelDiaDAO.saveAndFlush(cartaDelDia);
        } catch (Exception e){
            LOGGER.info("Error al actualizar la carta del dia");
            throw new IllegalArgumentException("Error al actualizar la carta del dia");
        }
    }

    @Transactional
    public void eliminarRelacionConCartaSemanal(Set<Long> idsCartasDelDia){
        try{
            idsCartasDelDia.forEach(id -> {
                CartaDelDia cartaDelDia = this.getById(id);
                cartaDelDia.setCartaSemanal(null);
                this.cartaDelDiaDAO.saveAndFlush(cartaDelDia);
            });
        }catch(Exception e){
            throw new IllegalArgumentException("Error al eliminar la relacion con la carta semanal");
        }
    }

}
