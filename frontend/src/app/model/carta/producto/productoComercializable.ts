export abstract class ProductoComercializable {
    id: number | undefined;
    nombre: string | undefined;
    precio: number | undefined;
    foto: Uint8Array | undefined;
  
    constructor(id: number, nombre: string, precio: number, foto?: Uint8Array) {
      this.id = id;
      this.nombre = nombre;
      this.precio = precio;
      this.foto = foto;
    }
  }