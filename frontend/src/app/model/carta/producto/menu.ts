import { ProductoComercializable } from './productoComercializable';
import { Comida } from './comida';

export class Menu extends ProductoComercializable {
  comidas: Array<Comida> | undefined;
  
  constructor(
    id: number,
    nombre: string,
    precio: number,
    foto?: Uint8Array,
    comidas?: Array<Comida>
  ) {
    super(id, nombre, precio, foto);
    this.comidas = comidas;
  }
}