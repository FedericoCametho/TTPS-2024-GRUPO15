export abstract class ProductoComercializable {
    id: number;
    nombre: string | undefined;
    precio: number | undefined;
    foto: string | undefined;
  
    constructor(id: number, nombre: string, precio: number, foto?: string) {
      this.id = id;
      this.nombre = nombre;
      this.precio = precio;
      this.foto = foto;
    }
  }