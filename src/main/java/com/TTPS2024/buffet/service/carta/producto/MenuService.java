package com.TTPS2024.buffet.service.carta.producto;

import com.TTPS2024.buffet.controller.request.carta.producto.MenuRequest;
import com.TTPS2024.buffet.dao.carta.producto.MenuDAO;
import com.TTPS2024.buffet.model.carta.producto.Comida;
import com.TTPS2024.buffet.model.carta.producto.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService extends ProductoComercializableService<Menu,MenuDAO, MenuRequest> {
    private ComidaService comidaService;
    @Autowired
    public MenuService(MenuDAO menuDAO, ComidaService comidaService) {
        super(menuDAO);
        this.comidaService = comidaService;
    }

    @Override
    public void sanitizeRequestSpecificFields(MenuRequest menuRequest){
        if(menuRequest.getComidas() == null || menuRequest.getComidas().isEmpty()){
            throw new IllegalArgumentException("Menu debe tener al menos una comida");
        }
    }

    @Override
    public void updateComidasEnMenuRelation(Menu menuOriginal, Menu menuUpdated){
        menuOriginal.getComidas().forEach(
                comida -> {
                    this.comidaService.updateComidaMenuRelation(menuUpdated,comida.getId());
                }
        );
    }

    @Override
    protected void setUpdateSpecificFields(Menu product, MenuRequest request) {
        product.getComidas().forEach(comida -> {
            this.comidaService.updateUnlinkComidaMenuRelation(comida.getId());
        });
    }

    @Override
    public void updateSpecificRelations(Menu updatedMenu, MenuRequest menuRequest){
            menuRequest.getComidas().forEach(comidaId -> {
                this.comidaService.updateComidaMenuRelation(updatedMenu, comidaId);
            });
    }

    @Override
    protected Menu createProductoComercializable(MenuRequest request) {
        return new Menu(request.getNombre(), request.getPrecio(), this.getComidasFromIds(request.getComidas()), request.getImagen(), request.isVeggie());
    }

    private List<Comida> getComidasFromIds(List<Long> comidasIds){
        List<Comida> comidas = new ArrayList<>();
        comidasIds.forEach(id -> {
            comidas.add(this.comidaService.getProductById(id));
        });
        return comidas;
    }

    public List<Menu> getMenuesVeggieByIds(List<Long> ids){
       return this.getProductsFromIds(ids).stream().filter(Menu::isVeggie).collect(Collectors.toList());
    }

    public List<Menu> getMenuesComunByIds(List<Long> ids){
        return this.getProductsFromIds(ids).stream().filter(menu -> !menu.isVeggie()).collect(Collectors.toList());
    }

}

