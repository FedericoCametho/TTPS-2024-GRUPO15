import { ProductoComercializable } from './productoComercializable';
import { Comida } from './comida';

export class Menu extends ProductoComercializable {
  comidas: Array<Comida>;
  veggie: boolean;

  constructor(
    id: number,
    nombre: string,
    precio: number,
    veggie: boolean,
    comidas: Array<Comida>,
    foto?: string
  ) {
    super(id, nombre, precio, foto);
    this.comidas = comidas;
    this.veggie =  veggie;
  }
}