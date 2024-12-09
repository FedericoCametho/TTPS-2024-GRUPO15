export abstract class Usuario {
    id!: number;
    dni!: number; 
    email!: string; 
    nombre!: string; 
    apellido!: string;
    rol!: string; 
    token?: string;
  }
  