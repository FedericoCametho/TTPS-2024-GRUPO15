package com.TTPS2024.buffet.service.carta;

import com.TTPS2024.buffet.controller.request.carta.CartaSemanalRequest;
import com.TTPS2024.buffet.dao.carta.CartaSemanalDAO;
import com.TTPS2024.buffet.helper.RequestValidatorHelper;
import com.TTPS2024.buffet.model.carta.CartaDelDia;
import com.TTPS2024.buffet.model.carta.CartaSemanal;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
@Service
public class CartaSemanalService {
    private static final Logger LOGGER = Logger.getLogger(CartaSemanalService.class.getName());
    private CartaSemanalDAO cartaSemanalDAO;
    private CartaDelDiaService cartaDelDiaService;
    @Autowired
    public CartaSemanalService(CartaSemanalDAO cartaSemanalDAO, CartaDelDiaService cartaDelDiaService) {
        this.cartaSemanalDAO = cartaSemanalDAO;
        this.cartaDelDiaService = cartaDelDiaService;
    }

    public CartaSemanal save(CartaSemanalRequest cartaSemanalRequest) {
        this.sanitize(cartaSemanalRequest);
        try {
            CartaSemanal cartaSemanal = this.createCartaSemanal(cartaSemanalRequest);
            CartaSemanal cartaSemanalResult = this.cartaSemanalDAO.saveAndFlush(cartaSemanal);
            this.updateCartasDelDiaRelation( cartaSemanalResult.getCartas(), cartaSemanalResult);
            return cartaSemanalResult;
        } catch (Exception e) {
            LOGGER.info("Error al guardar la carta semanal");
            throw new IllegalArgumentException("Error al guardar la carta semanal");
        }

    }

    public CartaSemanal getById(Long id) {
        RequestValidatorHelper.validateID(id);
        try{
            return this.cartaSemanalDAO.findById(id).orElse(null);
        } catch (Exception e) {
            LOGGER.info("No se encontro carta semanal con id: " + id);
            throw new IllegalArgumentException("No se encontro carta semanal con id: " + id);
        }
    }
    public CartaSemanal getByNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()){
            throw new IllegalArgumentException("El nombre de la carta no puede ser nulo o vacio");
        }
        try{
            return this.cartaSemanalDAO.findByNombre(nombre);
        } catch (Exception e) {
            LOGGER.info("No se encontro carta semanal con nombre: " + nombre);
            throw new IllegalArgumentException("No se encontro carta semanal con nombre: " + nombre);
        }
    }

    public void sanitize(CartaSemanalRequest cartaSemanalRequest) {
        if (cartaSemanalRequest.getNombre() == null || cartaSemanalRequest.getNombre().isEmpty()){
            throw new IllegalArgumentException("El nombre de la carta no puede ser nulo o vacio");
        }
        if (cartaSemanalRequest.getCartasDelDia() == null || cartaSemanalRequest.getCartasDelDia().isEmpty()) {
            throw new IllegalArgumentException("Cartas del dia son requeridas");
        }

        if (cartaSemanalRequest.getCartasDelDia().size() != 5) {
            throw new IllegalArgumentException("Se deben cargar las 5 cartas del dia");
        }

        checkUnaPorDia(this.cartaDelDiaService.getCartasDelDiaByIds(cartaSemanalRequest.getCartasDelDia()));

    }

    private void checkUnaPorDia(List<CartaDelDia> cartasDelDia) {
        cartasDelDia.stream().map(CartaDelDia::getDiaSemana).forEach(diaSemana -> {
            if (cartasDelDia.stream().filter(cartaDelDia -> cartaDelDia.getDiaSemana().equals(diaSemana)).count() > 1) {
                LOGGER.info("ERROR: No se puede repetir carta del dia para el mismo dia");
                throw new IllegalArgumentException("No se puede repetir carta del dia para el mismo dia");
            }
        });
    }


    private CartaSemanal createCartaSemanal(CartaSemanalRequest cartaSemanalRequest) {
        return new CartaSemanal(this.cartaDelDiaService.getCartasDelDiaByIds(cartaSemanalRequest.getCartasDelDia()), cartaSemanalRequest.getNombre());
    }

    public List<CartaSemanal> getAll() {
        return this.cartaSemanalDAO.findAll();
    }

    @Transactional
    public void delete(Long id){
        RequestValidatorHelper.validateID(id);
        try{
            this.cartaSemanalDAO.deleteById(id);
        } catch (Exception e){
            LOGGER.info("Error al eliminar carta semanal");
            throw new IllegalArgumentException("No se encontro carta semanal con id: " + id);
        }
    }

    @Transactional
    public CartaSemanal update(Long id, CartaSemanalRequest cartaSemanalRequest){
        RequestValidatorHelper.validateID(id);
        this.sanitize(cartaSemanalRequest);
        CartaSemanal cartaSemanalOriginal;
        try {
            cartaSemanalOriginal = this.getById(id);
        }catch (Exception e){
            LOGGER.info("La carta semanal no existe");
            throw new IllegalArgumentException("La carta semanal no existe");
        }
        try{
            cartaSemanalOriginal.setNombre(cartaSemanalRequest.getNombre());
            CartaSemanal updateCartaSemanal = this.cartaSemanalDAO.saveAndFlush(cartaSemanalOriginal);
            this.updateSpecificRelations(cartaSemanalOriginal, updateCartaSemanal, cartaSemanalRequest);
            return this.cartaSemanalDAO.saveAndFlush(updateCartaSemanal);
        } catch (Exception e){
            LOGGER.info("Error al actualizar carta semanal");
            throw new IllegalArgumentException("No se encontro carta semanal con id: " + id);
        }

    }

    private void updateCartasDelDiaRelation(List<CartaDelDia> cartasDelDiaIdsToUpdate, CartaSemanal updatedCartaSemanal) {
        cartasDelDiaIdsToUpdate.forEach(cartaDelDia -> {
            this.cartaDelDiaService.updateCartaSemanalRelation(updatedCartaSemanal, cartaDelDia.getId());
        });
    }

    private void updateSpecificRelations(CartaSemanal originalCartaSemanal, CartaSemanal updatedCartaSemanal, CartaSemanalRequest cartaSemanalRequest) {
        List<Long> idCartasDelDiaOriginal = originalCartaSemanal.getCartas().stream().mapToLong(CartaDelDia::getId).boxed().toList();
        List<Long> idCartasDelDiaRequest = cartaSemanalRequest.getCartasDelDia();

        List<CartaDelDia> cartasDelDiaToUpdate = cartaSemanalRequest.getCartasDelDia().stream()
        .filter(cartaDelDiaRequest -> !idCartasDelDiaOriginal.contains(cartaDelDiaRequest))
        .map(cartaDelDiaService::getById)
        .collect(Collectors.toList());

//        List<CartaDelDia> cartasDelDiaToUpdate = cartaSemanalRequest.getCartasDelDia().stream().filter(cartaDelDiaRequest -> !idCartasDelDiaOriginal.contains(cartaDelDiaRequest.getId())).collect(Collectors.toList());

        if(!cartasDelDiaToUpdate.isEmpty()){
            this.updateCartasDelDiaRelation(cartasDelDiaToUpdate, updatedCartaSemanal);
            this.cartaDelDiaService.eliminarRelacionConCartaSemanal(idCartasDelDiaOriginal.stream().filter(id -> !idCartasDelDiaRequest.contains(id)).collect(Collectors.toSet()));
        }
    }



}
