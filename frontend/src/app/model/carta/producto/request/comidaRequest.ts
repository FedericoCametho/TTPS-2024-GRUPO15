import { ProductoComercializableRequest } from './productoComercializableRequest';
import { TipoComida } from '../tipo-comida.enum';

export class ComidaRequest extends ProductoComercializableRequest {
    public inMenu: boolean | undefined;
    tipoComida: TipoComida | undefined;


}
