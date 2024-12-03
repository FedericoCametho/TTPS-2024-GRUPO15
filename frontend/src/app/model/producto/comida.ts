import { ProductoComercializable } from './productoComercializable';

export class Comida extends ProductoComercializable{
    public inMenu: boolean | undefined;

    constructor (id: number,
        nombre: string,
        precio: number,
        inMenu: boolean,
        foto?: Uint8Array
        ){
        super(id, nombre, precio, foto)
        this.inMenu = inMenu;
    }
}
    