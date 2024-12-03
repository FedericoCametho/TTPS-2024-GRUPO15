import { RequestUsuarioGeneral } from './requestUsuarioGeneral';

export abstract class UsuarioRequest extends RequestUsuarioGeneral {
  contrasena!: string; 
}
