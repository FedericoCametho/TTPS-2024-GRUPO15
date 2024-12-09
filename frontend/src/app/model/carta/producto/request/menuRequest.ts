import { ProductoComercializableRequest } from './productoComercializableRequest';
import { Comida } from '../comida';

export class MenuRequest extends ProductoComercializableRequest {
  comidas: Array<Comida> | undefined;
  veggie: boolean | undefined;
 
}