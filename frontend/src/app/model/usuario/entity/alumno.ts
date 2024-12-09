import { Usuario } from "./usuario";

export abstract class Alumno extends Usuario{
    foto?: string; 
    habilitado!: boolean; 
}