import { ProductoComercializable } from './productoComercializable';
import { TipoComida } from './tipo-comida.enum';

export class Comida extends ProductoComercializable {
    public inMenu: boolean | undefined;
    tipoComida: TipoComida;

    constructor(id: number,
        nombre: string,
        precio: number,
        inMenu: boolean,
        tipoComida: TipoComida,
        foto?: string,
    ) {
        super(id, nombre, precio, foto)
        this.inMenu = inMenu;
        this.tipoComida = tipoComida;
    }
}
