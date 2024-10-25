package com.TTPS2024.buffet.service.carta.producto;

import com.TTPS2024.buffet.controller.request.carta.producto.MenuRequest;
import com.TTPS2024.buffet.dao.carta.producto.MenuDAO;
import com.TTPS2024.buffet.model.carta.producto.Comida;
import com.TTPS2024.buffet.model.carta.producto.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        // no aplicable a este caso, solo para comidas
    }

    @Override
    public void updateSpecificRelations(Menu originalMenu, Menu updatedMenu, MenuRequest menuRequest){
        List<Long> idComidasMenu = originalMenu.getComidas().stream().mapToLong(Comida::getId).boxed().toList();
        List<Comida> comidasToUpdate = menuRequest.getComidas().stream().filter(comida -> !idComidasMenu.contains(comida.getId())).toList();
        if(!comidasToUpdate.isEmpty()){
            this.updateComidasEnMenuRelation(originalMenu, updatedMenu);
        }
    }

    @Override
    protected Menu createProductoComercializable(MenuRequest request) {
        return new Menu(request.getNombre(), request.getPrecio(), request.getComidas(), request.getImagen(), request.isVeggie());
    }

}

